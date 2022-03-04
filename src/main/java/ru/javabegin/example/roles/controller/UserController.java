package ru.javabegin.example.roles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.example.roles.entity.Role;
import ru.javabegin.example.roles.entity.User;
import ru.javabegin.example.roles.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
	private UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("")
	public String homePage() {
		return "index";
	}

	@PostMapping("process_register")
	public String processRegister(User user) {
		service.registerDefaultRole(user);

		return "register_success";
	}
	
	@GetMapping("register")
	public String regForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}

	
	@GetMapping("users")
	public String listUsers(Model model) {
		List<User> listUsers = service.listAll();
		List<Role> listRoles = service.listRoles();

		model.addAttribute("listUsers", listUsers);
		model.addAttribute("listRoles", listRoles);


		return "admin_page";
	}
	
	@GetMapping("users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = service.get(id);
		model.addAttribute("user", user);
		return "redirect:/users";
	}
	
	@PostMapping("users/save")
	public String saveUser(User user) {
		service.save(user);
		
		return "redirect:/users";
	}

	@PostMapping("users/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		service.delete(id);

		return "redirect:/users";
	}

}
