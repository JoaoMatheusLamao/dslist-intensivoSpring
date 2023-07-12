package com.devsuperior.dslist.controllers;

import java.util.List;

import com.devsuperior.dslist.dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.services.GameService;

//Anotações referentes ao controller /games

@RestController
@RequestMapping(value = "/games")
public class GameController {
	
	@Autowired
	private GameService gameService;
	

	@GetMapping
	//metodo que retorna todos os games (setados a partir de um service que, por sua vez, retorna um objeto DTO (padrão))
	public List<GameMinDTO> findAll() {
		List<GameMinDTO> result = gameService.findAll();
		return result;
	}

	@GetMapping(value = "/{id}")
	//metodo que retorna todos os games (setados a partir de um service que, por sua vez, retorna um objeto DTO (padrão))
	public GameDTO findByID(@PathVariable Long id) {
		GameDTO result = gameService.findById(id);
		return result;
	}

	@GetMapping(value = "/title={title}")
	public GameDTO findByTitle(@PathVariable String title){
		return gameService.findByTitle(title);
	}
}
