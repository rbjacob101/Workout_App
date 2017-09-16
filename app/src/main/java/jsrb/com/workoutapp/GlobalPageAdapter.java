package jsrb.com.workoutapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Jacob on 9/11/2017.
 */

public class GlobalPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public GlobalPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        position = position % HomepageActivity.PAGES;

        switch (position) {
            case 0:
                return MainFragment.newInstance("frag 1");
            case 1:
                return MainFragmentTwo.newInstance("frag 2");
            case 2:
                return MainFragment.newInstance("frag 3");
        }
        return null;
    }

    @Override
    public int getCount() {
        return HomepageActivity.PAGES * HomepageActivity.LOOPS;
    }
}
