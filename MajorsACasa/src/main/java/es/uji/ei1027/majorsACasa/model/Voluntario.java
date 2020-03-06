package es.uji.ei1027.majorsACasa.model;

public class Voluntario {
	private String idVoluntario;
	private String nombre;
	private String email;
	private String direccion;
	private String hobby;
	//private Date fechaPeticionVoluntario;
	//private Date fechaAceptacionVoluntariado; 
	//private Date fechaFin; 
	private boolean aceptado;
	private String genero;
	private String edad;
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
	
	public String getHobby() {
		return hobby;
	}
	
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	public Boolean getAceptado() {
		return aceptado;
	}
	
	public void setAceptado(Boolean aceptado) {
		this.aceptado = aceptado;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getEdad() {
		return edad;
	}
	
	public void setEdad(String edad) {
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
				+ direccion + ", hobby=" + hobby + ", aceptado=" + aceptado + ", genero=" + genero + ", edad=" + edad
				+ ", contrasenya=" + contrasenya + "]";
	}
	

}
