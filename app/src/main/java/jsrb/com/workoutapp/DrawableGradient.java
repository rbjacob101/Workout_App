package jsrb.com.workoutapp;

import android.graphics.drawable.GradientDrawable;
import android.os.Process;

//gradient method Runnable
public class DrawableGradient extends GradientDrawable implements Runnable {
    DrawableGradient(int[] colors, int cornerRadius) {
        super(GradientDrawable.Orientation.TL_BR, colors);}

    public void run() {
        //make Thread background thread
        android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

        //set gradient properties
        try {
            this.setShape(GradientDrawable.RECTANGLE);
            this.setGradientType(GradientDrawable.LINEAR_GRADIENT);
            this.setCornerRadius(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DrawableGradient SetTransparency(int transparencyPercent) {
        this.setAlpha(255-((255*transparencyPercent) / 100));
        return this;
    }
}