package es.uji.ei1027.majorsACasa.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MajorsACasaControllerAdvice {
	
	@ExceptionHandler(value = MajorsACasaException.class)
	public ModelAndView handleClubException(MajorsACasaException ex){
		ModelAndView mav = new ModelAndView("error/exceptionError");
		if(ex.getErrorName().equals("ErrorAccediendoDatosEmpresa")){
			mav = new ModelAndView("error/exceptionErrorContrato");
		}
		if(ex.getErrorName().equals("ErrorAccediendoDatosHorario")){
			mav = new ModelAndView("error/exceptionErrorHorario");
		}
		mav.addObject("message", ex.getMessage());
	    mav.addObject("errorName", ex.getErrorName());
	    return mav;
	}
}
