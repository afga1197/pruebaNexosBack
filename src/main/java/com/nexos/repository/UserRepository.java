package com.nexos.repository;

import com.nexos.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface UserRepository
		extends CrudRepository<User, String>, JpaSpecificationExecutor<User>, JpaRepository<User, String> {

	@Query(value = "SELECT m FROM User m WHERE m.name = :name")
	User findByName(@Param("name") String name);

}