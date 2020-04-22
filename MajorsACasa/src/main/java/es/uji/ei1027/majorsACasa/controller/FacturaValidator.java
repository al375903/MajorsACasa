package es.uji.ei1027.majorsACasa.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.uji.ei1027.majorsACasa.model.Factura;


public class FacturaValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		  return Factura.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Nadador.class.isAssignableFrom(cls);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Factura factura = (Factura)obj;
	}
}