package gurbani.ujagar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GeneralArrayAdapter extends ArrayAdapter<String>  {
	TextView textView;
	ImageView logo;
	private final Context context;
	private final String[] values;
	public GeneralArrayAdapter(Context context, int textViewResourceId, String[] values) {
		super(context, textViewResourceId, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v == null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_mobile, null);
			textView = (TextView) v.findViewById(R.id.label);
			logo = (ImageView) v.findViewById(R.id.logo);
		}
		textView.setText(values[position]);
		textView.setTextColor(Color.WHITE);
		String s = values[position];
		if (s.equals("Apps By Vismaad Apps")) {
			logo.setImageResource(R.drawable.android);
		}else if (s.equals("Rate This App")){
			logo.setImageResource(R.drawable.rate);
		}else if (s.equals("Follow on Facebook")) {
			logo.setImageResource(R.drawable.facebook);
		}else if(s.equals("Daily Inspiration")){
			logo.setImageResource(R.drawable.inspiration);
		}else if(s.equals("Feedback")){
			logo.setImageResource(R.drawable.feedback);
		}
		return v;
	}

}
