package com.e_mobadara.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragments.EncouragementActivity;
import fragments.ExcellentActivity;
import fragments.GoodActivity;


/**
 * Created by abdellahrs on 31/10/18.
 */

public class TabAdapter extends FragmentPagerAdapter {
    int numoftabs;

    public TabAdapter(FragmentManager fm, int mnumoftabs) {
        super(fm);
        this.numoftabs = mnumoftabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ExcellentActivity tab1 = new ExcellentActivity();
                return tab1;

            case 1:
                GoodActivity tab2 = new GoodActivity();
                return tab2;

            case 2:
                EncouragementActivity tab3 = new EncouragementActivity();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
