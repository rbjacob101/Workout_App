package jsrb.com.workoutapp;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class homepageActivity extends AppCompatActivity {

    public boolean ascending = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //find screen background layout
        View layout = (View) findViewById(R.id.bg);

        //draw gradient
        /* if API not >15, then use static bg */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            layout.setBackground(new DrawableGradient(new int[] {0xffee0979, 0xffff6a00}, 0).SetTransparency(10));
        } else {
            //static background

        }
    }
    //gradient method
    public class DrawableGradient extends GradientDrawable {
        DrawableGradient(int[] colors, int cornerRadius) {
            super(GradientDrawable.Orientation.TL_BR, colors);

            //set gradient properties
            try {
                this.setShape(GradientDrawable.RECTANGLE);
                this.setGradientType(GradientDrawable.LINEAR_GRADIENT);
                this.setCornerRadius(cornerRadius);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public DrawableGradient SetTransparency(int transparencyPercent) {
            this.setAlpha(255-((255*transparencyPercent) / 100));
            return this;

        }
    }
}
