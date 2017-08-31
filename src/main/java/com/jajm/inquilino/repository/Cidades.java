package com.jajm.inquilino.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jajm.inquilino.model.Cidade;
import com.jajm.inquilino.model.Estado;
import com.jajm.inquilino.repository.helper.cidade.CidadesQueries;

public interface Cidades extends JpaRepository<Cidade, Long>, CidadesQueries {

	public List<Cidade> findByEstadoCodigo(Long codigoEstado);

	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
	
}
