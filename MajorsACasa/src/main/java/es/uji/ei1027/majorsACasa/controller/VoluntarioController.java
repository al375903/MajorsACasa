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

import es.uji.ei1027.majorsACasa.dao.HorarioVoluntarioDao;
import es.uji.ei1027.majorsACasa.dao.VoluntarioDao;
import es.uji.ei1027.majorsACasa.model.Accion;
import es.uji.ei1027.majorsACasa.model.HorarioVoluntario;
import es.uji.ei1027.majorsACasa.model.UserDetails;
import es.uji.ei1027.majorsACasa.model.Voluntario;
import es.uji.ei1027.majorsACasa.services.HorarioService;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {

	private VoluntarioDao voluntarioDao;
	
	@Autowired
	public void setVoluntarioDao(VoluntarioDao voluntarioDao) {
		this.voluntarioDao = voluntarioDao;
	}
	
    private HorarioVoluntarioDao horarioVoluntarioDao;
	
	@Autowired
	public void setHorarioVoluntarioDao(HorarioVoluntarioDao horarioVoluntarioDao) {
		this.horarioVoluntarioDao = horarioVoluntarioDao;
	}
	
	private HorarioService horarioService;
	
	@Autowired
	public void setHorarioService(HorarioService horarioService) {
		this.horarioService = horarioService;
	}
	
	@RequestMapping("/list")
	public String listVoluntarios(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casVolunteer"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "voluntario/list");
          return "login";
        }
		model.addAttribute("voluntarios", voluntarioDao.getVoluntarios());
		return "voluntario/list";
	}
	
	@RequestMapping(value="add")
	public String addVoluntario(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casVolunteer"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "voluntario/add");
          return "login";
        }
		model.addAttribute("voluntario", new Voluntario());
		return "voluntario/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("voluntario") Voluntario voluntario,
									BindingResult bindingResult) {
		VoluntarioValidator voluntarioValidator = new VoluntarioValidator();
		voluntarioValidator.validate(voluntario, bindingResult);
		if(bindingResult.hasErrors())
			return "voluntario/add";
		try {
			voluntarioDao.addVoluntario(voluntario);
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
					"Ya existe un voluntario con id " + voluntario.getIdVoluntario(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"Error en el acceso a la base de datos", "ErrorAccediendoDatos");
		}
		return "redirect:list";
	}
	
	@RequestMapping(value="addHorario")
	public String addHorarioVoluntario(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casVolunteer"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "voluntario/addHorario");
          return "login";
        }
		model.addAttribute("horarioVoluntario", new HorarioVoluntario());
		return "voluntario/addHorario";
	}
	
	@RequestMapping(value="/addHorario", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("horarioVoluntario") HorarioVoluntario horarioVoluntario,
									BindingResult bindingResult) {
		HorarioVoluntarioValidator horarioVoluntarioValidator = new HorarioVoluntarioValidator();
		horarioVoluntarioValidator.validate(horarioVoluntario, bindingResult);
		if(bindingResult.hasErrors())
			return "voluntario/addHorario";
		try {
			horarioVoluntarioDao.addHorarioVoluntario(horarioVoluntario);
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
					"Ya existe un horario con id " + horarioVoluntario.getIdHorario(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"Error en el acceso a la base de datos", "ErrorAccediendoDatos");
		}
		return "redirect:index";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editVoluntario(HttpSession session, Model model, @PathVariable String id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casVolunteer"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "voluntario/update");
          return "login";
        }
		model.addAttribute("voluntario", voluntarioDao.getVoluntario(id));
		return "voluntario/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("voluntario") Voluntario voluntario,
										BindingResult bindingResult) {
		VoluntarioValidator voluntarioValidator = new VoluntarioValidator();
		voluntarioValidator.validate(voluntario, bindingResult);
		if(bindingResult.hasErrors())
			return "voluntario/update";
		voluntarioDao.updateVoluntario(voluntario);
		return "redirect:list";
	}
	
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(HttpSession session, Model model, @PathVariable String id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casVolunteer"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "redirect:../list");
          return "login";
        }
	    if (session.getAttribute("accion") == null) {
	    	model.addAttribute("accion", new Accion());
	    	session.setAttribute("ruta", "/voluntario/delete/"+id);
	    	return "confirm"; 
	    } else {
		    Accion accion=(Accion) session.getAttribute("accion");
	    	session.removeAttribute("accion");
	    	if(accion.getConfirmacion() != null && accion.getConfirmacion().equals("True")) {
	    		voluntarioDao.deleteVoluntario(id);
	    	}
	    }
		return "redirect:../list";
	}
	
	@RequestMapping("/index")
	public String indexBeneficiario(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("voluntario"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "voluntario/index");
          return "login";
        }
		return "voluntario/index";
	}
	
	@RequestMapping("/horarios")
	public String listPeticionesBeneficiario(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe")  || user.getTipo().equals("voluntario"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "voluntario/horarios");
          return "login";
        }
		model.addAttribute("horarios", horarioService.getHorariosDeUnVoluntario(user.getUsername()));
		return "voluntario/horarios";
	}
	
	@RequestMapping(value="/updateHorario/{id}", method=RequestMethod.GET)
	public String editHorarioVoluntario(HttpSession session, Model model, @PathVariable String id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("voluntario"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "voluntario/updateHorario");
          return "login";
        }
		model.addAttribute("horarioVoluntario", horarioVoluntarioDao.getHorarioVoluntario(id));
		return "voluntario/updateHorario";
	}
	
	@RequestMapping(value="/updateHorario", method=RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("horarioVoluntario") HorarioVoluntario horarioVoluntario,
										BindingResult bindingResult) {
		HorarioVoluntarioValidator horarioVoluntarioValidator = new HorarioVoluntarioValidator();
		horarioVoluntarioValidator.validate(horarioVoluntario, bindingResult);
		if(bindingResult.hasErrors())
			return "voluntario/updateHorario";
		horarioVoluntarioDao.updateHorarioVoluntario(horarioVoluntario);
		return "redirect:horarios";
	}
	
	@RequestMapping(value="/deleteHorario/{id}")
	public String processDeleteHorario(HttpSession session, Model model, @PathVariable String id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("voluntario"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "voluntario/deleteHorario");
          return "login";
        }
		if (session.getAttribute("accion") == null) {
	    	model.addAttribute("accion", new Accion());
	    	session.setAttribute("ruta", "/voluntario/deleteHorario/"+id);
	    	return "confirm"; 
	    } else {
		    Accion accion=(Accion) session.getAttribute("accion");
	    	session.removeAttribute("accion");
	    	if(accion.getConfirmacion() != null && accion.getConfirmacion().equals("True")) {
	    		horarioVoluntarioDao.deleteHorarioVoluntario(id);
	    	}
	    }
		return "redirect:../horarios";
	}
	
}






