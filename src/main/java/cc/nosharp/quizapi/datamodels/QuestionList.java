package cc.nosharp.quizapi.datamodels;

import lombok.Getter;

import java.util.HashMap;

public class QuestionList {

    /**
     * The Question numbers and the results of the questions.
     */
    @Getter
    private final HashMap<Integer, Question> questions;

    /**
     * The Identifier of the questions.
     */
    @Getter
    private final String questionListUUID;

    public QuestionList(String questionListUUID, HashMap<Integer, Question> questions){
        this.questionListUUID = questionListUUID;
        this.questions = questions;
    }
}
