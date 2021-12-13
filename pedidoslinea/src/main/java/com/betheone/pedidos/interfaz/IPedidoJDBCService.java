package com.betheone.pedidos.interfaz;

import java.util.List;

import com.betheone.pedidos.dto.Pedido;

public interface IPedidoJDBCService {
	
	public List<Pedido> getAll();

}
