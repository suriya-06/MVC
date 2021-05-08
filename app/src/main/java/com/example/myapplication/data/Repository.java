package com.example.myapplication.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.myapplication.controller.AppController;
import com.example.myapplication.model.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    ArrayList<Question> arrayList=new ArrayList<>();
    String url="https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public List<Question> getQuestion(final AnswerListAsyncResponse callback){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0;i<=response.length();i++) {
                        Question question=new Question(response.getJSONArray(i).getString(0),response.getJSONArray(i).getBoolean(1));
                        arrayList.add(question);
                       }
                    if (null != callback) callback.processFinished(arrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "onErrorResponse: ");
            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return arrayList;

    }
}
