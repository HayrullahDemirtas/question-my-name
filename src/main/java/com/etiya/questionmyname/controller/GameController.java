package com.etiya.questionmyname.controller;

import com.etiya.questionmyname.dto.GameResponseDTO;
import com.etiya.questionmyname.model.Game;
import com.etiya.questionmyname.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GameController {

    @Autowired
    private IGameService gameService;

    @CrossOrigin
    @PostMapping(path = "/createGame")
    public ResponseEntity<Game> createGame(@RequestParam("playerName") String playerName){
        Game createdGame = gameService.createGame(playerName);
        return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(path = "/gameList")
    public ResponseEntity<List<GameResponseDTO>> getAllGameByPlayerName(@RequestParam("playerName") String playerName){
        List<GameResponseDTO> gameList = gameService.getAllGameByPlayerName(playerName);
        return new ResponseEntity<>(gameList, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path = "/game")
    public ResponseEntity<GameResponseDTO> getGameByGameId(@RequestParam("gameId") Integer gameId){
        GameResponseDTO game = gameService.getGameByGameId(gameId);
        return new ResponseEntity<>(game,HttpStatus.OK);
    }
}
