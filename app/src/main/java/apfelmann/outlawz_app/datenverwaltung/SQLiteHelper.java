package apfelmann.outlawz_app.datenverwaltung;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

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

            //Abrufen von Daten.
            //ist das selbe wie Select From "spaltennamen" where "Auswahlwert"
    public Cursor query(String[] spaltennamen, String where,
                        String[]auswahlwert){

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables("timer");
        SQLiteDatabase db = getReadableDatabase();
        Cursor c =qb.query(db, spaltennamen, where, auswahlwert, null, null, null);
        return c;


    }






    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // BRAUCHEN WIR NICHT!
    }

    public int getID() {
        SQLiteDatabase db = this.getWritableDatabase();
        // TODO
        return 0;
    }

    public int getTimeStamp() {
        SQLiteDatabase db = this.getWritableDatabase();
        // TODO
        return 0;
    }

    public String getDescription() {
        SQLiteDatabase db = this.getWritableDatabase();
        // TODO
        return "";
    }
}
