package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

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

    public void  delete (Long id){
        repository.deleteById(id);
    }

    @Transactional
    public User update (Long id, User obj){
        User entity = repository.getReferenceById(id); // findbyId vai no bando de dados e tras o objeto o referenc so prepara o objeto monitorado pra alterar e dps mudar o banco de dados
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());


    }


}
