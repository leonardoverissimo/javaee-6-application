package br.com.objectzilla.agendamedica;

import java.util.Calendar;

public interface Agendamento {

	void marcaConsulta(Medico medico, Paciente paciente, Calendar horario);
}
