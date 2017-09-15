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

    private boolean OnFragmentOneGradient = true;
    private GradientBackgroundPainter fragmentOneGradientPainter;
    private GradientBackgroundPainter fragmentTwoGradientPainter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //fragment implementation
        List<Fragment> fragments = getFragments();
        pageAdapter = new GlobalPageAdapter(getSupportFragmentManager(), fragments);
        final ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //checks for when user swipes tabs and then checks for what tab they swiped to and sets gradient schema
            @Override
            public void onPageSelected(int arg0) {

                // switch-case for what tab user is on
                switch (pager.getCurrentItem()) {
                    case 0:
                        //fragment 1
                        fragmentOneGradient();
                        break;
                    case 1:
                        //fragment 2
                        fragmentTwoGradient();
                        break;
                    case 2:
                        //fragment 3
                        fragmentOneGradient();
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // disregard
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // disregard
            }
        });

        /*------------------------------------------------------------------------------------*/

        //find screen background layout
        View layout = getWindow().getDecorView();

        //define gradient colors to be used
        int[] sunsetGradient = new int[2];
        sunsetGradient[0] = R.drawable.gradient_1;
        sunsetGradient[1] = R.drawable.gradient_2;

        fragmentOneGradientPainter = new GradientBackgroundPainter(layout, sunsetGradient);
        fragmentOneGradientPainter.start();

    }

    /*transitiontest_1 gradient from dynamic gradient 1 to dynamic gradient 2
    must be integrated into sliding between fragments and changing gradient theme
     */

    public void fragmentOneGradient() {
        if (!OnFragmentOneGradient) {
            final View layout = getWindow().getDecorView();
            final View bg = findViewById(R.id.bg);

            new CountDownTimer(170, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    fragmentTwoGradientPainter.stop();
                    OnFragmentOneGradient = true;

                    bg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gradient_transparent));

                    int[] fragmentOneGradient = new int[2];
                    fragmentOneGradient[0] = R.drawable.gradient_1;
                    fragmentOneGradient[1] = R.drawable.gradient_2;

                    fragmentOneGradientPainter = new GradientBackgroundPainter(layout, fragmentOneGradient);
                    fragmentOneGradientPainter.start();
                }
            }.start();
            bg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transitiontest_2));
            TransitionDrawable transition = (TransitionDrawable) bg.getBackground();
            transition.startTransition(170);
        }
    }

    public void fragmentTwoGradient() {
        if (OnFragmentOneGradient){
            final View layout = getWindow().getDecorView();
            final View bg = findViewById(R.id.bg);

            new CountDownTimer(170, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    fragmentOneGradientPainter.stop();
                    OnFragmentOneGradient = false;

                    bg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gradient_transparent));

                    int[] fragmentTwoGradient = new int[2];
                    fragmentTwoGradient[0] = R.drawable.gradient_3;
                    fragmentTwoGradient[1] = R.drawable.gradient_4;

                    fragmentTwoGradientPainter = new GradientBackgroundPainter(layout, fragmentTwoGradient);
                    fragmentTwoGradientPainter.start();

                }
            }.start();
            bg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transitiontest_1));
            TransitionDrawable transition = (TransitionDrawable) bg.getBackground();
            transition.startTransition(170);
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


