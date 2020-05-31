package es.uji.ei1027.majorsACasa.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.uji.ei1027.majorsACasa.model.Peticion;


public class PeticionBeneficiarioValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		  return Peticion.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Nadador.class.isAssignableFrom(cls);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Peticion peticion = (Peticion)obj;
		
		if (peticion.getIdBeneficiario().trim().equals(""))
			 errors.rejectValue("idBeneficiario", "obligatorio", "Introduzca su DNI.");
		
		List<String> valores = Arrays.asList("Comida a domicilio", "Servicio de limpieza", "Servicio sanitario");
		if(!valores.contains(peticion.getTipoServicio()))
			 errors.rejectValue("tipoServicio", "obligatorio", "Seleccione un tipo de servicio.");
		
	}
}