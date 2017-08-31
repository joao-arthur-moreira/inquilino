package com.jajm.inquilino.service.event.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jajm.inquilino.model.Imovel;
import com.jajm.inquilino.model.ItemVenda;
import com.jajm.inquilino.repository.Imoveis;

@Component
public class VendaListener {

	@Autowired
	private Imoveis imoveis;
	
	@EventListener
	public void vendaEmitida(VendaEvent vendaEvent) {
		for (ItemVenda item : vendaEvent.getVenda().getItens()) {
			Imovel imovel = imoveis.findOne(item.getCerveja().getCodigo());
			imovel.setQuantidadeEstoque(imovel.getQuantidadeEstoque() - item.getQuantidade());
			imoveis.save(imovel);
		}
	}
	
}
