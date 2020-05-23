package es.uji.ei1027.majorsACasa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.ei1027.majorsACasa.dao.BeneficiarioDao;
import es.uji.ei1027.majorsACasa.dao.PeticionDao;
import es.uji.ei1027.majorsACasa.model.Peticion;

@Service
public class PeticionSrv implements PeticionService{
	
	@Autowired
	PeticionDao peticionDao;
	
	@Autowired
	BeneficiarioDao beneficarioDao;

	@Override
	public String getEstado(Peticion peticion) {
		String estado = null;
		if(peticion.getFechaAprobacion() != null) {
			estado = "Aceptada";
		}
		else {
			if(peticion.getFechaDenegacion() != null) {
				estado = "Denegada";
			}
			else {
				estado = "NoRevisada";
			}
		}
		
		return estado;
	}

	@Override
	public List<Peticion> getPeticionesBeneficario(String idBeneficiario) {
		List<Peticion> lista = peticionDao.getPeticionBeneficiario(idBeneficiario);
		return lista;
	} 
}
