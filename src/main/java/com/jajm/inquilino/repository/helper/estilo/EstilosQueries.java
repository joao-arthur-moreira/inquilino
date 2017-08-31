package com.jajm.inquilino.repository.helper.estilo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jajm.inquilino.model.Estilo;
import com.jajm.inquilino.repository.filter.EstiloFilter;

public interface EstilosQueries {
	
	public Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable);
	
}
