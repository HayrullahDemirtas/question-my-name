package com.etiya.questionmyname.service;

import com.etiya.questionmyname.dto.GuessRequestDTO;
import com.etiya.questionmyname.dto.GuessResponseDTO;

public interface IGuessService {
    GuessResponseDTO createGuess(GuessRequestDTO guessRequestDTO);
}
