package es.uji.ei1027.majorsACasa.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.uji.ei1027.majorsACasa.model.Empresa;


public class EmpresaValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		  return Empresa.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Nadador.class.isAssignableFrom(cls);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Empresa empresa = (Empresa)obj;
		
		if (empresa.getIdEmpresa().trim().equals(""))
			 errors.rejectValue("idEmpresa", "obligatorio", "Introduzca un Id.");
		
		if (empresa.getNombreEmpresa().trim().equals(""))
			 errors.rejectValue("nombreEmpresa", "obligatorio", "Introduzca el nombre de la empresa.");
		
		if (empresa.getNombreManager().trim().equals(""))
			 errors.rejectValue("nombreManager", "obligatorio", "Introduzca el nombre del manager de la empresa.");
		
		List<String> valores = Arrays.asList("Comida a domicilio", "Servicio de limpieza", "Servicio sanitario");
		if(!valores.contains(empresa.getTipoServicio()))
			 errors.rejectValue("tipoServicio", "obligatorio", "Seleccione un tipo de servicio.");
		
		if (empresa.getDireccion().trim().equals(""))
			 errors.rejectValue("direccion", "obligatorio", "Introduzca una dirección.");
		
		if (empresa.getTelefono().trim().length()<12)
			 errors.rejectValue("telefono", "incorrecto", "Introduzca un teléfono válido de 12 dígitos.");
		
		if (empresa.getEmailManager().trim().equals(""))
			 errors.rejectValue("emailManager", "obligatorio", "Introduzca un e-mail.");
		
		if (empresa.getHorarioAtencionCliente().trim().equals(""))
			 errors.rejectValue("horarioAtencionCliente", "obligatorio", "Introduzca la descripción del horario.");
	}
}