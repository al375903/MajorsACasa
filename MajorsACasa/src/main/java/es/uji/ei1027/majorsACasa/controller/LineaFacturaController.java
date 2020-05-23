package es.uji.ei1027.majorsACasa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.majorsACasa.dao.LineaFacturaDao;
import es.uji.ei1027.majorsACasa.model.Accion;
import es.uji.ei1027.majorsACasa.model.LineaFactura;
import es.uji.ei1027.majorsACasa.model.UserDetails;

@Controller
@RequestMapping("/lineaFactura")
public class LineaFacturaController {
	
	private LineaFacturaDao lineaFacturaDao;
	
	@Autowired
	public void setLineaFacturaDao(LineaFacturaDao lineaFacturaDao) {
		this.lineaFacturaDao = lineaFacturaDao;
	}
	
	@RequestMapping("/list")
	public String listLineasFactura(Model model) {
		model.addAttribute("lineasFactura", lineaFacturaDao.getLineasFactura());
		return "lineaFactura/list";
	}
	
	@RequestMapping(value="add")
	public String addLineaFactura(Model model) {
		model.addAttribute("lineaFactura", new LineaFactura());
		return "lineaFactura/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("lineaFactura") LineaFactura lineaFactura,
									BindingResult bindingResult) {
		LineaFacturaValidator lineaFacturaValidator = new LineaFacturaValidator();
		lineaFacturaValidator.validate(lineaFactura, bindingResult);
		if(bindingResult.hasErrors())
			return "lineaFactura/add";
		try {
			lineaFacturaDao.addLineaFactura(lineaFactura);
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
					"Ya existe una línea de esta factura con código " + lineaFactura.getCodigoLinea(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"Error en el acceso a la base de datos", "ErrorAccediendoDatos");
		}
		return "redirect:list";
	}
	
	// No están los métodos para modificar líneas de factura ya que no pueden ser modificadas
	// (los tres atributos forman la clave primaria)
	
	
	@RequestMapping(value="/delete/{idF},{idP},{c}")
	public String processDelete(HttpSession session, Model model, @PathVariable String idF, @PathVariable String idP, @PathVariable String c) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "redirect:../list");
          return "login";
        }
	    if (session.getAttribute("accion") == null) {
	    	model.addAttribute("accion", new Accion());
	    	session.setAttribute("ruta", "/lineaFactura/delete/"+idF+","+idP+","+c);
	    	return "confirm"; 
	    } else {
		    Accion accion=(Accion) session.getAttribute("accion");
	    	session.removeAttribute("accion");
	    	if(accion.getConfirmacion() != null && accion.getConfirmacion().equals("True")) {
	    		lineaFacturaDao.deleteLineaFactura(idF, idP, c);
	    	}
	    }
		return "redirect:../list";
	}
}





