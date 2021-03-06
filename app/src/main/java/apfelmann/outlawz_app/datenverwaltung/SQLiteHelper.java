package apfelmann.outlawz_app.datenverwaltung;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    /**
     *
     * @param ts
     * @return wenn -1 dann kein wert vorhanden.
     */
    public int getIdByTimetamp (int ts){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select " + KEY_ID + " From " + TABLENAME + " Where " + KEY_TIMESTAMP + "=?";
        Cursor c =db.rawQuery(query, new String[] {ts+""});

        int id=-1;
        if (c != null && c.moveToNext()){
            id = c.getInt(0);

        }
        return id;

    }

    public int getTimeStamp(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int ts = -1;
        //ABFRAGE der Stamps von der Tabelle
        Cursor c = db.rawQuery("SELECT " +KEY_TIMESTAMP + " FROM " + TABLENAME + " WHERE " + KEY_ID + "=?", new String[] {id+""});
        while(c != null && c.moveToNext()){
        //abspeichern
            ts = c.getInt(0);
        }
        c.close();
        db.close();
        return ts;
    }

    public String getDescription(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String desc = "";
        Cursor c = db.rawQuery("SELECT " + KEY_DESC + " FROM " + TABLENAME + " WHERE " + KEY_ID + "=?", new String[] {id+""});
        while(c != null && c.moveToNext()){
            desc = c.getString(0);
            }
        c.close();
        db.close();
        return desc;
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

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyTimestamp() {
        return KEY_TIMESTAMP;
    }

    public static String getKeyDesc() {
        return KEY_DESC;
    }
}
