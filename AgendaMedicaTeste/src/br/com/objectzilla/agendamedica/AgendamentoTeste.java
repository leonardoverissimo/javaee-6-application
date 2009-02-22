package br.com.objectzilla.agendamedica;

import java.util.Calendar;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;

public class AgendamentoTeste {
	
	@Test
	public void pacienteMarcaHoraComMedico() {
		
		Mockery context = new JUnit4Mockery();

		// ajustando o repositório de paciente
		final PacienteRepositorio pacienteRep =  context.mock(PacienteRepositorio.class);
		final Paciente pacienteRetornado = new Paciente();
		
		context.checking(new Expectations() {{
			pacienteRetornado.setNome("Maria");
			
			oneOf (pacienteRep).getPaciente(32L); will(returnValue(pacienteRetornado));
		}});
		
		// ajustando o repositório de médico
		final MedicoRepositorio medicoRep = context.mock(MedicoRepositorio.class);
		final Medico medicoRetornado = new Medico();
		
		context.checking(new Expectations() {{
			medicoRetornado.setNome("Dr. Gregory House");
			
			oneOf (medicoRep).getMedico(4L); will(returnValue(medicoRetornado));
		}});
		
		// id de paciente e médico
		long pacienteId = 32L;
		long medicoId = 4L;
		
		// cria um agendamento, passando os repositórios como parâmetro
		Agendamento agendamento;
		{
			AgendamentoImpl impl = new AgendamentoImpl();
			impl.setPacienteRepositorio(pacienteRep);
			impl.setMedicoRepositorio(medicoRep);
			agendamento = impl;
		}
		
		// cria um horário para a consulta
		Calendar horario = Calendar.getInstance();
		horario.set(2009, Calendar.MARCH, 5, 17, 00, 00);
		
		agendamento.marcaConsulta(medicoId, pacienteId, horario);
		
		// verifica se os métodos definidos no mock foram realmente chamados
		context.assertIsSatisfied();
		
		// agora, médico tem consulta com paciente marcada às 5
		Paciente pacienteMarcado = medicoRetornado.consulta(horario);
		Assert.assertEquals(pacienteRetornado.getNome(), pacienteMarcado.getNome());
	}
}
