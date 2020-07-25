package com.eventoapp.models;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "convidado")
public class Convidado implements Serializable {
	
	private static final long serialVersionUID = 2398037990610718513L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoConvidado;
	
	private String rg;
	private String nomeConvidado;
	
	@ManyToOne
	@JoinColumn(name="evento", nullable = false)
	private Evento evento;
	

	public long getCodigoConvidado() {
		return codigoConvidado;
	}

	public void setCodigoConvidado(long codigoConvidado) {
		this.codigoConvidado = codigoConvidado;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNomeConvidado() {
		return nomeConvidado;
	}

	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
}
