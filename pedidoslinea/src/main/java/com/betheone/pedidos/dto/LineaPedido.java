package com.betheone.pedidos.dto;

public class LineaPedido {

	private int cantidad;
	private Integer productoId;
	private Double totalLinea;

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	public Double getTotalLinea() {
		return totalLinea;
	}

	public void setTotalLinea(Double totalLinea) {
		this.totalLinea = totalLinea;
	}

}
