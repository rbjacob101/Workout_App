package jsrb.com.workoutapp;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

public class homepageActivity extends AppCompatActivity {

    private boolean gradientOnSunset = true;
    private GradientBackgroundPainter sunsetGradientPainter;
    private GradientBackgroundPainter springGradientPainter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //find screen background layout
        View layout = getWindow().getDecorView();

        //define gradient colors to be used
        int[] sunsetGradient = new int[2];
        sunsetGradient[0] = R.drawable.gradient_1;
        sunsetGradient[1] = R.drawable.gradient_2;

        sunsetGradientPainter = new GradientBackgroundPainter(layout, sunsetGradient);
        sunsetGradientPainter.start();
    }


    /*transitiontest_1 gradient from dynamic gradient 1 to dynamic gradient 2
    must be integrated into sliding between fragments and changing gradient theme
     */

    public void springGradient (View view) {
        if (gradientOnSunset){
            final View layout = getWindow().getDecorView();
            final View bg = ((ViewGroup) this
                    .findViewById(android.R.id.content)).getChildAt(0);
            bg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transitiontest_1));
            TransitionDrawable transition = (TransitionDrawable) bg.getBackground();
            transition.startTransition(100);

            new CountDownTimer(100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    sunsetGradientPainter.stop();
                    gradientOnSunset = false;

                    bg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gradient_transparent));

                    int[] springGradient = new int[2];
                    springGradient[0] = R.drawable.gradient_3;
                    springGradient[1] = R.drawable.gradient_4;

                    springGradientPainter = new GradientBackgroundPainter(layout, springGradient);
                    springGradientPainter.start();

                }
            }.start();
        }
    }

    public void sunsetGradient (View view) {
        if (!gradientOnSunset) {
            final View layout = getWindow().getDecorView();
            final View bg = ((ViewGroup) this
                    .findViewById(android.R.id.content)).getChildAt(0);
            bg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transitiontest_2));
            TransitionDrawable transition = (TransitionDrawable) bg.getBackground();
            transition.startTransition(100);

            new CountDownTimer(100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    springGradientPainter.stop();
                    gradientOnSunset = true;

                    bg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gradient_transparent));

                    int[] sunsetGradient = new int[2];
                    sunsetGradient[0] = R.drawable.gradient_1;
                    sunsetGradient[1] = R.drawable.gradient_2;

                    sunsetGradientPainter = new GradientBackgroundPainter(layout, sunsetGradient);
                    sunsetGradientPainter.start();
                }
            }.start();
        }
    }

    //do nothing if the user presses android back button
    @Override public void onBackPressed() {
        return;
    }

}


