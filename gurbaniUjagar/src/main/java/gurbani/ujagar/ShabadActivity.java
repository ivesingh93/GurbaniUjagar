package gurbani.ujagar;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

import java.io.IOException;
import java.util.ArrayList;

import static gurbani.ujagar.Constants.GENERAL;
import static gurbani.ujagar.Constants.SGGS;
import static gurbani.ujagar.R.id.Button07;

public class ShabadActivity extends SlidingActivity {
	static ArrayList<String> arr;
	static char[] search_word_arr;
	EditText shabad;
    static int line_num = 0;
	SharedPreferences sp;
	SharedPreferences.Editor spe;
	AlertDialog levelDialog;
	RelativeLayout relativeLayout1;
	boolean english_keyboard, search_from_start;
	Typeface face;
	private SGGSDB sggs_db;
	private RadioGroup search_start;
	int selectedRadioButton;
	RadioButton search_from;
	RadioButton start, anywhere;

	public void onBackPressed() {
		try{
			Class ourClass = Class.forName("gurbani.ujagar.MainPage");
			Intent ourIntent = new Intent(ShabadActivity.this, ourClass);
			startActivity(ourIntent);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		slide_menu();
		setContentView(R.layout.shabad_activity);

		sp = PreferenceManager.getDefaultSharedPreferences(this);
		spe = sp.edit();

		shabad = (EditText) findViewById(R.id.shabad);
		arr = new ArrayList<String>();
		int size = sp.getInt("arr_size", 0);

		for(int i=0;i<size;i++) {
			arr.add(sp.getString("arr" + i, null));
		}

		shabad.setText(sp.getString("shabad", ""));
		shabad.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				sp.edit().putString("shabad", s.toString()).commit();
			}
		});

		face = Typeface.createFromAsset(getAssets(),"fonts/anmollipinumbers.ttf");
		relativeLayout1 = (RelativeLayout)findViewById(R.id.relativeLayout1);
		english_keyboard = sp.getBoolean("english_keyboard", false);

		if (english_keyboard){
			relativeLayout1.setVisibility(View.INVISIBLE);
			shabad.setInputType(InputType.TYPE_CLASS_TEXT);
			shabad.setTypeface(null, Typeface.NORMAL);
		}else {
			relativeLayout1.setVisibility(View.VISIBLE);
			shabad.setInputType(InputType.TYPE_NULL);
			shabad.setTypeface(face);
		}

		start = (RadioButton) findViewById(R.id.start);
		anywhere = (RadioButton) findViewById(R.id.anywhere);
		search_from_start = sp.getBoolean("search_from_start", true);

		if(search_from_start){
			start.setChecked(true);
			anywhere.setChecked(false);
		}else{
			start.setChecked(false);
			anywhere.setChecked(true);
		}
