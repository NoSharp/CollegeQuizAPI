package cc.nosharp.quizapi.controllers;

import cc.nosharp.quizapi.data.RedisHandler;
import cc.nosharp.quizapi.datamodels.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class QuestionListController {

    @Async
    @CrossOrigin(origins = "*")
    @GetMapping("/api/v1/GetNewQuestionList")
    @ResponseBody
    public DeferredResult<QuestionOnlyList> getNewQuestionList(){
        DeferredResult<QuestionOnlyList> out = new DeferredResult<>();

        QuestionListMediator.getQuestionList().createNewQuesitonList(out::setResult);

        return out;
    }

}
