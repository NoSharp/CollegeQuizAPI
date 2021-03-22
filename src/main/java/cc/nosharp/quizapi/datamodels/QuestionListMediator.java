package cc.nosharp.quizapi.datamodels;

import cc.nosharp.quizapi.data.RedisHandler;
import cc.nosharp.quizapi.opentdb.OpenTDBAPI;
import cc.nosharp.quizapi.opentdb.TDBAPICategory;
import cc.nosharp.quizapi.opentdb.TDBAPIQuestionType;
import cc.nosharp.quizapi.opentdb.TDBAPIResult;

import java.util.HashMap;

public class QuestionListMediator {

    private static QuestionListMediator INSTANCE;

    private QuestionListMediator(){

    }

    public static QuestionListMediator getQuestionList(){
        if(INSTANCE == null){
           INSTANCE = new QuestionListMediator();
        }
        return INSTANCE;
    }

    public void createNewQuesitonList(QuestionListCreationCallback newQuestionListCallback){
        RedisHandler redisHandler = RedisHandler.getRedisHandler();
        String uuid = redisHandler.getNewUUID();
        OpenTDBAPI.GetNewQuestions(10,
                TDBAPICategory.SCIENCE_COMPUTER,
                TDBAPIQuestionType.TRUEFALSE,
                callback-> {
                    QuestionList questionList = QuestionList.fromTDBAPI(uuid, callback);
                    redisHandler.insertNewQuestionList(questionList);
                    newQuestionListCallback.callback(questionList);
        });
    }
}
