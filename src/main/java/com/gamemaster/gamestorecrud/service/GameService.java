package com.gamemaster.gamestorecrud.service;

import com.gamemaster.gamestorecrud.model.Game;
import com.gamemaster.gamestorecrud.model.Owner;
import com.gamemaster.gamestorecrud.repository.GameRepos;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GameService {
    @Autowired
    private GameRepos gameRepos;

    @Autowired
    private OwnerService ownerService;

    // Método retornar todos os jogos
    public List<Game> listAll(){
        return gameRepos.findAll();
    }

    // Método salva jogo
    public void save(Game game){
        gameRepos.save(game);
    }

    // Método retorna 1 jogo pelo ID
    public Game get(Integer id){
        return gameRepos.findById(id).get();
    }

    // Método remove 1 jogo pelo ID
    public void delete(Integer id){
        gameRepos.deleteById(id);
    }

    public ResponseEntity<Owner> assignGameOwner(@RequestBody Owner owner, @PathVariable Integer ownerId, @PathVariable Integer id){
        try{
            Owner existingOwner = ownerService.get(ownerId);

            if (existingOwner != null) {
                Game game = this.get(id);
                game.setOwnerId(ownerId);
                this.save(game);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<Owner>(HttpStatus.NOT_FOUND);
        }
    }
}
