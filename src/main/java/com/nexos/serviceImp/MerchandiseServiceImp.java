package com.nexos.serviceImp;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.nexos.entity.User;
import com.nexos.entity.History;
import com.nexos.entity.Merchandise;
import com.nexos.service.UserService;
import com.nexos.service.HistoryService;
import org.springframework.http.HttpStatus;
import com.nexos.service.MerchandiseService;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.nexos.repository.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MerchandiseServiceImp implements MerchandiseService {

	@Autowired
	private MerchandiseRepository merchandiseRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private HistoryService historyService;

	@Override
	public List<Merchandise> findAll() {
		return merchandiseRepository.findAll();
	}

	@Override
	public ResponseEntity<String> addMerchandise(Merchandise merchandise) {
		boolean exist = existMerchandise(merchandise);
		if (!exist) {
			merchandise.setId(UUID.randomUUID().toString());
			merchandise.setUser_id(userService.findById(merchandise.getUser_id().getId()));
			merchandiseRepository.save(merchandise);
			History history = new History();
			history.setId(UUID.randomUUID().toString());
			history.setId_user(merchandise.getUser_id());
			history.setDate(new Date());
			history.setCommentary("El usuario " + merchandise.getUser_id().getName() + " creo la mercancia "
					+ merchandise.getName() + " con " + merchandise.getQuantity() + " unidades");
			historyService.save(history);
			return new ResponseEntity<String>("Se creo la mercancia correctamente", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("La mercancia ya se encuentra registrada en el sistema",
					HttpStatus.BAD_REQUEST);
		}
	}

	private boolean existMerchandise(Merchandise merchandise) {
		try {
			Merchandise result = merchandiseRepository.findByName(merchandise.getName());
			if (result.equals(null)) {
				throw new Exception();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ResponseEntity<String> updateMerchandise(Merchandise merchandise) {
		try {
			Merchandise exist = merchandiseRepository.findByName(merchandise.getName());
			if (exist.getId().equals(merchandise.getId())) {
				User userUpdate = userService.findById(merchandise.getUser_id().getId());
				merchandise.setUser_id(exist.getUser_id());
				merchandiseRepository.save(merchandise);
				History history = new History();
				history.setId(UUID.randomUUID().toString());
				history.setId_user(userUpdate);
				history.setDate(new Date());
				history.setCommentary("El usuario " + history.getId_user().getName() + " actualizo la mercancia "
						+ merchandise.getName() + " con " + merchandise.getQuantity() + " unidades");
				historyService.save(history);
				return new ResponseEntity<String>("Se actualizo la mercancia correctamente", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Ya existe una mercancia con el mismo nombre",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			Merchandise exist = merchandiseRepository.findById(merchandise.getId()).get();
			User userUpdate = userService.findById(merchandise.getUser_id().getId());
			merchandise.setUser_id(exist.getUser_id());
			merchandiseRepository.save(merchandise);
			History history = new History();
			history.setId(UUID.randomUUID().toString());
			history.setId_user(userUpdate);
			history.setDate(new Date());
			history.setCommentary("El usuario " + history.getId_user().getName() + " modifico la mercancia con id "
					+ merchandise.getId() + ", le cambio el nombre a " + merchandise.getName() + " con "
					+ merchandise.getQuantity() + " unidades");
			historyService.save(history);
			return new ResponseEntity<String>("Se actualizo la mercancia correctamente", HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<String> deleteMerchandise(String id, String idUser) {
		Merchandise merchandise = merchandiseRepository.findById(id).get();
		if (merchandise.getUser_id().getId().equals(idUser)) {
			merchandiseRepository.delete(merchandise);
			return new ResponseEntity<String>("Se elimino la mercancia correctamente", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("El producto no se pudo eliminar debio a que fue creado por otro usuario",
					HttpStatus.BAD_REQUEST);
		}
	}

}