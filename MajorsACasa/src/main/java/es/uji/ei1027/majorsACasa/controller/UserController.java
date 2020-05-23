package es.uji.ei1027.majorsACasa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei1027.majorsACasa.dao.UserDao;
import es.uji.ei1027.majorsACasa.model.UserDetails;

@Controller 
@RequestMapping("/user")
public class UserController {
	private UserDao userDao;

	   @Autowired 
	   public void setSociDao(UserDao userDao) {
	       this.userDao = userDao;
	   }
	   
	   @RequestMapping("/list")
	   public String listSocis(HttpSession session, Model model) {
	       if (session.getAttribute("user") == null) 
	       { 
	          model.addAttribute("user", new UserDetails());
	          session.setAttribute("nextUrl", "/");
	          return "login";
	       } 
	       model.addAttribute("users", userDao.listAllUsers());
	       return "user/list";
	   }
}
