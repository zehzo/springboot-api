package br.com.nullbank.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nullbank.dtos.UserDto;
import br.com.nullbank.models.User;
import br.com.nullbank.repositories.UserRepository;
import br.com.nullbank.services.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	/*
	 * @PostMapping("/createUser")
	 * public ResponseEntity<Object> saveUser(@RequestBody User user){
	 * return userService.save(user);
	 * }
	 */

	@PostMapping("/createUser")
	public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto) {
		return userService.saveUserDto(userDto);
	}

	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
	}

	@PostMapping("/createUserTest")
	public ResponseEntity<Object> createUserTest() {
		var user = new User();
		user.setCpf("00000000001");
		user.setName("TESTE");
		user.setDhCreation(LocalDateTime.now(ZoneId.of("UTC")));
		user.setDhModification(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
	}
}