//		selectedRadioButton = search_start.getCheckedRadioButtonId();
//		search_from = (RadioButton) findViewById(selectedRadioButton);
		//search_from_start = sp.getBoolean("search_from_start", true);

		Button Button07 = (Button) findViewById(R.id.Button07);
		Button Button06 = (Button) findViewById(R.id.Button06);
		Button Button05 = (Button) findViewById(R.id.Button05);
		Button Button04 = (Button) findViewById(R.id.Button04);
		Button Button03 = (Button) findViewById(R.id.Button03);

		Button07.setTypeface(face);
		Button06.setTypeface(face);
		Button05.setTypeface(face);
		Button04.setTypeface(face);
		Button03.setTypeface(face);

		Button Button44 = (Button) findViewById(R.id.Button44);
		Button Button43 = (Button) findViewById(R.id.Button43);
		Button Button41 = (Button) findViewById(R.id.Button41);
		Button Button42 = (Button) findViewById(R.id.Button42);
		Button Button40 = (Button) findViewById(R.id.Button40);		

		Button44.setTypeface(face);
		Button43.setTypeface(face);
		Button41.setTypeface(face);
		Button42.setTypeface(face);
		Button40.setTypeface(face);

		Button Button48 = (Button) findViewById(R.id.Button48);
		Button Button47 = (Button) findViewById(R.id.Button47);
		Button Button49 = (Button) findViewById(R.id.Button49);
		Button Button46 = (Button) findViewById(R.id.Button46);		
		Button Button45 = (Button) findViewById(R.id.Button45);

		Button48.setTypeface(face);
		Button47.setTypeface(face);
		Button49.setTypeface(face);
		Button46.setTypeface(face);
		Button45.setTypeface(face);	

		Button Button54 = (Button) findViewById(R.id.Button54);
		Button Button52 = (Button) findViewById(R.id.Button52);
		Button Button53 = (Button) findViewById(R.id.Button53);
		Button Button50 = (Button) findViewById(R.id.Button50);		
		Button Button51 = (Button) findViewById(R.id.Button51);

		Button54.setTypeface(face);
		Button52.setTypeface(face);
		Button53.setTypeface(face);
		Button50.setTypeface(face);
		Button51.setTypeface(face);

		Button Button56 = (Button) findViewById(R.id.Button56);
		Button Button58 = (Button) findViewById(R.id.Button58);
		Button Button59 = (Button) findViewById(R.id.Button59);
		Button Button55 = (Button) findViewById(R.id.Button55);
		Button Button57 = (Button) findViewById(R.id.Button57);

		Button56.setTypeface(face);
		Button58.setTypeface(face);
		Button59.setTypeface(face);
		Button55.setTypeface(face);
		Button57.setTypeface(face);

		Button Button08 = (Button) findViewById(R.id.Button08);
		Button Button10 = (Button) findViewById(R.id.Button10);
		Button Button09 = (Button) findViewById(R.id.Button09);
		Button Button02 = (Button) findViewById(R.id.Button02);
		Button Button01 = (Button) findViewById(R.id.Button01);

		Button08.setTypeface(face);
		Button10.setTypeface(face);
		Button09.setTypeface(face);
		Button02.setTypeface(face);
		Button01.setTypeface(face);

		Button Button13 = (Button) findViewById(R.id.Button13);
		Button Button11 = (Button) findViewById(R.id.Button11);
		Button Button14 = (Button) findViewById(R.id.Button14);
		Button Button12 = (Button) findViewById(R.id.Button12);
		Button Button15 = (Button) findViewById(R.id.Button15);

		Button13.setTypeface(face);
		Button11.setTypeface(face);
		Button14.setTypeface(face);
		Button12.setTypeface(face);
		Button15.setTypeface(face);

		sggs_db = new SGGSDB(ShabadActivity.this);
		try {
			sggs_db.createDataBase();
		} catch (IOException e) {
			throw new Error("Unable to create database.");
		}

		try{
			sggs_db.openDataBase();
		}catch (SQLException sqle){
			throw sqle;
		}

//		search_start = (RadioGroup) findViewById(R.id.search_start);
		Button search = (Button)findViewById(R.id.search);		
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				selectedRadioButton = search_start.getCheckedRadioButtonId();
//				search_from = (RadioButton) findViewById(selectedRadioButton);

				if(!start.isChecked() && anywhere.isChecked()){
					search_from_start = false;
				}else{
					search_from_start = true;
				}
