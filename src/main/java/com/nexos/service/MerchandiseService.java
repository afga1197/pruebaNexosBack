package com.nexos.service;

import java.util.List;
import com.nexos.entity.Merchandise;
import org.springframework.http.ResponseEntity;

public interface MerchandiseService {

	List<Merchandise> findAll();

	ResponseEntity<String> addMerchandise(Merchandise merchandise);

	ResponseEntity<String> updateMerchandise(Merchandise merchandise);

	ResponseEntity<String> deleteMerchandise(String id, String idUser);

}