package app.quiz.quiz;



import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class Select extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);

	}
	

	public void generalScience(View v)
	{
		Intent i=new Intent(this,General.class);
		startActivity(i);

		
	}
	public void computerScience(View v)
	{
		Intent i=new Intent(this,Computer.class);
		startActivity(i);

		
	}
	public void sport(View v)
	{
		Intent i=new Intent(this,Sport.class);
		startActivity(i);

	}

	}