package cc.nosharp.quizapi.controllers;

import cc.nosharp.quizapi.datamodels.QuestionList;
import cc.nosharp.quizapi.datamodels.QuestionListMediator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class QuestionListController {

    @Async
    @GetMapping("/api/v1/GetNewQuestionList")
    @ResponseBody
    public DeferredResult<QuestionList> getNewQuestionList(){
        DeferredResult<QuestionList> out = new DeferredResult<>();
        QuestionListMediator.getQuestionList().createNewQuesitonList(out::setResult);

        return out;
    }
}
