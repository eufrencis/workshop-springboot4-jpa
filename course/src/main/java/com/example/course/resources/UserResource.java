package com.example.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.entities.User;
import com.example.course.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController // é a junção de @controller que lida com requisições web e de @responseBody que indica que a resposta de cada metodo deve ser escrita diretamente no corpo da resposta HTTP convertendo diretamente dem JSON;
@RequestMapping(value = "/users") // Aqui você define a "rota" ou o endereço do seu recurso.
public class UserResource {

    @Autowired
    private UserService service;
    
    @GetMapping // Indica que este método responde a uma requisição do tipo GET (que é o padrão quando você apenas acessa uma URL para ler dados).
    public ResponseEntity <List<User>> findAll(){ //É um objeto especial do Spring que serve para customizar a resposta HTTP. Ele permite que você controle não só os dados (o usuário), mas também os cabeçalhos e os códigos de status ("404 Not Found").
       List <User> list = service.findAll();
       
        return ResponseEntity.ok().body(list);
        
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <User> findById (@PathVariable Long id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    

    
    
}
