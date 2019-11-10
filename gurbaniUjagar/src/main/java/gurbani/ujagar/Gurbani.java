package gurbani.ujagar;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

import java.io.IOException;

import static gurbani.ujagar.Constants.ANAND_SAHIB;
import static gurbani.ujagar.Constants.ANG_PREF;
import static gurbani.ujagar.Constants.BANI_NAME_PREF;
import static gurbani.ujagar.Constants.BIG_BOLD_FONT_COLOR;
import static gurbani.ujagar.Constants.BIG_BOLD_FONT_SINGLE_BREAK;
import static gurbani.ujagar.Constants.BIG_FONT_DOUBLE_BREAK;
import static gurbani.ujagar.Constants.BIG_FONT_SINGLE_BREAK;
import static gurbani.ujagar.Constants.CHAUPAI_SAHIB;
import static gurbani.ujagar.Constants.CHOTTA_ANAND_SAHIB;
import static gurbani.ujagar.Constants.CURRENT_BANI_PREF;
import static gurbani.ujagar.Constants.DOUBLE_BREAK;
import static gurbani.ujagar.Constants.ENGLISH_FONT;
import static gurbani.ujagar.Constants.GENERAL;
import static gurbani.ujagar.Constants.GURBANI;
import static gurbani.ujagar.Constants.GURBANI_ENGLISH;
import static gurbani.ujagar.Constants.GURBANI_ENGLISH_TEEKA;
import static gurbani.ujagar.Constants.GURBANI_FONT;
import static gurbani.ujagar.Constants.GURBANI_PUNJABI;
import static gurbani.ujagar.Constants.GURBANI_PUNJABI_ENGLISH;
import static gurbani.ujagar.Constants.GURBANI_PUNJABI_ENGLISH_TEEKA;
import static gurbani.ujagar.Constants.GURBANI_PUNJABI_TEEKA;
import static gurbani.ujagar.Constants.GURBANI_TEEKA;
import static gurbani.ujagar.Constants.GURU_GRANTH;
import static gurbani.ujagar.Constants.JAAP_SAHIB;
import static gurbani.ujagar.Constants.JAPJI_SAHIB;
import static gurbani.ujagar.Constants.KIRTAN_SOHILA;
import static gurbani.ujagar.Constants.LAAVAN;
import static gurbani.ujagar.Constants.POS_PREF;
import static gurbani.ujagar.Constants.PUNJABI_FONT;
import static gurbani.ujagar.Constants.REHRAAS_SAHIB;
import static gurbani.ujagar.Constants.SALOK_MAHALLA_9;
import static gurbani.ujagar.Constants.SGGS;
import static gurbani.ujagar.Constants.SHABAD;
import static gurbani.ujagar.Constants.SHABAD_PREF;
import static gurbani.ujagar.Constants.SINGLE_BREAK;
import static gurbani.ujagar.Constants.SUKHMANI_SAHIB;
import static gurbani.ujagar.Constants.TAV_PARSAD;
import static gurbani.ujagar.Constants.TEEKA_ARTH_FONT;
import static gurbani.ujagar.Constants.TEEKA_PAD_ARTH_FONT;
import static gurbani.ujagar.Constants.granthVars;


/**
 * Created by Waheguru on 6/6/15.
 */
public class Gurbani extends SlidingActivity implements Animation.AnimationListener {

    LinearLayout options, textLayout, downview;
    Button previous, settings, next, search;
    EditText number;
    CheckBox punjabi, english, teeka, light;
    TextView gurbani, textChange, title;
    SeekBar textSeekBar;
    StringBuffer gurbani_text = new StringBuffer();
    boolean eng, pun, tee, low_light;
    private int granth_num = 1, visibility = 1, touch = 1, japji_num = 1, sukhmani_num = 1,
            anand_num = 1, page_num = 1;

