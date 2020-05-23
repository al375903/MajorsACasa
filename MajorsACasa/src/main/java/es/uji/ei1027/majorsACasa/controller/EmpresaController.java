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
import es.uji.ei1027.majorsACasa.model.Accion;
import es.uji.ei1027.majorsACasa.model.Contrato;
import es.uji.ei1027.majorsACasa.model.Empresa;
import es.uji.ei1027.majorsACasa.model.UserDetails;
import es.uji.ei1027.majorsACasa.services.ContratoService;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
	
	private EmpresaDao empresaDao;
	
	@Autowired
	public void setEmpresaDao(EmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}
	
	private ContratoDao contratoDao;
	
	@Autowired
	public void setContratoDao(ContratoDao contratoDao) {
		this.contratoDao = contratoDao;
	}
	
	private ContratoService contratoService;
	
	@Autowired
	public void setContratoService(ContratoService contratoService) {
		this.contratoService = contratoService;
	}
	
	@RequestMapping("/list")
	public String listEmpresas(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casCommittee"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "empresa/list");
          return "login";
        }
		model.addAttribute("empresas", empresaDao.getEmpresas());
		return "empresa/list";
	}
	
	@RequestMapping(value="add")
	public String addEmpresa(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casCommittee"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "empresa/add");
          return "login";
        }
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
	public String editEmpresa(HttpSession session, Model model, @PathVariable String id) { 
	   UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casCommittee"))) { 
         model.addAttribute("user", new UserDetails());
         session.setAttribute("nextUrl", "empresa/update");
         return "login";
       }
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
	public String processDelete(HttpSession session, Model model, @PathVariable String id) {
	  try {
		    UserDetails user = (UserDetails)session.getAttribute("user");
		    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casCommittee"))) { 
	          model.addAttribute("user", new UserDetails());
	          session.setAttribute("nextUrl", "redirect:../list");
	          return "login";
	        }
		    if (session.getAttribute("accion") == null) {
		    	model.addAttribute("accion", new Accion());
		    	session.setAttribute("ruta", "/empresa/delete/"+id);
		    	return "confirm"; 
		    } else {
			    Accion accion=(Accion) session.getAttribute("accion");
		    	session.removeAttribute("accion");
		    	if(accion.getConfirmacion() != null && accion.getConfirmacion().equals("True")) {
			    	empresaDao.deleteEmpresa(id);
		    	}
		    }
		} catch (DataAccessException e) {//DataIntegrityViolationException
			throw new MajorsACasaException(
					"Esta empresa aún tiene contratos pendientes, no se puede eliminar", "ContratosPendientes");
		}
	  return "redirect:../list";
	}
  
  @RequestMapping("/index")
	public String indexBeneficiario(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("empresa"))) { 
        model.addAttribute("user", new UserDetails());
        session.setAttribute("nextUrl", "empresa/index");
        return "login";
      }
		return "empresa/index";
	}
	
	@RequestMapping("/contratos")
	public String listPeticionesBeneficiario(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe")  || user.getTipo().equals("empresa"))) { 
        model.addAttribute("user", new UserDetails());
        session.setAttribute("nextUrl", "empresa/contratos");
        return "login";
      }
		model.addAttribute("contratos", contratoService.getContratosDeUnaEmpresa("Empresa SA")); //user.getUsername();
		return "empresa/contratos";
	}
	
	@RequestMapping(value="/updateContrato/{id}", method = RequestMethod.GET)
	public String editContrato(HttpSession session, Model model, @PathVariable String id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("empresa"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "empresa/updateContrato");
          return "login";
        }
		model.addAttribute("contrato", contratoDao.getContrato(id));
		return "empresa/updateContrato";
	}
	
	@RequestMapping(value="/updateContrato", method = RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("contrato") Contrato contrato,
										BindingResult bindingResult) {
		ContratoValidator contratoValidator = new ContratoValidator();
		contratoValidator.validate(contrato, bindingResult);
		if (bindingResult.hasErrors())
			return "empresa/updateContrato";
		contratoDao.updateContrato(contrato);
		return "redirect:contratos";
	}
	
	@RequestMapping(value="/deleteContrato/{id}") 
	public String processDeleteContrato(HttpSession session, Model model, @PathVariable String id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("empresa"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "redirect:../contratos");
          return "login";
        }
	    if (session.getAttribute("accion") == null) {
	    	model.addAttribute("accion", new Accion());
	    	session.setAttribute("ruta", "/empresa/deleteContrato/"+id);
	    	return "confirm"; 
	    } else {
		    Accion accion=(Accion) session.getAttribute("accion");
	    	session.removeAttribute("accion");
	    	if(accion.getConfirmacion() != null && accion.getConfirmacion().equals("True")) {
		    	contratoDao.deleteContrato(id);
	    	}
	    }
    	return "redirect:../contratos";
	}
}
