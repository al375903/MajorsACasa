package es.uji.ei1027.majorsACasa.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Factura {
	private String idFactura;
	private String idBeneficiario;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fechaCreacion;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fechaInicio;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fechaFin;
	private float cantidad;
	private String concepto;
	
	public Factura(){
	}
	
	public String getIdFactura() {
		return idFactura;
	}
	
	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}
	
	public String getIdBeneficiario() {
		return idBeneficiario;
	}
	
	public void setIdBeneficiario(String idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}
	
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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

	public float getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getConcepto() {
		return concepto;
	}
	
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", idBeneficiario=" + idBeneficiario + ", fechaCreacion="
				+ fechaCreacion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin 
				+ ", cantidad=" + cantidad + ", concepto=" + concepto + "]";
	}
	
}
