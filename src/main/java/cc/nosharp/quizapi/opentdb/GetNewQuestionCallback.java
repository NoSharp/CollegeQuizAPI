package cc.nosharp.quizapi.opentdb;

import java.util.HashMap;

public interface GetNewQuestionCallback {

    void callback(HashMap<Integer,TDAPIResult> callback);
}
