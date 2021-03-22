package cc.nosharp.quizapi.opentdb;

import lombok.Getter;

public enum TDAPIQuestionType {

    MULTIPLE("multiple"),
    TRUEFALSE("boolean");

    @Getter
    private final String type;

    TDAPIQuestionType(String type){
        this.type = type;
    }
}