//				Log.w("search", search_from.getText().toString());
				spe.putBoolean("search_from_start", search_from_start);
				spe.commit();


				if (shabad.getText().toString().matches("")) {
					Toast.makeText(getApplicationContext(), "Textfield cannot be empty.", Toast.LENGTH_SHORT).show();
				} else {
					if (english_keyboard){
						String query;
						if(search_from_start){
							query = "SELECT Gurmukhi, Ang, Author, Raag, Kirtan_ID, ID FROM 'SGGS' WHERE English_Initials LIKE '" + shabad.getText().toString() + "%'";
						}else{
							query = "SELECT Gurmukhi, Ang, Author, Raag, Kirtan_ID, ID FROM 'SGGS' WHERE English_Initials LIKE '%" + shabad.getText().toString() + "%'";
						}


						perform_search("english", shabad.getText().toString().toLowerCase().toCharArray(),
								false, query);
					}else{
						String query;
						if(search_from_start){
							query = "SELECT Gurmukhi, Ang, Author, Raag, Kirtan_ID, ID FROM 'SGGS' WHERE Gurmukhi_Initials LIKE '" + shabad.getText().toString() + "%'";
						}else{
							query = "SELECT Gurmukhi, Ang, Author, Raag, Kirtan_ID, ID FROM 'SGGS' WHERE Gurmukhi_Initials LIKE '%" + shabad.getText().toString() + "%'";
						}

						perform_search("punjabi", shabad.getText().toString().toCharArray(),
								true, query);
					}

				}
			}
		});

		Button back_space = (Button)findViewById(R.id.back);
		back_space.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(!shabad.getText().toString().matches("")){
					String shabad_word = shabad.getText().toString();
					shabad.setText(shabad_word.substring(0, (shabad_word.length() - 1)));
				}
			}
		});

		Button clear = (Button)findViewById(R.id.clear);
		clear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				shabad.setText("");
			}
		});

		Button settings = (Button)findViewById(R.id.settings);
		settings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				// Strings to Show In Dialog with Radio Buttons
				final CharSequence[] items = {"English Keyboard","Punjabi Keyboard"};

				// Creating and Building the Dialog
				AlertDialog.Builder builder = new AlertDialog.Builder(ShabadActivity.this);
				builder.setTitle("Select Keyboard");
				builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						switch(item) {
							case 0:
								relativeLayout1.setVisibility(View.INVISIBLE);
								shabad.setInputType(InputType.TYPE_CLASS_TEXT);
								english_keyboard = true;
								shabad.setText("");
								shabad.setTypeface(null, Typeface.NORMAL);
								break;
							case 1:
								relativeLayout1.setVisibility(View.VISIBLE);
								shabad.setInputType(InputType.TYPE_NULL);
								english_keyboard = false;
								shabad.setText("");
								shabad.setTypeface(face);
								break;
						}
						InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
						spe.putBoolean("english_keyboard", english_keyboard);
						spe.commit();
						levelDialog.dismiss();
					}
				});
				levelDialog = builder.create();
				levelDialog.show();


			}
		});

		Button info = (Button) findViewById(R.id.info);
		info.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new Dialog(ShabadActivity.this);
				dialog.setContentView(R.layout.shabad_info);

				TextView text_shabad = (TextView) dialog.findViewById(R.id.text_shabad);
				TextView punjabi_keyword = (TextView) dialog.findViewById(R.id.punjabi_keyword);

				text_shabad.setTypeface(face);
				punjabi_keyword.setTypeface(face);

				dialog.setCancelable(true);
				dialog.setTitle("  Info");
				dialog.show();
			}
		});

	}

	public void onClick(View v){
		switch(v.getId()){
		case Button07: shabad.setText(shabad.getText()+"a"); break;
		case R.id.Button06:	shabad.setText(shabad.getText()+"A"); break;
		case R.id.Button05: shabad.setText(shabad.getText()+"e"); break;
		case R.id.Button04: shabad.setText(shabad.getText()+"s"); break;
		case R.id.Button03: shabad.setText(shabad.getText()+"h"); break;

		case R.id.Button44:	shabad.setText(shabad.getText()+"k"); break;
		case R.id.Button43:	shabad.setText(shabad.getText()+"K"); break;
		case R.id.Button41:	shabad.setText(shabad.getText()+"g"); break;
		case R.id.Button42:	shabad.setText(shabad.getText()+"G"); break;
		case R.id.Button40:	shabad.setText(shabad.getText()+"|"); break;

		case R.id.Button48:	shabad.setText(shabad.getText()+"c"); break;
		case R.id.Button47:	shabad.setText(shabad.getText()+"C"); break;
		case R.id.Button49:	shabad.setText(shabad.getText()+"j"); break;
		case R.id.Button46:	shabad.setText(shabad.getText()+"J"); break;
		case R.id.Button45:	shabad.setText(shabad.getText()+"\\"); break;

		case R.id.Button54: shabad.setText(shabad.getText()+"t"); break;
		case R.id.Button52: shabad.setText(shabad.getText()+"T"); break;
		case R.id.Button53: shabad.setText(shabad.getText()+"f"); break;
		case R.id.Button50: shabad.setText(shabad.getText()+"F"); break;
		case R.id.Button51: shabad.setText(shabad.getText()+"x"); break;

		case R.id.Button56: shabad.setText(shabad.getText()+"q"); break;
		case R.id.Button58: shabad.setText(shabad.getText()+"Q"); break;
		case R.id.Button59: shabad.setText(shabad.getText()+"d"); break;
		case R.id.Button55: shabad.setText(shabad.getText()+"D"); break;
		case R.id.Button57: shabad.setText(shabad.getText()+"n"); break;

		case R.id.Button08: shabad.setText(shabad.getText()+"p"); break;
		case R.id.Button10: shabad.setText(shabad.getText()+"P"); break;
		case R.id.Button09: shabad.setText(shabad.getText()+"b"); break;
		case R.id.Button02: shabad.setText(shabad.getText()+"B"); break;
		case R.id.Button01: shabad.setText(shabad.getText()+"m"); break;

		case R.id.Button13: shabad.setText(shabad.getText()+"X"); break;
		case R.id.Button11: shabad.setText(shabad.getText()+"r"); break;
		case R.id.Button14: shabad.setText(shabad.getText()+"l"); break;
		case R.id.Button12: shabad.setText(shabad.getText()+"v"); break;
		case R.id.Button15: shabad.setText(shabad.getText()+"V"); break;

		}

	}

	private void perform_search(String language, char[] word_arr, boolean sensitivity, String query){

		search_word_arr = word_arr;
		sggs_db.enableSensitivity(sensitivity);
		Cursor cursor = sggs_db.shabadSearch(query);

		while(cursor.moveToNext()) {
			line_num++;
			String gurmukhi = cursor.getString(0), ang = cursor.getString(1), author = cursor.getString(2),
					raag = cursor.getString(3), kirtan_id = "", line_id = cursor.getString(5);
			if(cursor.getString(4) == null){
				kirtan_id = "null";
			}else{
				kirtan_id = cursor.getString(4);
			}

			arr.add(gurmukhi + ";;;" + ang + ";;;" + author + ";;;" + raag + ";;;" + kirtan_id + ";;;" + line_id);
		}

		if(language.equals("punjabi")){
			sggs_db.enableSensitivity(false);
		}

		spe.putInt("arr_size", arr.size());

		for(int i=0; i<arr.size(); i++) {
			spe.remove("arr" + i);
			spe.putString("arr" + i, arr.get(i));
		}

		if(arr.size() !=0){

			Toast.makeText(getApplicationContext(), arr.size()+" Result(s)", Toast.LENGTH_LONG).show();

			try{
				Class ourClass = Class.forName("gurbani.ujagar.Shabad");
				Intent ourIntent = new Intent(ShabadActivity.this, ourClass);
				startActivity(ourIntent);

			}catch(ClassNotFoundException e){
				e.printStackTrace();

			}

		}else{
			Toast.makeText(getApplicationContext(), "Sorry, no results.", Toast.LENGTH_LONG).show();
		}

	}

	public void slide_menu() {
		getSlidingMenu().setBehindOffset(150);
		getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		setBehindContentView(R.layout.menu);
		getSlidingMenu().setSecondaryMenu(R.layout.menu2);

		final ListView sggs = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simplerow, SGGS);
		sggs.setAdapter(adapter);
		final ConstantsMethods constant = new ConstantsMethods();

		sggs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				constant.sggsMethod(parent, view, position, id, ShabadActivity.this);
			}
		});

		ListView general = (ListView) findViewById(R.id.general_menu);
		ArrayAdapter<String> adapter2 = new GeneralArrayAdapter(this, R.layout.simplerow, GENERAL);
		general.setAdapter(adapter2);

		general.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				toggle();
				constant.generalMethod(parent, view, position, id, ShabadActivity.this);
			}
		});
	}
}