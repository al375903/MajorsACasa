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
	public String listEmpresas(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casCommittee"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "contrato/list");
          return "login";
        }
		model.addAttribute("contratos", contratoDao.getContratos());
		return "contrato/list";
	}
	
	@RequestMapping(value="add")
	public String addContrato(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casCommittee"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "contrato/add");
          return "login";
        }
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
	public String addEmpresaPorContrato(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casCommittee"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "contrato/addEmpresaPorContrato");
          return "login";
        }
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
	public String editContrato(HttpSession session, Model model, @PathVariable String id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casCommittee"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "contrato/update");
          return "login";
        }
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
	
	@RequestMapping(value="/delete/{id}") //Sin acabar confirm
	public String processDelete(HttpSession session, Model model, @PathVariable String id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casCommittee"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "redirect:../list");
          return "login";
        }
	    if (session.getAttribute("accion") == null) {
	    	model.addAttribute("accion", new Accion());
	    	session.setAttribute("ruta", "/contrato/delete/"+id);
	    	return "confirm"; 
	    } else {
		    Accion accion=(Accion) session.getAttribute("accion");
	    	session.removeAttribute("accion");
	    	if(accion.getConfirmacion() != null && accion.getConfirmacion().equals("True")) {
		    	contratoDao.deleteContrato(id);
	    	}
	    }
    	return "redirect:../list";
	}
	
	
	@RequestMapping("/portipo/{idEmpresa}")
	public String listContrPorTipo(HttpSession session, Model model, @PathVariable String idEmpresa) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casCommittee"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "contrato/portipo");
          return "login";
        }
		model.addAttribute("contratos", contratoService.getContratoByTipo(idEmpresa));
		model.addAttribute("idEmpresa", idEmpresa);
		return "contrato/portipo";
	}
}
