package com.betheone.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betheone.pedidos.entity.TblProducto;

public interface IProductoRepository extends JpaRepository<TblProducto, Integer> {

}
