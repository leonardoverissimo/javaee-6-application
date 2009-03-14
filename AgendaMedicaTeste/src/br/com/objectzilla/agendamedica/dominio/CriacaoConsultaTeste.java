package br.com.objectzilla.agendamedica.dominio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.objectzilla.agendamedica.dominio.HorarioDisponivel.DiaSemana;

public class CriacaoConsultaTeste {
	
	private Paciente paciente;
	private Medico medico;
	private Date horarioConsulta;
	
	@Before
	public void criaObjetos() {
		paciente = new Paciente();
		paciente.setNome("Leonardo");
		
		medico = new Medico();
		medico.setNome("Dr. Meredith Grey");
		
		Calendar c = Calendar.getInstance();
		c.set(2009, Calendar.MARCH, 02, 15, 00, 00);
		horarioConsulta = c.getTime();
	}
	
	@Test
	public void consultaNaoDisponivel() {
		// não informo o período de consulta 
		
		try {
			new Consulta(paciente, medico, horarioConsulta, 60);
			
			Assert.fail("Esperava-se o lançamento de exceçao");
		} catch (ConsultaNaoDisponivel e) {
			
		}
	}
	
	@Test
	public void consultaDisponivel() {
		
		medico.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 13, 00, 19, 00));
		
		Consulta consulta = new Consulta(paciente, medico, horarioConsulta, 60);
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		
		Assert.assertEquals(paciente, consulta.getPaciente());
		Assert.assertEquals(medico, consulta.getMedico());
		Assert.assertEquals("02032009150000", sdf.format(consulta.getInicio()));
		Assert.assertEquals("02032009160000", sdf.format(consulta.getFim()));
	}
	
	@Test
	public void consultaAposDisponibilidade() {
		medico.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 16, 00, 22, 00));
		
		try {
			new Consulta(paciente, medico, horarioConsulta, 60);
			
			Assert.fail("Esperava-se o lançamento de exceçao");
		} catch (ConsultaNaoDisponivel e) {
			
		}
	}
	
	@Test
	public void consultaAntesDisponibilidade() {
		medico.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 8, 00, 15, 00));
		
		try {
			new Consulta(paciente, medico, horarioConsulta, 60);
			
			Assert.fail("Esperava-se o lançamento de exceçao");
		} catch (ConsultaNaoDisponivel e) {
			
		}
	}
	
	@Test
	public void consultaExatamenteDisponibilidade() {
		medico.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 15, 00, 16, 00));
		
		Consulta consulta = new Consulta(paciente, medico, horarioConsulta, 60);
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		
		Assert.assertEquals(paciente, consulta.getPaciente());
		Assert.assertEquals(medico, consulta.getMedico());
		Assert.assertEquals("02032009150000", sdf.format(consulta.getInicio()));
		Assert.assertEquals("02032009160000", sdf.format(consulta.getFim()));
	}
	
	@Test
	public void consultaPacialmenteDisponivel() {
		medico.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 15, 30, 20, 00));
		
		try {
			new Consulta(paciente, medico, horarioConsulta, 60);
			
			Assert.fail("Esperava-se o lançamento de exceçao");
		} catch (ConsultaNaoDisponivel e) {
			
		}
	}
	
	@Test
	public void duasDisponibilidadesUmaOk() {
		medico.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUARTA, 13, 00, 19, 00));
		medico.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 13, 00, 19, 00));
		
		Consulta consulta = new Consulta(paciente, medico, horarioConsulta, 60);
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		
		Assert.assertEquals(paciente, consulta.getPaciente());
		Assert.assertEquals(medico, consulta.getMedico());
		Assert.assertEquals("02032009150000", sdf.format(consulta.getInicio()));
		Assert.assertEquals("02032009160000", sdf.format(consulta.getFim()));
	}
	
	@Test
	public void duasDisponibilidadesNenhumaOk() {
		medico.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUARTA, 13, 00, 19, 00));
		medico.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 8, 00, 12, 00));
		
		try {
			new Consulta(paciente, medico, horarioConsulta, 60);
			
			Assert.fail("Esperava-se o lançamento de exceçao");
		} catch (ConsultaNaoDisponivel e) {
			
		}
	}
	
	@Test
	public void duasConsultasAoMesmoTempo() {
		medico.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 8, 00, 17, 00));
		
		ConsultaFactory consultaFactory = new ConsultaFactory();
		Mockery context = new JUnit4Mockery();
		
		final ConsultaRepositorio consultaRep =  context.mock(ConsultaRepositorio.class);
		context.checking(new Expectations() {{
			one(consultaRep).existeConsultaPara(medico, horarioConsulta, 60); will(returnValue(false));
			one(consultaRep).existeConsultaPara(medico, horarioConsulta, 60); will(returnValue(true));
			
		}});
		consultaFactory.setConsultaRepositorio(consultaRep);
		
		// a primeira consulta está ok
		Consulta consulta = consultaFactory.novaConsulta(paciente, medico, horarioConsulta, 60);
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		
		Assert.assertEquals(paciente, consulta.getPaciente());
		Assert.assertEquals(medico, consulta.getMedico());
		Assert.assertEquals("02032009150000", sdf.format(consulta.getInicio()));
		Assert.assertEquals("02032009160000", sdf.format(consulta.getFim()));
		
		// a segunda consulta é no mesmo horário
		Paciente paciente2 = new Paciente();
		paciente2.setNome("Maria");
		try {
			
			consultaFactory.novaConsulta(paciente2, medico, horarioConsulta, 60);
			
			Assert.fail("Realizada segunda consulta por engano");
		} catch (ConsultaNaoDisponivel e) {
			
		}
	}
	
}
