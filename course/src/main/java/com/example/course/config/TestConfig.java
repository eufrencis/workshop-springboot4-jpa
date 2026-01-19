package com.example.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;

@Configuration // Mostra ao Spring que é uma classe especifica de configuração
@Profile("test") // Mostra que é uma configuração específica para o perfil de teste. O nome teste tem que ser igual la em resources aplication.properties spring.profiles.active=test
public class TestConfig implements CommandLineRunner{
//serve por enquanto pra fazer o database seeding povoar o banco de dados
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 

        userRepository.saveAll(Arrays.asList(u1, u2));
    }






    
}
