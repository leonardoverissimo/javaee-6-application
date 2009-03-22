package br.com.objectzilla.agendamedica.dominio;

import java.io.Serializable;
import java.util.Calendar;

public class HorarioDisponivel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4345100051116400270L;
	
	public enum DiaSemana {
		DOMINGO(Calendar.SUNDAY), SEGUNDA(Calendar.MONDAY), TERCA(Calendar.TUESDAY), QUARTA(
				Calendar.WEDNESDAY), QUINTA(Calendar.THURSDAY), SEXTA(Calendar.FRIDAY), SABADO(
				Calendar.SATURDAY);

		private DiaSemana(int diaSemana) {
			this.diaSemana = diaSemana;
		}

		public int getDiaSemana() {
			return diaSemana;
		}

		private int diaSemana;
	}

	public static HorarioDisponivel getInstance(DiaSemana diaSemana, int horaInicial,
			int minutoInicial, int horaFinal, int minutoFinal) {

		return new HorarioDisponivel(diaSemana, horaInicial, minutoInicial, horaFinal, minutoFinal);
	}

	private HorarioDisponivel(DiaSemana diaSemana, int horaInicial, int minutoInicial,
			int horaFinal, int minutoFinal) {
		this.diaSemana = diaSemana;
		this.horaInicial = horaInicial;
		this.minutoInicial = minutoInicial;
		this.horaFinal = horaFinal;
		this.minutoFinal = minutoFinal;
	}

	public boolean isDentroHorario(Calendar inicioConsulta, Calendar finalConsulta) {

		if (inicioConsulta.get(Calendar.DAY_OF_WEEK) == diaSemana.getDiaSemana()) {
			Calendar limiteInicial = Calendar.getInstance();
			// o dia seria igual à data marcada, apenas para não complicar
			limiteInicial.setTime(inicioConsulta.getTime());

			limiteInicial.set(Calendar.HOUR_OF_DAY, horaInicial);
			limiteInicial.set(Calendar.MINUTE, minutoInicial);
			limiteInicial.set(Calendar.SECOND, 0);

			Calendar limiteFinal = Calendar.getInstance();

			// o dia seria igual à data marcada, apenas para não complicar
			limiteFinal.setTime(inicioConsulta.getTime());

			// se hora final é menor que hora inicial, assumimos que a hora
			// final refere-se ao dia seguinte. Exemplo: inicial: 22 e
			// final: 04 significa um período que começa às 10 da noite de
			// um dia e vai até às 4 da manhã do dia seguinte
			if (horaFinal < horaInicial) {
				limiteFinal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			limiteFinal.set(Calendar.HOUR_OF_DAY, horaFinal);
			limiteFinal.set(Calendar.MINUTE, minutoFinal);
			limiteFinal.set(Calendar.SECOND, 0);


			// true se a data está no intervalo
			return inicioConsulta.compareTo(limiteInicial) >= 0
					&& finalConsulta.compareTo(limiteFinal) <= 0;
		}
		return false;
	}
	
	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public int getHoraInicial() {
		return horaInicial;
	}

	public int getMinutoInicial() {
		return minutoInicial;
	}

	public int getHoraFinal() {
		return horaFinal;
	}

	public int getMinutoFinal() {
		return minutoFinal;
	}

	private DiaSemana diaSemana;
	private int horaInicial;
	private int minutoInicial;
	private int horaFinal;
	private int minutoFinal;
}
