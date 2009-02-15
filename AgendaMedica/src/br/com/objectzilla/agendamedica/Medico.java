package br.com.objectzilla.agendamedica;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Medico {
	
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

	private String nome;
	private Map<Calendar, Paciente> agenda = new HashMap<Calendar, Paciente>();

}
