package com.example.zadanie2;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {

   private boolean correctAnswer;
   private Button showCorrectAnswerButton;
   private TextView answerTextView;
   public static final String KEY_EXTRA_ANSWER_SHOWN = "com.example.zadanie2.answerShown";

   private void setAnswerShownResult(boolean answerWasShown){
       Intent resultIntent = new Intent();
       resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown);
       setResult(RESULT_OK, resultIntent);
   }


   @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        showCorrectAnswerButton = findViewById(R.id.answer_button);
        answerTextView = findViewById(R.id.answer_text_view);

        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);

        showCorrectAnswerButton.setOnClickListener(v -> {
            int answer = correctAnswer ? R.string.button_true : R.string.button_false;
            answerTextView.setText(answer);
            setAnswerShownResult(true);
        });

    }
}