    private SharedPreferences sp;
    private SharedPreferences.Editor spe;
    private Animation animSlideIn, animSlideOut;
    private ScrollView scroll;
    private ImageButton bookmark;
    private View view;
    private DatabaseHelper bookmarkDB;
    private Cursor bookmarked_result;
    private SGGSDB sggs_db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.multi_page_web);
        if (Build.VERSION.SDK_INT >= 29) {

            LinearLayout li= (LinearLayout) findViewById(R.id.downview);
           li.setPadding(0,0,0,150);

        }

        // Initializing all the components.
        initialize();

        //save_data_to_db();

        // Slide Menu buttons' functions.
        slide_menu();

        sggs_db = new SGGSDB(Gurbani.this);
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

        // SharedPreference variables and cases.
        if (savedInstanceState != null) {
            granthVars.setBani(savedInstanceState.getString(CURRENT_BANI_PREF, GURU_GRANTH));
            granthVars.setTitle(savedInstanceState.getString(BANI_NAME_PREF, "title"));

            title.setText(granthVars.getTitle());
            granthVars.setTotalAng(savedInstanceState.getInt(ANG_PREF, 1));
            granthVars.setTranslationCase(savedInstanceState.getInt(POS_PREF, 0));
            granthVars.setShabadAng(savedInstanceState.getInt(SHABAD_PREF, 1));
        }

        if (granthVars.getTotalAng() == 1) {
            number.setVisibility(View.GONE);
            search.setVisibility(View.GONE);
            previous.setEnabled(false);
            next.setEnabled(false);
        }


        int num = sp.getInt("seekNum", 3);

        if (granthVars.getBani().equals(GURU_GRANTH)) {
            granth_num = sp.getInt("granth_num", 1);
            page_num = granth_num;


            if (granthVars.getShabadAng() != 0) {
                page_num = granthVars.getShabadAng();

                spe.putInt("granth_num", page_num);
                spe.commit();

                granthVars.setShabadAng(0);

            } else if (granthVars.getBookmarkAng() != 0) {
                page_num = granthVars.getBookmarkAng();

                spe.putInt("granth_num", page_num);
                spe.commit();

                granthVars.setBookmarkAng(0);

            } else if (granthVars.getAuthorAng() != 0) {
                page_num = granthVars.getAuthorAng();

                spe.putInt("granth_num", page_num);
                spe.commit();

                granthVars.setAuthorAng(0);

            } else if (granthVars.getRaagAng() != 0) {
                page_num = granthVars.getRaagAng();

                spe.putInt("granth_num", page_num);
                spe.commit();

                granthVars.setRaagAng(0);

            }

            if(granthVars.getTranslationCase() == 0) {
                pun = eng = tee = false;

            }else if(granthVars.getTranslationCase() == 1){
                tee = true;
                eng = pun = false;

            }else if(granthVars.getTranslationCase() == 2){
                pun = true;
                eng = tee = false;

            }else if(granthVars.getTranslationCase() == 3){
                eng = true;
                tee = pun = false;
            }

        } else if(granthVars.getBani().equals(SHABAD)){

            if (granthVars.getShabadAng() != 0) {
                page_num = granthVars.getShabadAng();

                spe.putInt("granth_num", page_num);
                spe.commit();

                granthVars.setShabadAng(0);

            } else if (granthVars.getBookmarkAng() != 0) {
                page_num = granthVars.getBookmarkAng();

                spe.putInt("granth_num", page_num);
                spe.commit();

                granthVars.setBookmarkAng(0);

            }

            if(granthVars.getTranslationCase() == 0) {
                pun = eng = tee = false;

            }else if(granthVars.getTranslationCase() == 1){
                tee = true;
                eng = pun = false;

            }else if(granthVars.getTranslationCase() == 2){
                pun = true;
                eng = tee = false;

            }else if(granthVars.getTranslationCase() == 3){
                eng = true;
                tee = pun = false;
            }

        } else if (granthVars.getBani().equals(JAPJI_SAHIB)) {
            japji_num = sp.getInt("japji_num", 1);
            page_num = japji_num;
            pun = eng = tee = false;

        } else if (granthVars.getBani().equals(SUKHMANI_SAHIB)) {
            sukhmani_num = sp.getInt("sukhmani_num", 1);
            page_num = sukhmani_num;
            pun = eng = tee = false;

        } else if (granthVars.getBani().equals(ANAND_SAHIB)) {
            anand_num = sp.getInt("anand_num", 1);
            page_num = anand_num;
            pun = eng = tee = false;

        } else if (granthVars.getBani().equals(CHOTTA_ANAND_SAHIB)
                || granthVars.getBani().equals(REHRAAS_SAHIB)
                || granthVars.getBani().equals(KIRTAN_SOHILA) || granthVars.getBani().equals(SALOK_MAHALLA_9)
                || granthVars.getBani().equals(LAAVAN)) {
            page_num = 1;
            pun = eng = tee = false;

        } else {
            pun = eng = tee = false;
        }


        low_light = sp.getBoolean("low_light", false);

        if (low_light) {
            light.setChecked(true);
            gurbani.setBackgroundColor(Color.BLACK);
            options.setBackgroundColor(Color.BLACK);
            title.setTextColor(Color.WHITE);
            number.setTextColor(Color.WHITE);
        }


        // Checks to see what translation to view.
        check();
        conditions();

        // Text Size Seek Bar.
        textSeekBar.setProgress(num);
        gurbani.setTextSize(TypedValue.COMPLEX_UNIT_PX, (textSeekBar.getProgress() + 5) * getResources().getDisplayMetrics().density);
        textChange.setText(String.valueOf(textSeekBar.getProgress()) + "pt");
        number.setText("" + page_num);

        // Functions when button clicked from the downview.
        downview_options();

        // Functions when next, previous, search is clicked to update the granth_num.
        open_ang();

        // Functions when buttons from main layout is clicked.
        main_layout_options();
