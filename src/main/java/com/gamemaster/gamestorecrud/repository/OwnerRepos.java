package com.gamemaster.gamestorecrud.repository;

import com.gamemaster.gamestorecrud.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepos extends JpaRepository<Owner, Integer> {

}
