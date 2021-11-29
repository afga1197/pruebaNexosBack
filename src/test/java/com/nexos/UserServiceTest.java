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
import static org.mockito.Mockito.when;
import com.nexos.repository.UserRepository;
import com.nexos.serviceImp.UserServiceImp;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImp userServiceImp;

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
		when(userRepository.findAll()).thenReturn(users);
		List<User> listUsers = userServiceImp.findAll();
		assertNotNull(listUsers);
	}

}