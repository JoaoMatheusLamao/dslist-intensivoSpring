package com.devsuperior.dslist.controllers;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.dto.ReplacementDTO;
import com.devsuperior.dslist.services.GameListService;
import com.devsuperior.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Anotações referentes ao controller /games

@RestController
@RequestMapping(value = "/lists")
public class GameListController {
	
	@Autowired
	private GameListService gameListService;

	@Autowired
	private GameService gameService;

	@GetMapping
	//metodo que retorna todos os games (setados a partir de um service que, por sua vez, retorna um objeto DTO (padrão))
	public List<GameListDTO> findAll() {
		List<GameListDTO> result = gameListService.findAll();
		return result;
	}

	@GetMapping(value = "/{listId}/games")
	//metodo que retorna todos os games (setados a partir de um service que, por sua vez, retorna um objeto DTO (padrão))
	public List<GameMinDTO> findByList(@PathVariable Long listId) {
		List<GameMinDTO> result = gameService.findByList(listId);
		return result;
	}

	@PostMapping(value = "{listId}/replacement")
	public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body){
		gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
	}

}
