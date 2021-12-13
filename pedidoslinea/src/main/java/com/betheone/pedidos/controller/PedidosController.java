package com.betheone.pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.betheone.pedidos.dto.Pedido;
import com.betheone.pedidos.dto.PedidoResponse;
import com.betheone.pedidos.interfaz.IPedidosService;  

@RestController
public class PedidosController {
	
	@Autowired
	private IPedidosService pedidoService;
	
	@GetMapping(value="/Pedidos", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Pedido> getPedidos(){
		List<Pedido> listado = pedidoService.findAll();
		return listado;
	}
	
	@GetMapping(value="/Pedidos/JDBC", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Pedido> getPedidosJDBC(){
		List<Pedido> listado = pedidoService.mostrarTodos();
		return listado;
	}
	
	@GetMapping(value="/Pedidos/{pedidoId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Pedido getPedido(@PathVariable(name="pedidoId") Integer pedidoId){
		Pedido pedido = pedidoService.findById(pedidoId);
		return pedido;
	}
	
	@PostMapping(value="/Pedidos", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PedidoResponse> savePedido(@RequestBody Pedido pedido) {
		
		PedidoResponse response = pedidoService.save(pedido);
		
		if(response.getCodigoRespuesta() == 0)
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);		

	}
	
	@PutMapping(value="/Pedidos", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PedidoResponse> updatePedido(@RequestBody Pedido pedido) {
		
		PedidoResponse response = pedidoService.update(pedido);
		
		if(response.getCodigoRespuesta() == 0)
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);		

		
	}
	
	@DeleteMapping(value="/Pedidos/{pedidoId}")
	public ResponseEntity<PedidoResponse> deletePedido(@PathVariable(name="pedidoId") Integer pedidoId) {
		PedidoResponse response = pedidoService.delete(pedidoId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
