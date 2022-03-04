package ru.javabegin.example.roles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javabegin.example.roles.entity.Role;
import ru.javabegin.example.roles.entity.User;
import ru.javabegin.example.roles.repo.RoleRepository;
import ru.javabegin.example.roles.repo.UserRepository;

import java.util.List;

@Service
public class UserService {

	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public void registerDefaultRole(User user) {
		Role roleUser = roleRepo.findByName("User");
		user.getRoles().add(roleUser);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}

	
	public List<User> listAll() {
		return userRepo.findAll();
	}

	public User get(Long id) {
		return userRepo.findById(id).get();
	}
	
	public List<Role> listRoles() {
		return roleRepo.findAll();
	}
	
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}

	public void delete(Long id) {
		userRepo.deleteById(id);
	}

}
