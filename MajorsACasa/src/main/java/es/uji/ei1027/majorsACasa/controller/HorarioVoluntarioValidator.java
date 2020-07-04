package es.uji.ei1027.majorsACasa.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei1027.majorsACasa.model.HorarioVoluntario;


public class HorarioVoluntarioValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		  return HorarioVoluntario.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Nadador.class.isAssignableFrom(cls);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		HorarioVoluntario horarioVoluntario = (HorarioVoluntario)obj;
		
		if (horarioVoluntario.getIdVoluntario().trim().equals(""))
			 errors.rejectValue("idVoluntario", "obligatorio", "Introduzca un Id de voluntario.");
		
	}
}