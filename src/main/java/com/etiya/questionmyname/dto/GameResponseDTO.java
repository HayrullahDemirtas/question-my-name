package com.etiya.questionmyname.dto;

import com.etiya.questionmyname.model.Guess;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameResponseDTO {

    public GameResponseDTO(String playerName, Integer gameCount, String keyword, List<Guess> guessList) {
        this.playerName = playerName;
        this.gameCount = gameCount;
        this.keyword = keyword;
        this.guessList = guessList;
    }

    private String playerName;
    private Integer gameCount;
    private String keyword;
    private List<Guess> guessList = new ArrayList<>();
}
