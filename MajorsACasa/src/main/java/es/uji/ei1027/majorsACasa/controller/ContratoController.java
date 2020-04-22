package es.uji.ei1027.majorsACasa.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.majorsACasa.dao.ContratoDao;
import es.uji.ei1027.majorsACasa.model.Contrato;

@Controller
@RequestMapping("/contrato")
public class ContratoController {
	
	private ContratoDao contratoDao;
	
	@Autowired
	public void setContratoDao(ContratoDao contratoDao) {
		this.contratoDao = contratoDao;
	}
	
	@RequestMapping("/list")
	public String listEmpresas(Model model) {
		model.addAttribute("contratos", contratoDao.getContratos());
		return "contrato/list";
	}
	
	@RequestMapping(value="add")
	public String addContrato(Model model) {
		model.addAttribute("contrato", new Contrato());
		return "contrato/add";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("contrato") Contrato contrato,
									BindingResult bindingResult) {
		ContratoValidator contratoValidator = new ContratoValidator();
		contratoValidator.validate(contrato, bindingResult);
		if (bindingResult.hasErrors())
			return "contrato/add";
		contratoDao.addContrato(contrato);
		return "redirect:list";
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.GET)
	public String editContrato(Model model, @PathVariable String id) {
		model.addAttribute("contrato", contratoDao.getContrato(id));
		return "contrato/update";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("contrato") Contrato contrato,
										BindingResult bindingResult) {
		ContratoValidator contratoValidator = new ContratoValidator();
		contratoValidator.validate(contrato, bindingResult);
		if (bindingResult.hasErrors())
			return "contrato/update";
		contratoDao.updateContrato(contrato);
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id) {
		contratoDao.deleteContrato(id);
		return "redirect:../list";
	}
	
}
