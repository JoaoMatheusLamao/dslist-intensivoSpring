package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projection.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;

	@Autowired
	private GameRepository gameRepository;
	
	//manipula um objeto GameList para retornar um objeto do tipo GameListDTO
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		List<GameListDTO> gameListDTO = result.stream().map(x -> new GameListDTO(x)).toList();
		return gameListDTO;
	}


	@Transactional(readOnly = true)
	public void move(Long listId, int sourceIndex, int destinationIndex){
		List<GameMinProjection> result = gameRepository.searchByList(listId);

		GameMinProjection obj = result.remove(sourceIndex);
		result.add(destinationIndex, obj);

		for (int i = 0; i < result.size(); i++){
			gameListRepository.updateBelongingPosition(listId, result.get(i).getId(), i);
		}
	}

}
