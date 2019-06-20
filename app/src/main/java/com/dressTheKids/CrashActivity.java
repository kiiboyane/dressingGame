package com.dressTheKids;

import java.io.File;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class CrashActivity extends Activity {
	String err ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		setContentView(R.layout.crash_activity);


		//Get  error from crashed activity
		Intent intent = getIntent();

		if(intent.getStringExtra("STACKTRACE")!=null)
			err = intent.getStringExtra("STACKTRACE");


		final TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText("Sorry, Something went wrong. \nPlease send error logs to developer.");

		findViewById(R.id.btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// save the error trace in vm folder of parent directory of SD card
				//Needs priviliges we will print error in mail anyway
			
				String filePath = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/vm/" + ".errorTrace.txt";
				sendErrorMail(CrashActivity.this, filePath , err);
				finish();
			}
		});

	}

	/**
	 * This list a set of application which can send email. 
	 * Here user have to pick one apps via email will be send to developer email id.
	 * @param mContext
	 * @param filePath
	 */
	private void sendErrorMail(Context mContext, String filePath, String err) {
		Intent sendIntent = new Intent(Intent.ACTION_SEND);
		String subject = "Error Description"; // here subject
		String body = "Sorry for your inconvenience .\nWe assure you that we will solve this problem as soon possible. \n \n"
			+err	+ "\n\nThanks for using app."; // here email body


		sendIntent.setType("plain/text");
		sendIntent.putExtra(Intent.EXTRA_EMAIL,
				new String[] { "XXXXXX@gmail.com" }); // your developer email id
		sendIntent.putExtra(Intent.EXTRA_TEXT, body);
		sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		sendIntent.putExtra(Intent.EXTRA_STREAM,
				Uri.fromFile(new File(filePath)));
		sendIntent.setType("message/rfc822");
		mContext.startActivity(Intent.createChooser(sendIntent, "Complete action using"));
	}
}

