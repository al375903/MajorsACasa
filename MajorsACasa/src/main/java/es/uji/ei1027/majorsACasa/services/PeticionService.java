package es.uji.ei1027.majorsACasa.services;

import java.util.List;

import es.uji.ei1027.majorsACasa.model.Peticion;

public interface PeticionService {

	public List<Peticion> getPeticionesBeneficario(String idBeneficiario);
}
