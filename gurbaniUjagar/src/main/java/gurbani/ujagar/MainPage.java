package gurbani.ujagar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

import gurbani.ujagar.sharedpreferences.Prefs;
import gurbani.ujagar.sharedpreferences.SharedPreferencesName;

import static gurbani.ujagar.Constants.GENERAL;
import static gurbani.ujagar.Constants.SGGS;


public class MainPage extends SlidingActivity {
	ImageView logo;
	TextView textView, txt1, txt2;
	//private Scringo scringo;
	ImageView image1, image2;
	int times;

	private SharedPreferences.Editor editor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.main);


		image1 = (ImageView) findViewById(R.id.scringoWelcome1);
		image2 = (ImageView) findViewById(R.id.scringoWelcome2);

		getSlidingMenu().setBehindOffset(150);
		getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		setBehindContentView(R.layout.menu);
		getSlidingMenu().setSecondaryMenu(R.layout.menu2);


		final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
		times = app_preferences.getInt("pointers", 0);
		editor = app_preferences.edit();
		if(times == 1){
			image1.setVisibility(View.GONE);
			image2.setVisibility(View.GONE);
		}
		times = 1;
		editor.putInt("pointers", times);
		editor.commit();


		final ListView sggs = (ListView)findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simplerow, SGGS);
		sggs.setAdapter(adapter);
		final ConstantsMethods constant = new ConstantsMethods();

		sggs.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				constant.sggsMethod(parent, view, position, id, MainPage.this);
			}
		});

		ListView general = (ListView)findViewById(R.id.general_menu);
		ArrayAdapter<String> adapter2 = new GeneralArrayAdapter(this, R.layout.simplerow, GENERAL);
		general.setAdapter(adapter2);

		general.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				toggle();
				constant.generalMethod(parent, view, position, id, MainPage.this);
			}
		});
	}

	public void onBackPressed(){
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(startMain);

	}

	@Override
	protected void onResume() {
		super.onResume();

		int popupCounter = Prefs.with(this).getInt(SharedPreferencesName.COUNT_LATER, 100);
		boolean canPopup = Prefs.with(MainPage.this).getBoolean(SharedPreferencesName.CAN_POPUP_VISIBLE, true);
		if (canPopup) {
			if (popupCounter == 100 || popupCounter == 5) {


				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Waheguru Ji Ka Khalsa Waheguru Ji Ki Fateh!")
						.setMessage("Please try our new app VismaadNaad to listen to Kirtan from Darbar Sahib Raagis and more.")
						.setCancelable(false)
						.setPositiveButton("Try now", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
								try {
									startActivity(new Intent(Intent.ACTION_VIEW,
											Uri.parse("market://details?id=" + "com.vismaad.naad&hl=en_IN")));
								} catch (android.content.ActivityNotFoundException anfe) {
									startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.vismaad.naad&hl=en_IN")));
								}
							}

						})
						.setNegativeButton("May be later", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
							}
						})
						.setNeutralButton("Not interested", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Prefs.with(MainPage.this).save(SharedPreferencesName.CAN_POPUP_VISIBLE, false);
								dialog.dismiss();
							}
						});

				AlertDialog alert = builder.create();
				alert.show();

				Prefs.with(this).save(SharedPreferencesName.COUNT_LATER, 1);
			} else {
				Prefs.with(this).save(SharedPreferencesName.COUNT_LATER, (popupCounter +1));
			}

		}
	}
}
