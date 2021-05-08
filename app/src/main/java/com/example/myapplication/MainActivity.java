package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.myapplication.controller.AppController;
import com.example.myapplication.data.AnswerListAsyncResponse;
import com.example.myapplication.data.Repository;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.Question;

import org.json.JSONArray;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        new Repository().getQuestion(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                binding.textView2.setText(questionArrayList.get(currentIndex).getQuestion());
            }
        });
    }
}