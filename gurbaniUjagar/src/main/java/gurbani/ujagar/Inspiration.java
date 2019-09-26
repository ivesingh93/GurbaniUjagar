package gurbani.ujagar;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.EmptyStackException;

import static gurbani.ujagar.Constants.GENERAL;
import static gurbani.ujagar.Constants.SGGS;

public class Inspiration extends SlidingFragmentActivity{
	SectionsPagerAdapter mSectionsPagerAdapter;
	static ViewPager mViewPager;
	static SharedPreferences getPrefs;
	static String mon = "";
	static String dat = "";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);

		slide_menu();

		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public Fragment getItem(int position) {
			Fragment insp = new Insp();
			return insp;
		}

		@Override
		public int getCount() {
			return 1;
		}

		@Override
		public CharSequence getPageTitle(int position) {

			return "Inspiration of the Day";
		}
	}
	@SuppressLint("ValidFragment")
	public static class Insp extends Fragment {
		public Insp() {

		}

		@SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.inspiration, container, false);
			if (Build.VERSION.SDK_INT >= 29) {
				LinearLayout li=rootView.findViewById(R.id.linearLayout2);
				li.setPadding(0,0,0,130);

			}
			final TextView textView = (TextView)rootView.findViewById(R.id.facebook);
			final Button save = (Button)rootView.findViewById(R.id.save);
			final Button share = (Button)rootView.findViewById(R.id.share);

			inspiration_calendar();
			textView.setClickable(true);
			textView.setMovementMethod(LinkMovementMethod.getInstance());
			String text = "<a href='https://www.facebook.com/sukrit.org?ref=br_tf'>Like Sukrit on Facebook</a>";
			textView.setText(Html.fromHtml(text));

			try {
				ImageView inspiration = (ImageView)rootView.findViewById(R.id.inspiration);
				final InputStream ims;

				try {
					//PhotoViewAttacher mAttacher;
					ims = rootView.getContext().getAssets().open("Inspirations/"+mon+"/"+dat+".jpg");
					Drawable d = Drawable.createFromStream(ims, null);
					inspiration.setImageDrawable(d);
//					mAttacher = new PhotoViewAttacher(mImage);
//					mAttacher.update();

				} catch (IOException e) {
					e.printStackTrace();
				}

				BitmapDrawable drawable = (BitmapDrawable) inspiration.getDrawable();
				final Bitmap bitmap = drawable.getBitmap();
				save.setOnClickListener(new View.OnClickListener() {

					@SuppressLint("SdCardPath") @Override
					public void onClick(View v) {
						Toast.makeText(rootView.getContext().getApplicationContext(), "Image Saved To Gallery", Toast.LENGTH_LONG).show();
						ContentValues values = new ContentValues();
						values.put(Images.Media.DATE_ADDED, System.currentTimeMillis());
						values.put(Images.Media.DATE_TAKEN, System.currentTimeMillis());
						values.put(Images.Media.TITLE, dat+"_"+mon);
						values.put(Images.Media.DISPLAY_NAME, dat+"_"+mon);
						values.put(Images.Media.DESCRIPTION, "Image");
						values.put(Images.Media.MIME_TYPE, "image/jpg");


						Uri url = null;
						try {
							url = rootView.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

							if (bitmap != null) {
								OutputStream imageOut = rootView.getContext().getContentResolver().openOutputStream(url);
								try {
									bitmap.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
								} finally {
									imageOut.close();
								}

								long id = ContentUris.parseId(url);
								Bitmap miniThumb = Images.Thumbnails.getThumbnail(rootView.getContext().getContentResolver(), id, Images.Thumbnails.MINI_KIND, null);
								Matrix matrix = new Matrix();
								float scaleX = 50f / miniThumb.getWidth();
								float scaleY = 50f / miniThumb.getHeight();

								matrix.setScale(scaleX, scaleY);

								Bitmap thumb = Bitmap.createBitmap(miniThumb, 0, 0,
										miniThumb.getWidth(),
										miniThumb.getHeight(), matrix,
										true
										);

								ContentValues valuess = new ContentValues(4);
								values.put(Images.Thumbnails.KIND,Images.Thumbnails.MICRO_KIND);
								values.put(Images.Thumbnails.IMAGE_ID,(int)id);
								values.put(Images.Thumbnails.HEIGHT,thumb.getHeight());
								values.put(Images.Thumbnails.WIDTH,thumb.getWidth());

								Uri urll = rootView.getContext().getContentResolver().insert(Images.Thumbnails.EXTERNAL_CONTENT_URI, valuess);

								try {
									OutputStream thumbOut = rootView.getContext().getContentResolver().openOutputStream(urll);
									thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut);
									thumbOut.close();
									return;
								} catch (FileNotFoundException ex) {
									return;
								} catch (IOException ex) {
									return;
								}




							} else {
								rootView.getContext().getContentResolver().delete(url, null, null);
								url = null;
							}
						} catch (Exception e) {
							if (url != null) {
								rootView.getContext().getContentResolver().delete(url, null, null);
								url = null;
							}
						}

					}
				});
				share.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						String pathofBmp = Images.Media.insertImage(rootView.getContext().getContentResolver(), bitmap, dat+"_"+mon, null);
					    Uri bmpUri = Uri.parse(pathofBmp);
					    final Intent emailIntent1 = new Intent(    android.content.Intent.ACTION_SEND);
					    emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					    emailIntent1.putExtra(Intent.EXTRA_STREAM, bmpUri);
					    emailIntent1.setType("image/png");
					    startActivity(emailIntent1);
					}
				});

			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
			return rootView;

		}
	}

	public static void inspiration_calendar(){

		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);

		String[] arr = {"January" , "February" , "March" , "April", "May", "June", "July", "August", "September", "October", "November", "December"};

		if (month == 0){
			mon = arr[0];
		}else if (month == 1){
			mon = arr[1];
		}else if (month == 2){
			mon = arr[2];
		}else if (month == 3){
			mon = arr[3];
		}else if (month == 4){
			mon = arr[4];
		}else if (month == 5){
			mon = arr[5];
		}else if (month == 6){
			mon = arr[6];
		}else if (month == 7){
			mon = arr[7];
		}else if (month == 8){
			mon = arr[8];
		}else if (month == 9){
			mon = arr[9];
		}else if (month == 10){
			mon = arr[10];
		}else if (month == 11){
			mon = arr[11];
		}

		String[] arr_of_dates = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
				"sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty_one", "twenty_two", "twenty_three", "twenty_four", "twenty_five", "twenty_six",
				"twenty_seven", "twenty_eight", "twenty_nine", "thirty", "thirty_one"};



		if (date == 1){
			dat = arr_of_dates[0];
		}else if (date == 2){
			dat = arr_of_dates[1];
		}else if (date == 3){
			dat = arr_of_dates[2];
		}else if (date == 4){
			dat = arr_of_dates[3];
		}else if (date == 5){
			dat = arr_of_dates[4];
		}else if (date == 6){
			dat = arr_of_dates[5];
		}else if (date == 7){
			dat = arr_of_dates[6];
		}else if (date == 8){
			dat = arr_of_dates[7];
		}else if (date == 9){
			dat = arr_of_dates[8];
		}else if (date == 10){
			dat = arr_of_dates[9];
		}else if (date == 11){
			dat = arr_of_dates[10];
		}else if (date == 12){
			dat = arr_of_dates[11];
		}else if (date == 13){
			dat = arr_of_dates[12];
		}else if (date == 14){
			dat = arr_of_dates[13];
		}else if (date == 15){
			dat = arr_of_dates[14];
		}else if (date == 16){
			dat = arr_of_dates[15];
		}else if (date == 17){
			dat = arr_of_dates[16];
		}else if (date == 18){
			dat = arr_of_dates[17];
		}else if (date == 19){
			dat = arr_of_dates[18];
		}else if (date == 20){
			dat = arr_of_dates[19];
		}else if (date == 21){
			dat = arr_of_dates[20];
		}else if (date == 22){
			dat = arr_of_dates[21];
		}else if (date == 23){
			dat = arr_of_dates[22];
		}else if (date == 24){
			dat = arr_of_dates[23];
		}else if (date == 25){
			dat = arr_of_dates[24];
		}else if (date == 26){
			dat = arr_of_dates[25];
		}else if (date == 27){
			dat = arr_of_dates[26];
		}else if (date == 28){
			dat = arr_of_dates[27];
		}else if (date == 29){
			dat = arr_of_dates[28];
		}else if (date == 30){
			dat = arr_of_dates[29];
		}else if (date == 31){
			dat = arr_of_dates[30];
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
				constant.sggsMethod(parent, view, position, id, Inspiration.this);
			}
		});

		ListView general = (ListView) findViewById(R.id.general_menu);
		ArrayAdapter<String> adapter2 = new GeneralArrayAdapter(this, R.layout.simplerow, GENERAL);
		general.setAdapter(adapter2);

		general.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				toggle();
				constant.generalMethod(parent, view, position, id, Inspiration.this);
			}
		});
	}
}
