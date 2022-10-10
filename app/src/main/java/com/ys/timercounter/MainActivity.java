package com.ys.timercounter;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private final int DEFAULT_TIME = 60;
    private TextView viewCounter;
    private EditText textTime;
    private TextView textTimer;
    private Button button_Plus;
    private Button button_Minus;
    private Button button_resetCounter;
    private Button button_startTimer;
    private int counterdownTime;
    private CountDownTimer countDownTimer ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// title
        viewCounter = findViewById(R.id.textView_counter);
        viewCounter.setText(Integer.toString(counter));

// input number for second
        textTime = findViewById(R.id.timer_input);
        textTime.setText(Integer.toString(DEFAULT_TIME));

// timer text
        textTimer = findViewById(R.id.textView_timer);
        textTimer.setText(textTime.getText().toString());

// Plus button for counter
        button_Plus = findViewById(R.id.button_plus);
        button_Plus.setOnClickListener(view -> {
            counter++;
            viewCounter.setText(Integer.toString(counter));
        });

// Minus button for counter
        button_Minus = findViewById(R.id.button_minus);
        button_Minus.setOnClickListener(view -> {
            if (counter > 0) {
                counter--;
                viewCounter.setText(Integer.toString(counter));
            }
        });

// reset button for counter
        button_resetCounter = findViewById(R.id.button_resetcounter);
        button_resetCounter.setOnClickListener(view -> {
            counter=0;
            viewCounter.setText(Integer.toString(counter));
        });


// Start button for timer
        button_startTimer = findViewById(R.id.button_start);
        button_startTimer.setOnClickListener(view -> {
            String inputTime = textTime.getText().toString();
            textTimer.setText(inputTime);
            counterdownTime = Integer.parseInt(inputTime);
            button_startTimer.setEnabled(false);

            countDownTimer = new CountDownTimer(counterdownTime * 1000,1000) {
                @Override
                public void onTick(long l) {
                    if(counterdownTime>0){
                        counterdownTime--;
                        textTimer.setText(Integer.toString(counterdownTime));
                    }
                }

                @Override
                public void onFinish() {
                    button_startTimer.setEnabled(true);
                    Toast.makeText(MainActivity.this, "time up", Toast.LENGTH_SHORT).show();
                    counter++;
                    viewCounter.setText(Integer.toString(counter));
                    Uri alarmSound = RingtoneManager. getDefaultUri (RingtoneManager.TYPE_NOTIFICATION );
                    MediaPlayer mp = MediaPlayer. create (getApplicationContext(), alarmSound);
                    mp.start();
                }
            }.start();
        });

// Reset button for timer
        Button button_resetTimer = findViewById(R.id.button_reset);
        button_resetTimer.setOnClickListener(view -> {
            textTimer.setText(textTime.getText());
            if (!button_startTimer.isEnabled() && button_startTimer != null){
                button_startTimer.setEnabled(true);
            }
            if (countDownTimer !=null){
                countDownTimer.cancel();
            }
        });

    }
}