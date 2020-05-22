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

import es.uji.ei1027.majorsACasa.dao.EmpresaDao;
import es.uji.ei1027.majorsACasa.model.Empresa;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
	
	private EmpresaDao empresaDao;
	
	@Autowired
	public void setEmpresaDao(EmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}
	
	@RequestMapping("/list")
	public String listEmpresas(Model model) {
		model.addAttribute("empresas", empresaDao.getEmpresas());
		return "empresa/list";
	}
	
	@RequestMapping(value="add")
	public String addEmpresa(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "empresa/add";
	}
   
   @RequestMapping(value="/add", method = RequestMethod.POST) 
   public String processAddSubmit(@ModelAttribute("empresa") Empresa empresa,
                                   BindingResult bindingResult, HttpSession session) {  
	   EmpresaValidator empresaValidator = new EmpresaValidator();
	   empresaValidator.validate(empresa, bindingResult);
	   if (bindingResult.hasErrors()) 
   		return "empresa/add";
	   try {
		   empresaDao.addEmpresa(empresa);
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
					"Ya existe una empresa con id " + empresa.getIdEmpresa(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"Error en el acceso a la base de datos", "ErrorAccediendoDatos");
		}
	   // Torna a la pàgina principal
       //return "redirect:/";
       String nextUrl = "list";
       /*if(session.getAttribute("nextUrl") != null) {
	       	nextUrl = (String) session.getAttribute("nextUrl");
	       	session.removeAttribute("nextUrl");
       }*/
   	 return "redirect:" + nextUrl;
	}
   
   
   @RequestMapping(value="/update/{id}", method = RequestMethod.GET) 
	public String editEmpresa(Model model, @PathVariable String id) { 
		model.addAttribute("empresa", empresaDao.getEmpresa(id));
		return "empresa/update"; 
	}
  
  @RequestMapping(value="/update", method = RequestMethod.POST) 
	public String processUpdateSubmit(
                          @ModelAttribute("empresa") Empresa empresa, 
                          BindingResult bindingResult) {
	  EmpresaValidator empresaValidator = new EmpresaValidator();
	  empresaValidator.validate(empresa, bindingResult);
	  if (bindingResult.hasErrors()) 
			 return "empresa/update";
		 empresaDao.updateEmpresa(empresa);
		 return "redirect:list"; 
	}
  
  @RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id) {
	  try {
		 empresaDao.deleteEmpresa(id);
		} catch (DataAccessException e) {//DataIntegrityViolationException
			throw new MajorsACasaException(
					"Esta empresa aún tiene contratos pendientes, no se puede eliminar", "ContratosPendientes");
		}
	  return "redirect:../list";
	}
}
