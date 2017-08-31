package com.jajm.inquilino.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jajm.inquilino.model.Imovel;
import com.jajm.inquilino.repository.Imoveis;
import com.jajm.inquilino.service.event.imovel.ImovelSalvoEvent;
import com.jajm.inquilino.service.exception.ImpossivelExcluirEntidadeException;
import com.jajm.inquilino.storage.FotoStorage;

@Service
public class CadastroImovelService {

	@Autowired
	private Imoveis imoveis;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Transactional
	public void salvar(Imovel imovel) {
		imoveis.save(imovel);
		
		publisher.publishEvent(new ImovelSalvoEvent(imovel));
	}
	
	@Transactional
	public void excluir(Imovel imovel) {
		try {
			String foto = imovel.getFoto();
			imoveis.delete(imovel);
			imoveis.flush();
			fotoStorage.excluir(foto);
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar imóvel. Já foi usada em alguma venda.");
		}
	}
	
}
