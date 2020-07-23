package com.eventoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventoapp.models.Evento;
import com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired //injeção de dependências
	private EventoRepository er;
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(Evento evento) {
		
		er.save(evento); //salvar os dados no banco de dados
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		//página em que será renderizada com os dados do evento
		ModelAndView mv = new ModelAndView("evento/index");
		
		//buscar os eventos no bd e armazenar em um array(iterable)
		Iterable<Evento> eventos = er.findAll();
		
		//o atributo é o msm da view ${evento}
		mv.addObject("evento", eventos);
		
		return mv;
	}

}
