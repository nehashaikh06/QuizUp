package app.quiz.quiz;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class Congratulations extends AppCompatActivity {

	TextView scoreTextView,correctTextView,wrongTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_congratulations);
		correctTextView=findViewById(R.id.correct_answer);
		wrongTextView=findViewById(R.id.wrong_answer);
		scoreTextView=findViewById(R.id.score_text);
		String score=getIntent().getExtras().getString("score");
		String wrongAnswer=getIntent().getExtras().getString("wrongAnswer");
		String correctAnswer=getIntent().getExtras().getString("correctAnswer");
		scoreTextView.setText(score);
		wrongTextView.setText(wrongAnswer);
	  correctTextView.setText(correctAnswer);

	}
}
