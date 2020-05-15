package es.uji.ei1027.majorsACasa.services;

import org.springframework.beans.factory.annotation.Autowired;

import es.uji.ei1027.majorsACasa.dao.PeticionDao;
import es.uji.ei1027.majorsACasa.model.Peticion;

public class PeticionSrv implements PeticionService{
	
	@Autowired
	PeticionDao peticionDao;

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
}
