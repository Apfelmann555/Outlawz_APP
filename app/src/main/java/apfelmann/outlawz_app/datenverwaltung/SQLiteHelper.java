package apfelmann.outlawz_app.datenverwaltung;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper{
    private static final String DBNAME = "outlawzdb";
    private static final String TABLENAME = "timer";
    private static final String KEY_ID = "id";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_DESC = "beschreibung";

    public SQLiteHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String command = "CREATE TABLE" + TABLENAME + "("
                + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TIMESTAMP + "INTEGER NOT NULL,"
                + KEY_DESC + "VARCHAR(20) NOT NULL);";
        db.execSQL(command);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // BRAUCHEN WIR NICHT!
    }

        /**
     * Ließt alle IDs aus der SQLite Datenbank aus und gibt sie als ArrayList zurück.
     *
     * @return Eine Liste mit allen IDs.
     **/
    public ArrayList<Integer> getIDs() {
        // Verwendbare Datenbank wird abgefragt
        SQLiteDatabase db = this.getWritableDatabase();
        // Erstellen einer Liste zum Abspeichern der IDs
        ArrayList<Integer> al = new ArrayList<>();

        // Query / Abfrage der Werte an den server
        Cursor c = db.rawQuery("SELECT " + KEY_ID + " FROM " + TABLENAME, null);
        // Alternative zur oberen Abfrage:
        // Cursor c = db.query(TABLENAME, new String[] {KEY_ID}, null, null, null, null, null);

        // Durchgehen aller Ergebnisse
        while(c != null && c.moveToNext()) {
            // Abspeichern des Ergebnisses in die Liste
            al.add(c.getInt(0));
        }
        // Schließen der Verbindungen
        c.close();
        db.close();
        // Rückgabe der Werte / IDs
        return al;
    }

    public ArrayList<Integer> getTimeStamp() {
        SQLiteDatabase db = this.getWritableDatabase();
        //neues Array wird erstellt
        ArrayList<Integer> als = new ArrayList<>();
        //ABFRAGE der Stamps von der Tabelle
        Cursor c = db.rawQuery("SELECT " +KEY_TIMESTAMP + " FROM " + TABLENAME, null);
        while(c != null && c.moveToNext()){
        //abspeichern
            als.add(c.getInt(0));
        }
        c.close();
        db.close();
        return als;
    }

    public ArrayList<Integer> getDescription() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList ald = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT " + KEY_DESC + " FROM " + TABLENAME, null);
        while(c != null && c.moveToNext()){
            ald.add(c.getString(0));
            }
        c.close();
        db.close();
        return ald;
    }

    /**
     * Bekommt ContentValues übergeben, welche in die Datenbank gespeichert werden.
     *
     * @param cv Die Werte zum Abspeichern in die Datenbank.
     * @return true wenn die Daten korrekt abgespeichert wurden.
     */
    public boolean insertDataSet(ContentValues cv) {
        // Verwendbare Datenbank wird abgefragt
        SQLiteDatabase db = this.getWritableDatabase();
        // Daten werden abgespeichert.
        long check = db.insert(TABLENAME, null, cv);
        // Schlieen der Verbindung.
        db.close();
        // Wenn check == -1 ist beim Abspeichern was schief gelaufen.
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }
}



