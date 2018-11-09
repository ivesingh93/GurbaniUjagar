package gurbani.ujagar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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



}
