package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    //Variable to hold the widget controls from the layout
    private Button mTrueButton;
    private Button mFalseButton;
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
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    //Add a index for which question we are on.
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Use findViewById to get a reference to the textview in the layout.
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        //Call the updateQuestion method.
        updateQuestion();


        //This uses the magical method findViewById to get a
        //layout resource from the layout file. We send in a
        //integer that represents what resource we would like
        //to get. The method returns a View object, and we then
        //need to down cast it to a Button class before we assign it.
        mTrueButton = (Button) findViewById(R.id.true_button);

        //This will set the onClickListener for the true button.
        //It uses an annonymous inner class to assign the listenter.
        //We essentailly create the onClikeListener class inside the
        //setOnClickListener method, and override the OnClick method
        //all at the same time.
        //All of our onclick listeners will look like this one.
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call the checkAnswer method and send over a true
                //since they pushed the true button.
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

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
        //Use the currentIndex to get the question in the array at that
        //index, and also call the getTextResId method (Property) to get
        //the associated string resource id.
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        //Set the text for the question. Using the integer resource id
        //that was fetched out from the array of questions.
        mQuestionTextView.setText(question);
    }

    //A  method to check whether the answer is correct or not,
    //and then toast out an associated message.
    private void checkAnswer(boolean userPressedTrue) {
        //Bool to represent the answer to the question we are on.
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        //Integer to hold the resource id of the correct/incorrect message
        //to display in the toast message
        int messageResId = 0;

        //If the user's press equals the questions answer
        if (userPressedTrue == answerIsTrue) {
            //Set the message to the correct message
            messageResId = R.string.correct_toast;
        } else {
            //Else the incorrect message
            messageResId = R.string.incorrect_toast;
        }
        //Make the toast using the assigned message
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

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
