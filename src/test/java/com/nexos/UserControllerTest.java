package com.nexos;

import org.junit.Test;
import java.util.Date;
import java.util.List;
import org.mockito.Mock;
import java.util.ArrayList;
import com.nexos.entity.Post;
import com.nexos.entity.User;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import com.nexos.service.UserService;
import static org.mockito.Mockito.when;
import com.nexos.controller.UserController;
import org.springframework.http.HttpStatus;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	UserService userService;

	@InjectMocks
	UserController userController;

	@Test
	public void list() {
		List<User> users = new ArrayList<>();
		User user = new User();
		user.setId("1");
		user.setName("andres gomez");
		Post post = new Post();
		post.setId("1");
		post.setName("gerente");
		user.setId_post(post);
		user.setAge(5);
		user.setAdmission_date(new Date());
		users.add(user);
		when(userService.findAll()).thenReturn(users);
		ResponseEntity<List<User>> listUsers = userController.list();
		assertEquals(200, listUsers.getStatusCodeValue());
	}

	@Test
	public void add() {
		User user = new User();
		user.setId("1");
		user.setName("andres gomez");
		Post post = new Post();
		post.setId("1");
		post.setName("gerente");
		user.setId_post(post);
		user.setAge(5);
		user.setAdmission_date(new Date());
		ResponseEntity<String> answer = new ResponseEntity<String>("Se creo el usuario correctamente", HttpStatus.OK);
		when(userService.addUser(user)).thenReturn(answer);
		ResponseEntity<String> addUser = userController.addUser(user);
		assertEquals(200, addUser.getStatusCodeValue());
	}

}