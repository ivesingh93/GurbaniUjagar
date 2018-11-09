package gurbani.ujagar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;

import java.util.ArrayList;

import static gurbani.ujagar.Constants.GURU_GRANTH;
import static gurbani.ujagar.Constants.GURU_GRANTH_TITLE;
import static gurbani.ujagar.Constants.granthVars;

public class AuthorIndex extends ExpandableListActivity{

	private ArrayList<String> parentItems = new ArrayList<String>();
	private ArrayList<Object> childItems = new ArrayList<Object>();
	ExpandableListView expandableList;
	private int lastExpandedPosition = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// this is not really  necessary as ExpandableListActivity contains an ExpandableList
		//setContentView(R.layout.main);


		expandableList = getExpandableListView(); // you can use (ExpandableListView) findViewById(R.id.list)

		expandableList.setDividerHeight(2);
		expandableList.setGroupIndicator(null);
		expandableList.setClickable(true);

		setGroupParents();
		setChildData();

		AuthorExpandableAdapter adapter = new AuthorExpandableAdapter(AuthorIndex.this, parentItems, childItems);

		adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
		expandableList.setAdapter(adapter);
		expandableList.setOnChildClickListener(this);

		expandableList.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				if (lastExpandedPosition != -1
						&& groupPosition != lastExpandedPosition) {
					expandableList.collapseGroup(lastExpandedPosition);
				}
				lastExpandedPosition = groupPosition;
			}
		});
	}



	public void setGroupParents() {
		parentItems.add("Baba Sheikh Farid");
		parentItems.add("Baba Sundar");
		parentItems.add("Bhagat Beni");
		parentItems.add("Bhagat Bhikhan");
		parentItems.add("Bhagat Dhanna");
		parentItems.add("Bhagat Kabir");
		parentItems.add("Bhagat Namdev");
		parentItems.add("Bhagat Parmanand");
		parentItems.add("Bhagat Pipa");
		parentItems.add("Bhagat Ravidas");
		parentItems.add("Bhagat Sain");
		parentItems.add("Bhagat Surdas");
		parentItems.add("Bhagat Trilochan");
		parentItems.add("Bhagat Jaidev");
		parentItems.add("Bhagat Ramanand");
		parentItems.add("Bhagat Sadhna");
		parentItems.add("Bhai Mardana");
		parentItems.add("Bhatt Balh");
		parentItems.add("Bhatt Bhalh");
		parentItems.add("Bhatt Bhikha");
		parentItems.add("Bhatt Gayand");
		parentItems.add("Bhatt Harbans");
		parentItems.add("Bhatt Jal-Jalap");
		parentItems.add("Bhatt Kal Sahar");
		parentItems.add("Bhatt Kalh");
		parentItems.add("Bhatt Kirat");
		parentItems.add("Bhatt Mathura");
		parentItems.add("Bhatt Nalh");
		parentItems.add("Bhatt Salh");
		parentItems.add("Bhatt Satta & Balwand");
		parentItems.add("Bhatt Talh");
		parentItems.add("Guru Amar Das Ji");
		parentItems.add("Guru Angad Dev Ji");
		parentItems.add("Guru Arjan Dev Ji");
		parentItems.add("Guru Nanak Dev Ji");
		parentItems.add("Guru Ram Das Ji");
		parentItems.add("Guru Teg Bahadur Ji");
	}

	public void setChildData() {

		// Baba Sheikh Farid
		ArrayList<String> child = new ArrayList<String>();
		child.add("Ang 488 - Raag Asa");
		child.add("Ang 794 - Raag Suhi");
		child.add("Ang 794 - Raag Suhi Lalit");
		child.add("Ang 1377 - Salok");
		childItems.add(child);

		// Baba Sundar
		child = new ArrayList<String>();
		child.add("Ang 923 - Raag Raamkali");
		childItems.add(child);

		// Bhagat Beni
		child = new ArrayList<String>();
		child.add("Ang 1351 - Raag Parbhati");
		child.add("Ang 974 - Raag Raamkali");
		child.add("Ang 93 - Sri Raag");
		childItems.add(child);

		// Bhagat Bhikhan
		child = new ArrayList<String>();
		child.add("Ang 659 - Raag Sorath");
		childItems.add(child);

		// Bhagat Dhanna
		child = new ArrayList<String>();
		child.add("Ang 487 - Raag Asa");
		child.add("Ang 695 - Raag Dhanaasree");
		childItems.add(child);

		// Bhagat Kabir
		child = new ArrayList<String>();
		child.add("Ang 330 - Gauri and Sorith");
		child.add("Ang 343 - Gauri Thiteen");
		child.add("Ang 475 - Raag Asa");
		child.add("Ang 1193 - Raag Basant");
		child.add("Ang 1195 - Raag Basant Hindol");
		child.add("Ang 1157 - Raag Bhairao");
		child.add("Ang 555 - Raag Bihagarra");
		child.add("Ang 855 - Raag Bilaaval");
		child.add("Ang 691 - Raag Dhanaasree");
		child.add("Ang 323 - Raag Gauri");
		child.add("Ang 332 - Raag Gauri Bairaagan");
		child.add("Ang 331 - Raag Gauri Chaytee");
		child.add("Ang 323 - Raag Gauri Guaarayree");
		child.add("Ang 334 - Raag Gauri Poorbee");
		child.add("Ang 870 - Raag Gond");
		child.add("Ang 524 - Raag Gujri");
		child.add("Ang 1123 - Raag Kedaraa");
		child.add("Ang 1102 - Raag Maaroo");
		child.add("Ang 1349 - Raag Prabhaati");
		child.add("Ang 947 - Raag Raamkali");
		child.add("Ang 1251 - Raag Saarag");
		child.add("Ang 654 - Raag Sorath");
		child.add("Ang 792 - Raag Suhi");
		child.add("Ang 793 - Raag Suhi Lalit");
		child.add("Ang 727 - Raag Tilang");
		child.add("Ang 1364 - Salok");
		child.add("Ang 91 - Sri Raag");		
		childItems.add(child);

		// Bhagat Namdev
		child = new ArrayList<String>();
		child.add("Ang 485 - Raag Asa");
		child.add("Ang 1195 - Raag Basant");
		child.add("Ang 1163 - Raag Bhairao");
		child.add("Ang 857 - Raag Bilaaval");
		child.add("Ang 874 - Raag Bilaaval Gond");
		child.add("Ang 692 - Raag Dhanaasree");
		child.add("Ang 345 - Raag Gauri Chaytee");
		child.add("Ang 873 - Raag Gond");
		child.add("Ang 525 - Raag Gujri");
		child.add("Ang 1318 - Raag Kaandda");
		child.add("Ang 1105 - Raag Maaroo");
		child.add("Ang 1292 - Raag Malaar");
		child.add("Ang 988 - Raag Maali Gaura");
		child.add("Ang 1350 - Raag Prabhaati");
		child.add("Ang 972 - Raag Raamkali");
		child.add("Ang 1252 - Raag Saarag");
		child.add("Ang 656 - Raag Sorath");
		child.add("Ang 727 - Raag Tilang");
		child.add("Ang 718 - Raag Todi");
		childItems.add(child);

		// Bhagat Parmanand
		child = new ArrayList<String>();
		child.add("Ang 1253 - Raag Saarag");
		childItems.add(child);

		// Bhagat Pipa
		child = new ArrayList<String>();
		child.add("Ang 695 - Raag Dhanaasree");
		childItems.add(child);

		// Bhagat Ravidas
		child = new ArrayList<String>();
		child.add("Ang 486 - Raag Asa");
		child.add("Ang 1196 - Raag Basant");
		child.add("Ang 1167 - Raag Bhairao");
		child.add("Ang 858 - Raag Bilaaval");
		child.add("Ang 694 - Raag Dhanaasree");
		child.add("Ang 345 - Raag Gauri");
		child.add("Ang 345 - Raag Gauri Bairaagan");
		child.add("Ang 346 - Raag Gauri Poorbee");
		child.add("Ang 875 - Raag Gond");
		child.add("Ang 525 - Raag Gujri");
		child.add("Ang 710 - Raag Jaitsri");
		child.add("Ang 1124 - Raag Kedaraa");
		child.add("Ang 1106 - Raag Maaroo");
		child.add("Ang 1293 - Raag Malaar");
		child.add("Ang 973 - Raag Raamkali");
		child.add("Ang 657 - Raag Sorath");
		child.add("Ang 793 - Raag Suhi");
		child.add("Ang 93 - Sri Raag");
		childItems.add(child);

		// Bhagat Sain
		child = new ArrayList<String>();
		child.add("Ang 695 - Raag Dhanaasree");
		childItems.add(child);


		// Bhagat Surdas
		child = new ArrayList<String>();
		child.add("Ang 1253 - Raag Saarag");
		childItems.add(child);

		// Bhagat Trilochan
		child = new ArrayList<String>();
		child.add("Ang 695 - Raag Dhanaasree");
		child.add("Ang 525 - Raag Gujri");
		child.add("Ang 92 - Sri Raag");
		childItems.add(child);

		// Bhagat Jaidev
		child = new ArrayList<String>();
		child.add("Ang 526 - Raag Gujri");
		child.add("Ang 1106 - Raag Maaroo");
		childItems.add(child);

		// Bhagat Ramanand
		child = new ArrayList<String>();
		child.add("Ang 1195 - Raag Basant Hindol");
		childItems.add(child);

		// Bhagat Sadhna
		child = new ArrayList<String>();
		child.add("Ang 858 - Raag Bilaaval");
		childItems.add(child);

		// Bhai Mardana
		child = new ArrayList<String>();
		child.add("Ang 553 - Raag Bihagarra");
		childItems.add(child);

		// Bhatt Balh
		child = new ArrayList<String>();
		child.add("Ang 1405 - Swaiyyai (Praise of Guru Ram Das Ji)");
		childItems.add(child);

		// Bhatt Bhalh
		child = new ArrayList<String>();
		child.add("Ang 1396 - Swaiyyai (Praise of Guru Amar Das Ji)");
		childItems.add(child);

		// Bhatt Bhikha
		child = new ArrayList<String>();
		child.add("Ang 1395 - Swaiyyai (Praise of Guru Amar Das Ji)");
		childItems.add(child);

		// Bhatt Gayand
		child = new ArrayList<String>();
		child.add("Ang 1400 - Swaiyyai (Praise of Guru Ram Das Ji)");
		childItems.add(child);

		// Bhatt Harbans
		child = new ArrayList<String>();
		child.add("Ang 1409 - Swaiyyai (Praise of Guru Arjan Dev Ji)");
		childItems.add(child);

		// Bhatt Jal-Jalap
		child = new ArrayList<String>();
		child.add("Ang 1394 - Swaiyyai (Praise of Guru Amar Das Ji)");
		childItems.add(child);

		// Bhatt Kal Sahar
		child = new ArrayList<String>();
		child.add("Ang 1391 - Swaiyyai (Praise of Guru Angad Dev Ji)");
		childItems.add(child);

		// Bhatt Kalh
		child = new ArrayList<String>();
		child.add("Ang 1392 - Swaiyyai (Praise of Guru Amar Das Ji)");
		child.add("Ang 1391 - Swaiyyai (Praise of Guru Angad Dev Ji)");
		child.add("Ang 1406 - Swaiyyai (Praise of Guru Arjan Dev Ji)");
		child.add("Ang 1389 - Swaiyyai (Praise of Guru Nanak Dev Ji)");
		child.add("Ang 1396 - Swaiyyai (Praise of Guru Ram Das Ji)");
		childItems.add(child);

		// Bhatt Kirat
		child = new ArrayList<String>();
		child.add("Ang 1395 - Swaiyyai (Praise of Guru Amar Das Ji");
		childItems.add(child);

		// Bhatt Mathura
		child = new ArrayList<String>();
		child.add("Ang 1408 - Swaiyyai (Praise of Guru Arjan Dev Ji)");
		child.add("Ang 1404 - Swaiyyai (Praise of Guru Ram Das Ji)");
		childItems.add(child);

		// Bhatt Nalh
		child = new ArrayList<String>();
		child.add("Ang 1398 - Swaiyyai (Praise of Guru Ram Das Ji)");
		childItems.add(child);

		// Bhatt Salh
		child = new ArrayList<String>();
		child.add("Ang 1396 - Swaiyyai (Praise of Guru Amar Das Ji)");
		child.add("Ang 1406 - Swaiyyai (Praise of Guru Ram Das Ji)");
		childItems.add(child);

		// Bhatt Satta & Balwand
		child = new ArrayList<String>();
		child.add("Ang 966 - Raag Raamkali");
		childItems.add(child);

		// Bhatt Talh
		child = new ArrayList<String>();
		child.add("Ang 1392 - Swaiyyai (Praise of Guru Angad Dev Ji)");
		childItems.add(child);

		// Guru Amar Das Ji
		child = new ArrayList<String>();
		child.add("Ang 360 - Raag Asa");
		child.add("Ang 1169 - Raag Basant");
		child.add("Ang 1177 - Raag Basant Hindol");
		child.add("Ang 1127 - Raag Bhairao");
		child.add("Ang 548 - Raag Bihagarra");
		child.add("Ang 796 - Raag Bilaaval");
		child.add("Ang 663 - Raag Dhanaasree");
		child.add("Ang 229 - Raag Gauri");
		child.add("Ang 162 - Raag Gauri Bairaagan");
		child.add("Ang 157 - Raag Gauri Guaarayree");
		child.add("Ang 243 - Raag Gauri Poorbee");
		child.add("Ang 490 - Raag Gujri");
		child.add("Ang 508 - Raag Gujri Ki Vaar");
		child.add("Ang 109 - Raag Maajh");
		child.add("Ang 993 - Raag Maaroo");
		child.add("Ang 1257 - Raag Malaar");
		child.add("Ang 1332 - Raag Prabhaati");
		child.add("Ang 1346 - Raag Prabhaati Bibhaas");
		child.add("Ang 880 - Raag Raamkali");
		child.add("Ang 1233 - Raag Saarag");
		child.add("Ang 599 - Raag Sorath");
		child.add("Ang 753 - Raag Suhi");
		child.add("Ang 365 - Raag Thitee Gauri");
		child.add("Ang 558 - Raag Vadahans");
		child.add("Ang 1376 - Salok");
		child.add("Ang 1412 - Salok Vaaraan and Vadheek");
		child.add("Ang 26 - Sri Raag");
		childItems.add(child);

		// Guru Angad Dev Ji
		child = new ArrayList<String>();
		child.add("Ang 463 - Raag Asa");		
		child.add("Ang 138 - Raag Maajh");
		child.add("Ang 1093 - Raag Maaroo");
		child.add("Ang 1279 - Raag Malaar");		
		child.add("Ang 954 - Raag Raamkali");
		child.add("Ang 1237 - Raag Saarag");
		child.add("Ang 653 - Raag Sorath");
		child.add("Ang 787 - Raag Suhi");
		child.add("Ang 83 - Sri Raag");
		childItems.add(child);

		// Guru Arjan Dev Ji
		child = new ArrayList<String>();
		child.add("Ang 1363 - Chaubolay");
		child.add("Ang 1360 - Gathaa");
		child.add("Ang 1429 - Mundaavani");
		child.add("Ang 1361 - Phunhay");
		child.add("Ang 12 - Raag Asa");
		child.add("Ang 409 - Raag Asa Aasavaree");
		child.add("Ang 720 - Raag Bairaarree");
		child.add("Ang 1180 - Raag Basant");
		child.add("Ang 1185 - Raag Basant Hindol");
		child.add("Ang 1136 - Raag Bhairao");
		child.add("Ang 537 - Raag Bihagarra");
		child.add("Ang 801 - Raag Bilaaval");
		child.add("Ang 528 - Raag Devagandhari");
		child.add("Ang 670 - Raag Dhanaasree");
		child.add("Ang 186 - Raag Gauri");
		child.add("Ang 203 - Raag Gauri Bairaagan");
		child.add("Ang 202 - Raag Gauri Chaytee");
		child.add("Ang 175 - Raag Gauri Guaarayree");
		child.add("Ang 214 - Raag Gauri Maalaa");
		child.add("Ang 214 - Raag Gauri Maalvaa");
		child.add("Ang 13  - Raag Gauri Poorbee");
		child.add("Ang 262  - Raag Gauri Sukhmanee");
		child.add("Ang 862 - Raag Gond");
		child.add("Ang 10 - Raag Gujri");
		child.add("Ang 700 - Raag Jaitsri");
		child.add("Ang 1298 - Raag Kaandda");
		child.add("Ang 1321 - Raag Kalayaan");
		child.add("Ang 1119 - Raag Kedaraa");
		child.add("Ang 96 - Raag Maajh");
		child.add("Ang 998 - Raag Maaroo");
		child.add("Ang 1266 - Raag Malaar");
		child.add("Ang 986 - Raag Maali Gaura");
		child.add("Ang 978 - Raag Nat Narayan");
		child.add("Ang 980 - Raag Nat Parhtaal");		
		child.add("Ang 1338 - Raag Prabhaati");
		child.add("Ang 1337 - Raag Prabhaati Bibhaas");
		child.add("Ang 882 - Raag Raamkali");
		child.add("Ang 1202 - Raag Saarag");
		child.add("Ang 608 - Raag Sorath");
		child.add("Ang 736 - Raag Suhi");
		child.add("Ang 761 - Raag Suhi Kaafee");
		child.add("Ang 296 - Raag Thitee Gauri");
		child.add("Ang 723 - Raag Tilang");
		child.add("Ang 711 - Raag Todi");
		child.add("Ang 1117 - Raag Tukhaari");
		child.add("Ang 562 - Raag Vadahans");
		child.add("Ang 1353 - Salok Sehshritee");
		child.add("Ang 1425 - Salok Vaaraan and Vadheek");
		child.add("Ang 1385 - Swaiyyai");
		child.add("Ang 42 - Sri Raag");

		childItems.add(child);

		// Guru Nanak Dev Ji
		child = new ArrayList<String>();
		child.add("Ang 1 - Jap");
		child.add("Ang 1 - Moolmantar");
		child.add("Ang 8 - Raag Asa");
		child.add("Ang 1168 - Raag Basant");
		child.add("Ang 1170 - Raag Basant Hindol");
		child.add("Ang 1125 - Raag Bhairao");
		child.add("Ang 556 - Raag Bihagarra");
		child.add("Ang 795 - Raag Bilaaval");
		child.add("Ang 843 - Raag Bilaaval Dakhnee");
		child.add("Ang 13 - Raag Dhanaasree");
		child.add("Ang 151 - Raag Gauri");
		child.add("Ang 156 - Raag Gauri Bairaagan");
		child.add("Ang 154 - Raag Gauri Chaytee");
		child.add("Ang 152 - Raag Gauri Dakhnee");
		child.add("Ang 12 - Raag Gauri Deepkee");
		child.add("Ang 151 - Raag Gauri Guaarayree");
		child.add("Ang 13  - Raag Gauri Poorbee");
		child.add("Ang 157  - Raag Gauri Poorbee Deepkee");
		child.add("Ang 489 - Raag Gujri");
		child.add("Ang 109 - Raag Maajh");
		child.add("Ang 989 - Raag Maaroo");
		child.add("Ang 1033 - Raag Maaroo Dakhnee");
		child.add("Ang 1014 - Raag Maaroo Kaafee");
		child.add("Ang 1254 - Raag Malaar");		
		child.add("Ang 1327 - Raag Prabhaati");
		child.add("Ang 1327 - Raag Prabhaati Bibhaas");
		child.add("Ang 1344 - Raag Prabhaati Dakhni");
		child.add("Ang 876 - Raag Raamkali");
		child.add("Ang 907 - Raag Raamkali Dakhni");
		child.add("Ang 1197 - Raag Saarag");
		child.add("Ang 595 - Raag Sorath");
		child.add("Ang 728 - Raag Suhi");
		child.add("Ang 751 - Raag Suhi Kaafee");
		child.add("Ang 418 - Raag Thitee Gauri");
		child.add("Ang 721 - Raag Tilang");
		child.add("Ang 1107 - Raag Tukhaari");
		child.add("Ang 557 - Raag Vadahans");
		child.add("Ang 580 - Raag Vadahans Dakhnee");
		child.add("Ang 8 - Salok");
		child.add("Ang 1353 - Salok Sehshritee");
		child.add("Ang 1410 - Salok Vaaraan and Vadheek");
		child.add("Ang 14 - Sri Raag");

		childItems.add(child);

		// Guru Ram Das Ji
		child = new ArrayList<String>();
		child.add("Ang 310 - Raag Asa");
		child.add("Ang 369 - Raag Asa Aasavaree");
		child.add("Ang 719 - Raag Bairaarree");
		child.add("Ang 1177 - Raag Basant");
		child.add("Ang 1178 - Raag Basant Hindol");
		child.add("Ang 1133 - Raag Bhairao");
		child.add("Ang 537 - Raag Bihagarra");
		child.add("Ang 798 - Raag Bilaaval");
		child.add("Ang 527 - Raag Devagandhari");
		child.add("Ang 666 - Raag Dhanaasree");
		child.add("Ang 234 - Raag Gauri");
		child.add("Ang 165 - Raag Gauri Bairaagan");
		child.add("Ang 163 - Raag Gauri Guaarayree");
		child.add("Ang 13  - Raag Gauri Poorbee");
		child.add("Ang 859 - Raag Gond");
		child.add("Ang 10 - Raag Gujri");
		child.add("Ang 696 - Raag Jaitsri");
		child.add("Ang 1294 - Raag Kaandda");
		child.add("Ang 1319 - Raag Kalayaan");
		child.add("Ang 1321 - Raag Kalayaan Bhopali");
		child.add("Ang 1118 - Raag Kedaraa");
		child.add("Ang 94 - Raag Maajh");
		child.add("Ang 995 - Raag Maaroo");
		child.add("Ang 1262 - Raag Malaar");
		child.add("Ang 984 - Raag Maali Gaura");
		child.add("Ang 975 - Raag Nat Narayan");		
		child.add("Ang 1335 - Raag Prabhaati");
		child.add("Ang 1335 - Raag Prabhaati Bibhaas");
		child.add("Ang 880 - Raag Raamkali");
		child.add("Ang 1198 - Raag Saarag");
		child.add("Ang 604 - Raag Sorath");
		child.add("Ang 731 - Raag Suhi");
		child.add("Ang 369 - Raag Thitee Gauri");
		child.add("Ang 723 - Raag Tilang");
		child.add("Ang 711 - Raag Todi");
		child.add("Ang 1113 - Raag Tukhaari");
		child.add("Ang 560 - Raag Vadahans");
		child.add("Ang 1421 - Salok Vaaraan and Vadheek");
		child.add("Ang 39 - Sri Raag");

		childItems.add(child);



		// Guru Teg Bahadur Ji
		child.add("Ang 411 - Raag Asa");
		child.add("Ang 1186 - Raag Basant");
		child.add("Ang 1186 - Raag Basant Hindol");
		child.add("Ang 537 - Raag Bihagarra");
		child.add("Ang 830 - Raag Bilaaval");
		child.add("Ang 536 - Raag Devagandhari");
		child.add("Ang 684 - Raag Dhanaasree");
		child.add("Ang 219 - Raag Gauri");
		child.add("Ang 1352 - Raag Jaijavanti");
		child.add("Ang 702 - Raag Jaitsri");
		child.add("Ang 1008 - Raag Maaroo");
		child.add("Ang 901 - Raag Raamkali");
		child.add("Ang 1231 - Raag Saarag");
		child.add("Ang 631 - Raag Sorath");
		child.add("Ang 726 - Raag Tilang");
		child.add("Ang 718 - Raag Todi");		
		child.add("Ang 1426 - Salok");
		childItems.add(child);
	}
}

