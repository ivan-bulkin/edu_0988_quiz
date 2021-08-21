package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {
    private TextView answerTextView;
    private TextView answersUserTextView;
    private int correctAnswer;
    private Button startAgainBtn;
    private boolean[] answersUser;//создаём массив, в котором будем хранить ответы пользователя
//Так не получилось(будем ещё передавать количество элементов массива и создадим его в onCreate):
//    boolean[] answersUser = new boolean[5];//создаём массив, в котором будем хранить ответы пользователя
//    private ArrayList<Boolean> answersUser = new ArrayList();//создаём массив, в котором будем хранить ответы пользователя создаём здесь ArrayList чтобы не мучиться с передачей размерности массива

    //    private boolean isAnswerTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        startAgainBtn = findViewById(R.id.startAgainBtn);

        correctAnswer = getIntent().getIntExtra("correctAnswer", 0);//получаем количество правильных ответов от другой активности
        answersUser = getIntent().getBooleanArrayExtra("answersUser");//Получаем элементы массива с ответами пользователя
        answerTextView = findViewById(R.id.answerTextView);//находим элемент answerTextView на активности activity_answer
        answerTextView.setText(Integer.toString(correctAnswer));//выводим количество правильных ответов
        answersUserTextView = findViewById(R.id.answersUserTextView);//находим элемент answersUserTextView на активности activity_answer
        answersUserTextView.setText("Вы ответили на вопросы:" + "\n");
        for (int i = 0; i < answersUser.length; i++) {
            answersUserTextView.append((i + 1) + " " + (answersUser[i] ? "Правильно" : "Не правильно") + "\n");//используем модный тернарный оператор
        }
        //нажатие кнопки Начать заново,
        startAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnswerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

//        isAnswerTrue = getIntent().getBooleanExtra("answer", false);
/*        if (isAnswerTrue)
            answerTextView.setText(R.string.yes);
        else
            answerTextView.setText(R.string.no);*/
        //тоже самое, что выше только с помощью тернарного оператора и одной строчкой !!!
        //в тернарном операторе мы можем выполнить только одну инструкцию!!!
//        answerTextView.setText(isAnswerTrue ? R.string.yes : R.string.no);


/*
    Лабораторная работа №12 (Android) завершение разработки первого приложения

        Задание

        * 1) Записываем строку "Вопрос - ваш ответ да/нет" в массив
        * 2) Складываем правильные ответы в переменню int
        * 3) Проверяем, что вопрос последний
        * 4) Если вопрос последний, то создаём интент
        * 5) Добавляем дополнение в Интент (массив с вопросамии ответами)
        * 6) Добавляем дополнение в Интент (int с числом правильных ответов)
        * 7) Запускаем активность
        * 8) На запущенной активности вывести строки из массива, тем самым отобразив вопросы и ответы пользователя*/
