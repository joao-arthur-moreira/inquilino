package com.jajm.inquilino.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jajm.inquilino.controller.page.PageWrapper;
import com.jajm.inquilino.dto.ImovelDTO;
import com.jajm.inquilino.model.Imovel;
import com.jajm.inquilino.model.Origem;
import com.jajm.inquilino.repository.Estilos;
import com.jajm.inquilino.repository.Imoveis;
import com.jajm.inquilino.repository.filter.ImovelFilter;
import com.jajm.inquilino.service.CadastroImovelService;
import com.jajm.inquilino.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/imoveis")
public class ImoveisController {
	
	@Autowired
	private Estilos estilos;
	
	@Autowired
	private CadastroImovelService cadastroImovelService;
	
	@Autowired
	private Imoveis imoveis;

	@RequestMapping("/nova")
	public ModelAndView nova(Imovel imovel) {
		ModelAndView mv = new ModelAndView("imovel/CadastroImovel");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}
	
	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Imovel imovel, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(imovel);
		}
		
		cadastroImovelService.salvar(imovel);
		attributes.addFlashAttribute("mensagem", "Imovel salvo com sucesso!");
		return new ModelAndView("redirect:/imoveis/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ImovelFilter imovelFilter, BindingResult result
			, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("imovel/PesquisaImoveis");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		
		PageWrapper<Imovel> paginaWrapper = new PageWrapper<>(imoveis.filtrar(imovelFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ImovelDTO> pesquisar(String skuOuNome) {
		return imoveis.porSkuOuNome(skuOuNome);
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Imovel imovel) {
		try {
			cadastroImovelService.excluir(imovel);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Imovel imovel) {
		ModelAndView mv = nova(imovel);
		mv.addObject(imovel);
		return mv;
	}
	
}
