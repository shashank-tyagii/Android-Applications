package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timer, question, score;
    TextView done;
    Button btn, text00, text01, text10, text11;
    ArrayList<Integer> answers = new ArrayList<>();
    CountDownTimer countdowntimer;
    int correctAnsPos;
    int totalAttempt,totalCorrect;

    public void chooseAnswer(View view){
        done.setVisibility(View.VISIBLE);
        if (String.valueOf(correctAnsPos).equals(view.getTag().toString())){     // User has clicked the correct answer

            done.setText("Correct Answer");
            totalCorrect++;
        } else {
            done.setText("Wrong Answer"); }
        totalAttempt++;
        score.setText(totalCorrect + "/" + totalAttempt);
        newQuestion();
    }

    public void newQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        question.setText(a + "+" + b);
        correctAnsPos = rand.nextInt(4);
        answers.clear();
        for (int i = 0; i <4 ; i++){
            if (i == correctAnsPos){
                answers.add(a+b);
            } else{
                int wrongAns = rand.nextInt(41);
                while (wrongAns == (a+b)) {                    // To verify if random number generated is same the correct answer
                    wrongAns = rand.nextInt(41);         // Sum is less than 40
                }
                answers.add(wrongAns);
            }
        }
        text00.setText(String.valueOf(answers.get(0)));
        text01.setText(String.valueOf(answers.get(1)));
        text10.setText(String.valueOf(answers.get(2)));
        text11.setText(String.valueOf(answers.get(3)));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn); done = findViewById(R.id.done); timer = findViewById(R.id.timer);
        question = findViewById(R.id.question); score = findViewById(R.id.score); text00 = findViewById(R.id.text00);
        text01 = findViewById(R.id.text01); text10 = findViewById(R.id.text10);text11 = findViewById(R.id.text11);
        btn.setVisibility(View.INVISIBLE);
        done.setVisibility(View.INVISIBLE);

        newQuestion();

                countdowntimer = new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long l) {
                        int timeLeft = (int) l/1000;
                        timer.setText(timeLeft + "s");
                    }

                    @Override
                    public void onFinish() {
                        done.setText("Done !");
                        btn.setVisibility(View.VISIBLE);
                        btn.setText("Play again !");
                    }
                }.start();


    }
    public void playAgain(View view ){

        newQuestion();
       totalAttempt = 0;
       totalCorrect = 0;
        score.setText(totalCorrect + "/" + totalAttempt);
        countdowntimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                int timeLeft = (int) l/1000;
                timer.setText(timeLeft + "s");
            }

            @Override
            public void onFinish() {
                done.setText("Done !");
                btn.setVisibility(View.VISIBLE);
                btn.setText("Play again !");
            }
        }.start();
        btn.setVisibility(View.INVISIBLE);
    }


}