package app.quiz.quiz;



import android.app.Activity;
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


public class General extends AppCompatActivity {

	private int currentQuestion, currentOption1, currentOption2, currentOption3, currentOption4, scoreCount = 0, questionCount = 0;
	private String[] question, answers, option1, option2, option3, option4;
	private Button answerButton;
	private  int wrongAnswer=0,correctAnswer=0;
	private TextView questionView, resultView, answerview, score;
	private RadioButton r1, r2, r3, r4;
	private RadioGroup radioGroup;
	private String answer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_general);
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

	public void init()
	{
		question=new String[]{"1)Brass gets discoloured in air because of the presence of which of the following gases in air?","2)Which of the following is a non metal that remains liquid at room temperature?"
				,"3)Chlorophyll is a naturally occurring chelate compound in which central metal is _______","4)Which of the following is used in pencils?","5)Which of the following metals forms an amalgam with other metals?","6)Chemical formula for water is","7)The gas usually filled in the electric bulb is","8)Washing soda is the common name for","9)Quartz crystals normally used in quartz clocks etc. is chemically","10)Which of the gas is not known as green house gas?"};

		answers=new String[]{"Hydrogen sulphide","Bromine","magnesium","Graphite","Mercury","H2O","Nitrogen","Sodium carbonate","silicon dioxide","Hydrogen"};

		option1=new String[]{"Oxygen","Phosphorous","copper","Graphite","Tin","NaAlO2","Nitrogen","Calcium bicarbonate","silicon dioxide","Methane"}; 
		
		option2=new String[]{"Hydrogen sulphide","Helium","iron","Silicon","Mercury","H2O","Hydrogen","Sodium carbonate","germanium oxide","Nitrous oxide"};
		
		option3=new String[]{"Carbon dioxide","Chlorine","magnesium","Charcoal","Lead","Al2O3","carbon dioxide","Sodium bicarbonate","a mixture of germanium oxide and silicon dioxide","Carbon dioxide"};
		
		option4=new String[]{"Nitrogen","Bromine","Calcium","Phosphorous","Zinc","CaSiO3","Oxygen","Calcium carbonate","sodium silicate","Hydrogen"};

		currentQuestion = -1;
		currentOption1 = -1;
		currentOption2 = -1;
		currentOption3 = -1;
		currentOption4 = -1;
		r1 = findViewById(R.id.general_radio0);
		r2 = findViewById(R.id.general_radio1);
		r3 = findViewById(R.id.general_radio2);
		r4 = findViewById(R.id.general_radio3);
		answerButton = findViewById(R.id.general_AnswerButton);
		questionView = findViewById(R.id.general_question_textview);
		answerview = findViewById(R.id.general_AnswerTextView);
		resultView = findViewById(R.id.general_result_text);
		score = findViewById(R.id.general_score);
		radioGroup = findViewById(R.id.general_radioGroup);
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
			nextQuestion();
			scoreCount++;
			correctAnswer++;
			score.setText("score " + scoreCount);
			resultView.setText("Correct Answer");
			resultView.setBackgroundColor(getResources().getColor(R.color.green));
		} else {
			wrongAnswer++;
			nextQuestion();
			resultView.setBackgroundColor(getResources().getColor(R.color.red));
			resultView.setText("Wrong Answer");


		}

		unCheckRadioButton();

		if (questionCount == 10) {
			String sco = score.getText().toString();

			if (sco.length() != 0) {
				Intent intent = new Intent(getApplicationContext(),
						Congratulations.class);

				intent.putExtra("wrongAnswer",""+wrongAnswer);
				intent.putExtra("correctAnswer",""+correctAnswer);
				intent.putExtra("score",""+ scoreCount);
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
