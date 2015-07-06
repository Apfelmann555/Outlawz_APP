package apfelmann.outlawz_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.awt.font.TextAttribute;


public class Timer_Activity extends Activity implements View.OnClickListener {


    //Anlegen der Variablen
    private Button  timer_activity_fischen;
    private Button  timer_activity_bade;
    private Button  timer_activity_kampf;
    private TextView timer_activity_toptext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_);

        //Zuweisen der XML Objekte an unsere Variabeln
        timer_activity_fischen = (Button) findViewById (R.id.timer_activity_fischen);
        timer_activity_bade = (Button) findViewById(R.id.timer_activity_bade);
        timer_activity_kampf = (Button) findViewById(R.id.timer_activity_kampf);
        timer_activity_toptext = (TextView) findViewById(R.id.timer_activity_toptext);

        // Zuweisen der onClicklistener für die Funktionalität der Knöpfe.
        timer_activity_fischen.setOnClickListener(this);
        timer_activity_bade.setOnClickListener(this);
        timer_activity_kampf.setOnClickListener(this);


    }

    @Override
    public void onClick(View button) {
        int kg = button.getId();

        if(kg == R.id.timer_activity_fischen){

            //Intent intent_at = new Intent(Timer_Activity.this,Fischen_Activity.class );
            //startActivity(intent_at);
            startActivity(new Intent(this, Fischen_Activity.class));
        }
        else if(kg == R.id.timer_activity_bade){
            startActivity(new Intent(this, Bade_Activity.class));
        }
        else if (kg == R.id.timer_activity_kampf){
            startActivity(new Intent(this, Kampf_Activity.class));
        }



    }
}
