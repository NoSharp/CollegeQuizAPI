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
    @GetMapping("/api/v1/GetNewQuestionList")
    @ResponseBody
    public DeferredResult<QuestionOnlyList> getNewQuestionList(){
        DeferredResult<QuestionOnlyList> out = new DeferredResult<>();
        QuestionListMediator.getQuestionList().createNewQuesitonList(out::setResult);

        return out;
    }

    @GetMapping("/api/v1/GetAnswer/{uuid}")
    @ResponseBody
    public AnswerList getAnswerList(@PathVariable("uuid") String uuid){
        QuestionList list = RedisHandler.getRedisHandler().getQuestionFromUUID(uuid);

        if(list == null) return null;

        return AnswerList.fromQuestionList(list);
    }
}
