package org.example.hana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HanaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanaApplication.class, args);
    }

}
