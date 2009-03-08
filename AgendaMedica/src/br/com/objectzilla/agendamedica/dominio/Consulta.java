package br.com.objectzilla.agendamedica.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class Consulta implements Serializable {
	private static final long serialVersionUID = -7641371162404328157L;
	
	public Consulta(Paciente paciente, Medico medico, Date inicio, int duracaoMinutos) {
		Set<HorarioDisponivel> disponibilidades = medico.getDisponibilidades();
		
		Calendar inicioConsulta = Calendar.getInstance();
		inicioConsulta.setTime(inicio);
		
		Calendar finalConsulta = Calendar.getInstance();
		finalConsulta.setTime(inicio);
		finalConsulta.add(Calendar.MINUTE, duracaoMinutos);
		
		boolean disponivel = false;

		for (HorarioDisponivel disponibilidade : disponibilidades) {
			
			if (disponibilidade.isDentroHorario(inicioConsulta, finalConsulta)) {
				disponivel = true;
				break;
			}
		}

		if (disponivel) {
			this.paciente = paciente;
			this.medico = medico;
			this.inicio = inicioConsulta.getTime();
			this.fim = finalConsulta.getTime();
		} else {
			throw new ConsultaNaoDisponivel();
		}
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public Date getInicio() {
		return inicio;
	}

	public Date getFim() {
		return fim;
	}
	
	private Paciente paciente;
	private Medico medico;
	private Date inicio;
	private Date fim;
}
