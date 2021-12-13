package com.betheone.pedidos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTES")
public class TblCliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clienteId;
	
	@Column(name="nombre", nullable = false)
	private String nombreCliente;
	
	@Column(name="apellido", nullable = false)
	private String apellidoCliente;
	
	@Column(name="identidad", nullable = false)
	private String identidad;
	
	@Column(name="telefono", nullable = false)
	private String telefono;
	
	@Column(name="direccion", nullable = false)
	private String direccion;
	
	@Column(name="edad", nullable = false)
	private int edad;
	
	@Column(name="email", nullable = false)
	private String email;

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getIdentidad() {
		return identidad;
	}

	public void setIdentidad(String identidad) {
		this.identidad = identidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
