package jsrb.com.workoutapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public boolean DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent homepage = new Intent(this, homepageActivity.class);

        //set action bar transparency
        View window = getWindow().getDecorView();

        Drawable gradient = ContextCompat.getDrawable(getApplicationContext(), R.drawable.gradient_1);
        window.setBackground(gradient);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#20111111"));
        }

        if (DEBUG) {
            startActivity(homepage);
        }

        //wait a few seconds and then hide logo image and restore actionBar
        new CountDownTimer(2500,1000) {
            @Override
            public void onTick (long millisUntilFinished){}

            @Override
            public void onFinish(){
                startActivity(homepage);
            }

        }.start();

    }
}