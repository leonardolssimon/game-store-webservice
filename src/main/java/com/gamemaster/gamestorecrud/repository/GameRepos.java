package com.gamemaster.gamestorecrud.repository;

import com.gamemaster.gamestorecrud.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepos extends JpaRepository<Game, Integer> {

}
