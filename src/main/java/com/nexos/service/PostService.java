package com.nexos.service;

import java.util.List;
import com.nexos.entity.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

	List<Post> findAll();

	ResponseEntity<String> addPost(Post post);

	Post findById(String id);

}