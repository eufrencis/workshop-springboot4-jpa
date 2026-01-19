package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> { // o JpaRepositery precisa da entidade e de quem é o ID 

    /*
    
1. O que é um Repository?
Repository é a Camada de Acesso a Dados (Data Access Layer). Ela fica abaixo do Service e acima do banco de dados. 
Sua única função é realizar operações no banco (salvar, buscar, deletar). 


2. A Herança Mágica: extends JpaRepository<User, Long>
Ao estender o JpaRepository, você está herdando (ganhando) um monte de métodos prontos. 

User: Você está dizendo ao Spring que este repositório é para gerenciar a entidade User. 

Long: Você está avisando que o tipo do ID da classe User é Long. 

3. Por que é uma interface e não uma class?
Você deve ter reparado que não escreveu nenhum código dentro dela. Isso acontece porque o Spring Data JPA cria a implementação para você em tempo de execução. 

Ao definir a interface, o Spring gera automaticamente comandos como findAll(), findById(), save() e delete() */



    



}
