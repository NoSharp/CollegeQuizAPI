package cc.nosharp.quizapi;

import cc.nosharp.quizapi.data.RedisHandler;
import cc.nosharp.quizapi.datamodels.Question;
import cc.nosharp.quizapi.datamodels.QuestionList;
import cc.nosharp.quizapi.datamodels.QuestionListMediator;
import cc.nosharp.quizapi.opentdb.*;
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
