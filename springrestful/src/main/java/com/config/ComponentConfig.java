package com.config;

import com.peoplespot.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yuchen on 2018/11/5.
 */
@Configuration
public class ComponentConfig {

    @Bean
    public UserService userService(){
        UserService userService = new UserService();
        return userService;
    }
}
