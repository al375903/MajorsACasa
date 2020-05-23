package es.uji.ei1027.majorsACasa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.majorsACasa.model.Accion;

@Controller
public class confirmController {

    @RequestMapping("/confirm")
    public String confirm(Model model) {
        model.addAttribute("accion", new Accion());
        return "confirm";
    }

    @RequestMapping(value="/confirm", method=RequestMethod.POST)
    public String checkConfirm(@ModelAttribute("accion") Accion accion,          
                BindingResult bindingResult, HttpSession session) {
        
    	session.setAttribute("accion", accion);//accion = confirmacion
    	String ruta = "/";
        if(session.getAttribute("ruta") != null) { //En este if ruta = nextUrl = delete/id
        	ruta = (String) session.getAttribute("ruta");
        	session.removeAttribute("ruta");
        }
        return "redirect:"+ruta;
    }
}
