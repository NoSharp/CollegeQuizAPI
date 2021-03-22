package cc.nosharp.quizapi.opentdb;

import lombok.Getter;

public enum TDBAPIQuestionType {

    MULTIPLE("multiple"),
    TRUEFALSE("boolean");

    @Getter
    private final String type;

    TDBAPIQuestionType(String type){
        this.type = type;
    }
}
