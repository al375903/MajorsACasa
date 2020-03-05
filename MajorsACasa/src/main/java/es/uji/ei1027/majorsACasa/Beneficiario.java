package es.uji.ei1027.majorsACasa;

public class Beneficiario {
   private String idBeneficiario;
   private String nombre;
   private String email;
   private String direccion;
   private String genero;
   private int edad;
   private String contrasenya;

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


   @Override
   public String toString() {
       return "Beneficiario{" +
    		   "idBeneficiario='" + idBeneficiario + "\'" +
               ",nombre='" + nombre + "\'" +
               ", email='" + email + "\'" +
               ", direccion='" + direccion + "\'" +
               ", genero='" + genero + "\'" +
               ", edad=" + edad + "\'" +
               ", contrasenya=" + contrasenya + 
               "}";
   }

}