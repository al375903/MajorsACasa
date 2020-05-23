package es.uji.ei1027.majorsACasa.services;

import java.util.List;

import es.uji.ei1027.majorsACasa.model.Peticion;

public interface PeticionService {

	public String getEstado(Peticion peticion);
	public List<Peticion> getPeticionesBeneficario(String idBeneficiario);
}
