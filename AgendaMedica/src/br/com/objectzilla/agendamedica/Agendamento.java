package br.com.objectzilla.agendamedica;

import java.util.Calendar;

import javax.ejb.Local;

@Local
public interface Agendamento {

	void marcaConsulta(long medico, long paciente, Calendar horario);
}
