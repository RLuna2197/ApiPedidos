package com.betheone.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betheone.pedidos.entity.TblCliente;

public interface IClienteRepository extends JpaRepository<TblCliente, Integer> {

}
