package es.uji.ei1027.majorsACasa.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Voluntario {
	private String idVoluntario;
	private String nombre;
	private String email;
	private String direccion;
	private String hobbies;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fechaPeticionVoluntariado;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fechaAceptacionVoluntariado;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate fechaFin; 
	private String aceptado;
	private String genero;
	private int edad;
	private String contrasenya;
	
	public Voluntario() {
	}
	
	public String getIdVoluntario() {
		return idVoluntario;
	}
	
	public void setIdVoluntario(String idVoluntario) {
		this.idVoluntario = idVoluntario;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getHobbies() {
		return hobbies;
	}
	
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	
	public LocalDate getFechaPeticionVoluntariado() {
		return fechaPeticionVoluntariado;
	}

	public void setFechaPeticionVoluntariado(LocalDate fechaPeticionVoluntariado) {
		this.fechaPeticionVoluntariado = fechaPeticionVoluntariado;
	}

	public LocalDate getFechaAceptacionVoluntariado() {
		return fechaAceptacionVoluntariado;
	}

	public void setFechaAceptacionVoluntariado(LocalDate fechaAceptacionVoluntariado) {
		this.fechaAceptacionVoluntariado = fechaAceptacionVoluntariado;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getAceptado() {
		return aceptado;
	}
	
	public void setAceptado(String aceptado) {
		this.aceptado = aceptado;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getContrasenya() {
		return contrasenya;
	}
	
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	@Override
	public String toString() {
		return "Voluntario [idVoluntario=" + idVoluntario + ", nombre=" + nombre + ", email=" + email + ", direccion="
				+ direccion + ", hobby=" + hobbies + ", aceptado=" + aceptado + ", genero=" + genero + ", edad=" + edad
				+ ", contrasenya=" + contrasenya + "]";
	}
	

}
