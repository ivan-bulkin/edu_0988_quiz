package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    private TextView answerTextView;
    private boolean isAnswerTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        isAnswerTrue = getIntent().getBooleanExtra("answer", false);

        answerTextView = findViewById(R.id.answerTextView);
/*        if (isAnswerTrue)
            answerTextView.setText(R.string.yes);
        else
            answerTextView.setText(R.string.no);*/
        //тоже самое, что выше только с помощью тернарного оператора и одной строчкой !!!
        //в тернарном операторе мы можем выполнить только одну инструкцию!!!
        answerTextView.setText(isAnswerTrue ? R.string.yes : R.string.no);
    }
}