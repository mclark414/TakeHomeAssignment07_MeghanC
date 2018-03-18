package com.example.android.classquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.mclark.andriod.classquiz.answer_is_true";
    private static final String EXTRA_SHOWN_RESULT = "com.mclark.android.classquiz.shown_answer";
    private boolean mAnswerTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    public static Intent newIntent(Context packageContent, boolean answerIsTrue) {
        Intent intent = new Intent(packageContent, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answerText);
        mShowAnswerButton = (Button) findViewById(R.id.showAnswer);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mAnswerTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }
                else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }
    public void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_SHOWN_RESULT, isAnswerShown);
        setResult(RESULT_OK, data);
    }
    public static boolean wasAnswerShown(Intent data){
        return data.getBooleanExtra(EXTRA_SHOWN_RESULT, false);
    }
}
