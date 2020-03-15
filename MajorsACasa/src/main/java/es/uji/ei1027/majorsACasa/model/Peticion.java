package es.uji.ei1027.majorsACasa.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Peticion {
	private String idPeticion;
	private String idBeneficiario;
	private String idContrato;
	private String comentarios;
	private String tipoServicio;
	private String estado;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fechaAprobacion;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fechaDenegacion;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fechaCreacion;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fechaCancelacion;
	
	public Peticion() {
	}

	public String getIdPeticion() {
		return idPeticion;
	}

	public void setIdPeticion(String idPeticion) {
		this.idPeticion = idPeticion;
	}

	public String getIdBeneficiario() {
		return idBeneficiario;
	}

	public void setIdBeneficiario(String idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDate getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(LocalDate fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public LocalDate getFechaDenegacion() {
		return fechaDenegacion;
	}

	public void setFechaDenegacion(LocalDate fechaDenegacion) {
		this.fechaDenegacion = fechaDenegacion;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(LocalDate fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	@Override
	public String toString() {
		return "Peticion [idPeticion=" + idPeticion + ", idBeneficiario=" + idBeneficiario 
				+ ", idContrato=" + idContrato + ", comentarios=" + comentarios 
				+ ", tipoServicio=" + tipoServicio + ", estado=" + estado
				+ ", fechaAprobacion=" + fechaAprobacion + ", fechaDenegacion=" + fechaDenegacion 
				+ ", fechaCreacion=" + fechaCreacion + ", fechaCancelacion=" + fechaCancelacion + "]";
	}
	
}
