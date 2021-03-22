package cc.nosharp.quizapi.datamodels;

import lombok.Getter;

public class QuestionAnswer {

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

    public QuestionAnswer(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
