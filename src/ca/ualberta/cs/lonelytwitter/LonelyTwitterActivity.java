package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	HashSet<String> values;
	String tweetCount;
	TextView tweetText;
	public ArrayAdapter<LonelyTweetModel> adapter;
	public ArrayList<LonelyTweetModel> twitter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		values = new HashSet<String>();
		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				LonelyTweetModel tweet = new LonelyTweetModel(text);
				twitter.add(tweet);
				adapter.notifyDataSetChanged();
				saveInFile();

			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		if (twitter == null){
			twitter = new ArrayList<LonelyTweetModel>();	
		}
		adapter = new ArrayAdapter<LonelyTweetModel>(this,
				R.layout.list_item, twitter);
		oldTweetsList.setAdapter(adapter);
	}

	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			InputStreamReader irs = new InputStreamReader(fis);
			Gson gson = new GsonBuilder().create();
			twitter = gson.fromJson(irs, new TypeToken<ArrayList<LonelyTweetModel>>() {}.getType());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onClickCounter(View view) {
		int i = 0;
		for (LonelyTweetModel m : twitter) {
			i = i+1;
		}
		Intent intent = new Intent(this, Counter.class);
		intent.putExtra("COUNT", i);
		saveInFile();
		startActivity(intent);
		
	}

	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			Gson gson = new GsonBuilder().create();
			gson.toJson(twitter, osw);
			osw.flush();
			osw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}