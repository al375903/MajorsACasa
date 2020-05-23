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

import es.uji.ei1027.majorsACasa.dao.FacturaDao;
import es.uji.ei1027.majorsACasa.model.Accion;
import es.uji.ei1027.majorsACasa.model.Factura;
import es.uji.ei1027.majorsACasa.model.UserDetails;

@Controller
@RequestMapping("/factura")
public class FacturaController {

	private FacturaDao facturaDao;
	
	@Autowired
	public void setFacturaDao(FacturaDao facturaDao) {
		this.facturaDao = facturaDao;
	}
	
	@RequestMapping("/list")
	public String listFacturas(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "factura/list");
          return "login";
        }
		model.addAttribute("facturas", facturaDao.getFacturas());
		return "factura/list";
	}
	
	@RequestMapping(value="add")
	public String addFactura(Model model) {
		model.addAttribute("factura", new Factura());
		return "factura/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("factura") Factura factura,
									BindingResult bindingResult) {
		FacturaValidator facturaValidator = new FacturaValidator();
		facturaValidator.validate(factura, bindingResult);
		if(bindingResult.hasErrors())
			return "factura/add";
		try {
			facturaDao.addFactura(factura);
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
					"Ya existe una factura con id " + factura.getIdFactura(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"No existe un beneficiario con id " + factura.getIdBeneficiario(), "ErrorAccediendoDatos");
		}
		return "redirect:list";
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.GET) 
	public String editFactura(Model model, @PathVariable String id) { 
		model.addAttribute("factura", facturaDao.getFactura(id));
		return "factura/update"; 
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("factura") Factura factura,
										BindingResult bindingResult) {
		FacturaValidator facturaValidator = new FacturaValidator();
		facturaValidator.validate(factura, bindingResult);
		if(bindingResult.hasErrors())
			return "factura/update";
		facturaDao.updateFactura(factura);
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(HttpSession session, Model model, @PathVariable String id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "redirect:../list");
          return "login";
        }
	    if (session.getAttribute("accion") == null) {
	    	model.addAttribute("accion", new Accion());
	    	session.setAttribute("ruta", "/factura/delete/"+id);
	    	return "confirm"; 
	    } else {
		    Accion accion=(Accion) session.getAttribute("accion");
	    	session.removeAttribute("accion");
	    	if(accion.getConfirmacion() != null && accion.getConfirmacion().equals("True")) {
	    		facturaDao.deleteFactura(id);
	    	}
	    }
		return "redirect:../list";
	}
}





