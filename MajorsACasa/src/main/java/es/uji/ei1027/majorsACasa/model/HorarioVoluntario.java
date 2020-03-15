package es.uji.ei1027.majorsACasa.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class HorarioVoluntario {
	private String idHorario;
	private String idVoluntario;
	private String idBeneficiario;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fecha;
	@DateTimeFormat(pattern = "HH:mm:ss.SSS")
	private LocalTime horaInicio;
	@DateTimeFormat(pattern = "HH:mm:ss.SSS")
	private LocalTime horaFin;
	private boolean libre;
	
	public HorarioVoluntario() {
	}

	public String getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(String idHorario) {
		this.idHorario = idHorario;
	}

	public String getIdVoluntario() {
		return idVoluntario;
	}

	public void setIdVoluntario(String idVoluntario) {
		this.idVoluntario = idVoluntario;
	}

	public String getIdBeneficiario() {
		return idBeneficiario;
	}

	public void setIdBeneficiario(String idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public boolean isLibre() {
		return libre;
	}

	public void setLibre(boolean libre) {
		this.libre = libre;
	}

	@Override
	public String toString() {
		return "HorarioVoluntario [idHorario=" + idHorario + ", idVoluntario=" + idVoluntario 
				+ ", idBeneficiario=" + idBeneficiario + ", fecha=" + fecha 
				+ ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", libre=" + libre + "]";
	}
	
}
