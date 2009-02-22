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
		
		context.checking(new Expectations() {{
			Paciente paciente = new Paciente();
			paciente.setNome("Maria");
			
			oneOf (pacienteRep).getPaciente(32L); will(returnValue(paciente));
		}});
		
		// ajustando o repositório de médico
		final MedicoRepositorio medicoRep = context.mock(MedicoRepositorio.class);
		
		context.checking(new Expectations() {{
			Medico medico = new Medico();
			medico.setNome("Dr. Gregory House");
			
			oneOf (medicoRep).getMedico(4L); will(returnValue(medico));
		}});
		
		// um paciente...
		Paciente paciente = pacienteRep.getPaciente(32L);
		
		// um médico...
		Medico medico = medicoRep.getMedico(4L);
		
		// ambos agendam um horário
		Agendamento agendamento = new AgendamentoImpl();
		Calendar horario = Calendar.getInstance();
		horario.set(2009, Calendar.MARCH, 5, 17, 00, 00);
		
		agendamento.marcaConsulta(medico, paciente, horario);
		
		// agora, médico tem consulta com paciente marcada às 5
		Paciente pacienteMarcado = medico.consulta(horario);
		Assert.assertEquals(paciente.getNome(), pacienteMarcado.getNome());
	}
}
