package com.nexos.service;

import java.util.List;
import com.nexos.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

	List<User> findAll();

	ResponseEntity<String> addUser(User user);

	User findById(String id);

}