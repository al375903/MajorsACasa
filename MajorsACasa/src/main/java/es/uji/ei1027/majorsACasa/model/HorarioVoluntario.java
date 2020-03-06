package es.uji.ei1027.majorsACasa.model;

public class HorarioVoluntario {
	private String idHorario;
	private String idVoluntario;
	private String idBeneficiario;
	//private Date fecha;
	//private Time horaInicio;
	//private Time horaFin;
	private boolean libre;
	
	public HorarioVoluntario() {
	}

	public String getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(String idHorario) {
		this.idHorario = idHorario;
	}

	public String getIdVoluntario() {
		return idVoluntario;
	}

	public void setIdVoluntario(String idVoluntario) {
		this.idVoluntario = idVoluntario;
	}

	public String getIdBeneficiario() {
		return idBeneficiario;
	}

	public void setIdBeneficiario(String idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}

	public boolean isLibre() {
		return libre;
	}

	public void setLibre(boolean libre) {
		this.libre = libre;
	}

	@Override
	public String toString() {
		return "HorarioVoluntario [idHorario=" + idHorario + ", idVoluntario=" + idVoluntario + ", idBeneficiario="
				+ idBeneficiario + ", libre=" + libre + "]";
	}
	
}
