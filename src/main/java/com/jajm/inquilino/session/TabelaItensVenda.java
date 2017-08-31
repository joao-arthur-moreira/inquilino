package com.jajm.inquilino.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.jajm.inquilino.model.Imovel;
import com.jajm.inquilino.model.ItemVenda;

public class TabelaItensVenda {

	private String uuid;
	private List<ItemVenda> itens = new ArrayList<>();
	
	public TabelaItensVenda(String uuid) {
		this.uuid = uuid;
	}

	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Imovel imovel, Integer quantidade) {
		Optional<ItemVenda> itemVendaOptional = buscarItemPorCerveja(imovel);
		
		ItemVenda itemVenda = null;
		if (itemVendaOptional.isPresent()) {
			itemVenda = itemVendaOptional.get();
			itemVenda.setQuantidade(itemVenda.getQuantidade() + quantidade);
		} else {
			itemVenda = new ItemVenda();
			itemVenda.setCerveja(imovel);
			itemVenda.setQuantidade(quantidade);
			itemVenda.setValorUnitario(imovel.getValor());
			itens.add(0, itemVenda);
		}
	}
	
	public void alterarQuantidadeItens(Imovel imovel, Integer quantidade) {
		ItemVenda itemVenda = buscarItemPorCerveja(imovel).get();
		itemVenda.setQuantidade(quantidade);
	}
	
	public void excluirItem(Imovel imovel) {
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getCerveja().equals(imovel))
				.findAny().getAsInt();
		itens.remove(indice);
	}
	
	public int total() {
		return itens.size();
	}

	public List<ItemVenda> getItens() {
		return itens;
	}
	
	private Optional<ItemVenda> buscarItemPorCerveja(Imovel imovel) {
		return itens.stream()
				.filter(i -> i.getCerveja().equals(imovel))
				.findAny();
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaItensVenda other = (TabelaItensVenda) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
