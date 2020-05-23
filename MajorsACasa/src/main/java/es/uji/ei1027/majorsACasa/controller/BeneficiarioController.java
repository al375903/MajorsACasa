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

import es.uji.ei1027.majorsACasa.dao.BeneficiarioDao;
import es.uji.ei1027.majorsACasa.dao.PeticionDao;
import es.uji.ei1027.majorsACasa.model.Accion;
import es.uji.ei1027.majorsACasa.model.Beneficiario;
import es.uji.ei1027.majorsACasa.model.Peticion;
import es.uji.ei1027.majorsACasa.model.UserDetails;
import es.uji.ei1027.majorsACasa.services.PeticionService;

@Controller
@RequestMapping("/beneficiario")
public class BeneficiarioController {
	
	private BeneficiarioDao beneficiarioDao;
	
	@Autowired
	public void setBeneficiarioDao(BeneficiarioDao beneficiarioDao) {
		this.beneficiarioDao = beneficiarioDao;
	}
	
	private PeticionDao peticionDao;
	
	@Autowired
	public void setPeticionDao(PeticionDao peticionDao) {
		this.peticionDao = peticionDao;
	}
	
	private PeticionService peticionService;
	
	@Autowired
	public void setPeticionService(PeticionService peticionService) {
		this.peticionService = peticionService;
	}

	@RequestMapping("/list")
	public String listBeneficiarios(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "beneficiario/list");
          return "login";
        }
		model.addAttribute("beneficiarios", beneficiarioDao.getBeneficiarios());
		return "beneficiario/list";
	}
	
	@RequestMapping(value="add")
	public String addBeneficiario(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "beneficiario/add");
          return "login";
        }
		model.addAttribute("beneficiario", new Beneficiario());
		return "beneficiario/add";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("beneficiario") Beneficiario beneficiario,
									BindingResult bindingResult) {
		BeneficiarioValidator beneficiarioValidator = new BeneficiarioValidator();
		beneficiarioValidator.validate(beneficiario, bindingResult);
		if (bindingResult.hasErrors())
			return "beneficiario/add";
		try {
			beneficiarioDao.addBeneficiario(beneficiario);
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
					"Ya existe un beneficiario con id " + beneficiario.getIdBeneficiario(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"Error en el acceso a la base de datos", "ErrorAccediendoDatos");
		}
		return "redirect:list";
	}
	
	@RequestMapping(value="addPeticion")
	public String addPeticion(Model model) {
		model.addAttribute("peticion", new Peticion());
		return "beneficiario/addPeticion";
	}
	
	@RequestMapping(value="/addPeticion", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("peticion") Peticion peticion,
									BindingResult bindingResult) {
		PeticionValidator peticionValidator = new PeticionValidator();
		peticionValidator.validate(peticion, bindingResult);
		if(bindingResult.hasErrors())
			return "beneficiario/addPeticion";
		try {
			for(Peticion peticion2 : peticionDao.getPeticiones()) {
				if(peticion2.getIdBeneficiario().equals(peticion.getIdBeneficiario())) {
					if(peticion2.getEstado().equals("Aceptada") || peticion2.getEstado().equals("NoRevisada")) {
						throw new MajorsACasaException(
								"No puede realizar una nueva petición teniendo una petición aceptada o por revisar", "ErrorCrearPetición");
					}
				}
			}
			peticionDao.addPeticion(peticion);
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
					"Ya existe una petición con id " + peticion.getIdPeticion(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"Error en el acceso a la base de datos", "ErrorAccediendoDatos");
		}
		return "redirect:index";
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.GET) 
	public String editBeneficiario(HttpSession session, Model model, @PathVariable String id) { 
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("beneficiario"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "beneficiario/update");
          return "login";
        }
		model.addAttribute("beneficiario", beneficiarioDao.getBeneficiario(id));
		return "beneficiario/update"; 
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("beneficiario") Beneficiario beneficiario,
										BindingResult bindingResult) {
		BeneficiarioValidator beneficiarioValidator = new BeneficiarioValidator();
		beneficiarioValidator.validate(beneficiario, bindingResult);
		if (bindingResult.hasErrors())
			return "beneficiario/update";
		beneficiarioDao.updateBeneficiario(beneficiario);
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(HttpSession session, Model model, @PathVariable String id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("beneficiario"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "redirect:../list");
          return "login";
        }
	    if (session.getAttribute("accion") == null) {
	    	model.addAttribute("accion", new Accion());
	    	session.setAttribute("ruta", "/beneficiario/delete/"+id);
	    	return "confirm"; 
	    } else {
		    Accion accion=(Accion) session.getAttribute("accion");
	    	session.removeAttribute("accion");
	    	if(accion.getConfirmacion() != null && accion.getConfirmacion().equals("True")) {
	    		beneficiarioDao.deleteBeneficiario(id);
	    	}
	    }
		return "redirect:../list";
	}
	
	@RequestMapping("/index")
	public String indexBeneficiario(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("beneficiario"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "beneficiario/index");
          return "login";
        }
		return "beneficiario/index";
	}
	
	@RequestMapping("/peticiones")
	public String listPeticionesBeneficiario(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe")  || user.getTipo().equals("beneficiario"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "beneficiario/peticiones");
          return "login";
        }
		model.addAttribute("peticiones", peticionService.getPeticionesBeneficario("20902106M")); //user.getUsername();
		return "beneficiario/peticiones";
	}
}
