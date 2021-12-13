package com.betheone.pedidos.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PEDIDOS")
public class TblPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pedidoId;
	
	@Column(name="fecha", nullable = false)
	private Date fecha;

	@Column(name="totalVenta", nullable = false)
	private Double totalVenta;
	
	@Column(name="numeroFactura", nullable = false)
	private String numeroFactura;
	

	@JoinColumn(name = "clienteId", referencedColumnName = "clienteId")
    @ManyToOne(optional = false)
	private TblCliente cliente;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<TblLineaPedido> lineaPedido;

	public TblPedido(Date fecha, TblCliente cliente, Double totalVenta, String numeroFactura) {
		this.fecha = fecha;
		this.cliente = cliente;
		this.totalVenta = totalVenta;
		this.numeroFactura = numeroFactura;
	}
	
	public TblPedido() {
		
	}

	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public TblCliente getCliente() {
		return cliente;
	}

	public void setCliente(TblCliente cliente) {
		this.cliente = cliente;
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

	public List<TblLineaPedido> getLineaPedido() {
		return lineaPedido;
	}

	public void setLineaPedido(List<TblLineaPedido> lineaPedido) {
		this.lineaPedido = lineaPedido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


}
