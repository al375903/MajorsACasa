package es.uji.ei1027.majorsACasa.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Contrato {
	private String idContrato;
	private String idEmpresa;
	private float precio;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fechaInicio;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fechaFin;
	
	public Contrato() {
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "Contrato [idContrato=" + idContrato + ", idEmpresa=" + idEmpresa 
				+ ", precio=" + precio + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
	
}
