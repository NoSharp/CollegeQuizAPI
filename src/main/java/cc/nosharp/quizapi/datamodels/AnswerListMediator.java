package cc.nosharp.quizapi.datamodels;

import cc.nosharp.quizapi.data.RedisHandler;

public class AnswerListMediator {
    private static AnswerListMediator INSTANCE;

    private AnswerListMediator(){

    }

    public static AnswerListMediator getQuestionList(){
        if(INSTANCE == null){
            INSTANCE = new AnswerListMediator();
        }
        return INSTANCE;
    }


    public AnswerList getAnswerList(String UUID){
        RedisHandler handler = RedisHandler.getRedisHandler();
        QuestionList list = handler.getQuestionFromUUID(UUID);
        return AnswerList.fromQuestionList(list);
    }

}
