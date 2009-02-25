package br.com.objectzilla.agendamedica.aplicacao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.objectzilla.agendamedica.dominio.Medico;
import br.com.objectzilla.agendamedica.dominio.MedicoRepositorio;
import br.com.objectzilla.agendamedica.dominio.Paciente;

@WebServlet(value="/consulta")
public class ConsultaServlet extends HttpServlet {

	private static final long serialVersionUID = 8084899967543431263L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		
		try {
			long medicoId = Long.parseLong(req.getParameter("medicoId"));
			
			Medico medico = medicoRep.getMedico(medicoId);
			
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, req.getLocale());
			Date d = df.parse(req.getParameter("horario"));
			
			Calendar horario = Calendar.getInstance(req.getLocale());
			horario.setTime(d);
			req.setAttribute("horario", horario);
			
			Paciente paciente = medico.consulta(horario);
			
			if (paciente == null) {
				paciente = new Paciente();
				paciente.setNome("(ninguém)");
			}
			
			req.setAttribute("paciente", paciente);
			
			req.getRequestDispatcher("/WEB-INF/consulta.jsp").forward(req, resp);
			
		} catch (Exception e) {
			PrintWriter out = resp.getWriter();
			out.println("Você fez alguma coisa de errado!<br/><br/>");
			e.printStackTrace(out);
		}
	}
	
	@EJB
	private MedicoRepositorio medicoRep;
}
