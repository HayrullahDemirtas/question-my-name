package com.etiya.questionmyname.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "guess")
public class Guess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guess_id")
    private Integer guessId;

    @Column(nullable = false, name = "game_id")
    private Integer gameId;

    @Column(nullable = false, name = "guess_count")
    private Integer guessCount;

    @Column(nullable = false, name = "complete")
    private Boolean complete;

    @Column(nullable = false, name = "guess_word")
    private String guessWord;

    @Column(nullable = false, name = "description")
    private String description;
}
