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

public class Computer extends AppCompatActivity {


	private int currentQuestion, currentOption1, currentOption2, currentOption3, currentOption4;
	private int  scoreCount = 0, questionCount = 0,wrongAnswer=0,correctAnswer=0;
	private String[] question, answers, option1, option2, option3, option4;
	private Button answerButton;
	private TextView questionView, resultView, answerview, score;
	private RadioButton r1, r2, r3, r4;
	private RadioGroup radioGroup;
	private String answer;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_computer);

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
		question = new String[] {
				"1.Which of the following languages is more suited to a structured program?",
				"2.A computer assisted method for the recording and analyzing of existing or hypothetical systems is",
				"3. The brain of any computer system is",
				"4.What difference does the 5th generation computer have from other generation computers?",
				"5. Which of the following computer language is used for artificial intelligence?",
				"6. Which Motherboard form factor uses one 20 pin connector",
				"7. A hard disk is divided into tracks which are further subdivided into:",
				"8. A wrist grounding strap contains which of the following:",
				"9. Which standard govern parallel communications?",
				"10.In laser printer technology, what happens during the conditioning stage?" };

		answers = new String[] { "PASCAL", "Data flow", "CPU",
				"Technological advancement", "PROLOG", "ATX", "Sectors",
				"Resistor", "IEEE 1284",
				"A uniform negative charge is placed on the photosensitive drum" };

		option1 = new String[] { "PL/1", "Data transmission", "ALUB",
				"Technological advancement", "FORTRANB", "ATX", "Clusters",
				"Surge protector", "RS232",
				"The corona wire places a uniform positive charge on the paper" };

		option2 = new String[] { "FORTRAN", "Data flow", "Memory",
				"Scientific code", "PROLOG", "AT", "Sectors", "Capacitor",
				"RS-232a",
				"A uniform negative charge is placed on the photosensitive drum" };

		option3 = new String[] { "BASIC", "Data capture", "CPU",
				"Object Oriented Programming", "C", "BABY AT", "Vectors",
				"Voltmeter", "CAT 5",
				"A uniform negative charge is placed on the toner" };

		option4 = new String[] { "PASCAL", "Data processing", "Control unit",
				"All of the above", "COBOL", "All of the above", "Heads",
				"Resistor", "IEEE 1284", "All of the above" };

		currentQuestion = -1;
		currentOption1 = -1;
		currentOption2 = -1;
		currentOption3 = -1;
		currentOption4 = -1;
		r1 = findViewById(R.id.computer_radio0);
		r2 = findViewById(R.id.computer_radio1);
		r3 = findViewById(R.id.computer_radio2);
		r4 = findViewById(R.id.computer_radio3);
		answerButton = findViewById(R.id.computer_answer_button);
		questionView = findViewById(R.id.computer_question_textview);
		answerview = findViewById(R.id.computer_answer_text_view);
		resultView = findViewById(R.id.computer_result_text);
		score = findViewById(R.id.computer_score);
		radioGroup = findViewById(R.id.computer_radio_group);
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
			nextQuestion();
			wrongAnswer++;
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