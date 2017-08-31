package com.jajm.inquilino.repository.helper.imovel;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jajm.inquilino.dto.ImovelDTO;
import com.jajm.inquilino.dto.ValorItensEstoque;
import com.jajm.inquilino.model.Imovel;
import com.jajm.inquilino.repository.filter.ImovelFilter;

public interface ImoveisQueries {

	public Page<Imovel> filtrar(ImovelFilter filtro, Pageable pageable);
	
	public List<ImovelDTO> porSkuOuNome(String skuOuNome);
	
	public ValorItensEstoque valorItensEstoque();
	
}
