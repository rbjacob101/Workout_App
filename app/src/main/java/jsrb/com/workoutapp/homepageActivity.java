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
        View layout = (View) getWindow().getDecorView();

        final int[] drawables = new int[4];
        drawables[0] = R.drawable.gradient_1;
        drawables[1] = R.drawable.gradient_2;
        drawables[2] = R.drawable.gradient_3;
        drawables[3] = R.drawable.gradient_4;

        gradientBackgroundPainter = new GradientBackgroundPainter(layout, drawables);
        gradientBackgroundPainter.start();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        gradientBackgroundPainter.stop();
    }

}


