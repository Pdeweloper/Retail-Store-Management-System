package com.rsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 */

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.rsm"
})
public class App {
    public static void main(String[] args) {
        
    	SpringApplication.run(App.class, args);
    }
}
