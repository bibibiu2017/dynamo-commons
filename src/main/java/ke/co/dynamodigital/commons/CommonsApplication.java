package ke.co.dynamodigital.commons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CommonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonsApplication.class, args);
    }


}
