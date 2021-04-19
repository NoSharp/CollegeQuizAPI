package cc.nosharp.quizapi.datamodels;

import cc.nosharp.quizapi.opentdb.OpenTDBAPI;
import cc.nosharp.quizapi.opentdb.TDBAPICategory;
import cc.nosharp.quizapi.opentdb.TDBAPIQuestionType;
import net.jodah.concurrentunit.Waiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionListMediatorTest {

    @Test
    public void mediatorCreatesInstance(){
        Assertions.assertNotNull(QuestionListMediator.getQuestionList());
    }

    @Test
    public void mediatorCreatesQuestions() throws Throwable{
        final Waiter waiter = new Waiter();
        QuestionListMediator mediator = QuestionListMediator.getQuestionList();
        Assertions.assertNotNull(mediator);

        mediator.createNewQuesitonList(createdQuestionList -> {
            Assertions.assertEquals(createdQuestionList.getQuestionList().size(),10);
            Assertions.assertEquals(createdQuestionList.getUUID().length(),36);
            waiter.resume();
        });
        waiter.await(15000);

    }
}
