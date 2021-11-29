package com.nexos.serviceImp;

import java.util.List;
import java.util.UUID;
import com.nexos.entity.Post;
import com.nexos.service.PostService;
import org.springframework.http.HttpStatus;
import com.nexos.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PostServiceImp implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public ResponseEntity<String> addPost(Post post) {
		boolean exist = existPost(post);
		if (!exist) {
			post.setId(UUID.randomUUID().toString());
			postRepository.save(post);
			return new ResponseEntity<String>("Se agrego el cargo correctamente", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("El cargo ya se encuentra registrado en el sistema",
					HttpStatus.BAD_REQUEST);
		}
	}

	private boolean existPost(Post post) {
		try {
			Post result = postRepository.findByName(post.getName());
			if (result.equals(null)) {
				throw new Exception();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Post findById(String id) {
		return postRepository.findById(id).get();
	}

}