///*
//        // Text Over Image
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog dialog = new Dialog(Gurbani.this);
//                dialog.setContentView(R.layout.grid_layout);
//                dialog.setCancelable(true);
//                dialog.setTitle("  Select an image");
//
//                GridView gridview = (GridView) dialog.findViewById(R.id.grid_view);
//                gridview.setAdapter(new ImageAdapter(getApplicationContext()));
//                /**
//                 * On Click event for Single Gridview Item
//                 * */
//                gridview.setOnItemClickListener(new OnItemClickListener() {
//                    public void onItemClick(AdapterView<?> parent, View v,
//                                            int position, long id) {
//
//                        // Sending image id to FullScreenActivity
//                        Intent i = new Intent(getApplicationContext(), TextOverImage.class);
//                        // passing array index
//                        i.putExtra("id", position);
//                        startActivity(i);
//                    }
//                });
//
//                dialog.show();
//
////                try {
////                    Class ourClass = Class.forName("gurbani.ujagar.TextOverImage");
////                    Intent ourIntent = new Intent(Gurbani.this, ourClass);
////                    startActivity(ourIntent);
////                } catch (ClassNotFoundException e) {
////                    e.printStackTrace();
////                }
//        */
//            }
//        });

    }


    private void conditions() {

        String columns = "";
        int col_nums = 0;

        // View All 3
        if (tee && pun && eng) {
            teeka.setChecked(true);
            punjabi.setChecked(true);
            english.setChecked(true);
            columns = GURBANI_PUNJABI_ENGLISH_TEEKA;
            col_nums = 5;
        }

        // View Only 2
        if (tee && pun && !eng) {
            teeka.setChecked(true);
            punjabi.setChecked(true);
            english.setChecked(false);
            columns = GURBANI_PUNJABI_TEEKA;
            col_nums = 4;

        }
        if (tee && eng && !pun) {
            teeka.setChecked(true);
            punjabi.setChecked(false);
            english.setChecked(true);
            columns = GURBANI_ENGLISH_TEEKA;
            col_nums = 4;

        }
        if (pun && eng && !tee) {
            teeka.setChecked(false);
            punjabi.setChecked(true);
            english.setChecked(true);
            columns = GURBANI_PUNJABI_ENGLISH;
            col_nums = 3;

        }

        // View Only 1
        if (tee && !pun && !eng) {
            teeka.setChecked(true);
            punjabi.setChecked(false);
            english.setChecked(false);
            columns = GURBANI_TEEKA;
            col_nums = 3;

        }
        if (pun && !tee && !eng) {
            teeka.setChecked(false);
            punjabi.setChecked(true);
            english.setChecked(false);
            columns = GURBANI_PUNJABI;
            col_nums = 2;

        }
        if (eng && !pun && !tee) {
            teeka.setChecked(false);
            punjabi.setChecked(false);
            english.setChecked(true);
            columns = GURBANI_ENGLISH;
            col_nums = 2;

        }

        // View None
        if (!eng && !pun && !tee) {
            teeka.setChecked(false);
            punjabi.setChecked(false);
            english.setChecked(false);
            columns = GURBANI;
            col_nums = 1;

        }

        read_columns(columns, col_nums);

    }

    private void read_columns(String columns, int col_nums){
        gurbani_text.setLength(0);
        Cursor cursor = sggs_db.getQueryResult(columns, page_num);
        view_correct_pankti_info_and_layout(cursor);

        if (granthVars.getBani().equals(JAAP_SAHIB) || granthVars.getBani().equals(CHAUPAI_SAHIB)
                || granthVars.getBani().equals(TAV_PARSAD)) {

            while (cursor.moveToNext()) {
                String line = cursor.getString(0).replace("<>", "&lt&gt");


                if(columns.equals(GURBANI_PUNJABI)){
                    String line2 = cursor.getString(1).replace("<>", "&lt&gt");

                    gurbani_text.append(GURBANI_FONT + line + BIG_FONT_SINGLE_BREAK);
                    gurbani_text.append(PUNJABI_FONT + line2 +
                            DOUBLE_BREAK);
                }else if(columns.equals(GURBANI)){
                    gurbani_text.append(GURBANI_FONT + line + BIG_FONT_DOUBLE_BREAK);
                }
            }
        }else {
            while(cursor.moveToNext()){
                String[] lines = new String[col_nums];
                for(int i = 0; i < col_nums; i++){
                    lines[i] = cursor.getString(i).replace("<>", "&lt&gt");
                }

                int line_id = 0;

                if(granthVars.getBani().equals(GURU_GRANTH) || granthVars.getBani().equals(SHABAD)){
                    line_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID")));
                }

                if(lines[0].equals(granthVars.getSearchedLine()) && line_id == granthVars.getLineId()){
                    gurbani_text.append(BIG_BOLD_FONT_COLOR + lines[0] + BIG_BOLD_FONT_SINGLE_BREAK);
                }else{
                    gurbani_text.append(GURBANI_FONT + lines[0] + BIG_FONT_SINGLE_BREAK);
                }

                if(columns.equals(GURBANI)) {
                    gurbani_text.append("<br>");

                } else if(columns.equals(GURBANI_PUNJABI)){
                    gurbani_text.append(PUNJABI_FONT + lines[1] + DOUBLE_BREAK);

                } else if(columns.equals(GURBANI_TEEKA)){
                    teeka_null_case(lines[1], lines[2]);

                } else if(columns.equals(GURBANI_ENGLISH)){
                    gurbani_text.append(ENGLISH_FONT + lines[1] + DOUBLE_BREAK);

                } else if(columns.equals(GURBANI_ENGLISH_TEEKA)){
                    gurbani_text.append(ENGLISH_FONT + lines[1] + SINGLE_BREAK);
                    teeka_null_case(lines[2], lines[3]);

                } else if(columns.equals(GURBANI_PUNJABI_TEEKA)){
                    gurbani_text.append(PUNJABI_FONT + lines[1] + SINGLE_BREAK);
                    teeka_null_case(lines[2], lines[3]);

                } else if(columns.equals(GURBANI_PUNJABI_ENGLISH)){
                    gurbani_text.append(PUNJABI_FONT + lines[1] + SINGLE_BREAK + ENGLISH_FONT +
                            lines[2] + DOUBLE_BREAK);

                }else if(columns.equals(GURBANI_PUNJABI_ENGLISH_TEEKA)){
                    gurbani_text.append(PUNJABI_FONT + lines[1] + SINGLE_BREAK + ENGLISH_FONT + lines[2] +
                            SINGLE_BREAK);
                    teeka_null_case(lines[3], lines[4]);

                }

            }
        }
        gurbani.setText(Html.fromHtml(gurbani_text.toString() + "<br><br>"));
    }

    private boolean check() {
        bookmarked_result = bookmarkDB.getAllData();
        while (bookmarked_result.moveToNext()) {
            if (bookmarked_result.getString(2).equals("" + page_num)) {
                bookmark.setImageResource(R.drawable.bookmark_add);
                return true;
            }
        }
        bookmark.setImageResource(R.drawable.bookmark_del);
        return false;
    }

    private void initialize() {
        gurbani = (TextView) findViewById(R.id.gurbani);
        textChange = (TextView) findViewById(R.id.textChange);
        title = (TextView) findViewById(R.id.title);

        punjabi = (CheckBox) findViewById(R.id.punjabi);
        teeka = (CheckBox) findViewById(R.id.teeka);
        english = (CheckBox) findViewById(R.id.english);
        light = (CheckBox) findViewById(R.id.light);

        number = (EditText) findViewById(R.id.number);

        previous = (Button) findViewById(R.id.previous);
        next = (Button) findViewById(R.id.next);
        search = (Button) findViewById(R.id.search);
        settings = (Button) findViewById(R.id.settings);

        scroll = (ScrollView) findViewById(R.id.SCROLLER_ID);

        bookmark = (ImageButton) findViewById(R.id.bookmark);

        textSeekBar = (SeekBar) findViewById(R.id.textSeekBar);

        textLayout = (LinearLayout) findViewById(R.id.textLayout);
        options = (LinearLayout) findViewById(R.id.options);
        downview = (LinearLayout) findViewById(R.id.downview);

        RelativeLayout screen_layout = (RelativeLayout) findViewById(R.id.screen_layout);
        view = screen_layout;

        animSlideIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animSlideOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        animSlideIn.setAnimationListener(this);
        animSlideOut.setAnimationListener(this);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/anmollipinumbers.ttf");
        gurbani.setTypeface(face);
        textLayout.setVisibility(View.GONE);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        spe = sp.edit();

        bookmarkDB = new DatabaseHelper(this);
    }

    private void downview_options() {
        // View GurbaniPunjabi
        punjabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pun = punjabi.isChecked();
                conditions();
            }
        });

        // View GurbaniTeeka
        teeka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tee = teeka.isChecked();
                conditions();
            }
        });

        // View GurbaniEnglish
        english.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                eng = english.isChecked();
                conditions();
            }
        });

        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (light.isChecked()) {
                    gurbani.setBackgroundColor(Color.BLACK);
                    options.setBackgroundColor(Color.BLACK);
                    title.setTextColor(Color.WHITE);
                    number.setTextColor(Color.WHITE);

                } else {
                    gurbani.setBackgroundColor(Color.WHITE);
                    options.setBackgroundColor(Color.WHITE);
                    title.setTextColor(Color.BLACK);
                    number.setTextColor(Color.BLACK);


                }

                spe.putBoolean("low_light", light.isChecked());
                spe.commit();

            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.clearAnimation();

                if (visibility == 0) {
                    textLayout.startAnimation(animSlideIn);
                    textLayout.setVisibility(View.GONE);
                    visibility = 1;
                } else {
                    textLayout.startAnimation(animSlideOut);
                    textLayout.setVisibility(View.VISIBLE);
                    visibility = 0;
                    textSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                            textChange.setText(String.valueOf(progress) + "pt");
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            gurbani.setTextSize(TypedValue.COMPLEX_UNIT_PX, (seekBar.getProgress() + 5) * getResources().getDisplayMetrics().density);
                            spe.putInt("seekNum", textSeekBar.getProgress());
                            spe.commit();
                        }
                    });
                }
            }
        });
    }

    private void open_ang() {
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page_num != 1) {
                    page_num -= 1;
                    check();
                    scroll.fullScroll(ScrollView.FOCUS_UP);
                    number.setText("" + page_num);
                    conditions();
                    granthVars.setSearchedLine("");
                    bani_case();
                }


            }
        });

        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (page_num != granthVars.getTotalAng()) {
                    page_num += 1;
                    check();
                    scroll.fullScroll(ScrollView.FOCUS_UP);
                    number.setText("" + page_num);
                    conditions();
                    granthVars.setSearchedLine("");
                    bani_case();
                }

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(search.getText().toString().equals(" Jump to Ang " + page_num + " ")){
                    granthVars.setBani(GURU_GRANTH);
                }

                if (!(number.getText().toString().equals(""))) {

                    page_num = Integer.parseInt(number.getText().toString());

                    if (page_num <= granthVars.getTotalAng()) {
                        check();
                        scroll.fullScroll(ScrollView.FOCUS_UP);
                        conditions();
                        granthVars.setSearchedLine("");
                        bani_case();
                    } else {
                        // Doesn't work on Sharedpreference.
                        Toast.makeText(getApplicationContext(), granthVars.getTitle() + " " + page_num + " does not exist.", Toast.LENGTH_SHORT).show();
                    }

                }
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });
    }

    private void bani_case() {
        if (granthVars.getBani().equals("guru_granth")) {
            spe.putInt("granth_num", page_num);
            spe.commit();

        } else if (granthVars.getBani().equals("japji_sahib")) {
            spe.putInt("japji_num", page_num);
            spe.commit();

        } else if (granthVars.getBani().equals("sukhmani_sahib")) {
            spe.putInt("sukhmani_num", page_num);
            spe.commit();

        } else if (granthVars.getBani().equals("anand_sahib")) {
            spe.putInt("anand_num", page_num);
            spe.commit();

        }
    }

    private void main_layout_options() {
        gurbani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visibility == 0) {
                    textLayout.startAnimation(animSlideIn);
                    textLayout.setVisibility(View.GONE);
                    visibility = 1;
                }
            }
        });


        final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

            public boolean onDoubleTap(MotionEvent e) {
                if (touch == 1) {
                    options.setVisibility(View.GONE);
                    downview.setVisibility(View.GONE);
                    touch = 0;
                } else {
                    options.setVisibility(View.VISIBLE);
                    downview.setVisibility(View.VISIBLE);
                    touch = 1;
                }
                return true;
            }

        });

        gurbani.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);

            }

        });


        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!check()) {
                    boolean isInserted;
                    if (!granthVars.getSearchedLine().equals("")) {
                        isInserted = bookmarkDB.insertData(granthVars.getSearchedLine(), "" + page_num, granthVars.getBani());

                    } else {
                        isInserted = bookmarkDB.insertData("AMg " + page_num, "" + page_num, granthVars.getBani());
                    }
                    if (isInserted) {
                        Toast.makeText(getApplicationContext(), "Ang added to bookmarks.", Toast.LENGTH_LONG).show();
                        bookmark.setImageResource(R.drawable.bookmark_add);
                    }
                } else {
                    bookmarked_result = bookmarkDB.getAllData();
                    while (bookmarked_result.moveToNext()) {
                        if (bookmarked_result.getString(2).equals("" + page_num)) {
                            bookmarkDB.deleteData(bookmarked_result.getString(0));
                        }
                    }
                    bookmark.setImageResource(R.drawable.bookmark_del);
                    Toast.makeText(getApplicationContext(), "Ang/Shabad removed from bookmarks.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void view_correct_pankti_info_and_layout(Cursor cursor){
        // View Correct Title
        title.setText(granthVars.getCorrectTitle());

        // When there's only one page, no need to show Number EditText, Search/Previous/Next Button
        if (granthVars.getBani().equals(JAAP_SAHIB) || granthVars.getBani().equals(CHAUPAI_SAHIB)
                || granthVars.getBani().equals(TAV_PARSAD) || granthVars.getBani().equals(CHOTTA_ANAND_SAHIB)
                || granthVars.getBani().equals(REHRAAS_SAHIB)
                || granthVars.getBani().equals(KIRTAN_SOHILA) || granthVars.getBani().equals(SALOK_MAHALLA_9)
                || granthVars.getBani().equals(LAAVAN) || granthVars.getBani().equals(SHABAD)) {
            number.setVisibility(View.GONE);
            search.setVisibility(View.GONE);
            previous.setVisibility(View.GONE);
            next.setVisibility(View.GONE);
        }

        if (granthVars.getBani().equals(SHABAD)) {

            // If bani is Shabad, change the title to view Author/Raag info and set the new page number.
            if(cursor.moveToFirst()){
                int author_col = cursor.getColumnIndex("Author");
                int raag_col = cursor.getColumnIndex("Raag");
                title.setText((cursor.getString(author_col) + "  \n" + cursor.getString(raag_col) + "  \n"));
                search.setVisibility(View.VISIBLE);
                search.setText(" Jump to Ang " + page_num + " ");
                cursor.moveToPrevious();
            }

        }else if (granthVars.getBani().equals(GURU_GRANTH) || granthVars.getBani().equals(JAPJI_SAHIB)
                || granthVars.getBani().equals(SUKHMANI_SAHIB) || granthVars.getBani().equals(ANAND_SAHIB)){

            // If bani contains more than 1 page, show Number EditText, Search/Previous/Next Button and
            // set the Search Button to "Search"
            number.setVisibility(View.VISIBLE);
            search.setVisibility(View.VISIBLE);
            previous.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);

            search.setText("Search");
        }


        // Bookmark should be visible if bani is shabad or guru_granth.
        if(granthVars.getBani().equals(SHABAD) || granthVars.getBani().equals(GURU_GRANTH)){
            bookmark.setVisibility(View.VISIBLE);
        }else{
            bookmark.setVisibility(View.GONE);
        }

        // Do not show teeka/english checkboxes, since the translation to the banis below does not exist.
        if (granthVars.getBani().equals(JAAP_SAHIB)
                || granthVars.getBani().equals(CHAUPAI_SAHIB) || granthVars.getBani().equals(TAV_PARSAD)) {
            teeka.setVisibility(View.GONE);
            english.setVisibility(View.GONE);
        }
    }

    private void teeka_null_case(String pad_arth, String arth){
        if(pad_arth.equals("") || arth.equals("")){
            if(pad_arth.equals("") && !arth.equals(""))
                gurbani_text.append(TEEKA_ARTH_FONT + arth + DOUBLE_BREAK);

            if(!pad_arth.equals("") && arth.equals(""))
                gurbani_text.append(TEEKA_PAD_ARTH_FONT + pad_arth + DOUBLE_BREAK);

            if(pad_arth.equals("") && arth.equals(""))
                gurbani_text.append(SINGLE_BREAK);

        }else{
            gurbani_text.append(TEEKA_PAD_ARTH_FONT + pad_arth + SINGLE_BREAK + TEEKA_ARTH_FONT +
                    arth + DOUBLE_BREAK);
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
                constant.sggsMethod(parent, view, position, id, Gurbani.this);
            }
        });

        ListView general = (ListView) findViewById(R.id.general_menu);
        ArrayAdapter<String> adapter2 = new GeneralArrayAdapter(this, R.layout.simplerow, GENERAL);
        general.setAdapter(adapter2);

        general.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toggle();
                constant.generalMethod(parent, view, position, id, Gurbani.this);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(CURRENT_BANI_PREF, granthVars.getBani());
        savedInstanceState.putString(BANI_NAME_PREF, granthVars.getTitle());
        savedInstanceState.putInt(ANG_PREF, granthVars.getTotalAng());
        savedInstanceState.putInt(POS_PREF, granthVars.getTranslationCase());
        savedInstanceState.putInt(SHABAD_PREF, granthVars.getShabadAng());

    }

    @Override
    public void onBackPressed() {
        if (visibility == 0) {
            textLayout.startAnimation(animSlideIn);
            textLayout.setVisibility(View.GONE);
            visibility = 1;
        } else {
            finish();
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        textLayout.clearAnimation();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }
}