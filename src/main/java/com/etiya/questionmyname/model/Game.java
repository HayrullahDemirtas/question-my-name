package com.etiya.questionmyname.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_id")
    private Integer gameId;

    @Column(nullable = false, name = "player_name")
    private String playerName;

    @Column(nullable = false, name = "game_count")
    private Integer gameCount;

    @Column(nullable = false, name = "keyword")
    private String keyword;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "game_id")
    private List<Guess> guessList = new ArrayList<>();
}
