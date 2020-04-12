package es.uji.ei1027.majorsACasa.model;

public class Empresa {
	private String idEmpresa;
	private String nombreEmpresa;
	private String nombreManager;
	private String direccion;
	private String telefono;
	private String horarioAtencionCliente;
	private String emailManager;
	private String tipoServicio;
	
	public Empresa() {
	}
	
	public String getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	public String getNombreManager() {
		return nombreManager;
	}
	
	public void setNombreManager(String nombreManager) {
		this.nombreManager = nombreManager;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getHorarioAtencionCliente() {
		return horarioAtencionCliente;
	}
	
	public void setHorarioAtencionCliente(String horarioatencionCliente) {
		this.horarioAtencionCliente = horarioatencionCliente;
	}
	
	public String getEmailManager() {
		return emailManager;
	}
	
	public void setEmailManager(String emailManager) {
		this.emailManager = emailManager;
	}
	
	public String getTipoServicio() {
		return tipoServicio;
	}
	
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	@Override
	public String toString() {
		return "Empresa [idEmpresa=" + idEmpresa + ", nombreEmpresa=" + nombreEmpresa + ", nombreManager="
				+ nombreManager + ", direccion=" + direccion + ", telefono=" + telefono + ", horarioatencionCliente="
				+ horarioAtencionCliente + ", emailManager=" + emailManager + ", tipoServicio=" + tipoServicio + "]";
	}
	
}
