package gurbani.ujagar;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static gurbani.ujagar.Constants.*;

public class SGGSDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DB_NAME = "Sikh_Granths.sqlite";
    private static String DB_PATH = null;
    private final Context myContext;
    private SQLiteDatabase myDataBase;


    /*


    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper();
        SQLiteDatabase database = helper.getReadableDatabase();
        myPath = database.getPath();

    } else {
        String DB_PATH = Environment.getDataDirectory() + "/data/my.trial.app/databases/";
        myPath = DB_PATH + dbName;
    }

    checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    checkDB.disableWriteAheadLogging();

     */

    public SGGSDB(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            SQLiteDatabase database = getReadableDatabase();
            DB_PATH = database.getPath();
            database.close();

        }else if(android.os.Build.VERSION.SDK_INT >= 4.2){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/" + DB_NAME;
            Log.i("DB Path", DB_PATH);
        }else{
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/" + DB_NAME;
        }



    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist){

        }else{
            //this.getReadableDatabase();
            try{
                copyDataBase();
            }catch (IOException e){
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e) {

        }
        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null ? true:false;
    }


    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH;
        this.myDataBase.close();
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while((length = myInput.read(buffer)) > 0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH;
        this.myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (this.myDataBase != null) {
            this.myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }

    // Try switch/case rather than if conditions.
    public Cursor getQueryResult(String column, int page_num){

        SQLiteDatabase db = this.getWritableDatabase();
        db.close();
        Cursor cursor = null;
        String table = "";
        // Dasam Granth Banis - Only if Gurmukhi or Punjabi Translation.
        if (granthVars.getBani().equals(JAAP_SAHIB) || granthVars.getBani().equals(CHAUPAI_SAHIB)
                || granthVars.getBani().equals(TAV_PARSAD)) {

            if(granthVars.getBani().equals(JAAP_SAHIB)) {
                table = "JaapSahib";
            }else if(granthVars.getBani().equals(CHAUPAI_SAHIB)){
                table = "ChaupaiSahib";
            }else if(granthVars.getBani().equals(TAV_PARSAD)){
                table = "TavParsadSwaiye";
            }

            if(column.equals(GURBANI)){
                cursor = db.rawQuery("SELECT Gurmukhi FROM " + table, null);
            }else if(column.equals(GURBANI_PUNJABI)) {
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi FROM " + table, null);
            }

            // Banis from SGGS - Only if Gurmukhi, Punjabi, Teeka, and English Translations
        }else if(granthVars.getBani().equals(CHOTTA_ANAND_SAHIB)){
            String query =  "(SELECT * FROM 'SGGS' WHERE BANI_ID BETWEEN 1 AND 5 AND Bani=\"Anand Sahib\"\n" +
                            "UNION\n" +
                            "SELECT * FROM 'SGGS' WHERE BANI_ID=40 AND Bani=\"Anand Sahib\")\n";

            if(column.equals(GURBANI)){
                query = "SELECT Gurmukhi FROM " + query;

            }else if(column.equals(GURBANI_PUNJABI)){
                query = "SELECT Gurmukhi, Punjabi FROM " + query;

            }else if(column.equals(GURBANI_TEEKA)){
                query = "SELECT Gurmukhi, Teeka_Pad_Arth, Teeka_Arth FROM " + query;

            }else if(column.equals(GURBANI_ENGLISH)){
                query = "SELECT Gurmukhi, English FROM " + query;

            }else if(column.equals(GURBANI_ENGLISH_TEEKA)){
                query = "SELECT Gurmukhi, English, Teeka_Pad_Arth, Teeka_Arth " + query;

            }else if(column.equals(GURBANI_PUNJABI_TEEKA)){
                query = "SELECT Gurmukhi, Punjabi, Teeka_Pad_Arth, Teeka_Arth FROM " + query;

            }else if(column.equals(GURBANI_PUNJABI_ENGLISH)){
                query = "SELECT Gurmukhi, Punjabi, English FROM " + query;

            }else if(column.equals(GURBANI_PUNJABI_ENGLISH_TEEKA)){
                query = "SELECT Gurmukhi, Punjabi, English, Teeka_Pad_Arth, Teeka_Arth FROM " + query;

            }

            cursor = db.rawQuery(query, null);

        }else if (granthVars.getBani().equals(JAPJI_SAHIB) || granthVars.getBani().equals(SUKHMANI_SAHIB)
                || granthVars.getBani().equals(ANAND_SAHIB) || granthVars.getBani().equals(KIRTAN_SOHILA)
                || granthVars.getBani().equals(SALOK_MAHALLA_9) || granthVars.getBani().equals(LAAVAN)) {

            if (granthVars.getBani().equals(JAPJI_SAHIB)){
                table = "Japji Sahib";
            }else if (granthVars.getBani().equals(SUKHMANI_SAHIB)){
                table = "Sukhmani Sahib";
            }else if (granthVars.getBani().equals(ANAND_SAHIB)){
                table = "Anand Sahib";
            }else if (granthVars.getBani().equals(KIRTAN_SOHILA)){
                table = "Kirtan Sohila";
            }else if (granthVars.getBani().equals(SALOK_MAHALLA_9)){
                table = "Salok Mahalla 9";
            }else if (granthVars.getBani().equals(LAAVAN)){
                table = "Laavan";
            }

            if(column.equals(GURBANI)){
                cursor = db.rawQuery("SELECT Gurmukhi FROM 'SGGS' WHERE Bani='" + table + "' AND Bani_ID=" + page_num , null);

            }else if(column.equals(GURBANI_PUNJABI)){
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi FROM 'SGGS' WHERE Bani='" + table + "' AND Bani_ID=" + page_num , null);

            }else if(column.equals(GURBANI_TEEKA)){
                cursor = db.rawQuery("SELECT Gurmukhi, Teeka_Pad_Arth, Teeka_Arth FROM 'SGGS' WHERE Bani='" + table + "' AND Bani_ID=" + page_num , null);

            }else if(column.equals(GURBANI_ENGLISH)){
                cursor = db.rawQuery("SELECT Gurmukhi, English FROM 'SGGS' WHERE Bani='" + table + "' AND Bani_ID=" + page_num , null);

            }else if(column.equals(GURBANI_ENGLISH_TEEKA)){
                cursor = db.rawQuery("SELECT Gurmukhi, English, Teeka_Pad_Arth, Teeka_Arth FROM 'SGGS' WHERE Bani='" + table + "' AND Bani_ID=" + page_num , null);

            }else if(column.equals(GURBANI_PUNJABI_TEEKA)){
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, Teeka_Pad_Arth, Teeka_Arth FROM 'SGGS' WHERE Bani='" + table + "' AND Bani_ID=" + page_num , null);

            }else if(column.equals(GURBANI_PUNJABI_ENGLISH)){
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, English FROM 'SGGS' WHERE Bani='" + table + "' AND Bani_ID=" + page_num , null);

            }else if(column.equals(GURBANI_PUNJABI_ENGLISH_TEEKA)){
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, English, Teeka_Pad_Arth, Teeka_Arth FROM 'SGGS' WHERE Bani='" + table + "' AND Bani_ID=" + page_num , null);

            }


        } else if(granthVars.getBani().equals(REHRAAS_SAHIB)){

            if(column.equals(GURBANI)){
                cursor = db.rawQuery("SELECT Gurmukhi FROM 'RehraasSahib'", null);

            }else if(column.equals(GURBANI_PUNJABI)){
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi FROM 'RehraasSahib'", null);

            }else if(column.equals(GURBANI_TEEKA)){
                cursor = db.rawQuery("SELECT Gurmukhi, Teeka_Pad_Arth, Teeka_Arth FROM 'RehraasSahib'", null);

            }else if(column.equals(GURBANI_ENGLISH)){
                cursor = db.rawQuery("SELECT Gurmukhi, English FROM 'RehraasSahib'", null);

            }else if(column.equals(GURBANI_ENGLISH_TEEKA)){
                cursor = db.rawQuery("SELECT Gurmukhi, English, Teeka_Pad_Arth, Teeka_Arth FROM 'RehraasSahib'", null);

            }else if(column.equals(GURBANI_PUNJABI_TEEKA)){
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, Teeka_Pad_Arth, Teeka_Arth FROM 'RehraasSahib'", null);

            }else if(column.equals(GURBANI_PUNJABI_ENGLISH)){
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, English FROM 'RehraasSahib'", null);

            }else if(column.equals(GURBANI_PUNJABI_ENGLISH_TEEKA)){
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, English, Teeka_Pad_Arth, Teeka_Arth FROM 'RehraasSahib'", null);

            }

        } else if(granthVars.getBani().equals(GURU_GRANTH)){

            if(column.equals(GURBANI)){
                cursor = db.rawQuery("SELECT Gurmukhi, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

            }else if(column.equals(GURBANI_PUNJABI)){
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

            }else if(column.equals(GURBANI_TEEKA)){
                cursor = db.rawQuery("SELECT Gurmukhi, Teeka_Pad_Arth, Teeka_Arth, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

            }else if(column.equals(GURBANI_ENGLISH)){
                cursor = db.rawQuery("SELECT Gurmukhi, English, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

            }else if(column.equals(GURBANI_ENGLISH_TEEKA)){
                cursor = db.rawQuery("SELECT Gurmukhi, English, Teeka_Pad_Arth, Teeka_Arth, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

            }else if(column.equals(GURBANI_PUNJABI_TEEKA)){
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, Teeka_Pad_Arth, Teeka_Arth, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

            }else if(column.equals(GURBANI_PUNJABI_ENGLISH)){
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, English, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

            }else if(column.equals(GURBANI_PUNJABI_ENGLISH_TEEKA)){
                cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, English, Teeka_Pad_Arth, Teeka_Arth, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

            }
        } else if(granthVars.getBani().equals(SHABAD)){
            cursor = db.rawQuery("SELECT Kirtan_ID FROM SGGS WHERE Gurmukhi = '" + granthVars.getSearchedLine() + "'", null);

            int kirtan_id = 0;
            while(cursor.moveToNext()){
                kirtan_id = cursor.getInt(0);
                if(kirtan_id == granthVars.getKirtanId()){
                    break;
                }
            }
            if(kirtan_id == 0){
                granthVars.setBani(GURU_GRANTH);

                if(column.equals(GURBANI)){
                    cursor = db.rawQuery("SELECT Gurmukhi, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

                }else if(column.equals(GURBANI_PUNJABI)){
                    cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

                }else if(column.equals(GURBANI_TEEKA)){
                    cursor = db.rawQuery("SELECT Gurmukhi, Teeka_Pad_Arth, Teeka_Arth, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

                }else if(column.equals(GURBANI_ENGLISH)){
                    cursor = db.rawQuery("SELECT Gurmukhi, English, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

                }else if(column.equals(GURBANI_ENGLISH_TEEKA)){
                    cursor = db.rawQuery("SELECT Gurmukhi, English, Teeka_Pad_Arth, Teeka_Arth, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

                }else if(column.equals(GURBANI_PUNJABI_TEEKA)){
                    cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, Teeka_Pad_Arth, Teeka_Arth, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

                }else if(column.equals(GURBANI_PUNJABI_ENGLISH)){
                    cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, English, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

                }else if(column.equals(GURBANI_PUNJABI_ENGLISH_TEEKA)){
                    cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, English, Teeka_Pad_Arth, Teeka_Arth, ID FROM 'SGGS' WHERE Ang=" + page_num , null);

                }
            }else{
                if(column.equals(GURBANI)){
                    cursor = db.rawQuery("SELECT Gurmukhi, Author, Raag, Ang, ID FROM 'SGGS' WHERE Kirtan_ID=" + kirtan_id , null);

                }else if(column.equals(GURBANI_PUNJABI)){
                    cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, Author, Raag, Ang, ID FROM 'SGGS' WHERE Kirtan_ID=" + kirtan_id  , null);

                }else if(column.equals(GURBANI_TEEKA)){
                    cursor = db.rawQuery("SELECT Gurmukhi, Teeka_Pad_Arth, Teeka_Arth, Author, Raag, Ang, ID FROM 'SGGS' WHERE Kirtan_ID=" + kirtan_id  , null);

                }else if(column.equals(GURBANI_ENGLISH)){
                    cursor = db.rawQuery("SELECT Gurmukhi, English, Author, Raag, Ang, ID FROM 'SGGS' WHERE Kirtan_ID=" + kirtan_id  , null);

                }else if(column.equals(GURBANI_ENGLISH_TEEKA)){
                    cursor = db.rawQuery("SELECT Gurmukhi, English, Teeka_Pad_Arth, Teeka_Arth, Author, Raag, Ang, ID FROM 'SGGS' WHERE Kirtan_ID=" + kirtan_id  , null);

                }else if(column.equals(GURBANI_PUNJABI_TEEKA)){
                    cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, Teeka_Pad_Arth, Teeka_Arth, Author, Raag, Ang, ID FROM 'SGGS' WHERE Kirtan_ID=" + kirtan_id  , null);

                }else if(column.equals(GURBANI_PUNJABI_ENGLISH)){
                    cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, English, Author, Raag, Ang, ID FROM 'SGGS' WHERE Kirtan_ID=" + kirtan_id  , null);

                }else if(column.equals(GURBANI_PUNJABI_ENGLISH_TEEKA)){
                    cursor = db.rawQuery("SELECT Gurmukhi, Punjabi, English, Teeka_Pad_Arth, Teeka_Arth, Author, Raag, Ang, ID FROM 'SGGS' WHERE Kirtan_ID=" + kirtan_id  , null);

                }
            }

        }

        return cursor;
    }

    public Cursor shabadSearch(String query){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public void enableSensitivity(boolean condition){
        SQLiteDatabase db = this.getWritableDatabase();

        db.rawQuery("PRAGMA case_sensitive_like = " + condition, null);

    }

}
