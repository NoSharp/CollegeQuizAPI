package cc.nosharp.quizapi.datamodels;

import lombok.Getter;

import java.util.HashMap;

public class QuestionOnlyList {

    /**
     * Just a number and question list.
     */
    @Getter
    private final HashMap<Integer, String> QuestionList;

    /**
     * The UUID of the questions.
     */
    @Getter
    private final String UUID;


    public QuestionOnlyList(String UUID, HashMap<Integer, String> questionList){
        this.UUID = UUID;
        this.QuestionList = questionList;
    }

    public static QuestionOnlyList fromQuestionList(QuestionList list){
        String uuid = list.getQuestionListUUID();
        HashMap<Integer, String> questions = new HashMap<>();
        for (int i = 0; i < list.getQuestions().size(); i++) {
            questions.put(i, list.getQuestions().get(i).getQuestion());
        }
        return new QuestionOnlyList(uuid, questions);
    }
}