class AuthorExpandableAdapter extends BaseExpandableListAdapter {

	static Activity activity;
	static ArrayList<Object> childtems;
	static LayoutInflater inflater;
	static ArrayList<String> parentItems, child;

	public AuthorExpandableAdapter(Activity context, ArrayList<String> parents, ArrayList<Object> childern) {
		activity = context;
		this.parentItems = parents;
		this.childtems = childern;
	}

	public void setInflater(LayoutInflater inflater, Activity activity) {
		this.inflater = inflater;
		this.activity = activity;
	}


	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

		child = (ArrayList<String>) childtems.get(groupPosition);

		TextView textView = null;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.group, null);
		}

		textView = (TextView) convertView.findViewById(R.id.textView1);
		textView.setText(child.get(childPosition));

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				//				if (childPosition == 0){
				//					
				//				}

				String text = child.get(childPosition);
				granthVars.setAuthorAng(Integer.parseInt(text.replaceAll("\\D", "")));

				CharSequence options[] = new CharSequence[]{"Gurbani Only", "Punjabi Teeka", "Punjabi Translation", "English Translation"};

				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle("Open Ang " + granthVars.getAuthorAng() + " with: ");
				builder.setItems(options, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						if(which >= 0 && which <= 3){
							granthVars.setBani(GURU_GRANTH);
							granthVars.setTotalAng(1430);
							granthVars.setTitle(GURU_GRANTH_TITLE);
							granthVars.setTranslationCase(which);

							try {
								Class ourClass = Class.forName("gurbani.ujagar.Gurbani");
								Intent i = new Intent(activity, ourClass);
								activity.startActivity(i);

							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
				});
				builder.show();



				//Toast.makeText(activity, ""+num, Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.row, null);
		}

		((CheckedTextView) convertView).setText(parentItems.get(groupPosition));
		((CheckedTextView) convertView).setChecked(isExpanded);

		return convertView;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return ((ArrayList<String>) childtems.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return parentItems.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	public void onGroupExpanded(int groupPosition) {

	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}

