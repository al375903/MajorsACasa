package es.uji.ei1027.majorsACasa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.ei1027.majorsACasa.dao.PeticionDao;
import es.uji.ei1027.majorsACasa.model.Peticion;

@Service
public class PeticionSrv implements PeticionService{
	
	@Autowired
	PeticionDao peticionDao;

	@Override
	public List<Peticion> getPeticionesBeneficario(String idBeneficiario) {
		List<Peticion> lista = peticionDao.getPeticionBeneficiario(idBeneficiario);
		return lista;
	} 
}
