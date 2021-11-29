package com.nexos;

import org.junit.Test;
import java.util.List;
import org.mockito.Mock;
import java.util.ArrayList;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import com.nexos.entity.Merchandise;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import org.springframework.http.HttpStatus;
import org.mockito.junit.MockitoJUnitRunner;
import com.nexos.service.MerchandiseService;
import org.springframework.http.ResponseEntity;
import com.nexos.controller.MerchandiseController;

@RunWith(MockitoJUnitRunner.class)
public class MerchandiseControllerTest {

	@Mock
	MerchandiseService merchandiseService;

	@InjectMocks
	MerchandiseController merchandiseController;

	@Test
	public void list() {
		List<Merchandise> merchandises = new ArrayList<>();
		Merchandise merchandise = new Merchandise();
		merchandise.setId("1");
		merchandise.setName("llantas");
		merchandises.add(merchandise);
		when(merchandiseService.findAll()).thenReturn(merchandises);
		ResponseEntity<List<Merchandise>> merchandiseList = merchandiseController.list();
		assertEquals(200, merchandiseList.getStatusCodeValue());
	}

	@Test
	public void addMerchandise() {
		Merchandise merchandise = new Merchandise();
		merchandise.setId("1");
		merchandise.setName("llantas");
		ResponseEntity<String> answer = new ResponseEntity<String>("Se creo la mercancia correctamente", HttpStatus.OK);
		when(merchandiseService.addMerchandise(merchandise)).thenReturn(answer);
		ResponseEntity<String> merchandiseAdd = merchandiseController.addMerchandise(merchandise);
		assertEquals(200, merchandiseAdd.getStatusCodeValue());
	}

}