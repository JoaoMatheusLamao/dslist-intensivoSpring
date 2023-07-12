package com.devsuperior.dslist.services;

import java.util.List;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.projection.GameMinProjection;
import com.devsuperior.dslist.projection.GameProjection;
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

	@Transactional(readOnly = true)
	public GameDTO findByTitle(String title){
		String[] textoSeparado = title.split("_");
		String titleOk = null;
		for (String x:
			 textoSeparado) {
			if (titleOk != null){
				titleOk = titleOk + " " + x;
			} else {
				titleOk = x;
			}
		}
		GameProjection result = gameRepository.searchByTitle(titleOk);
		GameDTO dto = new GameDTO(result);
		return dto;
	}
}
