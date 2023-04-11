package com.etiya.questionmyname.service;

import com.etiya.questionmyname.dto.GameResponseDTO;
import com.etiya.questionmyname.model.Game;

import java.util.List;

public interface IGameService {
    Game createGame(String playerName);
    GameResponseDTO getGameByGameId(Integer gameId);
    List<GameResponseDTO> getAllGameByPlayerName(String playerName);
}
