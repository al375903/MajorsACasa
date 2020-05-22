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

import es.uji.ei1027.majorsACasa.dao.ContratoDao;
import es.uji.ei1027.majorsACasa.dao.EmpresaDao;
import es.uji.ei1027.majorsACasa.model.Contrato;
import es.uji.ei1027.majorsACasa.model.Empresa;
import es.uji.ei1027.majorsACasa.services.ContratoService;

@Controller
@RequestMapping("/contrato")
public class ContratoController {
	
	private ContratoDao contratoDao;
	public EmpresaDao empresaDao;
	private ContratoService contratoService;
	
	@Autowired
	public void setContratoService(ContratoService contratoService) {
		this.contratoService = contratoService;
	}
	
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
		
		try {
			contratoDao.addContrato(contrato);
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
			"Ya existe un contrato " + contrato.getIdContrato(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"La empresa introducida no existe.", "ErrorAccediendoDatosEmpresa");
		}
		
		return "redirect:list";
	}
	
	@RequestMapping(value="addEmpresaPorContrato")
	public String addEmpresaPorContrato(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "contrato/addEmpresaPorContrato";
	}
   
   @RequestMapping(value="/addEmpresaPorContrato", method = RequestMethod.POST) 
   public String processAddSubmitPorContrato(@ModelAttribute("empresa") Empresa empresa,
                                   BindingResult bindingResult, HttpSession session) {  
	   EmpresaValidator empresaValidator = new EmpresaValidator();
	   empresaValidator.validate(empresa, bindingResult);
	   if (bindingResult.hasErrors()) 
   		return "contrato/addEmpresaPorContrato";
	   try {
		   contratoDao.addEmpresa(empresa);
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
					"Ya existe una empresa con id " + empresa.getIdEmpresa(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"Error en el acceso a la base de datos", "ErrorAccediendoDatos");
		}
   	 return "redirect:add";
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
	
	
	@RequestMapping("/portipo/{idEmpresa}")
	public String listContrPorTipo(Model model, @PathVariable String idEmpresa) {
		model.addAttribute("contratos", contratoService.getContratoByTipo(idEmpresa));
		model.addAttribute("idEmpresa", idEmpresa);
		return "contrato/portipo";
	}
}
