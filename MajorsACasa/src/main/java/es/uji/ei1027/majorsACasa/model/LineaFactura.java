package es.uji.ei1027.majorsACasa.model;

public class LineaFactura {
	private String idFactura;
	private String idPeticion;
	private String codigoLinea;
	
	public LineaFactura() {
	}

	public String getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}

	public String getIdPeticion() {
		return idPeticion;
	}

	public void setIdPeticion(String idPeticion) {
		this.idPeticion = idPeticion;
	}

	public String getCodigoLinea() {
		return codigoLinea;
	}

	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}

	@Override
	public String toString() {
		return "LineaFactura [idFactura=" + idFactura 
				+ ", idPeticion=" + idPeticion 
				+ ", codigoLinea=" + codigoLinea + "]";
	}
	
}
