package cc.nosharp.quizapi.opentdb;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class OpenTDBAPI {

    private static final String BaseURL = "https://opentdb.com/api.php";


    public static void GetNewQuestions(int amount,
                                       TDAPICategory category,
                                       TDAPIQuestionType type,
                                       GetNewQuestionCallback callback){
        String endpoint = "";
        endpoint += "amount=" + amount;
        endpoint += "&category=" + category.getId();
        endpoint += "&type=" + type.getType();
        final HttpGet getQuestions = new HttpGet(BaseURL + "?" + endpoint);

        CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        httpClient.start();
        httpClient.execute(getQuestions, new FutureCallback<HttpResponse>(){
            public void completed(HttpResponse response) {
                HttpEntity entity = response.getEntity();
                if(entity == null){
                    return;
                }
                String jsonData = null;
                try {
                    jsonData = EntityUtils.toString(entity);
                    callback.callback(getResultsFromJSON(jsonData));
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            public void failed(Exception e) {

            }

            public void cancelled() {

            }
        });

    }

    private static HashMap<Integer, TDAPIResult> getResultsFromJSON(String json){
        HashMap<Integer, TDAPIResult> results = new HashMap<>();
        JSONObject object = new JSONObject(json);
        JSONArray resultsArray = object.getJSONArray("results");
        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject jsonResult = resultsArray.getJSONObject(i);
            String category = jsonResult.getString("category");
            String type = jsonResult.getString("type");
            String question = jsonResult.getString("question");
            String correctAnswer = jsonResult.getString("correct_answer");
            results.put(i, new TDAPIResult(category, type, question, correctAnswer));
        }
        return results;
    }
}
