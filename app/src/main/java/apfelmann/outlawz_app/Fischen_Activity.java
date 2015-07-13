package apfelmann.outlawz_app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.NumberPicker.OnValueChangeListener;

public class Fischen_Activity extends Activity
{
    private TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fischen_);

        setupUI();
    }

    public void setupUI()
    {
        tv = (TextView) findViewById(R.id.tvt);

        NumberPicker np = (NumberPicker) findViewById(R.id.uhr);

        np.setOnValueChangedListener(new OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal) {
                tv.setText(String.valueOf(newVal));
            }
        });

        np.setMaxValue(180);
        np.setMinValue(0);



        }

    }
