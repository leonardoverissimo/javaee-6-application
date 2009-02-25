package br.com.objectzilla.agendamedica.dominio;

import java.util.Calendar;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;

import br.com.objectzilla.agendamedica.aplicacao.Agendamento;

public class AgendamentoTeste {
	
	@Test
	public void pacienteMarcaHoraComMedico() {
		
		Mockery context = new JUnit4Mockery();

		// ajustando o repositório de paciente
		final PacienteRepositorio pacienteRep =  context.mock(PacienteRepositorio.class);
		final Paciente pacienteRetornado = new Paciente();
		
		try {
			context.checking(new Expectations() {{
				pacienteRetornado.setNome("Maria");
				
				oneOf (pacienteRep).getPaciente(32L); will(returnValue(pacienteRetornado));
			}});
		} catch (PacienteNaoEncontradoException e) { Assert.fail(); }
		
		// ajustando o repositório de médico
		final MedicoRepositorio medicoRep = context.mock(MedicoRepositorio.class);
		final Medico medicoRetornado = new Medico();
		
		try {
			context.checking(new Expectations() {{
				medicoRetornado.setNome("Dr. Gregory House");
				
				oneOf (medicoRep).getMedico(4L); will(returnValue(medicoRetornado));
				oneOf (medicoRep).salvaConsultaMedico(medicoRetornado);
			}});
		} catch (MedicoNaoEncontradoException e1) { Assert.fail(); }
		
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
		
		try {
			agendamento.marcaConsulta(medicoId, pacienteId, horario);
			
			// verifica se os métodos definidos no mock foram realmente chamados
			context.assertIsSatisfied();
			
			// agora, médico tem consulta com paciente marcada às 5
			Paciente pacienteMarcado = medicoRetornado.consulta(horario);
			Assert.assertEquals(pacienteRetornado.getNome(), pacienteMarcado.getNome());
			
		} catch (PacienteNaoEncontradoException e) {
			Assert.fail();
		} catch (MedicoNaoEncontradoException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void consultaComPacienteInexistente() {
		Mockery context = new JUnit4Mockery();

		// ajustando o repositório de paciente
		final PacienteRepositorio pacienteRep =  context.mock(PacienteRepositorio.class);
		final Paciente pacienteRetornado = new Paciente();
		
		try {
			context.checking(new Expectations() {{
				pacienteRetornado.setNome("Maria");
				
				oneOf (pacienteRep).getPaciente(32L); will(throwException(new PacienteNaoEncontradoException()));
			}});
		} catch (PacienteNaoEncontradoException e) { Assert.fail(); }
		
		// id de paciente e médico
		long pacienteId = 32L;
		long medicoId = 4L;
		
		// cria um agendamento, passando os repositórios como parâmetro
		Agendamento agendamento;
		{
			AgendamentoImpl impl = new AgendamentoImpl();
			impl.setPacienteRepositorio(pacienteRep);
			impl.setMedicoRepositorio(null);
			agendamento = impl;
		}
		
		try {
			agendamento.marcaConsulta(medicoId, pacienteId, null);
			
			Assert.fail("marcaConsulta() não lançou exceção de PacienteNaoEncontrado");
		} catch (PacienteNaoEncontradoException e) {
			// sucesso
		} catch (MedicoNaoEncontradoException e) {
			Assert.fail();
		}
	}
	
	
	@Test
	public void consultaComMedicoInexistente() {
		Mockery context = new JUnit4Mockery();

		// ajustando o repositório de paciente
		final PacienteRepositorio pacienteRep =  context.mock(PacienteRepositorio.class);
		final Paciente pacienteRetornado = new Paciente();
		
		try {
			context.checking(new Expectations() {{
				pacienteRetornado.setNome("Maria");
				
				oneOf (pacienteRep).getPaciente(32L); will(returnValue(pacienteRetornado));
			}});
		} catch (PacienteNaoEncontradoException e) { Assert.fail(); }
		
		// ajustando o repositório de médico
		final MedicoRepositorio medicoRep = context.mock(MedicoRepositorio.class);
		final Medico medicoRetornado = new Medico();
		
		try {
			context.checking(new Expectations() {{
				medicoRetornado.setNome("Dr. Gregory House");
				
				oneOf (medicoRep).getMedico(4L); will(throwException(new MedicoNaoEncontradoException()));
			}});
		} catch (MedicoNaoEncontradoException e) { Assert.fail(); }
		
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
		
		try {
			agendamento.marcaConsulta(medicoId, pacienteId, null);
			
			Assert.fail("marcaConsulta() não lançou nenhuma exceção");
		} catch (PacienteNaoEncontradoException e) {
			Assert.fail();
		} catch (MedicoNaoEncontradoException e) {
			// sucesso
		}
	}
}