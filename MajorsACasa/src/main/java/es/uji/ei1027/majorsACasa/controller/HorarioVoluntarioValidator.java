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
		
		if (horarioVoluntario.getIdHorario().trim().equals(""))
			 errors.rejectValue("idHorario", "obligatorio", "Introduzca un Id de horario.");
		
		if(horarioVoluntario.getIdHorario().trim().length()<3)
			errors.rejectValue("idHorario", "longitud", "Introduzca un Id de horario de mínimo 3 dígitos.");
		
		if (horarioVoluntario.getIdBeneficiario().trim().equals(""))
			 errors.rejectValue("idHorario", "obligatorio", "Introduzca un Id de beneficiario.");
	}
}