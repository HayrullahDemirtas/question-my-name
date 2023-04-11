package com.etiya.questionmyname;


import com.etiya.questionmyname.dto.GuessRequestDTO;
import com.etiya.questionmyname.model.Game;
import com.etiya.questionmyname.model.Guess;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class QuestionMyNameApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllGameByPlayerName() throws Exception {
		String playerName = "hayrullah";
		mockMvc.perform(get("/api/v1/gameList?playerName={playerName}", playerName)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].playerName").exists())
				.andExpect(jsonPath("$[*].gameCount").exists())
				.andExpect(jsonPath("$[*].keyword").exists())
				.andExpect(jsonPath("$[*].guessList").exists());

	}

	@Test
	public void testGetGameByGameId() throws Exception {
		String gameId = "37";
		mockMvc.perform(get("/api/v1/game?gameId={gameId}", gameId)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.playerName").exists())
				.andExpect(jsonPath("$.gameCount").exists())
				.andExpect(jsonPath("$.keyword").exists())
				.andExpect(jsonPath("$.guessList").exists());

	}

	@Test
	public void testCreateGame() throws Exception {
		String playerName = "hakan";
		List<Guess> guessList = new ArrayList<>();
		Game game = new Game();
		game.setGameId(38);
		game.setPlayerName(playerName);
		game.setGameCount(1);
		game.setKeyword("sharpener");
		game.setGuessList(guessList);

		mockMvc.perform(post("/api/v1/createGame?playerName={playerName}", playerName)
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(game)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.playerName").exists())
				.andExpect(jsonPath("$.keyword").exists())
				.andExpect(jsonPath("$.gameId").exists())
				.andExpect(jsonPath("$.gameCount").exists())
				.andExpect(jsonPath("$.guessList").isEmpty());
	}

	@Test
	public void testCreateGuess() throws Exception {
		GuessRequestDTO guessRequestDTO = new GuessRequestDTO(44, 1, "war");

		mockMvc.perform(post("/api/v1/createGuess")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(guessRequestDTO)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.complete").exists())
				.andExpect(jsonPath("$.description").exists());
	}

	public static String asJsonString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
