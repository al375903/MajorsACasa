package es.uji.ei1027.majorsACasa.controller;

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

import es.uji.ei1027.majorsACasa.dao.PeticionDao;
import es.uji.ei1027.majorsACasa.model.Peticion;

@Controller
@RequestMapping("/peticion")
public class PeticionController {
	
	private PeticionDao peticionDao;
	
	@Autowired
	public void setPeticionDao(PeticionDao peticionDao) {
		this.peticionDao = peticionDao;
	}
	
	@RequestMapping("/list")
	public String listPeticiones(Model model) {
		model.addAttribute("peticiones", peticionDao.getPeticiones());
		return "peticion/list";
	}
	
	@RequestMapping(value="add")
	public String addPeticion(Model model) {
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
								"No puede realizar una nueva petición teniendo una petición aceptado o por revisar", "ErrorCrearPetición");
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
		return "redirect:list";
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.GET)
	public String editPeticion(Model model, @PathVariable String id) {
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
		peticionDao.updatePeticion(peticion);
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id) {
		peticionDao.deletePeticion(id);
		return "redirect:../list";
	}
}





