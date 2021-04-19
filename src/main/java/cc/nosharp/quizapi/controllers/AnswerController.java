package cc.nosharp.quizapi.controllers;

import cc.nosharp.quizapi.data.RedisHandler;
import cc.nosharp.quizapi.datamodels.AnswerList;
import cc.nosharp.quizapi.datamodels.QuestionList;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnswerController {
    @CrossOrigin(origins = "*")
    @GetMapping("/api/v1/GetAnswer/{uuid}")
    @ResponseBody
    public AnswerList getAnswerList(@PathVariable("uuid") String uuid){
        RedisHandler handler = RedisHandler.getRedisHandler();
        // Validate UUID minimum length.
        if(uuid.length() < 22 && !handler.doesUUIDExist(uuid)) return null;
        QuestionList list = handler.getQuestionFromUUID(uuid);

        if(list == null) return null;
        handler.deleteGameUUID(uuid);
        return AnswerList.fromQuestionList(list);
    }
}
