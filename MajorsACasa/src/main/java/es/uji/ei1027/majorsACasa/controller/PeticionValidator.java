package es.uji.ei1027.majorsACasa.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.uji.ei1027.majorsACasa.model.Peticion;


public class PeticionValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		  return Peticion.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Nadador.class.isAssignableFrom(cls);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Peticion peticion = (Peticion)obj;
		
		if (peticion.getIdPeticion().trim().equals(""))
			 errors.rejectValue("idPeticion", "obligatorio", "Introduzca un Id.");
		
		if (peticion.getIdBeneficiario().trim().equals(""))
			 errors.rejectValue("idBeneficiario", "obligatorio", "Introduzca un Id.");
		
		if (peticion.getIdContrato().trim().equals(""))
			 errors.rejectValue("idContrato", "obligatorio", "Introduzca un Id.");
		
		List<String> valores = Arrays.asList("Comida a domicilio", "Servicio de limpieza", "Servicio sanitario");
		if(!valores.contains(peticion.getTipoServicio()))
			 errors.rejectValue("tipoServicio", "obligatorio", "Seleccione un tipo de servicio.");
		
		if(peticion.getEstado().equals("Aceptada")) {
			if(peticion.getFechaAprobacion() == null) {
				errors.rejectValue("fechaAprobacion", "obligatorio", "Seleccione una fecha de aprobación.");
			}
			if(peticion.getFechaCancelacion() != null) {
				errors.rejectValue("fechaCancelacion", "noCancelacion", "No puede seleccionar una fecha de cancelación.");
			}
			if(peticion.getFechaDenegacion() != null) {
				errors.rejectValue("fechaDenegacion", "noDenegacion", "No puede seleccionar una fecha de denegación.");
			}
		}
		
		if(peticion.getEstado().equals("Denegada")) {
			if(peticion.getFechaDenegacion() == null) {
				errors.rejectValue("fechaDenegacion", "obligatorio", "Seleccione una fecha de denegación.");
			}
			if(peticion.getFechaAprobacion() != null) {
				errors.rejectValue("fechaAprobacion", "noAprobación", "No puede seleccionar una fecha de aprobación.");
			}
			if(peticion.getFechaCancelacion() != null) {
				errors.rejectValue("fechaCancelacion", "noCancelacion", "No puede seleccionar una fecha de cancelación.");
			}
		}
		
		if(peticion.getEstado().equals("No revisada")) {
			if(peticion.getFechaDenegacion() != null) {
				errors.rejectValue("fechaDenegacion", "noDenegación", "No puede seleccionar una fecha de denegación.");
			}
			if(peticion.getFechaAprobacion() != null) {
				errors.rejectValue("fechaAprobacion", "noAprobación", "No puede seleccionar una fecha de aprobación.");
			}
		}
		/*List<String> estados = Arrays.asList("Aceptada", "Denegada", "No revisada");
		if(!estados.contains(peticion.getTipoServicio()))
			 errors.rejectValue("estado", "obligatorio", "Seleccione un estado.");*/
	}
}