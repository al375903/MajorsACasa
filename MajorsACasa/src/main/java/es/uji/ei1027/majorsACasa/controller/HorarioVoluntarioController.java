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
import es.uji.ei1027.majorsACasa.dao.HorarioVoluntarioDao;
import es.uji.ei1027.majorsACasa.dao.VoluntarioDao;
import es.uji.ei1027.majorsACasa.model.Accion;
import es.uji.ei1027.majorsACasa.model.Beneficiario;
import es.uji.ei1027.majorsACasa.model.HorarioVoluntario;
import es.uji.ei1027.majorsACasa.model.UserDetails;
import es.uji.ei1027.majorsACasa.model.Voluntario;

@Controller
@RequestMapping("/horarioVoluntario")
public class HorarioVoluntarioController {

	private HorarioVoluntarioDao horarioVoluntarioDao;
	private BeneficiarioDao beneficiarioDao;
	private VoluntarioDao voluntarioDao;
	
	@Autowired
	public void setHorarioVoluntarioDao(HorarioVoluntarioDao horarioVoluntarioDao) {
		this.horarioVoluntarioDao = horarioVoluntarioDao;
	}
	
	@Autowired
	public void setVoluntarioDao(VoluntarioDao voluntarioDao) {
		this.voluntarioDao = voluntarioDao;
	}
	
	@Autowired
	public void setBeneficiarioDao(BeneficiarioDao beneficiarioDao) {
		this.beneficiarioDao = beneficiarioDao;
	}
	
	@RequestMapping("/list")
	public String listHorariosVoluntarios(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casVolunteer"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "horarioVoluntario/list");
          return "login";
        }
		model.addAttribute("horariosVoluntarios", horarioVoluntarioDao.getHorariosVoluntarios());
		return "horarioVoluntario/list";
	}
	
	@RequestMapping(value="add")
	public String addHorarioVoluntario(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casVolunteer"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "horarioVoluntario/add");
          return "login";
        }
		model.addAttribute("horarioVoluntario", new HorarioVoluntario());
		return "horarioVoluntario/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("horarioVoluntario") HorarioVoluntario horarioVoluntario,
									BindingResult bindingResult) {
		HorarioVoluntarioValidator horarioVoluntarioValidator = new HorarioVoluntarioValidator();
		horarioVoluntarioValidator.validate(horarioVoluntario, bindingResult);
		if(bindingResult.hasErrors())
			return "horarioVoluntario/add";
		try {
			if(horarioVoluntario.getIdBeneficiario().equals("")) {
				horarioVoluntario.setIdBeneficiario(null);
				horarioVoluntario.setLibre(true);
			}
			horarioVoluntarioDao.addHorarioVoluntario(horarioVoluntario);
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
					"Ya existe un horario con id " + horarioVoluntario.getIdHorario(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"Error en el acceso a la base de datos", "ErrorAccediendoDatosHorario");
		}
		return "redirect:list";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editHorarioVoluntario(HttpSession session, Model model, @PathVariable int id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casVolunteer"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "horarioVoluntario/update");
          return "login";
        }
		model.addAttribute("horarioVoluntario", horarioVoluntarioDao.getHorarioVoluntario(id));
		return "horarioVoluntario/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("horarioVoluntario") HorarioVoluntario horarioVoluntario,
										BindingResult bindingResult) {
		HorarioVoluntarioValidator horarioVoluntarioValidator = new HorarioVoluntarioValidator();
		horarioVoluntarioValidator.validate(horarioVoluntario, bindingResult);
		if(bindingResult.hasErrors())
			return "horarioVoluntario/update";
		if(horarioVoluntario.getIdBeneficiario().equals("")) {
			horarioVoluntario.setIdBeneficiario(null);
			horarioVoluntario.setLibre(true);
		}
		horarioVoluntarioDao.updateHorarioVoluntario(horarioVoluntario);
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(HttpSession session, Model model, @PathVariable int id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casVolunteer"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "redirect:../list");
          return "login";
        }
	    if (session.getAttribute("accion") == null) {
	    	model.addAttribute("accion", new Accion());
	    	session.setAttribute("ruta", "/horarioVoluntario/delete/"+id);
	    	return "confirm"; 
	    } else {
		    Accion accion=(Accion) session.getAttribute("accion");
	    	session.removeAttribute("accion");
	    	if(accion.getConfirmacion() != null && accion.getConfirmacion().equals("True")) {
	    		HorarioVoluntario hv = horarioVoluntarioDao.getHorarioVoluntario(id);
	    		Voluntario v = voluntarioDao.getVoluntario(hv.getIdVoluntario());
	    		horarioVoluntarioDao.deleteHorarioVoluntario(id);
	    	    System.out.println("\nSe ha enviado un correo a " + v.getIdVoluntario() + "(" + v.getEmail() + ") con el siguiente mensaje: \n\tSe ha eliminado el horario con fecha " + hv.getFecha() + " de " + hv.getHoraInicio() + " a " + hv.getHoraFin());
	    	}
	    }
		return "redirect:../list";
	}
	
	@RequestMapping(value="/reservar/{id}")
	public String reservarHorarioVoluntario(HttpSession session, Model model, @PathVariable int id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("beneficiario"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "beneficiario/index");
          return "login";
        }
	    HorarioVoluntario hv = horarioVoluntarioDao.getHorarioVoluntario(id);
	    hv.setLibre(false);
	    hv.setIdBeneficiario(user.getUsername());
	    horarioVoluntarioDao.updateHorarioVoluntario(hv);
	    Beneficiario b = beneficiarioDao.getBeneficiario(hv.getIdBeneficiario());
	    Voluntario v = voluntarioDao.getVoluntario(hv.getIdVoluntario());
	    System.out.println("\nSe ha enviado un correo a " + b.getIdBeneficiario() + "(" + b.getEmail() + ") con el siguiente mensaje: \n\tSe ha reservado el horario para fecha " + hv.getFecha() + " de " + hv.getHoraInicio() + " a " + hv.getHoraFin());
	    System.out.println("\nSe ha enviado un correo a " + v.getIdVoluntario() + "(" + v.getEmail() + ") con el siguiente mensaje: \n\tSe ha reservado su horario con fecha " + hv.getFecha() + " de " + hv.getHoraInicio() + " a " + hv.getHoraFin());
		return "redirect:/beneficiario/index";
	}
}
