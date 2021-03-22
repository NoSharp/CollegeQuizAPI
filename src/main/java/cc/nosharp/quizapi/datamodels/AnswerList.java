package cc.nosharp.quizapi.datamodels;

import lombok.Getter;

import java.util.HashMap;

public class AnswerList {

    /**
     * The integer is the Question number.
     * The boolean is the answer to the question.
     */
    @Getter
    private final HashMap<Integer, Boolean> AnswerList;

    /**
     * The UUID of the quiz.
     */
    @Getter
    private final String UUID;

    public AnswerList(String UUID, HashMap<Integer, Boolean> answerList){
        this.UUID = UUID;
        this.AnswerList = answerList;
    }

    public static AnswerList fromQuestionList(QuestionList list){
        String uuid = list.getQuestionListUUID();
        HashMap<Integer, Boolean> answerList = new HashMap<>();

        for (int i = 1; i < list.getQuestions().size(); i++) {
            boolean result = list.getQuestions().get(i).getAnswer().equals("True");
            answerList.put(i, result);
        }
        return new AnswerList(uuid, answerList);
    }
}
