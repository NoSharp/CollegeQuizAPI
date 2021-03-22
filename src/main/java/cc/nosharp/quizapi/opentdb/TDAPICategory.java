package cc.nosharp.quizapi.opentdb;

import lombok.Getter;

public enum TDAPICategory {



    SCIENCE_COMPUTER(18),
    SCIENCE(15);

    @Getter
    private final int id;

    TDAPICategory(int id){
        this.id = id;
    }
}
