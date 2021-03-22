package cc.nosharp.quizapi.opentdb;

import lombok.Getter;

public class TDBAPIResult {

    /**
     * The Category of the question
     */
    @Getter
    private final String category;

    /**
     * The type of question (boolean or multiple)
     */
    @Getter
    private final String type;

    /**
     * The actual question
     */
    @Getter
    private final String question;

    /**
     * The correct answer
     */
    @Getter
    private final String correctAnswer;

    public TDBAPIResult(String category, String type, String question, String correctAnswer){
        this.category = category;
        this.type = type;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }
}
