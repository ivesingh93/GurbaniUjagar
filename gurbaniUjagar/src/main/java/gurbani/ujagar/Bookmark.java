package gurbani.ujagar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingListActivity;

import java.util.ArrayList;

import static gurbani.ujagar.Constants.GENERAL;
import static gurbani.ujagar.Constants.SGGS;
import static gurbani.ujagar.Constants.granthVars;

/**
 * Created by Waheguru on 6/7/15.
 */
public class Bookmark extends SlidingListActivity {
    ArrayList<String> bookmark_arr = new ArrayList<String>();
    ArrayList<String> bookmark_arr_date = new ArrayList<String>();
    ArrayList<String> bookmark_arr_ang = new ArrayList<String>();
    String[] arr = {"January" , "February" , "March" , "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    DatabaseHelper bookmarkDB;
    static String keyword;
    Cursor res;
    private TextView text, date, ang;

    public class MyCustomAdapter extends ArrayAdapter<String> {
        private Typeface font;
        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<String> arr) {

            super(context, textViewResourceId, arr);
            font = Typeface.createFromAsset(getAssets(), "fonts/anmollipinumbers.ttf");
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.custom_view, null);

            }
            text = (TextView) v.findViewById(R.id.text);
            date = (TextView) v.findViewById(R.id.date);
            ang = (TextView) v.findViewById(R.id.ang);

            text.setTypeface(font);
            text.setTextSize(24);
            text.setText(bookmark_arr.get(position));

            date.setTextSize(16);
            date.setText(bookmark_arr_date.get(position));

            ang.setTypeface(font);
            ang.setTextSize(16);
            ang.setText(bookmark_arr_ang.get(position));
            return v;
        }
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        slide_menu();
        bookmarkDB = new DatabaseHelper(Bookmark.this);
        res = bookmarkDB.getAllData();

        while(res.moveToNext()){
            bookmark_arr.add(res.getString(1));
            String date[] = res.getString(4).split(";");
            String mon[] = date[0].split("-");
            bookmark_arr_date.add(mon[2] + " " + month(mon[1]) + ", " + mon[0]);
            bookmark_arr_ang.add("AMg " +res.getString(2));
        }

        setListAdapter(new MyCustomAdapter(Bookmark.this, R.layout.custom_view, bookmark_arr));
        final ListView list = getListView();
        list.setBackgroundColor(Color.rgb(85, 129, 136));

    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        Object o = this.getListAdapter().getItem(position);
        keyword = o.toString();
        res = bookmarkDB.getAllData();
        while(res.moveToNext()){
            if (keyword.equals(res.getString(1))){
                granthVars.setBookmarkAng(Integer.parseInt(res.getString(2)));
                granthVars.setBani(res.getString(3));

                if(!res.getString(1).contains("AMg")) {
                    granthVars.setSearchedLine(res.getString(1));
                }
            }
        }

        CharSequence options[] = new CharSequence[]{"Ang/Shabad Only", "Ang/Shabad with Punjabi Teeka", "Ang/Shabad with Punjabi Translation", "Ang/Shabad with English Translation"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Bookmark.this);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which >= 0 && which <= 3){

                    granthVars.setTotalAng(1430);
                    granthVars.setTranslationCase(which);


                    try {
                        Class ourClass = Class.forName("gurbani.ujagar.Gurbani");
                        Intent i = new Intent(Bookmark.this, ourClass);
                        startActivity(i);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        builder.show();
    }

    public String month(String month){
        if (month.equals("01")){
            return arr[0];
        }else if (month.equals("02")){
            return arr[1];
        }else if (month.equals("03")){
            return arr[2];
        }else if (month.equals("04")){
            return arr[3];
        }else if (month.equals("05")){
            return arr[4];
        }else if (month.equals("06")){
            return arr[5];
        }else if (month.equals("07")){
            return arr[6];
        }else if (month.equals("08")){
            return arr[7];
        }else if (month.equals("09")){
            return arr[8];
        }else if (month.equals("10")){
            return arr[9];
        }else if (month.equals("11")){
            return arr[10];
        }else{
            return arr[11];
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
                constant.sggsMethod(parent, view, position, id, Bookmark.this);
            }
        });

        ListView general = (ListView) findViewById(R.id.general_menu);
        ArrayAdapter<String> adapter2 = new GeneralArrayAdapter(this, R.layout.simplerow, GENERAL);
        general.setAdapter(adapter2);

        general.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toggle();
                constant.generalMethod(parent, view, position, id, Bookmark.this);
            }
        });
    }

    @Override
    public void onRestart() {  // After a pause OR at startup
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
