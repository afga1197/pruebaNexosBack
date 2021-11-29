package com.nexos.serviceImp;

import java.util.List;
import java.util.UUID;
import com.nexos.entity.User;
import com.nexos.service.PostService;
import com.nexos.service.UserService;
import com.nexos.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostService postService;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public ResponseEntity<String> addUser(User user) {
		boolean exist = existUser(user);
		if (!exist) {
			user.setId(UUID.randomUUID().toString());
			user.setId_post(postService.findById(user.getId_post().getId()));
			userRepository.save(user);
			return new ResponseEntity<String>("Se creo el usuario correctamente", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("El usuario ya existe en el sistema", HttpStatus.BAD_REQUEST);
		}
	}

	private boolean existUser(User user) {
		try {
			User result = userRepository.findByName(user.getName());
			if (result.equals(null)) {
				throw new Exception();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public User findById(String id) {
		return userRepository.findById(id).get();
	}

}