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
			

			if (inicioConsulta.get(Calendar.DAY_OF_WEEK) == disponibilidade.diaSemana.ordinal() + 1) {
				Calendar limiteInicial = Calendar.getInstance();
				// o dia seria igual à data marcada, apenas para não complicar
				limiteInicial.setTime(inicio);

				limiteInicial.set(Calendar.HOUR_OF_DAY, disponibilidade.horaInicial);
				limiteInicial.set(Calendar.MINUTE, disponibilidade.minutoInicial);
				limiteInicial.set(Calendar.SECOND, 0);

				Calendar limiteFinal = Calendar.getInstance();
				// o dia seria igual à data marcada, apenas para não complicar
				limiteInicial.setTime(inicio);
				// se hora final é menor que hora inicial, assumimos que a hora
				// final refere-se ao dia seguinte. Exemplo: inicial: 22 e
				// final: 04 significa um período que começa às 10 da noite de
				// um dia e vai até às 4 da manhã do dia seguinte
				if (disponibilidade.horaFinal < disponibilidade.horaInicial) {
					limiteFinal.add(Calendar.DAY_OF_MONTH, 1);
				}
				
				// verifica se a data está no intervalo
				if (inicioConsulta.compareTo(limiteInicial) >= 0 && finalConsulta.compareTo(limiteFinal) <= 0) {
					disponivel = true;
					break;
				}
				
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
