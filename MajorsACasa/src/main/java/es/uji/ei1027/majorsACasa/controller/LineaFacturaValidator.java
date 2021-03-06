package es.uji.ei1027.majorsACasa.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.uji.ei1027.majorsACasa.model.LineaFactura;


public class LineaFacturaValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		  return LineaFactura.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Nadador.class.isAssignableFrom(cls);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		LineaFactura lineaFactura = (LineaFactura)obj;
		
		if (lineaFactura.getIdFactura().trim().equals(""))
			 errors.rejectValue("idFactura", "obligatorio", "Introduzca un Id de factura.");
		
		if (lineaFactura.getIdPeticion().trim().equals(""))
			 errors.rejectValue("idPeticion", "obligatorio", "Introduzca un Id de petición.");
		
		if (lineaFactura.getCodigoLinea().trim().equals(""))
			 errors.rejectValue("codigoLinea", "obligatorio", "Introduzca un código de línea.");
	}
}