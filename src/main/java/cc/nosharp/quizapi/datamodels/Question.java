package cc.nosharp.quizapi.datamodels;

import lombok.Getter;

public class Question {

    /**
     * The question asked.
     */
    @Getter
    private final String question;

    /**
     * The answer to the question.
     */
    @Getter
    private final String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
