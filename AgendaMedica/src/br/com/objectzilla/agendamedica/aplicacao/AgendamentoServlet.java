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

@WebServlet(value = "/marcaConsulta")
public class AgendamentoServlet extends HttpServlet {

	private static final long serialVersionUID = -1218896541620785877L;
	
	@EJB
	private Agendamento agendamento;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {

		try {
			long medicoId = Long.parseLong(req.getParameter("medicoId"));
			long pacienteId = Long.parseLong(req.getParameter("pacienteId"));

			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,
					DateFormat.SHORT, req.getLocale());

			String horario = req.getParameter("dia") + " " + req.getParameter("hora");
			Date date = dateFormat.parse(horario);

			Calendar cal = Calendar.getInstance(req.getLocale());
			cal.setTime(date);

			// chamando método de agendamento
			agendamento.marcaConsulta(medicoId, pacienteId, cal);

			resp.sendRedirect(resp.encodeRedirectURL("consulta?medicoId=" + medicoId
					+ "&horario=" + horario));

		} catch (Exception e) {
			PrintWriter out = resp.getWriter();
			out.print("Você fez alguma coisa de errado!");
		}
	}
}
