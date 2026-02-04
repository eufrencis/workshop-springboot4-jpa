package com.example.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.course.entities.User;
import com.example.course.services.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


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

    /*
     * 1. ServletUriComponentsBuilder -> Fábrica que constrói URLs.
     * 2. .fromCurrentRequest()       -> Captura o endereço atual (ex: localhost:8080/users).
     * 3. .path("/{id}")              -> Cria um "espaço vazio" para o ID no final da URL.
     * 4. .buildAndExpand(obj.getId())-> Preenche o "espaço vazio" com o ID real do objeto salvo.
     * 5. .toUri()                    -> Converte o texto montado em um objeto de endereço (URI).
     * RESULTADO: Se salvou o User 5, a URI vira "http://localhost:8080/users/5".
     * 6. URI cria uma variavel pra guardar endereço web completo gerado
     */
    @PostMapping
    public ResponseEntity <User> insert(@RequestBody User obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        //
        return ResponseEntity.created(uri).body(obj);

    }

    /*
     * 1. ResponseEntity<Void> -> Vou retornar uma resposta HTTP, mas o corpo do JSON será VAZIO.
     * 2. @PathVariable Long id -> Pega o ID que vem na URL (ex: /users/5).
     * 3. service.delete(id)    -> Chama a lógica no banco para apagar.
     * 4. .noContent()          -> Prepara o status HTTP 204 (Sucesso sem conteúdo).
     * 5. .build()              -> "Fecha o pacote". Gera a resposta final para enviar ao Postman/IntelliJ.
     */

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    
    

    
    
}
