package br.com.objectzilla.agendamedica;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;


public class AgendamentoTeste {
	
	@Test
	public void pacienteMarcaHoraComMedico() {
		// um paciente...
		Paciente paciente = new Paciente();
		paciente.setNome("Maria");
		
		// um médico...
		Medico medico = new Medico();
		medico.setNome("Dr. Gregory House");
		
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
