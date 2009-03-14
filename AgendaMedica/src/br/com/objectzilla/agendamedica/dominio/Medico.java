package br.com.objectzilla.agendamedica.dominio;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Medico implements Serializable {
	
	private static final long serialVersionUID = 7280903799887385565L;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Set<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(Set<Consulta> consultas) {
		this.consultas = consultas;
	}

	public void adicioneDisponibilidade(HorarioDisponivel horarioDisponivel) {
		disponibilidades.add(horarioDisponivel);	
	}
	
	public Set<HorarioDisponivel> getDisponibilidades() {
		return Collections.unmodifiableSet(disponibilidades);
	}
	
	private long id;
	private String nome;
	private Set<HorarioDisponivel> disponibilidades = new HashSet<HorarioDisponivel>();
	private Set<Consulta> consultas;
}
