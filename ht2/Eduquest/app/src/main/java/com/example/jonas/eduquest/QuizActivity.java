package com.example.jonas.eduquest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    private Button mShowAnswerBtn;
    private Button mNextQuestionBtn;
    private JSONArray mQuestions;
    private int mCurrentQuestionId = 0;
    private TextView mQuizHeader;
    private TextView mCurrentQuizText;
    private Random mRandom = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mShowAnswerBtn = (Button)findViewById(R.id.showAnswerBtn);
        mNextQuestionBtn = (Button)findViewById(R.id.nextQuestionBtn);
        mQuizHeader = (TextView)findViewById(R.id.quizHeader);
        mCurrentQuizText = (TextView)findViewById(R.id.quizText);

        try {
            mQuestions = new JSONArray(getIntent().getStringExtra("questions"));
        } catch (Exception e) {
            // This is never reached. Content is guaranteed to be valid JSON.
        }

        nextQuestion();
    }

    public void onNextQuestionBtnClicked(View v) {
        nextQuestion();
    }


    public void nextQuestion() {
        int id;
        if (mQuestions.length() == 1) {
            id = 0;
            try {
                JSONObject question = mQuestions.getJSONObject(id);
                mCurrentQuizText.setText(question.getString("question"));
            } catch (JSONException e) {
                // This is never reached.
            }
        }
        else {
            do {
                id = mRandom.nextInt(mQuestions.length());
                if (id != mCurrentQuestionId) {
                    try {
                        JSONObject question = mQuestions.getJSONObject(id);
                        mCurrentQuizText.setText(question.getString("question"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } while (id == mCurrentQuestionId);
        }

        mCurrentQuestionId = id;
        mQuizHeader.setText("Question");
        mShowAnswerBtn.setVisibility(View.VISIBLE);
        mNextQuestionBtn.setVisibility(View.INVISIBLE);
    }

    public void showAnswer(View view) {
        try {
            JSONObject question = mQuestions.getJSONObject(mCurrentQuestionId);
            mCurrentQuizText.setText(question.getString("answer"));
        } catch (Exception e) {
            // This is never reached.
        }

        ScrollView textArea = (ScrollView)findViewById(R.id.quizScrollContainer);
        textArea.scrollTo(0, 0);
        mQuizHeader.setText("Answer");
        mShowAnswerBtn.setVisibility(View.INVISIBLE);
        mNextQuestionBtn.setVisibility(View.VISIBLE);
    }
}
