package gurbani.ujagar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends Activity {

	Button buttonSend;
	EditText textTo;
	EditText textSubject;
	EditText textMessage;
	EditText name;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.feedback);


		buttonSend = (Button) findViewById(R.id.buttonSend);
		textSubject = (EditText) findViewById(R.id.editTextSubject);
		textMessage = (EditText) findViewById(R.id.editTextMessage);
		name = (EditText) findViewById(R.id.name);

		buttonSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!(textSubject.getText().toString().isEmpty()) && !(textMessage.getText().toString().isEmpty()
						&& !(name.getText().toString().isEmpty()))){

					String subject = textSubject.getText().toString();
					String message = textMessage.getText().toString();
					String user = name.getText().toString();
					Intent email = new Intent(Intent.ACTION_SEND);
					email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "ivesingh@gmail.com"});
					email.putExtra(Intent.EXTRA_SUBJECT, user+" - Feedback from Android App - "+subject);
					email.putExtra(Intent.EXTRA_TEXT, message);
					email.setType("message/rfc822");

					startActivity(Intent.createChooser(email, "Choose an Email client :"));
				}else{
					Toast.makeText(getApplicationContext(), "All fields required! ", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}