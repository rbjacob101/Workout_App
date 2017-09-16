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

    //set time in milliseconds for gradient transition between tabs
    public int transTime = 150;

    private boolean OnFragmentOneGradient = true;
    private GradientBackgroundPainter fragmentOneGradientPainter;
    private GradientBackgroundPainter fragmentTwoGradientPainter;

    public final static int PAGES = 3;
    public final static int LOOPS = 1000;
    public final static int FIRST_PAGE = PAGES * LOOPS / 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //fragment implementation
        List<Fragment> fragments = getFragments();
        pageAdapter = new GlobalPageAdapter(getSupportFragmentManager(), fragments);
        final ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);
        pager.setCurrentItem(FIRST_PAGE);


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //checks for when user swipes tabs and then checks for what tab they swiped to and sets gradient schema
            @Override
            public void onPageSelected(int arg0) {

                // switch-case for what tab user is on
                switch (pager.getCurrentItem()%3) {
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

        //find screen background layout
        View layout = getWindow().getDecorView();

        //define gradient colors to be used
        int[] sunsetGradient = new int[4];
        sunsetGradient[0] = R.drawable.gradient_1;
        sunsetGradient[1] = R.drawable.gradient_2;
        sunsetGradient[2] = R.drawable.gradient_5;
        sunsetGradient[3] = R.drawable.gradient_6;

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

            new CountDownTimer(transTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    fragmentTwoGradientPainter.stop();
                    OnFragmentOneGradient = true;

                    bg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gradient_transparent));

                    int[] fragmentOneGradient = new int[4];
                    fragmentOneGradient[0] = R.drawable.gradient_1;
                    fragmentOneGradient[1] = R.drawable.gradient_2;
                    fragmentOneGradient[2] = R.drawable.gradient_5;
                    fragmentOneGradient[3] = R.drawable.gradient_6;

                    fragmentOneGradientPainter = new GradientBackgroundPainter(layout, fragmentOneGradient);
                    fragmentOneGradientPainter.start();
                }
            }.start();
            bg.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transitiontest_2));
            TransitionDrawable transition = (TransitionDrawable) bg.getBackground();
            transition.startTransition(transTime);
        }
    }

    public void fragmentTwoGradient() {
        if (OnFragmentOneGradient){
            final View layout = getWindow().getDecorView();
            final View bg = findViewById(R.id.bg);

            new CountDownTimer(transTime, 1000) {
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
            transition.startTransition(transTime);
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


