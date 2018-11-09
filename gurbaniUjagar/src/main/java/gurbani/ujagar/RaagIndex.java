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

public class RaagIndex extends ExpandableListActivity{
	
	private ArrayList<String> parentItems = new ArrayList<String>();
	private ArrayList<Object> childItems = new ArrayList<Object>();
	ExpandableListView expandableList;
	private int lastExpandedPosition = -1;
	Context context;
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

		RaagExpandableAdapter adapter = new RaagExpandableAdapter(RaagIndex.this, parentItems, childItems);
		
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
		parentItems.add("japjisahib");
		parentItems.add("Rehraas Sahib");
		parentItems.add("Sohila Sahib");
		parentItems.add("Siri Raag");
		parentItems.add("Raag Maajh");
		parentItems.add("Raag Gauri");
		parentItems.add("Raag Asa");
		parentItems.add("Raag Gujri");
		parentItems.add("Raag Devagandhari");
		parentItems.add("Raag Bihagarra");
		parentItems.add("Raag Vadahans");
		parentItems.add("Raag Sorath");
		parentItems.add("Raag Dhanasri");
		parentItems.add("Raag Jaitsri");
		parentItems.add("Raag Todi");
		parentItems.add("Raag Bairaarree");
		parentItems.add("Raag Tilang");
		parentItems.add("Raag Soohi");
		parentItems.add("Raag Bilaaval");
		parentItems.add("Raag Gaund");
		parentItems.add("Raag Ramkalee");
		parentItems.add("Raag Nat Narayan");
		parentItems.add("Raag Maali Gaura");
		parentItems.add("Raag Maaroo");
		parentItems.add("Raag Tukhaari");
		parentItems.add("Raag Kedaraa");
		parentItems.add("Raag Bhairao");
		parentItems.add("Raag Basant");
		parentItems.add("Raag Saarag");
		parentItems.add("Raag Malaar");
		parentItems.add("Raag Kaandda");
		parentItems.add("Raag Kalayaan");
		parentItems.add("Raag Prabhaati");
		parentItems.add("Raag Jaijavanti");
		parentItems.add("No Raag");
	}

	public void setChildData() {
		
		// japjisahib
		ArrayList<String> child = new ArrayList<String>();
		child.add("Ang 1 - japjisahib");
		childItems.add(child);
		
		// Rehraas Sahib
		child = new ArrayList<String>();
		child.add("Ang 8 - Rehraas Sahib");
		childItems.add(child);
		
		// Sohila Sahib
		child = new ArrayList<String>();
		child.add("Ang 12 - Sohila Sahib");
		childItems.add(child);
		
		// Siri Raag
		child = new ArrayList<String>();
		child.add("Ang 14 - Guru Nanak Dev Ji");
		child.add("Ang 26 - Guru Amar Das Ji");
		child.add("Ang 39 - Guru Ram Das Ji");
		child.add("Ang 42 - Guru Arjan Dev Ji");
		child.add("Ang 53 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 64 - Guru Amar Das Ji, Ashtpadiyan");
		child.add("Ang 70 - Guru Arjan Dev Ji, Ashtapadiyan");
		child.add("Ang 71 - Guru Nanak Dev Ji");
		child.add("Ang 73 - Guru Arjan Dev Ji");
		child.add("Ang 74 - Guru Nanak Dev Ji");
		child.add("Ang 76 - Guru Ram Das Ji, Pahrei (The Hours)");
		child.add("Ang 77 - Guru Arjan Dev Ji, Pahrei (The Hours)");
		child.add("Ang 78 - Guru Ram Das Ji, Chhant");
		child.add("Ang 79 - Guru Arjan Dev Ji, Chhant");
		child.add("Ang 81 - Guru Ram Das Ji, Vanjara (The Merchant)");
		child.add("Ang 83 - Guru Amar Das Ji, Guru Ram Das Ji, Guru Nanak Dev Ji, Guru Angad Dev Ji, Sri Rag Ki Var Mahalla IV");
		child.add("Ang 91 - Bhagat Kabeer");
		child.add("Ang 92 - Bhagat Trilochan");
		child.add("Ang 92 - Bhagat Kabeer");
		child.add("Ang 93 - Bhagat Beni");
		child.add("Ang 93 - Bhagat Ravi Das");
		childItems.add(child);
			
		// Raag Majh
		child = new ArrayList<String>();
		child.add("Ang 94 - Guru Ram Das Ji");
		child.add("Ang 96 - Guru Arjan Dev Ji");
		child.add("Ang 109 - Guru Nanak Dev Ji, Ashtpadi");
		child.add("Ang 110 - Guru Amar Das Ji, Ashtpadiyan");
		child.add("Ang 129 - Guru Ram Das Ji, Ashtpadi");
		child.add("Ang 130 - Guru Arjan Dev Ji, Ashtpadiyan");
		child.add("Ang 133 - Guru Arjan Dev Ji, Bara Maha (Song of the Twelve Months");
		child.add("Ang 136 - Guru Arjan Dev Ji, Din-Rayni (Day and Night");
		child.add("Ang 137 - Guru Nanak Dev Ji, Guru Angad Dev Ji, Guru Ram Das Ji, Guru Amar Das Ji, Var in The Measure Majh");
		childItems.add(child);
		
		// Raag Gauri
		child = new ArrayList<String>();
		child.add("Ang 151 - Guru Nanak Dev Ji");
		child.add("Ang 157 - Guru Amar Das Ji");
		child.add("Ang 163 - Guru Ram Das Ji");
		child.add("Ang 175 - Guru Arjan Dev Ji");
		child.add("Ang 185 - Guru Arjan Dev Ji, Quartets and Couplets");
		child.add("Ang 219 - Guru Tegh Bahadur Ji");
		child.add("Ang 220 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 229 - Guru Amar Das Ji, Ashtpadiyan");
		child.add("Ang 234 - Guru Ram Das Ji, Karhalei (Camel Drivers)");
		child.add("Ang 235 - Guru Arjan Dev Ji, Ashtpadiyan");		
		child.add("Ang 242 - Guru Nanak Dev Ji, Chhant");
		child.add("Ang 243 - Guru Amar Das Ji, Chhant");
		child.add("Ang 247 - Guru Arjan Dev Ji, Chhant");
		child.add("Ang 250 - Guru Arjan Dev Ji, Baavan Akkhari (Acrostic)");
		child.add("Ang 262 - Guru Arjan Dev Ji, Sukhmani (Jewel of Bliss)");
		child.add("Ang 296 - Guru Arjan Dev Ji, Thittin (Lunar Dates)");
		child.add("Ang 300 - Guru Ram Das Ji, Guru Amar Das Ji, Guru Arjan Dev Ji, Var");
		child.add("Ang 318 - Guru Arjan Dev Ji, Var");
		child.add("Ang 323 - Bhagat Kabeer, Guru Arjan Dev Ji");
		child.add("Ang 330 - Bhagat Kabeer, Ashtpadi");
		child.add("Ang 340 - Bhagat Kabeer, Bawan Akkhari (Acrostic)");
		child.add("Ang 343 - Bhagat Kabeer, Thiteen (Lunar Dates of Fortnight)");
		child.add("Ang 344 - Bhagat Kabeer, Var - Seven Days of the Week");
		child.add("Ang 345 - Bhagat Naamdev");
		child.add("Ang 345 - Bhagat Ravi Das");	
		childItems.add(child);
		
		// Raag Asa
		child = new ArrayList<String>();
		child.add("Ang 347 - Guru Nanak Dev Ji");
		child.add("Ang 348 - Guru Ram Das Ji");
		child.add("Ang 348 - Guru Nanak Dev Ji");
		child.add("Ang 360 - Guru Amar Das Ji");
		child.add("Ang 364 - Guru Amar Das Ji, (Quintets)");
		child.add("Ang 365 - Guru Amar Das Ji, In the Form Kafi");
		child.add("Ang 365 - Guru Ram Das Ji");
		child.add("Ang 369 - Guru Ram Das Ji, To the Tune Kafi");
		child.add("Ang 370 - Guru Arjan Dev Ji");
		child.add("Ang 396 - Guru Arjan Dev Ji, To the Tune Kafi");
		child.add("Ang 409 - Guru Arjan Dev Ji, Asavari (sub-measure)");
		child.add("Ang 411 - Guru Tegh Bahadur Ji");
		child.add("Ang 411 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 418 - Guru Nanak Dev Ji, Ashtpadiyan in the Tune Kafi");
		child.add("Ang 422 - Guru Amar Das Ji, Ashtpadiyan");
		child.add("Ang 424 - Guru Amar Das Ji, Ashtpadiyan in the Tune Kafi");
		child.add("Ang 430 - Guru Arjan Dev Ji, Ashtpadiyan");
		child.add("Ang 432 - Guru Nanak Dev Ji, Patti (Acrostic)");
		child.add("Ang 434 - Guru Amar Das Ji, Patti (Acrostic)");
		child.add("Ang 435 - Guru Nanak Dev Ji, Chhant");
		child.add("Ang 439 - Guru Amar Das Ji, Chhant");
		child.add("Ang 442 - Guru Ram Das Ji, Chhant");
		child.add("Ang 452 - Guru Arjan Dev Ji, Chhant");
		child.add("Ang 462 - Guru Nanak Dev Ji, Guru Angad Dev Ji, Asa Ki Var");
		child.add("Ang 475 - Bhagat Kabeer");
		child.add("Ang 485 - Bhagat Naamdev");
		child.add("Ang 486 - Bhagat Ravi Das");
		child.add("Ang 487 - Bhagat Dhanna");
		child.add("Ang 488 - Bhagat Farid");
		childItems.add(child);
		
		// Raag Gujari
		child = new ArrayList<String>();
		child.add("Ang 489 - Guru Nanak Dev Ji");
		child.add("Ang 490 - Guru Amar Das Ji");
		child.add("Ang 492 - Guru Ram Das Ji");
		child.add("Ang 495 - Guru Arjan Dev Ji");
		child.add("Ang 503 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 506 - Guru Amar Das Ji");
		child.add("Ang 506 - Guru Ram Das Ji");
		child.add("Ang 507 - Guru Arjan Dev Ji");
		child.add("Ang 508 - Guru Amar Das Ji, Gujari Ki Var");
		child.add("Ang 517 - Guru Arjan Dev Ji, Var");
		child.add("Ang 524 - Bhagat Kabeer");
		child.add("Ang 525 - Bhagat Naamdev");
		child.add("Ang 525 - Bhagat Ravidas");
		child.add("Ang 525 - Bhagat Trilochan");
		child.add("Ang 526 - Bhagat Jaideva");
		childItems.add(child);
		
		// Raag Devagandhari
		child = new ArrayList<String>();
		child.add("Ang 527 - Guru Ram Das Ji");
		child.add("Ang 528 - Guru Arjan Dev Ji");
		child.add("Ang 528 - Guru Tegh Bahadur Ji");
		childItems.add(child);
		
		// Raag Bihagara
		child = new ArrayList<String>();
		child.add("Ang 537 - Guru Arjan Dev Ji");
		child.add("Ang 537- Guru Tegh Bahadur Ji");
		child.add("Ang 538 - Guru Ram Das Ji, Chhant");
		child.add("Ang 541 - Guru Arjan Dev Ji, Chhant");
		child.add("Ang 548 - Guru Ram Das Ji, Guru Amar Das Ji, Mardana, Guru Arjan Dev Ji, Guru Nanak Dev Ji, Var");
		childItems.add(child);
		
		// Raag Vadahans
		child = new ArrayList<String>();
		child.add("Ang 557 - Guru Nanak Dev Ji");
		child.add("Ang 558 - Guru Amar Das Ji");
		child.add("Ang 560 - Guru Ram Das Ji");
		child.add("Ang 562 - Guru Arjan Dev Ji");
		child.add("Ang 564 - Guru Amar Das Ji, Ashtpadiyan");
		child.add("Ang 565 - Guru Nanak Dev Ji, Chhant");
		child.add("Ang 567 - Guru Amar Das Ji, Chhant");
		child.add("Ang 572 - Guru Ram Das Ji, Chhant");
		child.add("Ang 575 - Guru Ram Das Ji, Ghorian (Song of the Mare)");
		child.add("Ang 576 - Guru Arjan Dev Ji");
		child.add("Ang 578 - Guru Nanak Dev Ji, Alahaniyan (Dirges)");
		child.add("Ang 582 - Guru Amar Das Ji");
		child.add("Ang 585 - Guru Ram Das Ji, Guru Amar Das Ji, Guru Nanak Dev Ji, Var");
		childItems.add(child);
		
		// Raag Sorath
		child = new ArrayList<String>();
		child.add("Ang 595 - Guru Nanak Dev Ji");
		child.add("Ang 599 - Guru Amar Das Ji");
		child.add("Ang 604 - Guru Ram Das Ji");
		child.add("Ang 608 - Guru Arjan Dev Ji");
		child.add("Ang 631 - Guru Tegh Bahadur Ji");
		child.add("Ang 634 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 637 - Guru Amar Das Ji");
		child.add("Ang 639 - Guru Arjan Dev Ji, Ashtpadiyan");
		child.add("Ang 642 - Guru Ram Das Ji, Guru Nanak Dev Ji, Guru Amar Das Ji, Guru Angad Dev Ji, Var");
		child.add("Ang 654 - Bhagat Kabeer");
		child.add("Ang 656 - Bhagat Naamdev");
		child.add("Ang 657 - Bhagat Ravidas");
		child.add("Ang 659 - Bhagat Bhikhan");
		childItems.add(child);
		
		// Raag Dhanasari
		child = new ArrayList<String>();
		child.add("Ang 660 - Guru Nanak Dev Ji");
		child.add("Ang 663 - Guru Amar Das Ji");
		child.add("Ang 666 - Guru Ram Das Ji");
		child.add("Ang 670 - Guru Arjan Dev Ji");
		child.add("Ang 684 - Guru Tegh Bahadur Ji");
		child.add("Ang 685 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 686 - Guru Arjan Dev Ji, Ashtpadi");
		child.add("Ang 687 - Guru Nanak Dev Ji, Chhant");
		child.add("Ang 690 - Guru Ram Das Ji, Chhant");
		child.add("Ang 691 - Guru Arjan Dev Ji, Chhant");
		child.add("Ang 691 - Bhagat Kabeer");
		child.add("Ang 692 - Bhagat Naamdev");
		child.add("Ang 694 - Bhagat Ravidas");
		child.add("Ang 695 - Bhagat Trilochan");
		child.add("Ang 695 - Bhagat Sain");
		child.add("Ang 695 - Bhagat Pipa");
		child.add("Ang 695 - Bhagat Dhanna");		
		childItems.add(child);
		
		// Raag Jaitsri
		child = new ArrayList<String>();
		child.add("Ang 696 - Guru Ram Das Ji");
		child.add("Ang 700 - Guru Arjan Dev Ji");
		child.add("Ang 702 - Guru Tegh Bahadur Ji");
		child.add("Ang 703 - Guru Arjan Dev Ji, Chhant");
		child.add("Ang 705 - Guru Arjan Dev Ji, Var");
		child.add("Ang 710 - Bhagat Ravidas");
		childItems.add(child);
		
		// Raag Todi
		child = new ArrayList<String>();
		child.add("Ang 711 - Guru Ram Das Ji");
		child.add("Ang 711 - Guru Arjan Dev Ji");
		child.add("Ang 718 - Guru Tegh Bahadur Ji");
		child.add("Ang 718 - Bhagat Naamdev");
		childItems.add(child);
		
		// Raag Bairaaree
		child = new ArrayList<String>();
		child.add("Ang 719 - Guru Ram Das Ji");
		child.add("Ang 720 - Guru Arjan Dev Ji");
		childItems.add(child);
		
		// Raag Tilang
		child = new ArrayList<String>();
		child.add("Ang 721 - Guru Nanak Dev Ji");
		child.add("Ang 723 - Guru Ram Das Ji");
		child.add("Ang 723 - Guru Arjan Dev Ji");
		child.add("Ang 724 - Guru Nanak Dev Ji");
		child.add("Ang 725 - Guru Ram Das Ji");
		child.add("Ang 726 - Guru Tegh Bahadur Ji");
		child.add("Ang 727 - Bhagat Kabeer");
		child.add("Ang 727 - Bhagat Naamdev");
		childItems.add(child);
		
		// Raag Soohi
		child = new ArrayList<String>();
		child.add("Ang 728 - Guru Nanak Dev Ji");
		child.add("Ang 731 - Guru Ram Das Ji");
		child.add("Ang 736 - Guru Arjan Dev Ji");
		child.add("Ang 750 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 753 - Guru Amar Das Ji, Ashtpadiyan");
		child.add("Ang 757 - Guru Ram Das Ji, Ashtpadiyan");
		child.add("Ang 759 - Guru Arjan Dev Ji, Ashtpadiyan");
		child.add("Ang 761 - Guru Arjan Dev Ji, Ashtpadiyan in the Tune of Kafi");
		child.add("Ang 762 - Guru Nanak Dev Ji, Kuchajji (Ill Natured Woman)");
		child.add("Ang 763 - Guru Arjan Dev Ji, Gunvanti (The Female Decked with Merits");
		child.add("Ang 763 - Guru Nanak Dev Ji, Chhant");
		child.add("Ang 767 - Guru Amar Das Ji, Chhant");
		child.add("Ang 772 - Guru Ram Das Ji, Chhant");
		child.add("Ang 777 - Guru Arjan Dev Ji, Chhant");
		child.add("Ang 785 - Guru Amar Das Ji, Guru Nanak Dev Ji, Guru Angad Dev Ji, Var Suhi Ki");
		child.add("Ang 792 - Bhagat Kabeer");
		child.add("Ang 793 - Bhagat Ravidas");
		child.add("Ang 794 - Bhagat Farid");
		childItems.add(child);
		
		// Raag Bilaaval
		child = new ArrayList<String>();
		child.add("Ang 795 - Guru Nanak Dev Ji");
		child.add("Ang 796 - Guru Amar Das Ji");
		child.add("Ang 798 - Guru Ram Das Ji");
		child.add("Ang 801 - Guru Arjan Dev Ji");
		child.add("Ang 830 - Guru Tegh Bahadur Ji");
		child.add("Ang 831 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 832 - Guru Amar Das Ji, Ashtpadiyan");
		child.add("Ang 833 - Guru Ram Das Ji, Ashtpadiyan");
		child.add("Ang 837 - Guru Arjan Dev Ji, Ashtpadiyan");
		child.add("Ang 838 - Guru Nanak Dev Ji, Thittin (The Lunar Dates)");
		child.add("Ang 841 - Guru Amar Das Ji, Var Sat (Days of the Week)");
		child.add("Ang 843 - Guru Nanak Dev Ji, Chhant");
		child.add("Ang 844 - Guru Ram Das Ji, Chhant");
		child.add("Ang 845 - Guru Arjan Dev Ji, Chhant");
		child.add("Ang 849 - Guru Ram Das Ji, Guru Amar Das Ji, Guru Nanak Dev Ji, Bilaval Ki Var");
		child.add("Ang 855 - Bhagat Kabeer");
		child.add("Ang 857 - Bhagat Naamdev");
		child.add("Ang 858 - Bhagat Ravidas");
		child.add("Ang 858 - Bhagat Sadhna");
		childItems.add(child);
		
		// Raag Gaund
		child = new ArrayList<String>();
		child.add("Ang 859 - Guru Ram Das Ji");
		child.add("Ang 862 - Guru Arjan Dev Ji");
		child.add("Ang 869 - Guru Arjan Dev Ji, Ashtpadiyan");
		child.add("Ang 870 - Bhagat Kabeer");
		child.add("Ang 873 - Bhagat Naamdev");
		child.add("Ang 875 - Bhagat Ravidas");
		childItems.add(child);
		
		// Raag Ramkali
		child = new ArrayList<String>();
		child.add("Ang 876 - Guru Nanak Dev Ji");
		child.add("Ang 880 - Guru Amar Das Ji");
		child.add("Ang 880 - Guru Ram Das Ji");
		child.add("Ang 882 - Guru Arjan Dev Ji");
		child.add("Ang 901 - Guru Tegh Bahadur Ji");
		child.add("Ang 902 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 908 - Guru Amar Das Ji, Ashtpadiyan");
		child.add("Ang 912 - Guru Arjan Dev Ji, Ashtpadiyan");
		child.add("Ang 917 - Guru Amar Das Ji, Anand (Bliss)");
		child.add("Ang 923 - Baba Sundar, Sadd (The Call)");
		child.add("Ang 924 - Guru Arjan Dev Ji, Chhant");
		child.add("Ang 929 - Guru Nanak Dev Ji, Dakhni Onkar (Semi-Acrostic)");
		child.add("Ang 938 - Guru Nanak Dev Ji, Siddha Gosh (Dialogue with Siddhas)");
		child.add("Ang 947 - Guru Amar Das Ji, Bhagat Kabeer, Guru Nanak Dev Ji, Guru Angad Dev Ji, Ramkali Ki Var");
		child.add("Ang 957 - Guru Arjan Dev Ji, Bhagat Kabeer, Sheikh Farid, Ramkali Ki Var");
		child.add("Ang 966 - Rai Balwand and Satta, Ramkali Ki Var");
		child.add("Ang 968 - Bhagat Kabeer");
		child.add("Ang 972 - Bhagat Naamdev");
		child.add("Ang 973 - Bhagat Ravidas");
		child.add("Ang 974 - Bhagat Beni");
		childItems.add(child);
		
		// Raag Nat Narayan
		child = new ArrayList<String>();
		child.add("Ang 975 - Guru Ram Das Ji");
		child.add("Ang 978 - Guru Arjan Dev Ji");
		child.add("Ang 980 - Guru Ram Das Ji, Ashtpadiyan");
		childItems.add(child);
		
		// Raag Mali Gaura
		child = new ArrayList<String>();
		child.add("Ang 984 - Guru Ram Das Ji");
		child.add("Ang 986 - Guru Arjan Dev Ji");
		child.add("Ang 988 - Bhagat Naamdev");
		childItems.add(child);
		
		// Raag Maaroo
		child = new ArrayList<String>();
		child.add("Ang 989 - Guru Nanak Dev Ji");
		child.add("Ang 993 - Guru Amar Das Ji");
		child.add("Ang 995 - Guru Ram Das Ji");
		child.add("Ang 998 - Guru Arjan Dev Ji");
		child.add("Ang 1008 - Guru Tegh Bahadur Ji");
		child.add("Ang 1008 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 1014 - Guru Nanak Dev Ji, To the Tune Kafi");
		child.add("Ang 1016 - Guru Amar Das Ji, Ashtpadiyan");
		child.add("Ang 1017 - Guru Arjan Dev Ji, Ashtpadiyan");
		child.add("Ang 1019 - Guru Arjan Dev Ji, Anjuliyan (Supplication)");
		child.add("Ang 1020 - Guru Nanak Dev Ji, Solahe (Sixteen Stanzaed Hymns)");
		child.add("Ang 1033 - Guru Nanak Dev Ji, Dakhne");
		child.add("Ang 1043 - Guru Amar Das Ji, Solahe (Sixteen Stanzaed Hymns)");
		child.add("Ang 1069 - Guru Ram Das Ji, Solahe (Sixteen Stanzaed Hymns)");
		child.add("Ang 1071 - Guru Arjan Dev Ji, Solahe (Sixteen Stanzaed Hymns)");
		child.add("Ang 1086 - Guru Amar Das Ji, Guru Nanak Dev Ji, Guru Ram Das Ji, Guru Angad Dev Ji, Maru Ki Var");
		child.add("Ang 1094 - Guru Arjan Dev Ji, Dakhne (Sloks in the SW Dialect)");
		child.add("Ang 1102 - Bhagat Kabeer");
		child.add("Ang 1105 - Bhagat Naamdev");
		child.add("Ang 1105 - Bhagat Kabeer");
		child.add("Ang 1106 - Bhagat Jaidev");
		child.add("Ang 1106 - Bhagat Kabeer");
		child.add("Ang 1106 - Bhagat Ravidas");
		childItems.add(child);
		
		// Raag Tukhaari
		child = new ArrayList<String>();
		child.add("Ang 1107 - Guru Nanak Dev Ji, Bara Maha (Song of the 12 Months)");
		child.add("Ang 1110 - Guru Nanak Dev Ji, Chhant");
		child.add("Ang 1113 - Guru Ram Das Ji, Chhant");
		child.add("Ang 1117 - Guru Arjan Dev Ji, Chhant");
		childItems.add(child);
		
		// Raag Kedaraa
		child = new ArrayList<String>();
		child.add("Ang 1118 - Guru Ram Das Ji");
		child.add("Ang 1119 - Guru Arjan Dev Ji");
		child.add("Ang 1123 - Bhagat Kabeer");
		child.add("Ang 1124 - Bhagat Ravidas");
		childItems.add(child);
		
		// Raag Bhairao
		child = new ArrayList<String>();
		child.add("Ang 1125 - Guru Nanak Dev Ji");
		child.add("Ang 1128 - Guru Amar Das Ji");
		child.add("Ang 1134 - Guru Ram Das Ji");
		child.add("Ang 1136 - Guru Arjan Dev Ji");
		child.add("Ang 1153 - Guru Arjan Dev Ji, Partal");
		child.add("Ang 1153 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 1154 - Guru Amar Das Ji");
		child.add("Ang 1155 - Guru Arjan Dev Ji, Ashtpadiyan");
		child.add("Ang 1157 - Bhagat Kabeer");
		child.add("Ang 1162 - Bhagat Kabeer, Ashtpadi");
		child.add("Ang 1163 - Bhagat Naamdev");
		child.add("Ang 1167 - Bhagat Ravidas");
		child.add("Ang 1167 - Bhagat Naamdev");
		childItems.add(child);
		
		// Raag Basant
		child = new ArrayList<String>();
		child.add("Ang 1168 - Guru Nanak Dev Ji");
		child.add("Ang 1169 - Guru Amar Das Ji");
		child.add("Ang 1169 - Guru Nanak Dev Ji");
		child.add("Ang 1170 - Guru Amar Das Ji, (Couplets)");
		child.add("Ang 1171 - Guru Nanak Dev Ji, Hindol");
		child.add("Ang 1172 - Guru Amar Das Ji, Do-Tuke (Couplets)");
		child.add("Ang 1176 - Guru Ram Das Ji, Ik-Tuke (Couplets)");
		child.add("Ang 1177 - Guru Amar Das Ji, Hindol");
		child.add("Ang 1177 - Guru Ram Das Ji, Ik-Tuke (Couplets)");
		child.add("Ang 1178 - Guru Ram Das Ji, Hindol");
		child.add("Ang 1180 - Guru Arjan Dev Ji");
		child.add("Ang 1186 - Guru Tegh Bahadur Ji");
		child.add("Ang 1187 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 1191 - Guru Ram Das Ji");
		child.add("Ang 1192 - Guru Arjan Dev Ji");
		child.add("Ang 1193 - Guru Arjan Dev Ji, Basant Ki Var");
		child.add("Ang 1193 - Bhagat Kabeer");
		child.add("Ang 1195 - Bhagat Ramanand");
		child.add("Ang 1195 - Bhagat Naamdev");
		child.add("Ang 1196 - Bhagat Ravidas");
		child.add("Ang 1196 - Bhagat Kabeer");
		childItems.add(child);
		
		// Raag Sarag
		child = new ArrayList<String>();
		child.add("Ang 1197 - Guru Nanak Dev Ji");
		child.add("Ang 1198 - Guru Ram Das Ji");
		child.add("Ang 1200 - Guru Ram Das Ji, Partal");
		child.add("Ang 1202 - Guru Arjan Dev Ji");
		child.add("Ang 1229 - Guru Arjan Dev Ji, Partal");
		child.add("Ang 1231 - Guru Tegh Bahadur Ji");
		child.add("Ang 1232 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 1233 - Guru Amar Das Ji, Ashtpadiyan");
		child.add("Ang 1235 - Guru Arjan Dev Ji, Ashtpadi");
		child.add("Ang 1236 - Guru Arjan Dev Ji, Chhant");
		child.add("Ang 1237 - Guru Ram Das Ji, Guru Angad Dev Ji, Guru Nanak Dev Ji, Guru Amar Das Ji, Guru Arjan Dev Ji, Sarag Ki Var");
		child.add("Ang 1251 - Bhagat Kabeer");
		child.add("Ang 1252 - Bhagat Naamdev");
		child.add("Ang 1253 - Bhagat Parmanand");
		child.add("Ang 1253 - Bhagat Surdas");
		child.add("Ang 1253 - Bhagat Kabeer");
		childItems.add(child);
		
		// Raag Malaar
		child = new ArrayList<String>();
		child.add("Ang 1254 - Guru Nanak Dev Ji");
		child.add("Ang 1257 - Guru Amar Das Ji");
		child.add("Ang 1262 - Guru Ram Das Ji");
		child.add("Ang 1265 - Guru Ram Das Ji, Partal");
		child.add("Ang 1266 - Guru Arjan Dev Ji");
		child.add("Ang 1271 - Guru Arjan Dev Ji, Partal");
		child.add("Ang 1273 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 1276 - Guru Amar Das Ji, Ashtpadiyan");
		child.add("Ang 1278 - Guru Arjan Dev Ji, Chhant");
		child.add("Ang 1278 - Guru Nanak Dev Ji, Guru Angad Dev Ji, Guru Amar Das Ji, Guru Arjan Dev Ji, Var Malak Ki");
		child.add("Ang 1292 - Bhagat Naamdev");
		child.add("Ang 1293 - Bhagat Ravidas");
		child.add("Ang 1293 - Bhagat Naamdev");
		childItems.add(child);
		
		// Raag Kaandda
		child = new ArrayList<String>();
		child.add("Ang 1294 - Guru Ram Das Ji");
		child.add("Ang 1296 - Guru Ram Das Ji, Partal (In Variably Rhythm");
		child.add("Ang 1298 - Guru Arjan Dev Ji");	//Fixed Page from 1297 to 1298
		child.add("Ang 1308 - Guru Ram Das Ji, Ashtpadiyan");
		child.add("Ang 1312 - Guru Arjan Dev Ji, Chhant");
		child.add("Ang 1312 - Guru Ram Das Ji, Kaandda Ki Var");
		child.add("Ang 1318 - Bhagat Naamdev");
		childItems.add(child);
		
		// Raag Kalyan
		child = new ArrayList<String>();
		child.add("Ang 1319 - Guru Ram Das Ji");
		child.add("Ang 1321 - Guru Arjan Dev Ji");
		child.add("Ang 1323 - Guru Ram Das Ji, Ashtpadiyan");
		childItems.add(child);
		
		// Raag Prabhati
		child = new ArrayList<String>();
		child.add("Ang 1327 - Guru Nanak Dev Ji");
		child.add("Ang 1332 - Guru Amar Das Ji");
		child.add("Ang 1335 - Guru Ram Das Ji");
		child.add("Ang 1337 - Guru Arjan Dev Ji");
		child.add("Ang 1342 - Guru Nanak Dev Ji, Ashtpadiyan");
		child.add("Ang 1346 - Guru Amar Das Ji");
		child.add("Ang 1347 - Guru Arjan Dev Ji, Ashtpadiyan");
		child.add("Ang 1349 - Bhagat Kabeer");
		child.add("Ang 1350 - Bhagat Naamdev");
		child.add("Ang 1351 - Bhagat Beni");
		childItems.add(child);
		
		// Raag Jaijavanti
		child = new ArrayList<String>();
		child.add("Ang 1352 - Guru Tegh Bahadur Ji");
		childItems.add(child);
		
		// No Raag
		child = new ArrayList<String>();
		child.add("Ang 1353 - Guru Nanak Dev Ji, Salok in Sahaskrithee");
		child.add("Ang 1353 - Guru Arjan Dev Ji");
		child.add("Ang 1360 - Guru Arjan Dev Ji, Gatha Language");
		child.add("Ang 1361 - Guru Arjan Dev Ji, Phunahe (Hymns with Repeated Burden)");
		child.add("Ang 1363 - Guru Arjan Dev Ji, Chaubole (Discourses with Four Listeners)");
		child.add("Ang 1364 - Salok Bhagat Kabeer, Guru Arjan Dev Ji, Guru Amar Das Ji");
		child.add("Ang 1377 - Salok Sheikh Farid, Guru Arjan Dev Ji, Guru Amar Das Ji");
		child.add("Ang 1385 - Guru Arjan Dev Ji, Swayyas");
		child.add("Ang 1389 - Bhatta De Swaiyyai - In Praise of Guru Nanak Dev Ji");
		child.add("Ang 1391 - Bhatta De Swaiyyai - In Praise of Guru Angad Dev Ji");
		child.add("Ang 1392 - Bhatta De Swaiyyai - In Praise of Guru Amar Das Ji");
		child.add("Ang 1396 - Bhatta De Swaiyyai - In Praise of Guru Ram Das Ji");
		child.add("Ang 1406 - Bhatta De Swaiyyai - In Praise of Guru Arjan Dev Ji");
		child.add("Ang 1410 - Guru Nanak Dev Ji");
		child.add("Ang 1412 - Guru Amar Das Ji");
		child.add("Ang 1421 - Guru Ram Das Ji");
		child.add("Ang 1424 - Guru Arjan Dev Ji");
		child.add("Ang 1426 - Salok Guru Tegh Bahadur Ji");
		child.add("Ang 1429 - Guru Arjan Dev Ji, Mundavani (The Closing Seal)");
		child.add("Ang 1429 - Guru Arjan Dev Ji, Sloka");
		child.add("Ang 1429 - Raag mala String of Musical Measures");
		childItems.add(child);
	
	}
}

class RaagExpandableAdapter extends BaseExpandableListAdapter {

	static Activity activity;
	static ArrayList<Object> childtems;
	static LayoutInflater inflater;
	static ArrayList<String> parentItems, child;

	public RaagExpandableAdapter(Activity context, ArrayList<String> parents, ArrayList<Object> childern) {
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

				granthVars.setRaagAng(Integer.parseInt(text.replaceAll("\\D", "")));

				CharSequence options[] = new CharSequence[]{"Gurbani Only", "Punjabi Teeka", "Punjabi Translation", "English Translation"};

				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle("Open Ang " + granthVars.getRaagAng() + " with: ");
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


