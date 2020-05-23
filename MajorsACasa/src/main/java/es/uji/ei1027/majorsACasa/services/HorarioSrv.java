package es.uji.ei1027.majorsACasa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.ei1027.majorsACasa.dao.HorarioVoluntarioDao;
import es.uji.ei1027.majorsACasa.model.HorarioVoluntario;

@Service
public class HorarioSrv implements HorarioService {

	@Autowired
	HorarioVoluntarioDao horarioVoluntarioDao;
	
	@Override
	public List<HorarioVoluntario> getHorariosDeUnVoluntario(String idVoluntario) {
		List<HorarioVoluntario> lista = horarioVoluntarioDao.getHorariosDeUnVoluntario(idVoluntario);
		return lista;
	}

}
