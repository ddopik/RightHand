package view;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.ddopik.scopelistner.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import UiComponanets.simleViewPager.ViewPagerActivity;
import UiComponanets.simleViewPager.ViewPagerAdapter;
import butterknife.BindView;


public class MainActivity extends ViewPagerActivity {
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.tabs)
    public TabLayout tabLayout;
    @BindView(R.id.viewpager)
    public ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public TabLayout getTabLayout() {
        return tabLayout;
    }

    @Override
    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean displayHomeAsUp() {
        return false;
    }

    @Override
    public ViewPagerAdapter getviewPagerAdapter() {
        return new ViewPagerAdapter(this.getSupportFragmentManager());
    }

    @Override
    public List<Fragment> getFragments() {
        List fragments = new ArrayList();
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());
        fragments.add(new FragmentThree());
        return fragments;
    }

    @Override
    public String[] getFragmentsTitles() {
        return new String[]{"Store", "Notes", "Slide"};
    }







}

//        switch (requestCode) {
//            case FragmentOne.EXTERNAL_STORAGE_PERMISSION_CONSTANT:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    SharedPreferences permissionStatus = getSharedPreferences("permissionStatus", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = permissionStatus.edit();
//                    editor.putBoolean(permissions[0], true);
//                    editor.commit();
//                }
//            case FragmentOne.REQUEST_PERMISSION_Contact:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    SharedPreferences permissionStatus = getSharedPreferences("permissionStatus", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = permissionStatus.edit();
//                    editor.putBoolean(permissions[0], true);
//                    editor.commit();
//                }
//        }

