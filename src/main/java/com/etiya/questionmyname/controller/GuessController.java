package com.etiya.questionmyname.controller;

import com.etiya.questionmyname.dto.GuessRequestDTO;
import com.etiya.questionmyname.dto.GuessResponseDTO;
import com.etiya.questionmyname.service.IGuessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class GuessController {

    @Autowired
    private IGuessService guessService;

    @CrossOrigin
    @PostMapping(path = "/createGuess")
    public ResponseEntity<GuessResponseDTO> createGuess(@RequestBody GuessRequestDTO guessRequestDTO){
        GuessResponseDTO createdGuess = guessService.createGuess(guessRequestDTO);
        return new ResponseEntity<>(createdGuess, HttpStatus.CREATED);
    }
}
