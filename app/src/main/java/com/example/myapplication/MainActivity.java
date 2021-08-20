package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button yesBtn;
    Button noBtn;
    Button showAnswer;
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
        Log.d("SYSTEM INFO:", "Вызван метод onSaveInstanceState");
        savedInstanceState.putInt("CurrentQuestionIndex", questionIndex);//Сохраняем текущий номер вопроса
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SYSTEM INFO:", "Вызван метод onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
            questionIndex = savedInstanceState.getInt("CurrentQuestionIndex", 0);//Восстанавливаем номер вопроса
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        showAnswer = findViewById(R.id.showAnswer);
        questionTextView = findViewById(R.id.questionTextView);

        questionTextView.setText(questions[questionIndex].getQuestionText());

        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("answer", questions[questionIndex].isAnswerTrue());
                startActivity(intent);
            }
        });

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
    }

    public void checkAnswer(boolean btn) {
        if ((questions[questionIndex].isAnswerTrue() && btn) || (!questions[questionIndex].isAnswerTrue() && !btn)) {
            Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Не правильно!", Toast.LENGTH_SHORT).show();

        }
        questionIndex = (questionIndex + 1) % questions.length;
        questionTextView.setText(questions[questionIndex].getQuestionText());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("SYSTEM INFO:", "Вызван метод onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("SYSTEM INFO:", "Вызван метод onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("SYSTEM INFO:", "Вызван метод onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("SYSTEM INFO:", "Вызван метод onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SYSTEM INFO:", "Вызван метод onDestroy");
    }
}