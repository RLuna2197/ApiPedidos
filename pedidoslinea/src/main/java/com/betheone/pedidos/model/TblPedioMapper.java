package com.betheone.pedidos.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.betheone.pedidos.dto.Pedido;

public class TblPedioMapper implements RowMapper<Pedido> {

	@Override
	public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pedido pedido = new Pedido();
		pedido.setPedidoId(rs.getInt(1));
		pedido.setFecha(rs.getDate(2));
		pedido.setNumeroFactura(rs.getString(3));
		pedido.setTotalVenta(rs.getDouble(4));
		pedido.setClienteId(rs.getInt(5));
		return pedido;
	}

}
