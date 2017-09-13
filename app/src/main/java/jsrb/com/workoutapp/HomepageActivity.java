package jsrb.com.workoutapp;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends FragmentActivity {

    GlobalPageAdapter pageAdapter;

    private boolean gradientOnSunset = true;
    private GradientBackgroundPainter sunsetGradientPainter;
    private GradientBackgroundPainter springGradientPainter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //fragment implementation
        List<Fragment> fragments = getFragments();
        pageAdapter = new GlobalPageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);

        /*------------------------------------------------------------------------------------*/

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

    //to integrate, remove (View view) and call as function
    public void springGradient() {
        if (gradientOnSunset){
            final View layout = getWindow().getDecorView();
            final View bg = findViewById(R.id.bg);

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
            bg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transitiontest_1));
            TransitionDrawable transition = (TransitionDrawable) bg.getBackground();
            transition.startTransition(100);
        }
    }

    //to integrate, remove (View view) and call as function
    public void sunsetGradient () {
        if (!gradientOnSunset) {
            final View layout = getWindow().getDecorView();
            final View bg = findViewById(R.id.bg);

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
            bg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transitiontest_2));
            TransitionDrawable transition = (TransitionDrawable) bg.getBackground();
            transition.startTransition(100);
        }
    }

    //do nothing if the user presses android back button
    @Override public void onBackPressed() {}

    //getFragments method
    //also where you add a new fragment into the sliding roster
    private List<Fragment> getFragments(){

        List<Fragment> fList = new ArrayList<>();

        fList.add(MainFragment.newInstance("Fragment 1"));
        fList.add(MainFragmentTwo.newInstance("Fragment 2"));
        fList.add(MainFragment.newInstance("Fragment 3"));

        return fList;

    }
}


