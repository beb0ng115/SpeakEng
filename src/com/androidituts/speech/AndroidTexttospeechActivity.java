package com.androidituts.speech;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AndroidTexttospeechActivity extends Activity implements
		TextToSpeech.OnInitListener {
	/** Called when the activity is first created. */

	private TextToSpeech speech;
	private ImageButton speak;
	private EditText comments;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		speech = new TextToSpeech(this, this);

		speak = (ImageButton) findViewById(R.id.imageButton1);

		comments = (EditText) findViewById(R.id.editText1);

		// button on click event
		speak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				speak();
			}

		});
	}

	@Override
	public void onDestroy() {
		// Don’t forget to shutdown tts!
		if (speech != null) {
			speech.stop();
			speech.shutdown();
		}
		super.onDestroy();
	}

	@Override
	public void onInit(int status)

	{

		if (status == TextToSpeech.SUCCESS)

		{

			int result = speech.setLanguage(Locale.US);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			}

			else

			{
				speak.setEnabled(true);
				speak();
			}

		}

		else

		{
			Log.e("TTS", "Initilization Failed!");
		}

	}

	private void speak() {

		String text = comments.getText().toString();

		speech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
}