package es.uji.ei1027.majorsACasa.model;

public class Factura {
	private String idFactura;
	private String idBeneficiario;
	//private Date fechaCreacion;
	//private Date fechaInicio;
	//private Date fechaFin;
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
		return "Factura [idFactura=" + idFactura + ", idBeneficiario=" + idBeneficiario + ", cantidad=" + cantidad
				+ ", concepto=" + concepto + "]";
	}
	
}
