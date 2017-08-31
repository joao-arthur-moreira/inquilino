package com.jajm.inquilino.service.event.imovel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jajm.inquilino.storage.FotoStorage;

@Component
public class ImovelListener {

	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#evento.temFoto() and #evento.novaFoto")
	public void cervejaSalva(ImovelSalvoEvent evento) {
		fotoStorage.salvar(evento.getImovel().getFoto());
	}
	
}
