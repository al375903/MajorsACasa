package es.uji.ei1027.majorsACasa.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String listEmpresa(Model model) {
		model.addAttribute("empresas", empresaDao.getEmpresas());
		return "empresa/list";
	}
	
	@RequestMapping(value="add")
	public String addEmpresa(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "empresa/add";
	}
   
   @RequestMapping(value="/add", method=RequestMethod.POST) 
   public String processAddSubmit(@ModelAttribute("empresa") Empresa empresa,
                                   BindingResult bindingResult) {  
   	 if (bindingResult.hasErrors()) 
   			return "empresa/add";
   	 empresaDao.addEmpresa(empresa);
   	 return "redirect:list"; 
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
		 if (bindingResult.hasErrors()) 
			 return "empresa/update";
		 empresaDao.updateEmpresa(empresa);
		 return "redirect:list"; 
	}
  
  @RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id) {
         empresaDao.deleteEmpresa(id);
         return "redirect:../list"; 
	}
  
  // Esto es una prueba

}
