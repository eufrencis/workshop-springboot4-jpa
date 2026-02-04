package com.example.course.services;

import java.util.List;
import java.util.Optional;

import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj){
        return repository.save(obj);
    }

    public void  delete (Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional //garante que a conexão com o banco fique aberta durante todo o processo de atualização.
    public User update (Long id, User obj){
        try {
            User entity = repository.getReferenceById(id); // findbyId vai no bando de dados e tras o objeto o referenc so prepara o objeto monitorado pra alterar e dps mudar o banco de dados
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }

    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());


    }


}
