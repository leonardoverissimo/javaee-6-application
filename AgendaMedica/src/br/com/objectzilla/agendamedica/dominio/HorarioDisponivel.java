package br.com.objectzilla.agendamedica.dominio;

public class HorarioDisponivel {
	public enum DiaSemana {DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO}

	public HorarioDisponivel(DiaSemana diaSemana, int horaInicial, int minutoInicial,
			int horaFinal, int minutoFinal) {
		this.diaSemana = diaSemana;
		this.horaInicial = horaInicial;
		this.minutoInicial = minutoInicial;
		this.horaFinal = horaFinal;
		this.minutoFinal = minutoFinal;
	}

	DiaSemana diaSemana;
	int horaInicial;
	int minutoInicial;
	int horaFinal;
	int minutoFinal;
}
