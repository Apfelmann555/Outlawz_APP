package apfelmann.outlawz_app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {


    //Anlegen der Variabeln
    private TextView textviewillkommen;
    private Button  buttonguides;
    private Button  buttontimer;
    private Button  buttondatenbank;
    private Button  buttonkalender;
    private Button  buttonmen�;
    private View    guides;
    sss

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Zuweisen der XML Objekte an unsere Variabeln

        buttonguides = (Button) findViewById(R.id.buttonguides);
        textviewillkommen = (TextView) findViewById(R.id.textviewwillkommen);
        buttontimer =   (Button)    findViewById(R.id.buttontimer);
        buttondatenbank = (Button)  findViewById(R.id.buttondatenbank);
        buttonkalender = (Button) findViewById(R.id.buttonkalender);
        buttonmen� = (Button) findViewById(R.id.buttonmen�);

        private void onClick(View knopfdr�ck){



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
