package es.uji.ei1027.majorsACasa.model;

public class Contrato {
	private String idContrato;
	private String idEmpresa;
	private float precio;
	private String tipoServicio;
	//private Date fechaInicio;
	//private Date fechaFin;
	
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

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	@Override
	public String toString() {
		return "Contrato [idContrato=" + idContrato + ", idEmpresa=" + idEmpresa + ", precio=" + precio
				+ ", tipoServicio=" + tipoServicio + "]";
	}
	
}
