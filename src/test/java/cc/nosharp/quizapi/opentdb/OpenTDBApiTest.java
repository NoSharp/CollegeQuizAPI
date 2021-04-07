package cc.nosharp.quizapi.opentdb;

import net.jodah.concurrentunit.Waiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OpenTDBApiTest {

    @Test
    public void GetNewQuestionsTest() throws Throwable {

        final Waiter waiter = new Waiter();
        OpenTDBAPI.GetNewQuestions(10, TDBAPICategory.SCIENCE_COMPUTER, TDBAPIQuestionType.TRUEFALSE, callback ->{
            Assertions.assertEquals(callback.size(), 10);
            waiter.resume();
        });
        waiter.await(15000);
    }
}
