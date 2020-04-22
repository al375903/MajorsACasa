package es.uji.ei1027.majorsACasa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.majorsACasa.dao.LineaFacturaDao;
import es.uji.ei1027.majorsACasa.model.LineaFactura;

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
		if(bindingResult.hasErrors())
			return "lineaFactura/add";
		lineaFacturaDao.addLineaFactura(lineaFactura);
		return "redirect:list";
	}
	
	// No están los métodos para modificar líneas de factura ya que no pueden ser modificadas
	// (los tres atributos forman la clave primaria)
	
	
	// NOTA: REVISAR ESTE MÉTODO
	@RequestMapping(value="/delete/{idF},{idP},{c}")
	public String processDelete(@PathVariable String idF, @PathVariable String idP, @PathVariable String c) {
		lineaFacturaDao.deleteLineaFactura(idF, idP, c);
		return "redirect:../list";
	}
	
}





