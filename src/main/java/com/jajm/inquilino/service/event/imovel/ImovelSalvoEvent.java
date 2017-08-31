package com.jajm.inquilino.service.event.imovel;

import org.springframework.util.StringUtils;

import com.jajm.inquilino.model.Imovel;

public class ImovelSalvoEvent {

	private Imovel imovel;

	public ImovelSalvoEvent(Imovel imovel) {
		this.imovel = imovel;
	}

	public Imovel getImovel() {
		return imovel;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(imovel.getFoto());
	}

	public boolean isNovaFoto() {
		return imovel.isNovaFoto();
	}
	
}
