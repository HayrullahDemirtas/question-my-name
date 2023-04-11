package com.etiya.questionmyname.service;

import com.etiya.questionmyname.dto.GuessRequestDTO;
import com.etiya.questionmyname.dto.GuessResponseDTO;
import com.etiya.questionmyname.model.Game;
import com.etiya.questionmyname.model.Guess;
import com.etiya.questionmyname.repository.IGameRepository;
import com.etiya.questionmyname.repository.IGuessRepository;
import com.etiya.questionmyname.util.GuessCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
@Service
public class GuessService implements IGuessService{
    @Autowired
    private IGuessRepository guessRepository;

    @Autowired
    private IGameRepository gameRepository;

    @Override
    public GuessResponseDTO createGuess(GuessRequestDTO guessRequestDTO) {
        GuessResponseDTO response = new GuessResponseDTO();
        log.info("Guessed word by user " + guessRequestDTO.getGuessWord());
        Optional<Game> game = gameRepository.findById(guessRequestDTO.getGameId());

        if (game.isPresent()) {
            Guess guess = new Guess();
            guess.setGameId(guessRequestDTO.getGameId());
            guess.setGuessCount(guessRequestDTO.getGuessCount());
            guess.setGuessWord(guessRequestDTO.getGuessWord());
            if (guessRequestDTO.getGuessCount() > 20) {
                guess.setGuessCount(20);
                guess.setComplete(false);
                guess.setDescription("Sorry. You did not succeed. May the force be with you!");
            } else if (guessRequestDTO.getGuessWord().equalsIgnoreCase(game.get().getKeyword())) {
                guess.setComplete(true);
                guess.setDescription("Congratulations, you know the word “" + game.get().getKeyword() + "”!");
            } else {
                guess.setComplete(false);

                Pair<Integer, String> correctlyGuessedLetters = GuessCalculator
                        .calculateCorrectlyGuessedLetters(game.get().getKeyword(), guessRequestDTO.getGuessWord());

                String correctlyGuessedLettersInWrongPlace = GuessCalculator
                        .calculateCorrectlyGuessedLettersInWrongPlace(game.get().getKeyword(), guessRequestDTO.getGuessWord());

                String description = "correctly guessed letters = " + correctlyGuessedLetters.getFirst();
                description += " letter in the right place = " + correctlyGuessedLetters.getFirst() + " / " + correctlyGuessedLetters.getSecond() + " /";

                if (correctlyGuessedLettersInWrongPlace.length() == 0) {
                    description += " letters that were guessed correctly but in the wrong place = 0";
                } else {
                    description += " correctly guessed letters in the wrong place = " + correctlyGuessedLettersInWrongPlace.length() + " / " + correctlyGuessedLettersInWrongPlace + " /";
                }
                guess.setDescription(description);
            }
            guessRepository.save(guess);

            response.setComplete(guess.getComplete());
            response.setDescription(guess.getDescription());
        }
        return response;
    }
}
