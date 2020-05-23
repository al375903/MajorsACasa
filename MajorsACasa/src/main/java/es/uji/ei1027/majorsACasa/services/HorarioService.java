package es.uji.ei1027.majorsACasa.services;

import java.util.List;

import es.uji.ei1027.majorsACasa.model.HorarioVoluntario;



public interface HorarioService {

	public List<HorarioVoluntario> getHorariosDeUnVoluntario(String idVoluntario);

}
