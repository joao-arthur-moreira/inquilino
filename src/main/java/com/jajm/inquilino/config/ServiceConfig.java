package com.jajm.inquilino.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jajm.inquilino.service.CadastroImovelService;
import com.jajm.inquilino.storage.FotoStorage;
import com.jajm.inquilino.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroImovelService.class)
public class ServiceConfig {

	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}
	
}
