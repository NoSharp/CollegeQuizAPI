package cc.nosharp.quizapi.controllers;

import cc.nosharp.quizapi.datamodels.Question;
import cc.nosharp.quizapi.datamodels.QuestionList;
import cc.nosharp.quizapi.datamodels.QuestionListProtos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.UUID;

@Controller
public class QuestionListController {

    @GetMapping("/api/v1/GetNewQuestionList")
    @ResponseBody
    private QuestionList getNewQuestionList(){

        return null;
    }
}
