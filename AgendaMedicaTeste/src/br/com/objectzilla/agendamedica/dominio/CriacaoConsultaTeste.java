package br.com.objectzilla.agendamedica.dominio;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import br.com.objectzilla.agendamedica.dominio.HorarioDisponivel.DiaSemana;

public class CriacaoConsultaTeste {
	
	@Test
	public void consultaNaoDisponivel() {
		
		Paciente p = new Paciente();
		p.setNome("Leonardo");
		
		Medico m = new Medico();
		m.setNome("Dr. Meredith Grey");
		
		// não informo o período de consulta 
		
		Calendar c = Calendar.getInstance();
		c.set(2009, Calendar.MARCH, 01, 15, 00, 00);
		
		try {
			new Consulta(p, m, c.getTime(), 60);
			
			Assert.fail("Esperava-se o lançamento de exceçao");
		} catch (ConsultaNaoDisponivel e) {
			
		}
	}
	
	@Test
	public void consultaDisponivel() {
		
		Paciente p = new Paciente();
		p.setNome("Leonardo");
		
		Medico m = new Medico();
		m.setNome("Dr. Meredith Grey");
		
		m.adicioneDisponibilidade(new HorarioDisponivel(DiaSemana.SEGUNDA, 13, 00, 19, 00));
		
		// não informo o período de consulta 
		
		Calendar c = Calendar.getInstance();
		c.set(2009, Calendar.MARCH, 02, 15, 00, 00);
		
		
		Consulta consulta = new Consulta(p, m, c.getTime(), 60);
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		
		Assert.assertEquals(p, consulta.getPaciente());
		Assert.assertEquals(m, consulta.getMedico());
		Assert.assertEquals("02032009150000", sdf.format(consulta.getInicio()));
		Assert.assertEquals("02032009160000", sdf.format(consulta.getFim()));
	}
}
