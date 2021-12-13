package com.betheone.pedidos.interfaz;

import java.util.List;

import com.betheone.pedidos.dto.Pedido;
import com.betheone.pedidos.dto.PedidoResponse;

public interface IPedidosService {
	
	public List<Pedido> findAll();
	
	public Pedido findById(Integer pedidoId);
	
	public PedidoResponse save(Pedido pedido);
	
	public PedidoResponse update(Pedido pedido);
	
	public PedidoResponse delete(Integer pedidoId);
	
	public List<Pedido> mostrarTodos();

}
