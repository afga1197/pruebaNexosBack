package com.nexos.controller;

import java.util.List;
import com.nexos.entity.Post;
import com.nexos.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping
	@RequestMapping("/listPost")
	public ResponseEntity<List<Post>> list() {
		return new ResponseEntity<List<Post>>(postService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	@RequestMapping("/addPost")
	public ResponseEntity<String> addPost(@RequestBody Post post) {
		return postService.addPost(post);
	}

}