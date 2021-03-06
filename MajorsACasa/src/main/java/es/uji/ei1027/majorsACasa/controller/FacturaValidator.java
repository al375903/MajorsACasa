package es.uji.ei1027.majorsACasa.controller;

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
		if (factura.getIdFactura().trim().equals(""))
			 errors.rejectValue("idFactura", "obligatorio", "Introduzca un Id.");
		
		if (factura.getIdBeneficiario().trim().equals(""))
			 errors.rejectValue("idBeneficiario", "obligatorio", "Introduzca un Id.");
	}
}