package es.uji.ei1027.majorsACasa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.majorsACasa.dao.FacturaDao;
import es.uji.ei1027.majorsACasa.model.Factura;

@Controller
@RequestMapping("/factura")
public class FacturaController {

	private FacturaDao facturaDao;
	
	@Autowired
	public void setFacturaDao(FacturaDao facturaDao) {
		this.facturaDao = facturaDao;
	}
	
	@RequestMapping("/list")
	public String listFacturas(Model model) {
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
		if(bindingResult.hasErrors())
			return "factura/add";
		facturaDao.addFactura(factura);
		return "redirect:list";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("factura") Factura factura,
										BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "factura/update";
		facturaDao.updateFactura(factura);
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id) {
		facturaDao.deleteFactura(id);
		return "redirect:../list";
	}
}





