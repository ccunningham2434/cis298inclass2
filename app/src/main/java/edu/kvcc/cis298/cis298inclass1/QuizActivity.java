package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    //Variable to hold the widget controls from the layout
    private RadioGroup mQuestionGroup;

    private RadioButton mChoice1;
    private RadioButton mChoice2;
    private RadioButton mChoice3;
    private RadioButton mChoice4;

    private Button mSubmitButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    //Our question bank. We are creating new instances of the Question
    //class, and putting them into the question array that is also being
    //declared here. The constructor for a Question takes in an integer
    //and a boolean. R.string.questionName is actually an integer that
    //references a string in the R file. That is why we are storing an
    //integer and not a string. We want the 'pointer' to the string, rather
    //than the string itself.
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_1_multiple,
                        R.id.multiple_choice_3,
                        new int[] {
                                R.string.question_1_choice_1,
                                R.string.question_1_choice_2,
                                R.string.question_1_choice_3,
                                R.string.question_1_choice_4
                        }),
            new Question(R.string.question_2_multiple,
                    R.id.multiple_choice_2,
                    new int[] {
                            R.string.question_2_choice_1,
                            R.string.question_2_choice_2,
                            R.string.question_2_choice_3,
                            R.string.question_2_choice_4
                    })
    };

    //Add a index for which question we are on.
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Log out that the onCreate method was fired
        Log.d(TAG, "onCreate(Bundle) called");

        //Use findViewById to get a reference to the textview in the layout.
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        //Check the Bundle to see if it is null. If it is not,
        //we will fetch out the mCurrentIndex from it using the
        //same constant key KEY_INDEX that we used to put the
        //value in the bundle.
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        mChoice1 = (RadioButton) findViewById(R.id.multiple_choice_1);
        mChoice2 = (RadioButton) findViewById(R.id.multiple_choice_2);
        mChoice3 = (RadioButton) findViewById(R.id.multiple_choice_3);
        mChoice4 = (RadioButton) findViewById(R.id.multiple_choice_4);

        mQuestionGroup = (RadioGroup) findViewById(R.id.multiple_group);

        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Query the radio button group to find out which
                //radio button was selected. Store the id of the
                //selected one in the variable selectedAnswerId.
                int selectedAnswerId = mQuestionGroup.getCheckedRadioButtonId();

                //Pass the id of the selected radio button into the
                //checkAnswer method. The checkAnswer handles Toasting
                //whether it is correct or not.
                checkAnswer(selectedAnswerId);
            }
        });



        //Call the updateQuestion method.
        updateQuestion();

        //The Next Button
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Increment the index, and mod it by the length of the array.
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                //Call the update question method.
                updateQuestion();
            }
        });

    }

    private void updateQuestion() {
        //Clear all of the radio buttons before changing the
        //text on them
        mQuestionGroup.clearCheck();

        //Use the currentIndex to get the question in the array at that
        //index, and also call the getTextResId method (Property) to get
        //the associated string resource id.
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        //Set the text for the question. Using the integer resource id
        //that was fetched out from the array of questions.
        mQuestionTextView.setText(question);

        //Fetch the question choice strings from the question object
        int[] choices = mQuestionBank[mCurrentIndex].getChoiceResIds();

        //Assign each question choice text to the text property of
        //the radio button
        mChoice1.setText(choices[0]);
        mChoice2.setText(choices[1]);
        mChoice3.setText(choices[2]);
        mChoice4.setText(choices[3]);
    }

    //A  method to check whether the answer is correct or not,
    //and then toast out an associated message.
    private void checkAnswer(int selectedRadioButtonId) {

        //The current questions correct radio button id
        int correctAnswer = mQuestionBank[mCurrentIndex].getCorrectAnswerResId();

        //Integer to hold the resource id of the correct/incorrect message
        //to display in the toast message
        int messageResId = 0;

        //If the user's press equals the questions answer
        if (selectedRadioButtonId == correctAnswer) {
            //Set the message to the correct message
            messageResId = R.string.correct_toast;
        } else {
            //Else the incorrect message
            messageResId = R.string.incorrect_toast;
        }
        //Make the toast using the assigned message
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    //This method is called right before onPause is called.
    //This is where you should use the passed in Bundle to save
    //the state of the activity. The Bundle has methods on it
    //to put values in a key => value type of way.
    //We are using putInt to store the mCurrentIndex in the bundle
    //under a key of KEY_INDEX. KEY_INDEX is really a const declared
    //at the top of this class.
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSavedInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }


    //We don't need these
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
