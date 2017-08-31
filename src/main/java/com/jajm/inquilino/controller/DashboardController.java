package com.jajm.inquilino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jajm.inquilino.repository.Imoveis;
import com.jajm.inquilino.repository.Clientes;
import com.jajm.inquilino.repository.Vendas;

@Controller
public class DashboardController {

	@Autowired
	private Vendas vendas;
	
	@Autowired
	private Imoveis imoveis;
	
	@Autowired
	private Clientes clientes;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		mv.addObject("vendasNoAno", vendas.valorTotalNoAno());
		mv.addObject("vendasNoMes", vendas.valorTotalNoMes());
		mv.addObject("ticketMedio", vendas.valorTicketMedioNoAno());
		
		mv.addObject("valorItensEstoque", imoveis.valorItensEstoque());
		mv.addObject("totalClientes", clientes.count());
		
		return mv;
	}
	
}
