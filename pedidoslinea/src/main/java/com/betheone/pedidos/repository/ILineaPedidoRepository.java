package com.betheone.pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betheone.pedidos.entity.TblLineaPedido;
import com.betheone.pedidos.entity.TblPedido;

public interface ILineaPedidoRepository extends JpaRepository<TblLineaPedido, Integer> {
	
	public List<TblLineaPedido> findByPedido(TblPedido pedido);
	
}
