package com.etiya.questionmyname.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuessRequestDTO {
    public GuessRequestDTO(Integer gameId, Integer guessCount, String guessWord) {
        this.gameId = gameId;
        this.guessCount = guessCount;
        this.guessWord = guessWord;
    }

    private Integer gameId;
    private Integer guessCount;
    private String guessWord;
}
