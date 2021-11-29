package com.nexos.repository;

import com.nexos.entity.Merchandise;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface MerchandiseRepository extends CrudRepository<Merchandise, String>,
		JpaSpecificationExecutor<Merchandise>, JpaRepository<Merchandise, String> {

	@Query(value = "SELECT m FROM Merchandise m WHERE m.name = :name")
	Merchandise findByName(@Param("name") String name);

}