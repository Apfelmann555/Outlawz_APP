package apfelmann.outlawz_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;


public class MainActivity extends Activity implements OnClickListener{


    //Anlegen der Variabeln
    private TextView textviewillkommen;
    private Button buttonguides;
    private Button buttontimer;
    private Button buttondatenbank;
    private Button buttonkalender;
    private Button buttonmenue;
    private View guides;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Zuweisen der XML Objekte an unsere Variabeln
        buttonguides = (Button) findViewById(R.id.buttonguides);
        textviewillkommen = (TextView) findViewById(R.id.textviewwillkommen);
        buttontimer = (Button) findViewById(R.id.buttontimer);
        buttondatenbank = (Button) findViewById(R.id.buttondatenbank);
        buttonkalender = (Button) findViewById(R.id.buttonkalender);
        buttonmenue = (Button) findViewById(R.id.buttonmenue);
        buttontimer.setOnClickListener(this);

    }

    public void onClick(View v) {
        int kg = v.getId();


        if(kg == R.id.buttontimer){
            Intent Intent_timer = new Intent(MainActivity.this, Timer_Activity.class);
            startActivity(Intent_timer);
        }
                else if(kg == R.id.buttonguides){
                //Intent Intent_guides= new Intent (MainActivity.this, Guides_Activity.class );
                //startActivity(Intent_guides);
                    }
                else if(kg == R.id.buttondatenbank){
                //Intent Intent_datenbank= new Intent(MainActivity.this, Datanbank_Activity.class);
                //startActivity(Intent_datenbank);
                    }
                else if(kg==R.id.buttonkalender){
                //Intent Intent_kalender= new Intent(MainActivity.this, Kalender_Activity.clss);
                //startActivity(Intent_kalender);
                    }
                else if(kg==R.id.buttonmenue){
                //Intent Intent_menue = new Intent(MainActivity.this, Menue_Activity.class);
                //startActivity(Intent_menue);
                 }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
