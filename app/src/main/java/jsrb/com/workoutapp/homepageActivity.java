package jsrb.com.workoutapp;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class homepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //find screen background layout
        View layout = getWindow().getDecorView();

        //initiate the gradient thread
        /* if API not >15, then use static bg */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            DrawableGradient gradient = new DrawableGradient(new int[]{0xffee0979, 0xffff6a00}, 0).SetTransparency(10);
            layout.setBackground(gradient);
            new Thread(gradient).start();
        } else {
            /*static background, NOTE: uses functions that were deprecated in API 22, but because if statement checks
            for a build version that is >15, these functions will only be used if API is 15*/
            Drawable sBackground = getResources().getDrawable(R.drawable.background_gradient);
            layout.setBackgroundDrawable(sBackground);
        }
    }
}

