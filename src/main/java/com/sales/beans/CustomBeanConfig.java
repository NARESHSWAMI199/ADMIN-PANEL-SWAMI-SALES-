package com.sales.beans;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class CustomBeanConfig {


    @Bean
    public Logger getLogger(){
        return Logger.getGlobal();
    }
}
