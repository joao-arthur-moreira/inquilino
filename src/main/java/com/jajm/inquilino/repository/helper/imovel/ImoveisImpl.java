package com.jajm.inquilino.repository.helper.imovel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jajm.inquilino.dto.ImovelDTO;
import com.jajm.inquilino.dto.ValorItensEstoque;
import com.jajm.inquilino.model.Imovel;
import com.jajm.inquilino.repository.filter.ImovelFilter;
import com.jajm.inquilino.repository.paginacao.PaginacaoUtil;

public class ImoveisImpl implements ImoveisQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Imovel> filtrar(ImovelFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Imovel.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Override
	public List<ImovelDTO> porSkuOuNome(String skuOuNome) {
		String jpql = "select new com.jajm.inquilino.dto.ImovelDTO(codigo, sku, nome, origem, valor, foto) "
				+ "from Imovel where lower(sku) like lower(:skuOuNome) or lower(nome) like lower(:skuOuNome)";
		List<ImovelDTO> cervejasFiltradas = manager.createQuery(jpql, ImovelDTO.class)
					.setParameter("skuOuNome", skuOuNome + "%")
					.getResultList();
		return cervejasFiltradas;
	}
	
	@Override
	public ValorItensEstoque valorItensEstoque() {
		String query = "select new com.jajm.inquilino.dto.ValorItensEstoque(sum(valor * quantidadeEstoque), sum(quantidadeEstoque)) from Imovel";
		return manager.createQuery(query, ValorItensEstoque.class).getSingleResult();
	}
	
	private Long total(ImovelFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Imovel.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(ImovelFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getSku())) {
				criteria.add(Restrictions.eq("sku", filtro.getSku()));
			}
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (isEstiloPresente(filtro)) {
				criteria.add(Restrictions.eq("estilo", filtro.getEstilo()));
			}

			if (filtro.getOrigem() != null) {
				criteria.add(Restrictions.eq("origem", filtro.getOrigem()));
			}

			if (filtro.getValorDe() != null) {
				criteria.add(Restrictions.ge("valor", filtro.getValorDe()));
			}

			if (filtro.getValorAte() != null) {
				criteria.add(Restrictions.le("valor", filtro.getValorAte()));
			}
		}
	}
	
	private boolean isEstiloPresente(ImovelFilter filtro) {
		return filtro.getEstilo() != null && filtro.getEstilo().getCodigo() != null;
	}

}
