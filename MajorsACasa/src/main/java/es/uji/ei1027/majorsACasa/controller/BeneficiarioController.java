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
import es.uji.ei1027.majorsACasa.model.Beneficiario;
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
		beneficiarioDao.deleteBeneficiario(id);
		return "redirect:../list";
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
