package com.nexos.serviceImp;

import com.nexos.entity.History;
import com.nexos.service.HistoryService;
import org.springframework.stereotype.Service;
import com.nexos.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class HistoryServiceImp implements HistoryService {

	@Autowired
	private HistoryRepository historyRepository;

	@Override
	public void save(History history) {
		historyRepository.save(history);
	}

}