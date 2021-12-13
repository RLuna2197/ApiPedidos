package com.betheone.pedidos.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betheone.pedidos.dto.LineaPedido;
import com.betheone.pedidos.dto.Pedido;
import com.betheone.pedidos.dto.PedidoResponse;
import com.betheone.pedidos.entity.TblCliente;
import com.betheone.pedidos.entity.TblLineaPedido;
import com.betheone.pedidos.entity.TblPedido;
import com.betheone.pedidos.entity.TblProducto;
import com.betheone.pedidos.interfaz.IPedidoJDBCService;
import com.betheone.pedidos.interfaz.IPedidosService;
import com.betheone.pedidos.repository.IClienteRepository;
import com.betheone.pedidos.repository.ILineaPedidoRepository;
import com.betheone.pedidos.repository.IPedidoRepository;
import com.betheone.pedidos.repository.IProductoRepository;

@Service
public class PedidosService implements IPedidosService {

	@Autowired
	private IPedidoRepository pedidoRepository;

	@Autowired
	private IClienteRepository clienteRepository;

	@Autowired
	private ILineaPedidoRepository lnPedRepository;

	@Autowired
	private IProductoRepository prodRepository;
	
	@Autowired
	private IPedidoJDBCService jdbRepository;

	@Override
	public List<Pedido> findAll() {

		List<TblPedido> lista = pedidoRepository.findAll(); // SELECT * FROM PEDIDOS;
		List<Pedido> listadoPedidos = entityListToDtoList(lista);

		return listadoPedidos;
	}

	@Override
	public Pedido findById(Integer pedidoId) {

		Pedido result = new Pedido();

		Optional<TblPedido> pedido = pedidoRepository.findById(pedidoId);

		if (pedido.isPresent()) {
			result = entityToDto(pedido.get());
		}

		return result;
	}

	@Override
	public PedidoResponse save(Pedido pedido) {

		return guardarPedido(pedido);

	}

	@Override
	public PedidoResponse update(Pedido pedido) {

		PedidoResponse response = new PedidoResponse();
		TblPedido ent = pedidoRepository.findByNumeroFactura(pedido.getNumeroFactura());	
		
		if(ent != null) {
			//TblPedido ent = entity.get();
			ent.setNumeroFactura(pedido.getNumeroFactura());
			
			Optional<TblCliente> cliente = clienteRepository.findById(pedido.getClienteId());
			if(cliente.isPresent()) {
				
				ent.setCliente(cliente.get());
				
				pedidoRepository.save(ent);
				
				response.setCodigoRespuesta(0);
				response.setMensajeRespuesta("El Pedido No." +ent.getPedidoId()+ " se actualizo");
				
			}else {
				response.setCodigoRespuesta(1);
				response.setMensajeRespuesta("El Cliente no existe");
			}
			
		}else {
			response.setCodigoRespuesta(1);
			response.setMensajeRespuesta("El pedido no existe");
		}
	
		return response;
	}

	@Override
	public PedidoResponse delete(Integer pedidoId) {
		
		PedidoResponse response = new PedidoResponse();
		pedidoRepository.deleteById(pedidoId);
		
		response.setCodigoRespuesta(0);
		response.setMensajeRespuesta("Se elimino Correctamente");

		return response;
	}
	
	public List<Pedido> mostrarTodos(){
		List<Pedido> listadoPedidos = new ArrayList<>();
		listadoPedidos = jdbRepository.getAll();
		return listadoPedidos;
	}

	public List<Pedido> crearLista() {

		List<Pedido> listadoPedidos = new ArrayList<>();

		listadoPedidos.add(new Pedido(1, new Date(), 1, 100.00, "FACT-001"));
		listadoPedidos.add(new Pedido(2, new Date(), 1, 200.00, "FACT-002"));
		listadoPedidos.add(new Pedido(3, new Date(), 1, 300.00, "FACT-003"));
		listadoPedidos.add(new Pedido(4, new Date(), 1, 400.00, "FACT-004"));
		listadoPedidos.add(new Pedido(5, new Date(), 1, 500.55, "FACT-005"));

		return listadoPedidos;

	}

	public PedidoResponse guardarPedido(Pedido dto) {

		PedidoResponse response = new PedidoResponse();
		Boolean rollback = false;
		TblPedido pedido = null;
		Double total = 0.0;

		Optional<TblCliente> cliente = clienteRepository.findById(dto.getClienteId());

		if (cliente.isPresent()) {

			pedido = new TblPedido(dto.getFecha(), cliente.get(), dto.getTotalVenta(), dto.getNumeroFactura());
			pedido = pedidoRepository.save(pedido);

			for (LineaPedido linea : dto.getLineaPedido()) {
				TblLineaPedido lnPed = new TblLineaPedido();
				lnPed.setCantidad(linea.getCantidad());
				
				Optional<TblProducto> producto = prodRepository.findById(linea.getProductoId());
				
				if(producto.isPresent()) {
					lnPed.setProducto(producto.get());
					lnPed.setPedido(pedido);
					total += lnPed.getCantidad() * producto.get().getPrecio();
					lnPedRepository.save(lnPed);
				} else {
					response.setCodigoRespuesta(1);
					response.setMensajeRespuesta("El Producto con Id: "+ linea.getProductoId() +" no existe");
					rollback = true;
					break;
				}
			}
			
			pedido.setTotalVenta(total);
			pedido = pedidoRepository.save(pedido);

		}else {
			response.setCodigoRespuesta(1);
			response.setMensajeRespuesta("El Cliente no existe");
			return response;
		}
		
		if(rollback) {
			List<TblLineaPedido> lineas = lnPedRepository.findByPedido(pedido);		
			lnPedRepository.deleteAll(lineas);
			pedidoRepository.delete(pedido);
			return response;
		}

		response.setCodigoRespuesta(0);
		response.setMensajeRespuesta("Operacion exitosa, el numero del pedido es:"+ pedido.getPedidoId());

		return response;

	}

	public Pedido entityToDto(TblPedido ent) {

		Pedido pedido = new Pedido(ent.getPedidoId(), ent.getFecha(), ent.getCliente().getClienteId(),
				ent.getTotalVenta(), ent.getNumeroFactura());
		for (TblLineaPedido linea : ent.getLineaPedido()) {
			LineaPedido lnPed = new LineaPedido();
			lnPed.setCantidad(linea.getCantidad());
			lnPed.setProductoId(linea.getProducto().getProductoId());
			lnPed.setTotalLinea(linea.getCantidad() * linea.getProducto().getPrecio());
			pedido.getLineaPedido().add(lnPed);
		}

		return pedido;
	}

	public List<Pedido> entityListToDtoList(List<TblPedido> listado) {

		List<Pedido> listadoPedidos = new ArrayList<>();

		for (TblPedido ent : listado) {

			Pedido pedido = new Pedido(ent.getPedidoId(), ent.getFecha(), ent.getCliente().getClienteId(),
					ent.getTotalVenta(), ent.getNumeroFactura());

			for (TblLineaPedido linea : ent.getLineaPedido()) {
				LineaPedido lnPed = new LineaPedido();
				lnPed.setCantidad(linea.getCantidad());
				lnPed.setProductoId(linea.getProducto().getProductoId());
				lnPed.setTotalLinea(linea.getCantidad() * linea.getProducto().getPrecio());
				pedido.getLineaPedido().add(lnPed);
			}

			listadoPedidos.add(pedido);
		}

		return listadoPedidos;

	}

}
