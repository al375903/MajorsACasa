package es.uji.ei1027.majorsACasa.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.uji.ei1027.majorsACasa.model.Beneficiario;


public class BeneficiarioValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		  return Beneficiario.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Nadador.class.isAssignableFrom(cls);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Beneficiario beneficiario = (Beneficiario)obj;
		
		if (beneficiario.getNombre().trim().equals("")) {
			errors.rejectValue("nombre", "obligatorio",
					"Introduce tu nombre.");
		}
	}
}