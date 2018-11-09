package gurbani.ujagar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;

import static gurbani.ujagar.Constants.ANAND_SAHIB;
import static gurbani.ujagar.Constants.ANAND_SAHIB_ANG;
import static gurbani.ujagar.Constants.ANAND_SAHIB_TITLE;
import static gurbani.ujagar.Constants.CHAUPAI_SAHIB;
import static gurbani.ujagar.Constants.CHAUPAI_SAHIB_TITLE;
import static gurbani.ujagar.Constants.CHOTTA_ANAND_SAHIB;
import static gurbani.ujagar.Constants.CHOTTA_ANAND_SAHIB_TITLE;
import static gurbani.ujagar.Constants.GURU_GRANTH;
import static gurbani.ujagar.Constants.GURU_GRANTH_TITLE;
import static gurbani.ujagar.Constants.JAAP_SAHIB;
import static gurbani.ujagar.Constants.JAAP_SAHIB_TITLE;
import static gurbani.ujagar.Constants.JAPJI_SAHIB;
import static gurbani.ujagar.Constants.JAPJI_SAHIB_ANG;
import static gurbani.ujagar.Constants.JAPJI_SAHIB_TITLE;
import static gurbani.ujagar.Constants.KIRTAN_SOHILA;
import static gurbani.ujagar.Constants.KIRTAN_SOHILA_TITLE;
import static gurbani.ujagar.Constants.LAAVAN;
import static gurbani.ujagar.Constants.LAAVAN_TITLE;
import static gurbani.ujagar.Constants.REHRAAS_SAHIB;
import static gurbani.ujagar.Constants.REHRAAS_SAHIB_TITLE;
import static gurbani.ujagar.Constants.SALOK_MAHALLA_9;
import static gurbani.ujagar.Constants.SALOK_MAHALLA_9_TITLE;
import static gurbani.ujagar.Constants.SGGS_ANG;
import static gurbani.ujagar.Constants.SINGLE_ANG;
import static gurbani.ujagar.Constants.SUKHMANI_SAHIB;
import static gurbani.ujagar.Constants.SUKHMANI_SAHIB_ANG;
import static gurbani.ujagar.Constants.SUKHMANI_SAHIB_TITLE;
import static gurbani.ujagar.Constants.TAV_PARSAD;
import static gurbani.ujagar.Constants.TAV_PARSAD_TITLE;
import static gurbani.ujagar.Constants.granthVars;


public class ConstantsMethods {

    public void sggsMethod(AdapterView<?> parent, View view, int position, long id, Context context) {

        if(position >=0 && position <= 7){
            granthVars.setBani(GURU_GRANTH);
            granthVars.setTotalAng(SGGS_ANG);
            granthVars.setTitle(GURU_GRANTH_TITLE);
        }

        if (position >= 0 && position <= 3) {
            granthVars.setTranslationCase(position);

        } else if (position == 4) {
            try {
                Class ourClass = Class.forName("gurbani.ujagar.Bookmark");
                Intent ourIntent = new Intent(context, ourClass);
                context.startActivity(ourIntent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (position == 5) {
            try {
                Class ourClass = Class.forName("gurbani.ujagar.RaagIndex");
                Intent ourIntent = new Intent(context, ourClass);
                context.startActivity(ourIntent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (position == 6) {
            try {
                Class ourClass = Class.forName("gurbani.ujagar.AuthorIndex");
                Intent ourIntent = new Intent(context, ourClass);
                context.startActivity(ourIntent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (position == 7) {
            try {
                Class ourClass = Class.forName("gurbani.ujagar.ShabadActivity");
                Intent ourIntent = new Intent(context, ourClass);
                context.startActivity(ourIntent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (position == 8) {
            granthVars.setBani(JAPJI_SAHIB);
            granthVars.setTotalAng(JAPJI_SAHIB_ANG);
            granthVars.setTitle(JAPJI_SAHIB_TITLE);

        } else if (position == 9) {
            granthVars.setBani(SUKHMANI_SAHIB);
            granthVars.setTotalAng(SUKHMANI_SAHIB_ANG);
            granthVars.setTitle(SUKHMANI_SAHIB_TITLE);

        } else if (position == 10) {
            granthVars.setBani(JAAP_SAHIB);
            granthVars.setTotalAng(SINGLE_ANG);
            granthVars.setTitle(JAAP_SAHIB_TITLE);

        } else if (position == 11) {
            granthVars.setBani(CHAUPAI_SAHIB);
            granthVars.setTotalAng(SINGLE_ANG);
            granthVars.setTitle(CHAUPAI_SAHIB_TITLE);

        } else if (position == 12) {
            granthVars.setBani(ANAND_SAHIB);
            granthVars.setTotalAng(ANAND_SAHIB_ANG);
            granthVars.setTitle(ANAND_SAHIB_TITLE);

        } else if (position == 13) {
            granthVars.setBani(CHOTTA_ANAND_SAHIB);
            granthVars.setTotalAng(SINGLE_ANG);
            granthVars.setTitle(CHOTTA_ANAND_SAHIB_TITLE);

        }  else if (position == 14) {
            granthVars.setBani(REHRAAS_SAHIB);
            granthVars.setTotalAng(SINGLE_ANG);
            granthVars.setTitle(REHRAAS_SAHIB_TITLE);

        } else if (position == 15) {
            granthVars.setBani(TAV_PARSAD);
            granthVars.setTotalAng(SINGLE_ANG);
            granthVars.setTitle(TAV_PARSAD_TITLE);

        } else if (position == 16) {
            granthVars.setBani(KIRTAN_SOHILA);
            granthVars.setTotalAng(SINGLE_ANG);
            granthVars.setTitle(KIRTAN_SOHILA_TITLE);

        } else if (position == 17) {
            granthVars.setBani(SALOK_MAHALLA_9);
            granthVars.setTotalAng(SINGLE_ANG);
            granthVars.setTitle(SALOK_MAHALLA_9_TITLE);

        } else if (position == 18) {
            granthVars.setBani(LAAVAN);
            granthVars.setTotalAng(SINGLE_ANG);
            granthVars.setTitle(LAAVAN_TITLE);

        }

        if((position >= 0 && position <= 3) || position >= 8){
            try {
                Class ourClass = Class.forName("gurbani.ujagar.Gurbani");
                Intent ourIntent = new Intent(context, ourClass);
                context.startActivity(ourIntent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void generalMethod(AdapterView<?> parent, View view, int position, long id, Context context) {

        if (position == 0) {
            try {
                Class ourClass = Class.forName("gurbani.ujagar.Inspiration");
                Intent ourIntent = new Intent(context, ourClass);
                context.startActivity(ourIntent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (position == 1) {
            try {
                Class ourClass = Class.forName("gurbani.ujagar.Feedback");
                Intent ourIntent = new Intent(context, ourClass);
                context.startActivity(ourIntent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (position == 2) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Vismaad+Apps");
            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));

        } else if (position == 3) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=gurbani.ujagar&hl=en");
            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));

        } else if (position == 4) {
            Uri uri = Uri.parse("https://www.facebook.com/pages/IveSingh-Apps/1413125452234300");
            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }

}
