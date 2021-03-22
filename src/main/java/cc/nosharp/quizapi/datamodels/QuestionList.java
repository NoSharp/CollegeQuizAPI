package cc.nosharp.quizapi.datamodels;

import cc.nosharp.quizapi.opentdb.TDBAPIResult;
import lombok.Getter;

import java.util.HashMap;

public class QuestionList {

    /**
     * The Question numbers and the results of the questions.
     */
    @Getter
    private final HashMap<Integer, QuestionAnswer> questions;

    /**
     * The Identifier of the questions.
     */
    @Getter
    private final String questionListUUID;

    public QuestionList(String questionListUUID, HashMap<Integer, QuestionAnswer> questions){
        this.questionListUUID = questionListUUID;
        this.questions = questions;
    }

    public static QuestionList fromProtoBuffer(String UUID, QuestionListProtos.QuestionListProto buffer){
        HashMap<Integer, QuestionAnswer> questionsFromProtos = new HashMap<>();
        int questionCount = 0;
        for(QuestionListProtos.Question questionProto : buffer.getQuestionsList()){
            questionCount++;
            String answer = questionProto.getAnswer();
            String question = questionProto.getQuestion();
            questionsFromProtos.put(questionCount, new QuestionAnswer(question, answer));
        }
        return new QuestionList(UUID, questionsFromProtos);
    }

    public String toProtocolBuffer(){
        QuestionListProtos.QuestionListProto.Builder protoBuilder = QuestionListProtos.QuestionListProto.newBuilder();

        for (int key : this.questions.keySet()){
            QuestionAnswer question = this.questions.get(key);
            QuestionListProtos.Question questionProto = QuestionListProtos.Question.newBuilder()
                .setQuestion(question.getQuestion())
                .setAnswer(question.getAnswer())
            .build();
            protoBuilder.addQuestions(key, questionProto);
        }
        QuestionListProtos.QuestionListProto builtProto = protoBuilder.build();

        return builtProto.toString();
    }

    public static QuestionList fromTDBAPI(String UUID,HashMap<Integer, TDBAPIResult> data){
        HashMap<Integer, QuestionAnswer> questionList = new HashMap<>();

        for (int i = 0; i < data.keySet().size(); i++) {
            TDBAPIResult result = data.get(i);
            questionList.put(i, new QuestionAnswer(result.getQuestion(), result.getCorrectAnswer()));
        }

        return new QuestionList(UUID, questionList);
    }

}
