package com.example.course.config;


import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.Order;
import com.example.course.entities.User;
import com.example.course.entities.enums.OrderStatus;
import com.example.course.repositories.OrderRepository;
import com.example.course.repositories.UserRepository;

@Configuration // Mostra ao Spring que é uma classe especifica de configuração
@Profile("test") // Mostra que é uma configuração específica para o perfil de teste. O nome teste tem que ser igual la em resources aplication.properties spring.profiles.active=test
public class TestConfig implements CommandLineRunner{ // Essa interface é como um gatilho. O método que ela obriga você a criar (o método run) será executado automaticamente assim que o aplicativo terminar de subir
//serve por enquanto pra fazer o database seeding povoar o banco de dados
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.SHIPPED,  u1); 

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

    }






    
}
