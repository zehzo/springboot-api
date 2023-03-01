package br.com.nullbank.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.nullbank.dtos.UserDto;
import br.com.nullbank.models.User;
import br.com.nullbank.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	/*
	 * @Transactional
	 * public ResponseEntity<Object> save(User user) {
	 * if (Objects.nonNull(user.getName()) && Objects.nonNull(user.getCpf())) {
	 * if(userRepository.existsByCpf(user.getCpf())) {
	 * return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF ALREADY EXISTS");
	 * } else {
	 * 
	 * return ResponseEntity.status(HttpStatus.CREATED).body("USER CREATED");
	 * }
	 * } else {
	 * return ResponseEntity.status(HttpStatus.BAD_REQUEST).
	 * body("NAME AND CPF FIELDS ARE MANDATORY");
	 * }
	 * }
	 */

	@Transactional
	public ResponseEntity<Object> saveUserDto(@Valid UserDto userDto) {
		if (!userRepository.existsByCpf(userDto.getCpf())) {
			var user = resolveAndReturnUser(userDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("CPF already registered in use to system!");
		}
	}

	private @NotNull User resolveAndReturnUser(UserDto userDto) {
		var user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setName(user.getName().toUpperCase());
		user.setDhCreation(LocalDateTime.now(ZoneId.of("UTC")));
		user.setDhModification(LocalDateTime.now(ZoneId.of("UTC")));
		return user;
	}

}
