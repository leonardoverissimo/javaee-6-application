package br.com.objectzilla.agendamedica.aplicacao;

import java.util.Date;

import javax.ejb.Local;

import br.com.objectzilla.agendamedica.dominio.MedicoNaoEncontradoException;
import br.com.objectzilla.agendamedica.dominio.PacienteNaoEncontradoException;

@Local
public interface Agendamento {

	void marcaConsulta(long medico, long paciente, Date horario)
			throws PacienteNaoEncontradoException, MedicoNaoEncontradoException;
}
