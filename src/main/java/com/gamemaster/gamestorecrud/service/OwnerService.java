package com.gamemaster.gamestorecrud.service;

import com.gamemaster.gamestorecrud.model.Owner;
import com.gamemaster.gamestorecrud.repository.OwnerRepos;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepos ownerRepos;

    // Método retornar todos os jogos
    public List<Owner> listAll(){
        return ownerRepos.findAll();
    }

    // Método salva jogo
    public void save(Owner owner){
        ownerRepos.save(owner);
    }

    // Método retorna 1 jogo pelo ID
    public Owner get(Integer id){
        return ownerRepos.findById(id).get();
    }

    // Método remove 1 jogo pelo ID
    public void delete(Integer id){
        ownerRepos.deleteById(id);
    }


}
