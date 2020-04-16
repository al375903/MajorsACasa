package es.uji.ei1027.majorsACasa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.majorsACasa.dao.VoluntarioDao;
import es.uji.ei1027.majorsACasa.model.Voluntario;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {

	private VoluntarioDao voluntarioDao;
	
	@Autowired
	public void setVoluntarioDao(VoluntarioDao voluntarioDao) {
		this.voluntarioDao = voluntarioDao;
	}
	
	@RequestMapping("/list")
	public String listVoluntarios(Model model) {
		model.addAttribute("voluntarios", voluntarioDao.getVoluntarios());
		return "voluntario/list";
	}
	
	@RequestMapping(value="add")
	public String addVoluntario(Model model) {
		model.addAttribute("voluntario", new Voluntario());
		return "voluntario/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("voluntario") Voluntario voluntario,
									BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "voluntario/add";
		voluntarioDao.addVoluntario(voluntario);
		return "redirect:list";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editVoluntario(Model model, @PathVariable String id) {
		model.addAttribute("voluntario", voluntarioDao.getVoluntario(id));
		return "voluntario/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("voluntario") Voluntario voluntario,
										BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "voluntario/update";
		voluntarioDao.updateVoluntario(voluntario);
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id) {
		voluntarioDao.deleteVoluntario(id);
		return "redirect:../list";
	}
	
}






