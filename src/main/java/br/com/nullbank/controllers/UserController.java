package br.com.nullbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.nullbank.models.User;
import br.com.nullbank.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/createUser")
	public ResponseEntity<Object> saveUser(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
	}

	@GetMapping("/listUsers")
	public ResponseEntity<List<User>> listUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
	}

	@PostMapping("/createUserTeste")
	public ResponseEntity<Object> createUserTest(){
		var user = new User();
		user.setCpf("0000000000");
		user.setName("Jose");
		return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
	}
}
