package com.example.zadanie2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;

    private Question[] questions = new Question[]{
            new Question(R.string.q_java, true),
            new Question(R.string.q_gimp, true),
            new Question(R.string.q_photoshop, false),
            new Question(R.string.q_windows, false),
            new Question(R.string.q_facebook, true),
    };

    private int currentIndex = 0;
    private int score = 0;

    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if(userAnswer == correctAnswer){
            resultMessageId = R.string.correct_answer;
            score++;
        }else{
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionID());
    }

    private void quizSummary(){
        String resultMessage = getString(R.string.result, score, questions.length);
        Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        setNextQuestion();

        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questions.length;
                if (currentIndex == 0) {
                    quizSummary();
                    score = 0;
                }
                setNextQuestion();
            }
        });

    }
}
