package com.example.android.classquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int REQUEST_CODE_CHEAT = 0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_einstein, true),
            new Question(R.string.question_students, true),
            new Question(R.string.question_baseball, false),
            new Question(R.string.question_staten, false),
            new Question(R.string.question_empire, true),
            new Question(R.string.question_pinball, true)
    };

    private int mCurrentIndex = 0;
    private boolean misCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.question);
        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.answerTrue);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.answerFalse);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mPrevButton = (Button) findViewById(R.id.prevButton);
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mNextButton = (Button) findViewById(R.id.nextButton);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                misCheater = false;
                mCurrentIndex = (mCurrentIndex-1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mCheatButton = (Button) findViewById(R.id.cheatButton);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                boolean isAnswerTrue = mQuestionBank[mCurrentIndex].getAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this, isAnswerTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });
        updateQuestion();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(resultCode == REQUEST_CODE_CHEAT){
            if(data == null){
                return;
            }
            misCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
    private void checkAnswer(boolean userPressed){
        boolean isAnswerTrue = mQuestionBank[mCurrentIndex].getAnswerTrue();
        int messageResId = 0;
        if(userPressed == isAnswerTrue){
            messageResId = R.string.correct_toast;
        }
        else{
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}

