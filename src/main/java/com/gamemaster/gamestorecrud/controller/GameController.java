package com.gamemaster.gamestorecrud.controller;

import com.gamemaster.gamestorecrud.model.Game;
import com.gamemaster.gamestorecrud.model.Owner;
import com.gamemaster.gamestorecrud.service.GameService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/getAll")
    public List<Game> list(){
        return gameService.listAll();
    }

    @PostMapping("/add")
    public String add(@RequestBody Game game){
        gameService.save(game);
        return "Novo Jogo Adicionado";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> get(@PathVariable Integer id){
        try{
            Game game=gameService.get(id);
            return new ResponseEntity<Game>(game, HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> update(@RequestBody Game game, @PathVariable Integer id){
        try{
            Game existingGame=gameService.get(id);
            gameService.save(game);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        gameService.delete(id);
        return "Deleted Game with ID " + id;
    }

    @PostMapping("/{id}/owner")
    public String addOwner(@RequestBody Owner owner, @PathVariable(value = "id") Integer gameId,@PathVariable Integer id){
        gameService.assignGameOwner(owner, owner.getId(), gameId);
        return "Novo Proprietario Adicionado";
    }
}