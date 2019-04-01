package com.gin.praktice.pagerTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gin.praktice.R;

import java.util.ArrayList;
import java.util.List;

public class Acty_ProcessPager extends AppCompatActivity {

    private ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_process_pager);

        pager = (ViewPager)findViewById(R.id.processPager);
        pager.setOffscreenPageLimit(5);

        SplitorPagerAdapter adapter = new SplitorPagerAdapter(getSupportFragmentManager());

        Fragment fragment1 = new Frag_Splitor_Main();
        Fragment fragment2 = new Frag_Splitor_DDay();
        Fragment fragment3 = new Frag_Splitor_Location();

        adapter.addItem(fragment1);
        adapter.addItem(fragment2);
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);

    }



    class SplitorPagerAdapter extends FragmentStatePagerAdapter {
        List<Fragment> items = new ArrayList<>();

        public SplitorPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment fragment) {
            this.items.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return this.items.get(position);
        }

        @Override
        public int getCount() {
            return this.items.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page : " + position;
        }
    }
}
