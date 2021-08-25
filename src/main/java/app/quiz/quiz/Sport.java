package app.quiz.quiz;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Sport extends AppCompatActivity {

    private int currentQuestion, currentOption1, currentOption2, currentOption3, currentOption4, scoreCount = 0, questionCount = 0;
    private String[] question, answers, option1, option2, option3, option4;
    private Button answerButton;
    private int wrongAnswer=0,correctAnswer=0;
    private TextView questionView, resultView, answerview, score;
    private RadioButton r1, r2, r3, r4;
    private RadioGroup radioGroup;
    private String answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        init();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=findViewById(checkedId);
                //resultView.setText(radioButton.getText().toString());
                answer=radioButton.getText().toString();

            }
        });

    }

    public void init() {


        question = new String[]{"1. Which is the national sport of Canada? ", "2. Archery is the national sport of which country?", "3. ____________ has Cricket as its national sports.", "4. _____________ is the national sport of Turkey", "5. When was the Commonwealth game started?", "6. Which was the host country in 1998 for Asian Games?", "7. Which city has hosted Asian Games in 2006?", "8. In which country commonwealth games were held in 2010?", "9. Which city hosted commonwealth games in 1966?", "10. ____________ has won Cricket world cup for the maximum number of times."};

        answers = new String[]{"Lacrosse/Ice hockey", "Bhutan", "Jamaica", "Wrestling", "1930", "Thailand", "Doha", "India", "Kingston", "Australia"};

        option1 = new String[]{"Lacrosse/Ice hockey", "Afghanistan", "India", "Wrestling", "1930", "Thailand", "Doha", "Malaysia", "Perth", "Australia"};

        option2 = new String[]{"Cricket", "Bhutan", "Jamaica", "Rugby union", "1934", "Philippines", "Bangkok", "Canada", "Kingston", "India"};

        option3 = new String[]{"Field hockey", "Japan", " Sri Lanka", "Golf", "1938", "South Korea", "New Delhi", "India", "Melbourne", "West Indies"};

        option4 = new String[]{"Volleyball", "India", "United States", " Basketball", "1950", "China", "Tokyo", "Australia", "Kuala Lumpur", "Sri Lanka"};
        currentQuestion = -1;
        currentOption1 = -1;
        currentOption2 = -1;
        currentOption3 = -1;
        currentOption4 = -1;
        r1 = findViewById(R.id.radio0);
        r2 = findViewById(R.id.radio1);
        r3 = findViewById(R.id.radio2);
        r4 = findViewById(R.id.radio3);
        answerButton = findViewById(R.id.AnswerButton);
        questionView = findViewById(R.id.Questiontextview);
        answerview = findViewById(R.id.AnswerTextView);
        resultView = findViewById(R.id.result_text);
        score = findViewById(R.id.textView1);
        radioGroup = findViewById(R.id.radioGroupSport);
        {
            currentQuestion++;
            currentOption1++;
            currentOption2++;
            currentOption3++;
            currentOption4++;
            if (currentOption1 == option1.length)
                currentOption1 = 0;
            r1.setText(option1[currentOption1]);

            if (currentOption2 == option2.length)
                currentOption2 = 0;
            r2.setText(option2[currentOption2]);

            if (currentOption3 == option3.length)
                currentOption3 = 0;
            r3.setText(option3[currentOption3]);
            if (currentOption4 == option4.length)
                currentOption4 = 0;
            r4.setText(option4[currentOption4]);


            if (currentQuestion == question.length)
                currentQuestion = 0;
            questionView.setText(question[currentQuestion]);
            answerview.setText("");

            new CountDownTimer(60000, 1000) {

                public void onTick(long millisUntilFinished) {
                    answerview.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    answerview.setText("Time over");
                }
            }.start();


        }
        answerButton.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                if (r1.isChecked() || r2.isChecked() || r3.isChecked() || r4.isChecked()) {


                    checkAnswer();

                } else {
                    Toast.makeText(getApplicationContext(), "please select any option", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public boolean iscorrect(String answer) {
        return (answer.equalsIgnoreCase(answers[currentQuestion]));
    }


    public void checkAnswer() {
        questionCount++;

        if (iscorrect(answer)) {

            scoreCount++;
            correctAnswer++;
            score.setText("score " + scoreCount);
            resultView.setText("Correct Answer");
            resultView.setBackgroundColor(getResources().getColor(R.color.green));
            nextQuestion();
        } else {

            wrongAnswer++;
            resultView.setBackgroundColor(getResources().getColor(R.color.red));
            resultView.setText("Wrong Answer");
            nextQuestion();


        }

        unCheckRadioButton();

        if (questionCount == 10) {
            String sco = score.getText().toString();

            if (sco.length() != 0) {
                Intent intent = new Intent(getApplicationContext(),
                        Congratulations.class);

                intent.putExtra("wrongAnswer",""+wrongAnswer);
                intent.putExtra("correctAnswer",""+correctAnswer);
                intent.putExtra("score", ""+scoreCount);
                startActivity(intent);
                finish();
            }

        }
    }

    private void nextQuestion() {


        currentQuestion++;
        currentOption1++;
        currentOption2++;
        currentOption3++;
        currentOption4++;
        if (currentOption1 == option1.length)
            currentOption1 = 0;
        r1.setText(option1[currentOption1]);

        if (currentOption2 == option2.length)
            currentOption2 = 0;
        r2.setText(option2[currentOption2]);

        if (currentOption3 == option3.length)
            currentOption3 = 0;
        r3.setText(option3[currentOption3]);

        if (currentOption4 == option4.length)
            currentOption4 = 0;
        r4.setText(option4[currentOption4]);

        if (currentQuestion == question.length)
            currentQuestion = 0;

        questionView.setText(question[currentQuestion]);

    }


    private void unCheckRadioButton() {
        r1.setChecked(false);
        r2.setChecked(false);
        r3.setChecked(false);
        r4.setChecked(false);

    }
}