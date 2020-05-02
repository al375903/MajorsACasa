package es.uji.ei1027.majorsACasa.controller;

import java.util.Arrays;
import java.util.List;

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

import es.uji.ei1027.majorsACasa.dao.ContratoDao;
import es.uji.ei1027.majorsACasa.dao.EmpresaDao;
import es.uji.ei1027.majorsACasa.model.Contrato;
import es.uji.ei1027.majorsACasa.services.ContratoService;

@Controller
@RequestMapping("/contrato")
public class ContratoController {
	
	private ContratoDao contratoDao;
	private ContratoService contratoService;
	
	@Autowired
	public void setContratoDao(ContratoDao contratoDao) {
		this.contratoDao = contratoDao;
	}
	
	@Autowired
	public void setContratoService(ContratoService contratoService) {
		this.contratoService = contratoService;
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
		String tipoServicio = contratoService.getEmpresaTipoServicio(contrato.getIdContrato());
		ContratoValidator contratoValidator = new ContratoValidator();
		contratoValidator.validate(contrato, bindingResult);
		if (bindingResult.hasErrors())
			return "contrato/add";
		
		if(!contratoService.getIdEmpresa(contrato.getIdContrato())) {
			return "empresa/add";
		}
		
		try {
			contratoDao.addContrato(contrato, tipoServicio);
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
			"Ya existe un contrato " + contrato.getIdContrato(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"Error en el acceso a la base de datos", "ErrorAccediendoDatos");
		}
		
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
