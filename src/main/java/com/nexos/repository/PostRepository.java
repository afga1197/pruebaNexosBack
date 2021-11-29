package com.nexos.repository;

import com.nexos.entity.Post;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface PostRepository
		extends CrudRepository<Post, String>, JpaSpecificationExecutor<Post>, JpaRepository<Post, String> {

	@Query(value = "SELECT m FROM Post m WHERE m.name = :name")
	Post findByName(@Param("name") String name);

}