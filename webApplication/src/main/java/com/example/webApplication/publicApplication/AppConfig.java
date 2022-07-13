package com.example.webApplication.publicApplication;

import com.example.webApplication.fetchloggedusers.ActiveUserStorage;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
    }

    @Bean
    public ActiveUserStorage activeUserStorage() {
        return new ActiveUserStorage();
    }

    /*
        Not really sure if it's supposed to be declared as a bean,
        but i put it here to use anywhere in the code in the case when
        mapping a list is needed.
     */
//    @Bean
//    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
//        return source
//                .stream()
//                .map(element -> modelMapper().map(element, targetClass))
//                .collect(Collectors.toList());
//    }
}
