package com.nexos.repository;

import com.nexos.entity.History;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface HistoryRepository
		extends CrudRepository<History, String>, JpaSpecificationExecutor<History>, JpaRepository<History, String> {

}
