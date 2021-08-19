package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button yesBtn;
    Button noBtn;
    TextView questionTextView;
    Question[] questions = {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false)
    };
    int questionIndex = 0;

    @Override//Событие, которое срабатывает при повороте экрана
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("CurrentQuestionIndex", questionIndex);//Сохраняем текущий номер вопроса
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
            questionIndex = savedInstanceState.getInt("CurrentQuestionIndex", 0);//Восстанавливаем номер вопроса
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        questionTextView = findViewById(R.id.questionTextView);

        questionTextView.setText(questions[questionIndex].getQuestionText());

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions[questionIndex].isAnswerTrue()) {
                    Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Не правильно!", Toast.LENGTH_SHORT).show();

                }
                questionIndex = (questionIndex + 1) % questions.length;
                questionTextView.setText(questions[questionIndex].getQuestionText());
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!questions[questionIndex].isAnswerTrue()) {
                    Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Не правильно!", Toast.LENGTH_SHORT).show();
                }
                questionIndex = (questionIndex + 1) % questions.length;
                questionTextView.setText(questions[questionIndex].getQuestionText());
            }
        });
    }
}