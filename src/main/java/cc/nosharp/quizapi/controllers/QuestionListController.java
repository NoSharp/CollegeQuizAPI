package cc.nosharp.quizapi.controllers;

import cc.nosharp.quizapi.datamodels.QuestionList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionListController {

    @GetMapping("/api/v1/GetNewQuestionList")
    @ResponseBody
    private QuestionList getNewQuestionList(){
        return null;
    }
}
