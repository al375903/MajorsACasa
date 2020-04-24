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

import es.uji.ei1027.majorsACasa.dao.HorarioVoluntarioDao;
import es.uji.ei1027.majorsACasa.model.HorarioVoluntario;

@Controller
@RequestMapping("/horarioVoluntario")
public class HorarioVoluntarioController {

	private HorarioVoluntarioDao horarioVoluntarioDao;
	
	@Autowired
	public void setHorarioVoluntarioDao(HorarioVoluntarioDao horarioVoluntarioDao) {
		this.horarioVoluntarioDao = horarioVoluntarioDao;
	}
	
	@RequestMapping("/list")
	public String listHorariosVoluntarios(Model model) {
		model.addAttribute("horariosVoluntarios", horarioVoluntarioDao.getHorariosVoluntarios());
		return "horarioVoluntario/list";
	}
	
	@RequestMapping(value="add")
	public String addHorarioVoluntario(Model model) {
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
			horarioVoluntarioDao.addHorarioVoluntario(horarioVoluntario);
		} catch (DuplicateKeyException e) {
			throw new MajorsACasaException(
					"Ya existe un horario con id " + horarioVoluntario.getIdHorario(), "CPduplicada");
		} catch (DataAccessException e) {
			throw new MajorsACasaException(
					"Error en el acceso a la base de datos", "ErrorAccediendoDatos");
		}
		return "redirect:list";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editHorarioVoluntario(Model model, @PathVariable String id) {
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
		horarioVoluntarioDao.updateHorarioVoluntario(horarioVoluntario);
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id) {
		horarioVoluntarioDao.deleteHorarioVoluntario(id);
		return "redirect:../list";
	}
}







