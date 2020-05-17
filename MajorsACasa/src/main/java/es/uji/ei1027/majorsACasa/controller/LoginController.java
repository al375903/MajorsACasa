package es.uji.ei1027.majorsACasa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.majorsACasa.dao.UserDao;
import es.uji.ei1027.majorsACasa.model.UserDetails;

import org.springframework.validation.Errors; 
import org.springframework.validation.Validator;

class UserValidator implements Validator { 
    @Override
    public boolean supports(Class<?> cls) { 
        return UserDetails.class.isAssignableFrom(cls);
    }
    @Override 
    public void validate(Object obj, Errors errors) {
      // Exercici: Afegeix codi per comprovar que 
         // l'usuari i la contrasenya no estiguen buits 
         UserDetails user=(UserDetails)obj;
         if(user.getUsername().trim().equals(""))
        	 errors.rejectValue("username", "obligatori",
                     "Cal introduir un valor");
         if(user.getPassword().trim().equals(""))
        	 errors.rejectValue("password", "obligatori",
                     "Cal introduir un valor");
    }
}

@Controller
public class LoginController {
	@Autowired
    private UserDao userDao;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserDetails());
        return "login";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") UserDetails user,          
                BindingResult bindingResult, HttpSession session) {
        UserValidator userValidator = new UserValidator(); 
        userValidator.validate(user, bindingResult); 
        if (bindingResult.hasErrors()) {
            return "login";
            }
               // Comprova que el login siga correcte 
            // intentant carregar les dades de l'usuari 
            user = userDao.loadUserByUsername(user.getUsername(), user.getPassword()); 
            if (user == null) {
                bindingResult.rejectValue("password", "badpw", "Contrasenya incorrecta"); 
                return "login";
            }
            // Autenticats correctament. 
            // Guardem les dades de l'usuari autenticat a la sessió
            session.setAttribute("user", user);
            // Torna a la pàgina
            //return "redirect:/";
            String nextUrl = "/";
            if(session.getAttribute("nextUrl") != null) {
            	nextUrl = (String) session.getAttribute("nextUrl");
            	session.removeAttribute("nextUrl");
            }
            
            if(user.getTipo()=="casManager") {
            	return "casManager";
            }else if(user.getTipo()=="casVolunteer") {
            	nextUrl="casVolunteer";
            }else if(user.getTipo()=="casCommittee") {
            	nextUrl="casCommittee";
            }
            
            return "redirect:" + nextUrl;
        }

        @RequestMapping("/logout") 
        public String logout(HttpSession session) {
            session.invalidate(); 
            return "redirect:/";
        }
}
