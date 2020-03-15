package es.uji.ei1027.majorsACasa.model;

public class Beneficiario {
   private String idBeneficiario;
   private String nombre;
   private String email;
   private String direccion;
   private String genero;
   private int edad;
   private String contrasenya;
   private String hobbies;
   private String alergias;
   private String enfermedades;

   public Beneficiario() {
   }

   public String getIdBeneficiario() {
       return idBeneficiario;
   }

   public void setIdBeneficiario(String idBeneficiario) {
       this.idBeneficiario = idBeneficiario;
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

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(String enfermedades) {
		this.enfermedades = enfermedades;
	}

	@Override
	public String toString() {
		return "Beneficiario [idBeneficiario=" + idBeneficiario + ", nombre=" + nombre 
				+ ", email=" + email + ", direccion=" + direccion + ", genero=" + genero 
				+ ", edad=" + edad + ", contrasenya=" + contrasenya + ", hobbies=" + hobbies 
				+ ", alergias=" + alergias + ", enfermedades=" + enfermedades + "]";
	}

}