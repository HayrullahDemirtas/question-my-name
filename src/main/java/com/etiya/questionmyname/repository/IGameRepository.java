package com.etiya.questionmyname.repository;

import com.etiya.questionmyname.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGameRepository extends JpaRepository<Game, Integer> {
    List<Game> findAllByPlayerName(String playerName);
}
