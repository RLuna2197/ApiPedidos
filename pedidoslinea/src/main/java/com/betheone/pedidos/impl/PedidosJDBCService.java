package com.betheone.pedidos.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.betheone.pedidos.dto.Pedido;
import com.betheone.pedidos.interfaz.IPedidoJDBCService;
import com.betheone.pedidos.model.TblPedioMapper;

@Service
public class PedidosJDBCService implements IPedidoJDBCService {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<Pedido> getAll() {
		return jdbc.query("SELECT * FROM PEDIDOS", new TblPedioMapper());
	}

}
