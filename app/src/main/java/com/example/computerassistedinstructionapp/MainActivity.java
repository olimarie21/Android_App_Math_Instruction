package com.example.computerassistedinstructionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int num1;
    private int num2;
    int randResponse;
    Random r = new Random();
    List<String> wrongAnswerRes = Arrays.asList("No, please try again.",
            "Wrong. Try once more.", "Don't give up!", "No. Keep trying.");
    List<String> correctAnswerRes = Arrays.asList("Very good!",
            "Excellent!", "Nice work!", "Keep up the good work!");
    List<String> questionType = Arrays.asList("times", "plus", "minus");
    private String type;
    TextView questionOutput;
    Button getQuestion;
    Button submitResponse;
    EditText response;
    TextView programResponse;
    private String question;
    String programRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionOutput = findViewById(R.id.questionOutput);
        getQuestion = findViewById(R.id.getQuestion);
        submitResponse = findViewById(R.id.btnSubmit);
        response = findViewById(R.id.txtAnswer);
        programResponse = findViewById(R.id.txtProgramResponse);

        getQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand1 = new Random();
                Random rand2 = new Random();
                int maxNum = 10;

                num1 = rand1.nextInt(maxNum);
                num2 = rand2.nextInt(maxNum);

                randResponse = r.nextInt(questionType.size());
                type = questionType.get(randResponse);
                question = "How much is " + num1 + " " + type + " " + num2 + "?";

                questionOutput.setText(question);
                programResponse.setText("");
            }
        });

        submitResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res = Integer.parseInt(response.getText().toString());
                int correctRes;

                switch (type) {
                    case "times":
                        correctRes = num1 * num2;
                        break;
                    case "plus":
                        correctRes = num1 + num2;
                        break;
                    case "minus":
                        correctRes = num1 - num2;
                        break;
                    default:
                        correctRes = 0;
                        break;
                }

                if(res == correctRes) {
                    randResponse = r.nextInt(correctAnswerRes.size());
                    programResponse.setText(correctAnswerRes.get(randResponse));
                    programRes = correctAnswerRes.get(randResponse);
                } else {
                    randResponse = r.nextInt(wrongAnswerRes.size());
                    programResponse.setText(wrongAnswerRes.get(randResponse));
                    programRes = wrongAnswerRes.get(randResponse);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("questionOutput", question);
        outState.putString("programRes", programRes);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            question = savedInstanceState.getString("questionOutput", "");
            programRes = savedInstanceState.getString("programRes", programRes);

            questionOutput.setText(question);
            programResponse.setText(programRes);
        }
    }
}