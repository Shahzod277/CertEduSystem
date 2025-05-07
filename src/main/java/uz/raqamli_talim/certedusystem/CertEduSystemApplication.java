package uz.raqamli_talim.certedusystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CertEduSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CertEduSystemApplication.class, args);
    }

}
