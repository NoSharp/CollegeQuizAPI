package cc.nosharp.quizapi.data;


import cc.nosharp.quizapi.datamodels.QuestionList;
import cc.nosharp.quizapi.opentdb.OpenTDBAPI;
import cc.nosharp.quizapi.opentdb.TDBAPICategory;
import cc.nosharp.quizapi.opentdb.TDBAPIQuestionType;
import net.jodah.concurrentunit.Waiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class RedisHandlerTest {

    private static boolean ran = false;

    @Test
    public void getNewUUIDTest(){
        RedisHandler handler = RedisHandler.getRedisHandler();
        Assertions.assertEquals(handler.getNewUUID().length(), 36);
    }

    @Test
    public void doesUUIDExistTest() throws Throwable {
        RedisHandler handler = RedisHandler.getRedisHandler();
        String UUID = handler.getNewUUID();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final Waiter waiter = new Waiter();
        OpenTDBAPI.GetNewQuestions(10, TDBAPICategory.SCIENCE_COMPUTER, TDBAPIQuestionType.TRUEFALSE, callback ->{
            QuestionList generatedQuestions = QuestionList.fromTDBAPI(UUID, callback);
            handler.insertNewQuestionList(generatedQuestions);
            waiter.resume();
        });
        waiter.await(15000);
        Assertions.assertTrue(handler.doesUUIDExist(UUID));

    }

    @Test
    public void canDeleteQuestionListTest() throws Throwable{
        RedisHandler handler = RedisHandler.getRedisHandler();
        String UUID = handler.getNewUUID();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final Waiter waiter = new Waiter();
        OpenTDBAPI.GetNewQuestions(10, TDBAPICategory.SCIENCE_COMPUTER, TDBAPIQuestionType.TRUEFALSE, callback ->{
            QuestionList generatedQuestions = QuestionList.fromTDBAPI(UUID, callback);
            handler.insertNewQuestionList(generatedQuestions);
            waiter.resume();
        });
        waiter.await(15000);
        Assertions.assertTrue(handler.doesUUIDExist(UUID));
        handler.deleteGameUUID(UUID);
        Assertions.assertFalse(handler.doesUUIDExist(UUID));
    }

    @Test
    public void getsCorrectQuestionListFromUUID() throws Throwable {
        RedisHandler handler = RedisHandler.getRedisHandler();
        String UUID = handler.getNewUUID();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final Waiter waiter = new Waiter();
        OpenTDBAPI.GetNewQuestions(10, TDBAPICategory.SCIENCE_COMPUTER, TDBAPIQuestionType.TRUEFALSE, callback ->{
            QuestionList generatedQuestions = QuestionList.fromTDBAPI(UUID, callback);
            handler.insertNewQuestionList(generatedQuestions);
            waiter.resume();
        });
        waiter.await(15000);
        Assertions.assertTrue(handler.doesUUIDExist(UUID));

        QuestionList fromRedisQuestions = handler.getQuestionFromUUID(UUID);

        Assertions.assertNotNull(fromRedisQuestions);
        Assertions.assertEquals(fromRedisQuestions.getQuestions().size(), 10);
        Assertions.assertEquals(fromRedisQuestions.getQuestionListUUID(), handler.getGameKeyFromUUID(UUID));
    }
}
