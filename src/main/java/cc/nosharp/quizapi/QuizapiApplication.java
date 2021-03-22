package cc.nosharp.quizapi;

import cc.nosharp.quizapi.data.RedisHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizapiApplication {

    public static void main(String[] args) {
        // Initialize the redis handler.
        RedisHandler.getRedisHandler();

        SpringApplication.run(QuizapiApplication.class, args);
    }

}
