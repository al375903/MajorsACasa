package es.uji.ei1027.majorsACasa.controller;

import java.time.LocalDate;

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

@Controller
@RequestMapping("/peticion")
public class PeticionController {
	
	private PeticionDao peticionDao;
	private BeneficiarioDao beneficiarioDao;
	
	@Autowired
	public void setPeticionDao(PeticionDao peticionDao) {
		this.peticionDao = peticionDao;
	}
	
	@Autowired
	public void setBeneficiarioDao(BeneficiarioDao beneficiarioDao) {
		this.beneficiarioDao = beneficiarioDao;
	}
	
	@RequestMapping("/list")
	public String listPeticiones(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casManager"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "peticion/list");
          return "login";
        }
		model.addAttribute("peticiones", peticionDao.getPeticiones());
		return "peticion/list";
	}
	
	@RequestMapping(value="add")
	public String addPeticion(HttpSession session, Model model) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casManager"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "peticion/add");
          return "login";
        }
		model.addAttribute("peticion", new Peticion());
		return "peticion/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("peticion") Peticion peticion,
									BindingResult bindingResult) {
		PeticionValidator peticionValidator = new PeticionValidator();
		peticionValidator.validate(peticion, bindingResult);
		if(bindingResult.hasErrors())
			return "peticion/add";
		try {
			for(Peticion peticion2 : peticionDao.getPeticiones()) {
				if(peticion2.getIdBeneficiario().equals(peticion.getIdBeneficiario())) {
					if(peticion2.getEstado().equals("Aceptada") || peticion2.getEstado().equals("NoRevisada")) {
						throw new MajorsACasaException(
								"No puede realizar una nueva petición teniendo una petición aceptada o por revisar", "ErrorCrearPetición");
					}
				}
			}
			if(peticion.getFechaAprobacion() != null && peticion.getFechaAprobacion().isBefore(LocalDate.now().plusDays(1))) {
				peticion.setEstado("Aceptada");
			} else {
				peticion.setEstado("NoRevisada");
			}
			if(peticion.getFechaCancelacion() != null && peticion.getFechaCancelacion().isBefore(LocalDate.now().plusDays(1))) {
				peticion.setEstado("Denegada");
			} else if(peticion.getFechaDenegacion() != null && peticion.getFechaDenegacion().isBefore(LocalDate.now().plusDays(1))) {
				peticion.setEstado("Denegada");
			}
			peticion.setFechaCreacion(LocalDate.now());
			peticionDao.addPeticion(peticion);
			Beneficiario b = beneficiarioDao.getBeneficiario(peticion.getIdBeneficiario());
			System.out.println("\nSe ha enviado un correo a " + b.getIdBeneficiario() + "(" + b.getEmail() + ") con el siguiente mensaje: \n\tSe ha registrado una peticion de "+ peticion.getTipoServicio());
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
					"Ya existe una petición con id " + peticion.getIdPeticion(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"Error en el acceso a la base de datos", "ErrorAccediendoDatos");
		}
		return "redirect:list";
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.GET)
	public String editPeticion(HttpSession session, Model model, @PathVariable String id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casManager"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "peticion/update");
          return "login";
        }
		model.addAttribute("peticion", peticionDao.getPeticion(id));
		return "peticion/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("peticion") Peticion peticion,
										BindingResult bindingResult) {
		PeticionValidator peticionValidator = new PeticionValidator();
		peticionValidator.validate(peticion, bindingResult);
		if(bindingResult.hasErrors())
			return "peticion/update";
		
		if(peticion.getFechaAprobacion() != null && peticion.getFechaAprobacion().isBefore(LocalDate.now().plusDays(1))) {
			peticion.setEstado("Aceptada");
		} else {
			peticion.setEstado("NoRevisada");
		}
		if(peticion.getFechaCancelacion() != null && peticion.getFechaCancelacion().isBefore(LocalDate.now().plusDays(1))) {
			peticion.setEstado("Denegada");
		} else if(peticion.getFechaDenegacion() != null && peticion.getFechaDenegacion().isBefore(LocalDate.now().plusDays(1))) {
			peticion.setEstado("Denegada");
		}
		
		peticionDao.updatePeticion(peticion);
		if(peticion.getEstado() != "NoRevisada") {
			Beneficiario b = beneficiarioDao.getBeneficiario(peticion.getIdBeneficiario());
			System.out.println("\nSe ha enviado un correo a " + b.getIdBeneficiario() + "(" + b.getEmail() + ") con el siguiente mensaje: \n\tSu peticion de "+ peticion.getTipoServicio() + " ha sido " + peticion.getEstado());
		}
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(HttpSession session, Model model, @PathVariable String id) {
		UserDetails user = (UserDetails)session.getAttribute("user");
	    if (user == null || !(user.getTipo().equals("jefe") || user.getTipo().equals("casManager"))) { 
          model.addAttribute("user", new UserDetails());
          session.setAttribute("nextUrl", "redirect:../list");
          return "login";
        }
	    if (session.getAttribute("accion") == null) {
	    	model.addAttribute("accion", new Accion());
	    	session.setAttribute("ruta", "/peticion/delete/"+id);
	    	return "confirm"; 
	    } else {
		    Accion accion=(Accion) session.getAttribute("accion");
	    	session.removeAttribute("accion");
	    	if(accion.getConfirmacion() != null && accion.getConfirmacion().equals("True")) {
	    		Peticion peticion = peticionDao.getPeticion(id);
	    		peticionDao.deletePeticion(id);
				Beneficiario b = beneficiarioDao.getBeneficiario(peticion.getIdBeneficiario());
				System.out.println("\nSe ha enviado un correo a " + b.getIdBeneficiario() + "(" + b.getEmail() + ") con el siguiente mensaje: \n\tSu peticion de "+ peticion.getTipoServicio() + " ha sido eliminada.");
	    	}
	    }
		return "redirect:../list";
	}
}





