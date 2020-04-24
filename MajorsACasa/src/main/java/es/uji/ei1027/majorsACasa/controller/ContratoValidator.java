package es.uji.ei1027.majorsACasa.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.uji.ei1027.majorsACasa.model.Contrato;


public class ContratoValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		  return Contrato.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Nadador.class.isAssignableFrom(cls);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Contrato contrato = (Contrato)obj;
		if (contrato.getIdContrato().trim().equals(""))
			 errors.rejectValue("idContrato", "obligatorio", "Introduzca un Id.");
		
		if (contrato.getIdEmpresa().trim().equals(""))
			 errors.rejectValue("idEmpresa", "obligatorio", "Introduzca un Id.");
		
		/*if (contrato.getFechaInicio().toString().trim().equals(""))
			 errors.rejectValue("fechaInicio", "obligatorio", "Introduzca una fecha.");
		
		if (contrato.getFechaFin().toString().trim().equals(""))
			 errors.rejectValue("fechaFin", "obligatorio", "Introduzca una fecha.");*/
	}
}