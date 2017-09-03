package jsrb.com.workoutapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //remove actionBar
        getSupportActionBar().hide();

        //wait a few seconds and then hide logo image and restore actionBar
        new CountDownTimer(3000,1000) {
            @Override
            public void onTick (long millisUntilFinished){}

            @Override
            public void onFinish(){
                findViewById(R.id.imageView).setVisibility(View.GONE);
                getSupportActionBar().show();
            }

        }.start();




    }
}

