package com.hiteshsahu.cool_keyboard.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hiteshsahu.cool_keyboard.view.fragments.MagicKeyboardFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        //Create new Demo Fragments
        return MagicKeyboardFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "TypeWriter";
            case 1:
                return "Star War";
            case 2:
                return "Chat Bubble";
        }
        return null;
    }
}