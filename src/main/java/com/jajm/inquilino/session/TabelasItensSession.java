package com.jajm.inquilino.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.jajm.inquilino.model.Imovel;
import com.jajm.inquilino.model.ItemVenda;

@SessionScope
@Component
public class TabelasItensSession {

	private Set<TabelaItensVenda> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, Imovel imovel, int quantidade) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(imovel, quantidade);
		tabelas.add(tabela);
	}

	public void alterarQuantidadeItens(String uuid, Imovel imovel, Integer quantidade) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarQuantidadeItens(imovel, quantidade);
	}

	public void excluirItem(String uuid, Imovel imovel) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(imovel);
	}

	public List<ItemVenda> getItens(String uuid) {
		return buscarTabelaPorUuid(uuid).getItens();
	}
	
	public Object getValorTotal(String uuid) {
		return buscarTabelaPorUuid(uuid).getValorTotal();
	}
	
	private TabelaItensVenda buscarTabelaPorUuid(String uuid) {
		TabelaItensVenda tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensVenda(uuid));
		return tabela;
	}

	
}
