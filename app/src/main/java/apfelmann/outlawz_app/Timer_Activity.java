package apfelmann.outlawz_app;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Calendar;

import apfelmann.outlawz_app.datenverwaltung.SQLiteHelper;


public class Timer_Activity extends Activity implements View.OnClickListener {

    //Anlegen der Variablen
    private Button  create_timer;
    private TextView timer_activity_toptext;
    private Dialog dia;

    private SQLiteHelper sqlh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_);

        sqlh = new SQLiteHelper(this);
        dia = new Dialog(this);
        dia.setContentView(R.layout.custom_dialog);
        dia.setTitle("Neuer Timer!");
        // Setzen der min und max-Werte
        ((NumberPicker) dia.findViewById(R.id.dialog_numberpicker_sec)).setMinValue(0);
        ((NumberPicker) dia.findViewById(R.id.dialog_numberpicker_sec)).setMinValue(59);
        ((NumberPicker) dia.findViewById(R.id.dialog_numberpicker_min)).setMinValue(0);
        ((NumberPicker) dia.findViewById(R.id.dialog_numberpicker_min)).setMinValue(59);
        ((NumberPicker) dia.findViewById(R.id.dialog_numberpicker_h)).setMinValue(0);
        ((NumberPicker) dia.findViewById(R.id.dialog_numberpicker_sec)).setMinValue(23);


        // TODO Anpassen des Dialogfensters (zu Breit, nicht alle Views vorhanden)
        // TODO Set NumberPicker numbers: h, min, sec


        //Zuweisen der XML Objekte an unsere Variabeln
        timer_activity_toptext = (TextView) findViewById(R.id.timer_activity_toptext);
        create_timer = (Button) findViewById(R.id.bterstellen);

        // Zuweisen der onClicklistener für die Funktionalität der Knöpfe.
        create_timer.setOnClickListener(this);
    }

    @Override
    public void onClick(View button) {
        // TODO Datenbank: Datensatz anlegen
        // TODO ID des Datensatzen bekommen
        // TODO Neue View anlegen mit ID als id
        // TODO Countdown starten

        int kg = button.getId();
        switch(kg) {
            case R.id.bterstellen:
                Button dialogButton = (Button) dia.findViewById(R.id.dialog_button_save);
                dialogButton.setOnClickListener(this);
                dia.show();
                break;
            case R.id.dialog_button_save:
                dia.dismiss();
                break;
        }

        //startActivity(new Intent(this, Fischen_Activity.class));
    }

    /**
     * Liest den Wert aus dem Dialogsfenster aus und gibt ihn zurück;
     *
     * @return Die Anzahl an Stunden, welche vom Benutzer ausgewählt wurden
     */
    private int getHoursFromDialog() {
        return ((NumberPicker) dia.findViewById(R.id.dialog_numberpicker_h)).getValue();
    }

    /**
     * Liest den Wert aus dem Dialogsfenster aus und gibt ihn zurück;
     *
     * @return Die Anzahl an Minuten, welche vom Benutzer ausgewählt wurden
     */
    private int getMinutesFromDialog() {
        return ((NumberPicker) dia.findViewById(R.id.dialog_numberpicker_min)).getValue();
    }

    /**
     * Liest den Wert aus dem Dialogsfenster aus und gibt ihn zurück;
     *
     * @return Die Anzahl an Sekunden, welche vom Benutzer ausgewählt wurden
     */
    private int getSecondsFromDialog() {
        return ((NumberPicker) dia.findViewById(R.id.dialog_numberpicker_sec)).getValue();
    }

    public int berechnungtime; {
        // Werte bekommen
        int h = ((NumberPicker) dia.findViewById(R.id.dialog_numberpicker_h)).getValue();
        int min = ((NumberPicker) dia.findViewById(R.id.dialog_numberpicker_min)).getValue();
        int sec = ((NumberPicker) dia.findViewById(R.id.dialog_numberpicker_sec)).getValue();
        // Gibt momentanes Date aus
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, h);
        calendar.add(Calendar.MINUTE, min);
        calendar.add(Calendar.SECOND, sec);
        int timestamp = (int) calendar.getTime().getTime() / 1000;
        //int timestamp = calendar.getTime() / 1000;

        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.getKeyDesc(), ((EditText) dia.findViewById(R.id.dialog_edit_desc)).getText();
        cv.put(SQLiteHelper.getKeyTimestamp(), timestamp);

        sqlh.insertDataSet(cv);
        //                 ^^ wird übergeben
    }

    // TODO Timestamp in datenbank mit beschr. speichern
    // TODO View anschließend anlegen

    /**
     * Gibt den Timestamp von der aktuellen Zeit zurück.
     *
     * @return Der aktuelle Zeitstempel.
     */
    public long getCurrentDateAsTS() {
        java.util.Date date = new java.util.Date();
        return date.getTime();
    }

    /**
     * Liest den Timestamp aus der Datenbank für eine bestimmte ID aus.
     *
     * @param id Die ID von der der Timestamp ausgelesen werden soll.
     * @return Der gesuchte Timestamp aus der Datenbank.
     */
    public long getTimestempFor(int id) {
        return sqlh.getTimeStamp(id);

    }

    /**
     * Berechnet den Unterschied zwischen 2 UNIX-Zeitstempeln.
     * @param tst_future Der Zeitstempel mit dem Datum in der Zukunft.
     * @param tst_current Der Zeitstempel mit der momentanen Zeit.
     * @return Die differenz zwischen den beiden Zeitstempeln.
     */
    public int getDifference(int tst_future, int tst_current) {
        return (int) (tst_future - tst_current);
    }

    /**
     * Berechnet die verbleibenden Sekunden bis zur nächsten vollen Minute bis zum Datum .
     * @param tst_future Das Datum, bis welches die Sekunden berechnet werden sollen.
     * @param tst_current Das momentane Datum als Unix-Timestamp.
     * @return Die Anzahl der Sekunden (zwischen 0 und 59 inc.)
     */
    public int getSecondsBetween(int tst_future, int tst_current) {
        return getDifference(tst_future, tst_current) % 60;
    }

    /**
     * Berechnet die verbleibenden Minuten bis zur nächsten vollen Stunde bis zum Datum .
     * @param tst_future Das Datum, bis welches die Minuten berechnet werden sollen.
     * @param tst_current Das momentane Datum als Unix-Timestamp.
     * @return Die Anzahl der Minuten (zwischen 0 und 59 inc.)
     */
    public int getMinutesBetween(int tst_future, int tst_current) {
        int rest = getDifference(tst_future, tst_current) % (60 * 60);
        return (int) (rest / 60);
    }

    /**
     * Berechnet die verbleibenden Stunden bis zum nächsten vollen Tag bis zum Datum .
     * @param tst_future Das Datum, bis welches die Stunden berechnet werden sollen.
     * @param tst_current Das momentane Datum als Unix-Timestamp.
     * @return Die Anzahl der Stunden (zwischen 0 und 23 inc.)
     */
    public int getHoursBetween(int tst_future, int tst_current) {
        int rest = getDifference(tst_future, tst_current) % (60*60*24);
        return  (rest / (60 * 60));
    }

    /**
     * Berechnet die Tage bis zum vorgegebenen Datum.
     * @param tst_future Das Datum, bis welches die Tage berechnet werden sollen.
     * @param tst_current Das momentane Datum als Unix-Timestamp.
     * @return Die Anzahl an Tagen
     */
    public int getDaysBetween(int tst_future, int tst_current) {
        return (int) (getDifference(tst_future, tst_current) / (60*60*24));
    }
}
