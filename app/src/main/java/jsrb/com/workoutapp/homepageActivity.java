package jsrb.com.workoutapp;

import android.os.Bundle;
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


    //do nothing if the user presses android back button
    @Override public void onBackPressed() {
        return;
    }

}


