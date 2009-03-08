package br.com.objectzilla.agendamedica.dominio;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.objectzilla.agendamedica.aplicacao.Agendamento;

@Stateless
public class AgendamentoImpl implements Agendamento {

	@Override
	public void marcaConsulta(long medicoId, long pacienteId, Date horario)
			throws PacienteNaoEncontradoException, MedicoNaoEncontradoException {
		Paciente paciente = pacienteRepositorio.getPaciente(pacienteId);
		Medico medico = medicoRepositorio.getMedico(medicoId);

		medico.marcaConsulta(paciente, horario);

		medicoRepositorio.salvaConsultaMedico(medico);
	}

	@EJB
	public void setPacienteRepositorio(PacienteRepositorio pacienteRep) {
		pacienteRepositorio = pacienteRep;

	}

	@EJB
	public void setMedicoRepositorio(MedicoRepositorio medicoRep) {
		medicoRepositorio = medicoRep;
	}

	private PacienteRepositorio pacienteRepositorio;
	private MedicoRepositorio medicoRepositorio;
}
