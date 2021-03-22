package cc.nosharp.quizapi;

import cc.nosharp.quizapi.opentdb.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizapiApplication {

    public static void main(String[] args) {
        OpenTDBAPI.GetNewQuestions(10, TDBAPICategory.SCIENCE_COMPUTER, TDBAPIQuestionType.TRUEFALSE, callback -> {
            callback.keySet().forEach(x ->{
                System.out.println(callback.get(x));
            });
        });
        SpringApplication.run(QuizapiApplication.class, args);
    }

}
