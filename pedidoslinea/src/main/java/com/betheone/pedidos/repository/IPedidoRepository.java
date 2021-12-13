package com.betheone.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betheone.pedidos.entity.TblPedido;

public interface IPedidoRepository extends JpaRepository<TblPedido, Integer> {
	
	public TblPedido findByNumeroFactura(String numeroFactura); // SELECT * FROM PEDIDO WHERE NUMERO_FACTURA='';

}
