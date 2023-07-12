package com.devsuperior.dslist.services;

import java.util.List;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.projection.GameMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	//manipula um objeto Game para retornar um objeto do tipo GameMinDTO
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {
		List<Game> result = gameRepository.findAll();
		List<GameMinDTO> gameMinDTO = result.stream().map(x -> new GameMinDTO(x)).toList();
		return gameMinDTO;
	}

	@Transactional(readOnly = true)

	public GameDTO findById(Long gameId){
		Game result = gameRepository.findById(gameId).get();
		GameDTO dto = new GameDTO(result);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long listId) {
		List<GameMinProjection> result = gameRepository.searchByList(listId);
		return result.stream().map(x -> new GameMinDTO(x)).toList();

	}
}
