package edu.kvcc.cis298.cis298inclass1;

/**
 * Created by dbarnes on 9/19/2016.
 */
public class Question {

    private int mTextResId;
    private int mCorrectAnswerResId;
    private int[] mChoiceResIds;

    public Question(int textResId, int correctAnswerResId,
                    int[] choiceResIds) {
        mTextResId = textResId;
        mCorrectAnswerResId = correctAnswerResId;
        mChoiceResIds = choiceResIds;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public int getCorrectAnswerResId() {
        return mCorrectAnswerResId;
    }

    public void setCorrectAnswerResId(int correctAnswerResId) {
        mCorrectAnswerResId = correctAnswerResId;
    }

    public int[] getChoiceResIds() {
        return mChoiceResIds;
    }

    public void setChoiceResIds(int[] choiceResIds) {
        mChoiceResIds = choiceResIds;
    }
}
