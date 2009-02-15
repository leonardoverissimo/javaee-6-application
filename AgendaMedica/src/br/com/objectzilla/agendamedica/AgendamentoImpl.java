package br.com.objectzilla.agendamedica;

import java.util.Calendar;

public class AgendamentoImpl implements Agendamento {

	@Override
	public void marcaConsulta(Medico medico, Paciente paciente, Calendar horario) {
		medico.consulta(paciente, horario);

	}

}
