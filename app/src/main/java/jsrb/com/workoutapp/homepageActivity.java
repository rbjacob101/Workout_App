package jsrb.com.workoutapp;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class homepageActivity extends AppCompatActivity {

    private GradientBackgroundPainter gradientBackgroundPainter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //find screen background layout
        View layout = getWindow().getDecorView();

        //define gradient colors to be used
        int[] drawables = new int[2];
        drawables[0] = R.drawable.gradient_1;
        drawables[1] = R.drawable.gradient_2;

        gradientBackgroundPainter = new GradientBackgroundPainter(layout, drawables);
        gradientBackgroundPainter.start();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        gradientBackgroundPainter.stop();
    }

    /*transitionTest gradient from dynamic gradient 1 to dynamic gradient 2
    must be integrated into sliding between fragments and changing gradient theme
     */

    public void transitionGradient (View view) {
        gradientBackgroundPainter.stop();
        View layout = getWindow().getDecorView();
        layout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transitionTest));
        TransitionDrawable transition = (TransitionDrawable) layout.getBackground();
        transition.startTransition(100);
    }

    //do nothing if the user presses android back button
    @Override public void onBackPressed() {
        return;
    }

}


