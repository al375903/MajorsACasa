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
		if (beneficiario.getIdBeneficiario().trim().length() < 9) {
			errors.rejectValue("idBeneficiario", "obligatorio",
					"Introduzca un id de 9 caracteres.");
		}
		
		if (beneficiario.getNombre().trim().equals("")) {
			errors.rejectValue("nombre", "obligatorio",
					"Introduzca su nombre.");
		}
		
		if (beneficiario.getEmail().trim().equals("")) {
			errors.rejectValue("email", "obligatorio",
					"Introduzca su e-mail.");
		} else if (!beneficiario.getEmail().trim().contains("@")) {
			errors.rejectValue("email", "arroba", "La dirección debe llevar @.");
		}
		
		if (beneficiario.getDireccion().trim().equals("")) {
			errors.rejectValue("direccion", "obligatorio",
					"Introduzca su dirección.");
		}
		
		List<String> valores = Arrays.asList("Femenino", "Masculino");
		if (!valores.contains(beneficiario.getGenero())) {
			errors.rejectValue("genero", "valor incorrecto",
					"Seleccione un género.");
		}
		
		if (beneficiario.getEdad()<55) {
			errors.rejectValue("edad", "incorrecta",
					"Debe tener 55 años o más.");
		}
		
		if (beneficiario.getContrasenya().trim().equals("")) {
			errors.rejectValue("contrasenya", "obligatorio",
					"Introduzca una contraseña.");
		}
	}
}