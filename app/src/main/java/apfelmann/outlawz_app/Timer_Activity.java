package apfelmann.outlawz_app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.awt.font.TextAttribute;

import apfelmann.outlawz_app.datenverwaltung.SQLiteHelper;


public class Timer_Activity extends Activity implements View.OnClickListener {


    //Anlegen der Variablen
    private Button  timer_activity_fischen;
    private Button  timer_activity_bade;
    private Button  timer_activity_kampf;
    private TextView timer_activity_toptext;

    private SQLiteHelper sqlh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_);

        sqlh = new SQLiteHelper(this);

        //Zuweisen der XML Objekte an unsere Variabeln
        timer_activity_toptext = (TextView) findViewById(R.id.timer_activity_toptext);

        // Zuweisen der onClicklistener für die Funktionalität der Knöpfe.
        timer_activity_fischen.setOnClickListener(this);



    }

    @Override
    public void onClick(View button) {
        int kg = button.getId();
        Dialog dia = new Dialog(this);
        dia.setContentView(R.layout.custom_dialog);
        dia.setTitle("Neuer Timer!");
        Button dialogButton = (Button) findViewById(R.id.dialog_button_save);
        dialogButton.setOnClickListener( v -> dia.dismiss());
        dia.show();
        //startActivity(new Intent(this, Fischen_Activity.class));
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
