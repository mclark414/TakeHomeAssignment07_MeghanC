package com.example.android.classquiz;

/**
 * Created by mclark on 2/19/18.
 */

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    public int getTextResId(){
        return mTextResId;
    }
    public void setTextResId(int textResId){
        mTextResId = textResId;
    }
    public boolean getAnswerTrue(){
        return mAnswerTrue;
    }
    public void setAnswerTrue(boolean answerTrue){
        mAnswerTrue = answerTrue;
    }
    public Question(int textResId, boolean answerTrue){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
}
