package com.jajm.inquilino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jajm.inquilino.model.Venda;
import com.jajm.inquilino.repository.helper.venda.VendasQueries;

public interface Vendas extends JpaRepository<Venda, Long>, VendasQueries {

}
