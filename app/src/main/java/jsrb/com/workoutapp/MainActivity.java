package jsrb.com.workoutapp;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    public boolean DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = new Intent(this, homepageActivity.class);

        if (DEBUG == true) {
            startActivity(intent);
        }

        //wait a few seconds and then hide logo image and restore actionBar
        new CountDownTimer(3000,1000) {
            @Override
            public void onTick (long millisUntilFinished){}

            @Override
            public void onFinish(){
                startActivity(intent);
            }

        }.start();

    }
}