package com.example.jonas.eduquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private TextView mCurrentQuestionText;
    private TextView mCurrentAnswerText;
    private TextView mAnswerHeader;
    private Random mRandom = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mShowAnswerBtn = (Button)findViewById(R.id.showAnswerBtn);
        mNextQuestionBtn = (Button)findViewById(R.id.nextQuestionBtn);
        mCurrentQuestionText = (TextView)findViewById(R.id.questionText);
        mCurrentAnswerText = (TextView)findViewById(R.id.answerText);
        mAnswerHeader = (TextView)findViewById(R.id.answerHeader);

        try {
            mQuestions = new JSONArray(getIntent().getStringExtra("questions"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        nextQuestion(getCurrentFocus());
    }

    public void nextQuestion(View view) {
        int id;
        do {
            id = mRandom.nextInt(mQuestions.length());
            if (id != mCurrentQuestionId) {
                try {
                    JSONObject obj = mQuestions.getJSONObject(id);
                    mCurrentQuestionText.setText(obj.getString("question"));
                    mCurrentAnswerText.setText(obj.getString("answer"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } while (id == mCurrentQuestionId);

        mCurrentQuestionId = id;
        mCurrentAnswerText.setVisibility(View.INVISIBLE);
        mShowAnswerBtn.setVisibility(View.VISIBLE);
        mNextQuestionBtn.setVisibility(View.INVISIBLE);
        mAnswerHeader.setVisibility(View.INVISIBLE);
    }

    public void showAnswer(View view) {
        mCurrentAnswerText.setVisibility(View.VISIBLE);
        mShowAnswerBtn.setVisibility(View.INVISIBLE);
        mNextQuestionBtn.setVisibility(View.VISIBLE);
        mAnswerHeader.setVisibility(View.VISIBLE);
    }
}
