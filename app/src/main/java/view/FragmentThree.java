package view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ddopik.scopelistner.R;

import java.util.ArrayList;
import java.util.List;

import presenter.FragmentThreePresemter;
import presenter.MainActivityPresenter;


/**
 * Created by ddopik on 9/16/2017.
 */

public class FragmentThree extends Fragment {

    View mainView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView=inflater.inflate(R.layout.fragment_three, container, false);
        FragmentThreePresemter fragmentThreePresemter=new FragmentThreePresemter(getActivity(),mainView);
        fragmentThreePresemter.intializeRecyclerAdapter();
        fragmentThreePresemter.loadAnswers();
        return mainView;
    }

}
