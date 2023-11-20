package com.gamemaster.gamestorecrud.controller;

import com.gamemaster.gamestorecrud.model.Owner;
import com.gamemaster.gamestorecrud.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/getAll")
    public List<Owner> list(){
        return ownerService.listAll();
    }

    @PostMapping("/add")
    public String add(@RequestBody Owner owner){
        ownerService.save(owner);
        return "Novo Jogo Adicionado";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> get(@PathVariable Integer id){
        try{
            Owner owner=ownerService.get(id);
            return new ResponseEntity<Owner>(owner, HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<Owner>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> update(@RequestBody Owner owner, @PathVariable Integer id){
        try{
            Owner existingOwner=ownerService.get(id);
            ownerService.save(owner);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<Owner>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        ownerService.delete(id);
        return "Deleted Owner with ID " + id;
    }
}
