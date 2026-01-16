package com.example.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.entities.User;
import org.springframework.web.bind.annotation.GetMapping;



@RestController // diz ao Spring Boot que esta classe é um recurso web. Ela prepara a classe para responder requisições e garante que a resposta seja enviada no formato JSON.
@RequestMapping(value = "/users") // Aqui você define a "rota" ou o endereço do seu recurso.
public class UserResource {
    
    @GetMapping // Indica que este método responde a uma requisição do tipo GET (que é o padrão quando você apenas acessa uma URL para ler dados).
    public ResponseEntity <User> findAll(){ //É um objeto especial do Spring que serve para customizar a resposta HTTP. Ele permite que você controle não só os dados (o usuário), mas também os cabeçalhos e os códigos de status ("404 Not Found").
        User u = new User(1L, "Maria", "maria@gmail.com", "99999999", "12345");
        return ResponseEntity.ok().body(u);
        
    }

    
    
}
