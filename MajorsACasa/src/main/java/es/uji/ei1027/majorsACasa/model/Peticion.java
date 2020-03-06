package es.uji.ei1027.majorsACasa.model;

public class Peticion {
	private String idPeticion;
	private String idBeneficiario;
	private String idContrato;
	private String comentarios;
	private String tipoServicio;
	private String estado;
	//private Date fechaAprobacion;
	//private Date fechaDenegacion;
	//private Date fechaCreacion;
	//private Date fechaCancelacion;
	
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

	@Override
	public String toString() {
		return "Peticion [idPeticion=" + idPeticion + ", idBeneficiario=" + idBeneficiario + ", idContrato="
				+ idContrato + ", comentarios=" + comentarios + ", tipoServicio=" + tipoServicio + ", estado=" + estado
				+ "]";
	}
	
}
