package es.uji.ei1027.majorsACasa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.majorsACasa.dao.BeneficiarioDao;
import es.uji.ei1027.majorsACasa.model.Beneficiario;

@Controller
@RequestMapping("/beneficiario")
public class BeneficiarioController {
	
	private BeneficiarioDao beneficiarioDao;
	
	@Autowired
	public void setBeneficiarioDao(BeneficiarioDao beneficiarioDao) {
		this.beneficiarioDao = beneficiarioDao;
	}

	@RequestMapping("/list")
	public String listBeneficiarios(Model model) {
		model.addAttribute("beneficiarios", beneficiarioDao.getBeneficiarios());
		return "beneficiario/list";
	}
	
	@RequestMapping(value="add")
	public String addBeneficiario(Model model) {
		model.addAttribute("beneficiario", new Beneficiario());
		return "beneficiario/add";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("beneficiario") Beneficiario beneficiario,
									BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "beneficiario/add";
		beneficiarioDao.addBeneficiario(beneficiario);
		return "redirect:list";
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.GET) 
	public String editBeneficiario(Model model, @PathVariable String id) { 
		model.addAttribute("beneficiario", beneficiarioDao.getBeneficiario(id));
		return "beneficiario/update"; 
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("beneficiario") Beneficiario beneficiario,
										BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "beneficiario/update";
		beneficiarioDao.updateBeneficiario(beneficiario);
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id) {
		beneficiarioDao.deleteBeneficiario(id);
		return "redirect:../list";
	}
}
