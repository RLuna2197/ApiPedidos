package com.betheone.pedidos.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {

	private Integer pedidoId;
	private Date fecha;
	private Integer clienteId;
	private Double totalVenta;
	private String numeroFactura;
	private List<LineaPedido> lineaPedido;

	public Pedido(Integer pedidoId, Date fecha, Integer clienteId, Double totalVenta, String numeroFactura) {
		super();
		this.pedidoId = pedidoId;
		this.fecha = fecha;
		this.clienteId = clienteId;
		this.totalVenta = totalVenta;
		this.numeroFactura = numeroFactura;
	}
	
	public Pedido() {
		
	}

	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public List<LineaPedido> getLineaPedido() {
		if(lineaPedido == null) {
			lineaPedido = new ArrayList<>();
		}
		return lineaPedido;
	}

	public void setLineaPedido(List<LineaPedido> lineaPedido) {
		this.lineaPedido = lineaPedido;
	}
	
	

}
