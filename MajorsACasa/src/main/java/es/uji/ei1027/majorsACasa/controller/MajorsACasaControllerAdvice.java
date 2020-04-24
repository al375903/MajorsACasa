package es.uji.ei1027.majorsACasa.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MajorsACasaControllerAdvice {
	
	@ExceptionHandler(value = MajorsACasaException.class)
	public ModelAndView handleClubException(MajorsACasaException ex){
		ModelAndView mav = new ModelAndView("error/exceptionError");
		mav.addObject("message", ex.getMessage());
	    mav.addObject("errorName", ex.getErrorName());
	    return mav;
	}
}
