package com.etiya.questionmyname.repository;

import com.etiya.questionmyname.model.Guess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGuessRepository extends JpaRepository<Guess, Integer> {
}
