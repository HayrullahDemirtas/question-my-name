package com.etiya.questionmyname.service;

import com.etiya.questionmyname.dto.GameResponseDTO;
import com.etiya.questionmyname.model.Game;
import com.etiya.questionmyname.model.Guess;
import com.etiya.questionmyname.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GameService implements IGameService {

    @Autowired
    private IGameRepository gameRepository;

    @Override
    public Game createGame(String playerName) {
        String[] keywords = {"sharpener","adventure"};
        List<Guess> guessList = new ArrayList<>();

        Random random = new Random();
        int index = random.nextInt(keywords.length);
        String keyword = keywords[index];

        List<Game> gameList = gameRepository.findAllByPlayerName(playerName);
        Integer gameCount = gameList.size() + 1;

        Game game = new Game();
        game.setPlayerName(playerName);
        game.setGameCount(gameCount);
        game.setKeyword(keyword);
        game.setGuessList(guessList);

        gameRepository.save(game);

        return game;
    }

    @Override
    public GameResponseDTO getGameByGameId(Integer gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        return game.map(value -> new GameResponseDTO(value.getPlayerName() ,value.getGameCount(), value.getKeyword(), value.getGuessList())).orElse(null);
    }

    @Override
    public List<GameResponseDTO> getAllGameByPlayerName(String playerName) {
        List<GameResponseDTO> response = new ArrayList<>();
        List<Game> gameList = gameRepository.findAllByPlayerName(playerName);

        for (Game game : gameList) {
            GameResponseDTO gameResponseDTO = new GameResponseDTO(game.getPlayerName() ,game.getGameCount(), game.getKeyword(), game.getGuessList());
            response.add(gameResponseDTO);
        }
        return response;
    }
}
