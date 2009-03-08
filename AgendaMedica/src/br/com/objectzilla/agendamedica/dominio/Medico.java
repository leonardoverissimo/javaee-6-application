package br.com.objectzilla.agendamedica.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
	
	public Paciente consulta(Calendar horario) {
		return agenda.get(horario);
	}

	public void consulta(Paciente paciente, Calendar horario) {
		agenda.put(horario, paciente);
	}
	
	public void adicioneDisponibilidade(HorarioDisponivel horarioDisponivel) {
		disponibilidades.add(horarioDisponivel);	
	}
	
	public Set<HorarioDisponivel> getDisponibilidades() {
		return Collections.unmodifiableSet(disponibilidades);
	}
	
	private long id;
	private String nome;
	private Map<Calendar, Paciente> agenda = new HashMap<Calendar, Paciente>();
	private Set<HorarioDisponivel> disponibilidades = new HashSet<HorarioDisponivel>();
}
