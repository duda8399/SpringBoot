package com.eventoapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		
		//verificar se tem erros
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarEvento";
		}
		
		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		
		ModelAndView mv = new ModelAndView("evento/index");
		Iterable<Evento> eventos = er.findAll();
		
		mv.addObject("evento", eventos);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
				
		return mv;
	}
	
	@RequestMapping("/deletarEvento/{codigo}")
	public String deletarEvento(@PathVariable("codigo") long codigo) {
		
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
		return "redirect:/eventos";
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEvento(@PathVariable("codigo") long codigo, 
								 @Valid Convidado convidado,
								 BindingResult result,
								 RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/{codigo}";
		}
		
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		
		return "redirect:/{codigo}";
	}
	
	@RequestMapping("/deletarConvidado/{codigo}")
	public String deletarConvidado(@PathVariable("codigo") long codigo) {
		
		Convidado convidado = cr.findByCodigo(codigo);
		cr.delete(convidado);
		
		Evento evento = convidado.getEvento();
		long codigoLong = evento.getCodigo();
		String codigoConvidado = "" + codigoLong;
		return "redirect:/" + codigoConvidado;
	}

}
