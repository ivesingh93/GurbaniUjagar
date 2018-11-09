package gurbani.ujagar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import static gurbani.ujagar.Constants.GURU_GRANTH;
import static gurbani.ujagar.Constants.SGGS;
import static gurbani.ujagar.Constants.SGGS_ANG;
import static gurbani.ujagar.Constants.SHABAD;
import static gurbani.ujagar.Constants.SHABAD_LIST;
import static gurbani.ujagar.Constants.granthVars;

public class Shabad extends SlidingListActivity {

    @Override
    public void onBackPressed() {
        try {
            Class ourClass = Class.forName("gurbani.ujagar.ShabadActivity");
            Intent ourIntent = new Intent(Shabad.this, ourClass);
            startActivity(ourIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ShabadActivity.arr.clear();
        finish();
    }

    public class MyCustomAdapter extends ArrayAdapter<String> {
        private Typeface font;
        private TextView line, info;

        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<String> arr) {

            super(context, textViewResourceId, arr);
            font = Typeface.createFromAsset(getAssets(), "fonts/anmollipinumbers.ttf");
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.mylist, null);
            }

            String line_info = ShabadActivity.arr.get(position);
            String[] line_arr = line_info.split(";;;");
            String gurmukhi = line_arr[0];
            int page = Integer.parseInt(line_arr[1]);

            if(line_arr[4].equals("null")){
                info = (TextView) v.findViewById(R.id.info);
                info.setTextSize(14);
                info.setText("Ang " + page);
            }else{
                info = (TextView) v.findViewById(R.id.info);
                info.setTextSize(14);
                info.setText("Ang " + page + "\nAuthor: " + line_arr[2] + "\nRaag: " + line_arr[3]);
            }


            line = (TextView) v.findViewById(R.id.line);
            line.setTypeface(font);
            line.setTextSize(24);
            line.setText("" + gurmukhi);



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
        if(savedInstanceState != null){
            ShabadActivity.arr = savedInstanceState.getStringArrayList(SHABAD_LIST);

        }
        setListAdapter(new MyCustomAdapter(Shabad.this, R.layout.mylist, ShabadActivity.arr));
        final ListView list = getListView();
        list.setBackgroundColor(Color.rgb(85, 129, 136));

    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Object o = this.getListAdapter().getItem(position);

        String[] line_arr = o.toString().split(";;;");

        granthVars.setShabadAng(Integer.parseInt(line_arr[1]));
        granthVars.setSearchedLine(line_arr[0]);


        if(!line_arr[4].equals("null")){
            granthVars.setKirtanId(Integer.parseInt(line_arr[4]));
            granthVars.setBani(SHABAD);
        }else{
            granthVars.setBani(GURU_GRANTH);
        }

        granthVars.setLineId(Integer.parseInt(line_arr[5]));

        final CharSequence options[] = new CharSequence[]{"Ang/Shabad Only", "Ang/Shabad with Punjabi Teeka", "Ang/Shabad with Punjabi Translation", "Ang/Shabad with English Translation"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Shabad.this);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which >= 0 && which <= 3){

                    granthVars.setTranslationCase(which);
                    granthVars.setTotalAng(SGGS_ANG);

                    try {
                        Class ourClass = Class.forName("gurbani.ujagar.Gurbani");
                        Intent i = new Intent(Shabad.this, ourClass);
                        startActivity(i);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        builder.show();

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
                constant.sggsMethod(parent, view, position, id, Shabad.this);
            }
        });

        ListView general = (ListView) findViewById(R.id.general_menu);
        ArrayAdapter<String> adapter2 = new GeneralArrayAdapter(this, R.layout.simplerow, GENERAL);
        general.setAdapter(adapter2);

        general.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toggle();
                constant.generalMethod(parent, view, position, id, Shabad.this);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putStringArrayList(SHABAD_LIST, ShabadActivity.arr);

    }
}
