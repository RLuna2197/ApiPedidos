package com.betheone.pedidos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LINEAS_PEDIDO")
public class TblLineaPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer linePedidoId;
	
	@Column(name="cantidad", nullable = false)
	private int cantidad;
	
	@JoinColumn(name = "pedidoId", referencedColumnName = "pedidoId")
	@ManyToOne(optional = false)
	private TblPedido pedido;
	
	@JoinColumn(name = "productoId", referencedColumnName = "productoId")
	@ManyToOne(optional=false)
	private TblProducto producto;


	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getLinePedidoId() {
		return linePedidoId;
	}

	public void setLinePedidoId(Integer linePedidoId) {
		this.linePedidoId = linePedidoId;
	}

	public TblPedido getPedido() {
		return pedido;
	}

	public void setPedido(TblPedido pedido) {
		this.pedido = pedido;
	}

	public TblProducto getProducto() {
		return producto;
	}

	public void setProducto(TblProducto producto) {
		this.producto = producto;
	}
	
	
	

}
