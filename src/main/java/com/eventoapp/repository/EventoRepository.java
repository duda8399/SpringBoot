package com.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventoapp.models.Evento;

//essa interface nos permite utilizar métodos prontos (insert, update, delete)
public interface EventoRepository extends CrudRepository<Evento, String> {

}
