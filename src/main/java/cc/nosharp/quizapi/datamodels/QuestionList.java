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

    public static QuestionList fromProtoBuffer(String UUID, QuestionListProtos.QuestionListProto buffer){
        HashMap<Integer, Question> questionsFromProtos = new HashMap<>();
        int questionCount = 0;
        for(QuestionListProtos.Question questionProto : buffer.getQuestionsList()){
            questionCount++;
            String answer = questionProto.getAnswer();
            String question = questionProto.getQuestion();
            questionsFromProtos.put(questionCount, new Question(question, answer));
        }
        return new QuestionList(UUID, questionsFromProtos);
    }

    public String toProtocolBuffer(){
        QuestionListProtos.QuestionListProto.Builder protoBuilder = QuestionListProtos.QuestionListProto.newBuilder();

        for (int key : this.questions.keySet()){
            Question question = this.questions.get(key);
            QuestionListProtos.Question questionProto = QuestionListProtos.Question.newBuilder()
                .setQuestion(question.getQuestion())
                .setAnswer(question.getAnswer())
            .build();
            protoBuilder.addQuestions(key, questionProto);
        }
        QuestionListProtos.QuestionListProto builtProto = protoBuilder.build();
        return builtProto.toByteString().toStringUtf8();
    }
}
