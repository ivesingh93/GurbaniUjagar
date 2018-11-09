package gurbani.ujagar;

import static gurbani.ujagar.Constants.ANAND_SAHIB;
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
import static gurbani.ujagar.Constants.JAPJI_SAHIB_TITLE;
import static gurbani.ujagar.Constants.KIRTAN_SOHILA;
import static gurbani.ujagar.Constants.KIRTAN_SOHILA_TITLE;
import static gurbani.ujagar.Constants.LAAVAN;
import static gurbani.ujagar.Constants.LAAVAN_TITLE;
import static gurbani.ujagar.Constants.REHRAAS_SAHIB;
import static gurbani.ujagar.Constants.REHRAAS_SAHIB_TITLE;
import static gurbani.ujagar.Constants.SALOK_MAHALLA_9;
import static gurbani.ujagar.Constants.SALOK_MAHALLA_9_TITLE;
import static gurbani.ujagar.Constants.SUKHMANI_SAHIB;
import static gurbani.ujagar.Constants.SUKHMANI_SAHIB_TITLE;
import static gurbani.ujagar.Constants.TAV_PARSAD;
import static gurbani.ujagar.Constants.TAV_PARSAD_TITLE;

/**
 * Created by ivesingh on 1/4/17.
 */

public class GranthVars {

    private String bani;
    private String title;
    private String searched_line;

    private int total_ang;
    private int translation_case;
    private int shabad_ang;
    private int author_ang;
    private int raag_ang;
    private int bookmark_ang;
    private int kirtan_id;
    private int line_id;


    public String getBani() {
        return bani;
    }

    public void setBani(String bani) {
        this.bani = bani;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSearchedLine() {
        if(searched_line == null)
            searched_line = "";
        return searched_line;
    }

    public void setSearchedLine(String searched_line) {
        this.searched_line = searched_line;
    }

    public int getTotalAng() {
        return total_ang;
    }

    public void setTotalAng(int total_ang) {
        this.total_ang = total_ang;
    }

    public int getTranslationCase() {
        return translation_case;
    }

    public void setTranslationCase(int translation_case) {
        this.translation_case = translation_case;
    }

    public int getShabadAng() {
        return shabad_ang;
    }

    public void setShabadAng(int shabad_ang) {
        this.shabad_ang = shabad_ang;
    }

    public int getKirtanId() {
        return kirtan_id;
    }

    public void setKirtanId(int kirtan_id) {
        this.kirtan_id = kirtan_id;
    }

    public int getLineId() {
        return line_id;
    }

    public void setLineId(int line_id) {
        this.line_id = line_id;
    }

    public String getCorrectTitle(){
        if(getBani().equals(GURU_GRANTH)){
            return GURU_GRANTH_TITLE;
        }else if(getBani().equals(JAPJI_SAHIB)){
            return JAPJI_SAHIB_TITLE;
        }else if(getBani().equals(SUKHMANI_SAHIB)){
            return SUKHMANI_SAHIB_TITLE;
        }else if(getBani().equals(ANAND_SAHIB)){
            return ANAND_SAHIB_TITLE;
        }else if(getBani().equals(JAAP_SAHIB)){
            return JAAP_SAHIB_TITLE;
        }else if(getBani().equals(CHOTTA_ANAND_SAHIB)){
            return CHOTTA_ANAND_SAHIB_TITLE;
        }else if(getBani().equals(CHAUPAI_SAHIB)){
            return CHAUPAI_SAHIB_TITLE;
        }else if(getBani().equals(REHRAAS_SAHIB)){
            return REHRAAS_SAHIB_TITLE;
        }else if(getBani().equals(TAV_PARSAD)){
            return TAV_PARSAD_TITLE;
        }else if(getBani().equals(KIRTAN_SOHILA)){
            return KIRTAN_SOHILA_TITLE;
        }else if(getBani().equals(SALOK_MAHALLA_9)){
            return SALOK_MAHALLA_9_TITLE;
        }else if(getBani().equals(LAAVAN)){
            return LAAVAN_TITLE;
        }else{
            return "";
        }
    }

    public int getAuthorAng() {
        return author_ang;
    }

    public void setAuthorAng(int author_ang) {
        this.author_ang = author_ang;
    }

    public int getRaagAng() {
        return raag_ang;
    }

    public void setRaagAng(int raag_ang) {
        this.raag_ang = raag_ang;
    }

    public int getBookmarkAng() {
        return bookmark_ang;
    }

    public void setBookmarkAng(int bookmark_ang) {
        this.bookmark_ang = bookmark_ang;
    }
}
