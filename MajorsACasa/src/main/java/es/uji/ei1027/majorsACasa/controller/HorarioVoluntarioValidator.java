package es.uji.ei1027.majorsACasa.controller;


import java.util.Arrays;
import java.util.List;

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
		
		if (horarioVoluntario.getIdHorario().trim().equals(""))
			 errors.rejectValue("idHorarioVoluntario", "obligatorio", "Introduzca un Id.");
		
		if (horarioVoluntario.getIdVoluntario().trim().equals(""))
			 errors.rejectValue("idVoluntario", "obligatorio", "Introduzca un Id.");
		
		if (horarioVoluntario.getIdBeneficiario().trim().equals(""))
			 errors.rejectValue("idBeneficiario", "obligatorio", "Introduzca un Id.");
	}
}