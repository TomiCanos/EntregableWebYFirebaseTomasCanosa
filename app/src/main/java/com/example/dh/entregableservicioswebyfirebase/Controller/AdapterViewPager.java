package com.example.dh.entregableservicioswebyfirebase.Controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dh.entregableservicioswebyfirebase.Model.Paint;
import com.example.dh.entregableservicioswebyfirebase.View.DetallePaint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DH on 13/6/2018.
 */

public class AdapterViewPager extends FragmentStatePagerAdapter {

    private List<Fragment> paintsListFragment;

    public AdapterViewPager(FragmentManager fm, List<Paint> paintsList) {
        super(fm);
        paintsListFragment = new ArrayList<>();

        for (Paint paint : paintsList) {
            DetallePaint detallePaint = DetallePaint.fabricaDeFragments(paint);
            paintsListFragment.add(detallePaint);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return paintsListFragment.get(position);
    }

    @Override
    public int getCount() {
        return paintsListFragment.size();
    }
}
