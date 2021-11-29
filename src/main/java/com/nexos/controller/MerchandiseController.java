package com.nexos.controller;

import java.util.List;
import com.nexos.entity.Merchandise;
import org.springframework.http.HttpStatus;
import com.nexos.service.MerchandiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchandise")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class MerchandiseController {

	@Autowired
	private MerchandiseService merchandiseService;

	@GetMapping
	@RequestMapping("/listMerchandise")
	public ResponseEntity<List<Merchandise>> list() {
		return new ResponseEntity<List<Merchandise>>(merchandiseService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	@RequestMapping("/addMerchandise")
	public ResponseEntity<String> addMerchandise(@RequestBody Merchandise merchandise) {
		return merchandiseService.addMerchandise(merchandise);
	}

	@PutMapping
	@RequestMapping("/updateMerchandise")
	public ResponseEntity<String> updateMerchandise(@RequestBody Merchandise merchandise) {
		return merchandiseService.updateMerchandise(merchandise);
	}

	@DeleteMapping(value = "/{id}/{idUser}")
	@RequestMapping("/deleteMerchandise")
	public ResponseEntity<String> deleteMerchandise(@RequestParam("id") String id,
			@RequestParam("idUser") String idUser) {
		return merchandiseService.deleteMerchandise(id, idUser);
	}

}