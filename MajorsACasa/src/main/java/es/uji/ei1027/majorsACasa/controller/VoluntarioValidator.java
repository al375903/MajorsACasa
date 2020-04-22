package es.uji.ei1027.majorsACasa.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei1027.majorsACasa.model.Voluntario;


public class VoluntarioValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		  return Voluntario.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Nadador.class.isAssignableFrom(cls);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		 Voluntario voluntario = (Voluntario)obj;
		 if (voluntario.getIdVoluntario().trim().equals(""))
			 errors.rejectValue("idVoluntario", "obligatorio", "Introduzca un Id.");
		 
		 if (voluntario.getIdVoluntario().trim().length() < 9)
			 errors.rejectValue("idVoluntario", "minimo", "Debe tener al menos 9 caracteres.");
		 
		 if (voluntario.getNombre().trim().equals(""))
			 errors.rejectValue("nombre", "obligatorio", "Introduzca un nombre.");
		 
		 if (voluntario.getEmail().trim().equals(""))
			 errors.rejectValue("email", "obligatorio", "Introduzca un e-mail.");
		 
		 if (voluntario.getDireccion().trim().equals(""))
			 errors.rejectValue("direccion", "obligatorio", "Introduzca una direccion.");
		 
		 if (voluntario.getFechaPeticionVoluntariado().equals(null))
			 errors.rejectValue("fechaPeticionVoluntariado", "obligatorio", "Introduzca la fecha de peticion de voluntariado.");
		 
		 if (voluntario.getFechaFin().equals(null))
			 errors.rejectValue("fechaFin", "obligatorio", "Introduzca la fecha fin.");
		 
		 if (voluntario.getDireccion().trim().equals(""))
			 errors.rejectValue("direccion", "obligatorio", "Introduzca una direccion.");
		 
		 List<String> valores = Arrays.asList("Femenino", "Masculino");
		 if(!valores.contains(voluntario.getGenero()))
			 errors.rejectValue("genero", "valor incorrecto", "Seleccione el género.");
		 
		 if(voluntario.getEdad() < 18)
			 errors.rejectValue("edad",  "fuera de rango", "Debe ser igual o mayor a 18.");
		 
		 
	}
}