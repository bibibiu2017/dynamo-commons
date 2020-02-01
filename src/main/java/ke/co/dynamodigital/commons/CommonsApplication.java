package ke.co.dynamodigital.commons;

import ke.co.dynamodigital.commons.services.MessageSender;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static ke.co.dynamodigital.commons.utils.ObjectUtils.writeJson;

@Slf4j
@RestController
@EnableAspectJAutoProxy
@RequiredArgsConstructor
@SpringBootApplication
public class CommonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonsApplication.class, args);
    }

    final private MessageSender messageSender;

    @PostMapping("test")
    public void test(@RequestBody Body body) {
        log.debug("Body: {}",writeJson(body));
        messageSender.send(body,body.getTarget());
    }

    @Data
    static class Body {
        String target;
        String message;
    }

}
