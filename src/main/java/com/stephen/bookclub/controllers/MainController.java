package com.stephen.bookclub.controllers;





import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.stephen.bookclub.models.LoginUser;
import com.stephen.bookclub.models.User;
import com.stephen.bookclub.services.UserServ;

@Controller
public class MainController {
	@Autowired UserServ userServ;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser")User newUser, 
			BindingResult result, Model model, HttpSession session) {
		
		User user = userServ.register(newUser, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}else {
			session.setAttribute("userId", user.getId());
			return "redirect:/books";
		}
		
	}
	
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin")LoginUser newLogin,
			BindingResult result, Model model, HttpSession session) {
		
		User user = userServ.login(newLogin, result);
		
		if(result.hasErrors() || user==null) {
			model.addAttribute("newUser",new User());
			return "index.jsp";
		}
			session.setAttribute("userId", user.getId());
			return "redirect:/books";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		return "redirect:/";
	}
}
