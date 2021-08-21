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
    //    Button showAnswer;
    TextView questionTextView;
    Question[] questions = {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false)
    };
    int questionIndex = 0;
    int correctAnswer = 0;//переменная, в которой будет храниться количество правильных ответов
    boolean[] answersUser = new boolean[questions.length];//создаём массив, в котором будем хранить ответы пользователя

    @Override
//Событие, которое срабатывает при повороте экрана. Сохраняем тут значение переменных, чтобы их потом восстановить
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("SYSTEM INFO:", "Вызван метод onSaveInstanceState");
        savedInstanceState.putInt("currentQuestionIndex", questionIndex);//Сохраняем текущий номер вопроса
        savedInstanceState.putInt("correctAnswer", correctAnswer);//Сохраняем количество правильных ответов
        savedInstanceState.putBooleanArray("answersUser", answersUser);//Сохраняем массив с ответами пользователя
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SYSTEM INFO:", "Вызван метод onCreate");
        if (questionIndex == 0)
            correctAnswer = 0;//сбрасываем количество правильных ответов, если у нас первый вопрос. Это сделано, чтобы запускать приложение сколько угодно раз
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {//восстанавливаем значения при возвращении после поворота экрана
            questionIndex = savedInstanceState.getInt("currentQuestionIndex", 0);//Восстанавливаем номер вопроса
            correctAnswer = savedInstanceState.getInt("correctAnswer", 0);//Восстанавливаем количество правильных ответов
            answersUser = savedInstanceState.getBooleanArray("answersUser");//Восстанавливаем массив с ответами пользователя
        }
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
//        showAnswer = findViewById(R.id.showAnswer);
        questionTextView = findViewById(R.id.questionTextView);

        questionTextView.setText(questions[questionIndex].getQuestionText());

/*        //нажатие кнопки Показать ответы
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("answer", questions[questionIndex].isAnswerTrue());
                startActivity(intent);
            }
        });*/

        //нажатие кнопки ДА
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        //нажатие кнопки НЕТ
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
    }

    //метод, который выводит на экран сообщение Правильно!/Не правильно!
    public void checkAnswer(boolean btn) {
        if ((questions[questionIndex].isAnswerTrue() && btn) || (!questions[questionIndex].isAnswerTrue() && !btn)) {
            Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
            correctAnswer++;//если ответ правильный увеличиваем correctAnswer
            answersUser[questionIndex] = true;//сохраняем ответ пользователя в массиве
        } else {
            Toast.makeText(MainActivity.this, "Не правильно!", Toast.LENGTH_SHORT).show();
            answersUser[questionIndex] = false;//сохраняем ответ пользователя в массиве
        }
        if (questionIndex == questions.length - 1) {//при достижении последнего вопроса вызываем активность AnswerActivity
            Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
            intent.putExtra("correctAnswer", correctAnswer);//сохраняем значение correctAnswer для передачи между активностями
            intent.putExtra("answersUser", answersUser);//сохраняем значения массива answersUser(ответы пользователя) для передачи между активностями
            startActivity(intent);
        }
        questionIndex = (questionIndex + 1) % questions.length;//хитрым образом увеличиваем questionIndex на единицу, при этом при достижении последнего вопроса(questions.length) questionIndex сбрасывается в НОЛЬ
        questionTextView.setText(questions[questionIndex].getQuestionText());//выводим на экран следующий вопрос
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("SYSTEM INFO:", "Вызван метод onStart");
//        correctAnswer = 0;//сбрасываем количество правильных ответов на ноль, чтобы отвечать на вопросы ещё раз - это здесь не сработает, т.к. onStart срабатывает каждый раз при повороте экрана
        if (questionIndex == 0)
            correctAnswer = 0;//сбрасываем количество правильных ответов, если у нас первый вопрос. Это сделано, чтобы запускать приложение сколько угодно раз
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