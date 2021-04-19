package cc.nosharp.quizapi.opentdb;

import lombok.Getter;

public enum TDBAPICategory {
    SCIENCE_COMPUTER(18),
    SCIENCE(15);

    @Getter
    private final int id;

    TDBAPICategory(int id){
        this.id = id;
    }
}
