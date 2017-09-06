package jsrb.com.workoutapp;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Random;

public class homepageActivity extends AppCompatActivity {

    boolean ascending = true;
    float a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //find screen background layout
        View layout = getWindow().getDecorView();

        //deprecated API 15 setBackground

        //Drawable sBackground = getResources().getDrawable(R.drawable.background_gradient);
        //layout.setBackgroundDrawable(sBackground);
        Handler handler = new Handler();
        r.start();
    }

    public static int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(56), rnd.nextInt(256));
    }

    final Thread r = new Thread() {

        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    View layout = getWindow().getDecorView();

                    int[] colors = new int[2];
                    colors[0] = getRandomColor();
                    colors[1] = getRandomColor();


                    GradientDrawable gd = new GradientDrawable(
                            GradientDrawable.Orientation.TOP_BOTTOM, colors);

                    gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
                    gd.setGradientRadius(300f);
                    gd.setCornerRadius(0f);

                    while (ascending) {
                        a = a + (float) 0.01;
                        gd.setGradientCenter(0, a);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            layout.setBackground(gd);
                        }
                        /* if (a == 1) {
                            ascending = false;
                        }
                        */
                    }


                    }


                });
            }
        };
    }


