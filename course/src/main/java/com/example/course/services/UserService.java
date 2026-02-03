package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List <User> findAll (){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional <User> obj = repository.findById(id); // Optional é como uma caixa que pode ter um tenis ou nao para evitar o erro de nullpointerExeption
        // ele te retorna a caixa vazia ou nao se tiver vazia nao trava imediatamente e o metodo get pega oq esta la dentro se tiver
        return obj.get();
    }

    public User insert(User obj){
       return repository.save(obj);

    }





    

}
