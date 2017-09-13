package jsrb.com.workoutapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jacob on 9/11/2017.
 */

public class MainFragmentTwo extends Fragment {

    public static final String EXTRA_MESSAGE = "HELLO WORLD";
    public static MainFragmentTwo newInstance(String message) {
        MainFragmentTwo f = new MainFragmentTwo();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String message = getArguments().getString(EXTRA_MESSAGE);
        View v = inflater.inflate(R.layout.mainfragment_two_layout, container, false);
        return v;
    }
}
