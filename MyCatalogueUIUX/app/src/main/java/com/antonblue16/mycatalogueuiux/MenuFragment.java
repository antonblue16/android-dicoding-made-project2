package com.antonblue16.mycatalogueuiux;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class MenuFragment extends Fragment
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;

    public MenuFragment()
    {}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new menuAdapter(getChildFragmentManager()));

        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });

        return view;
    }

    private class menuAdapter extends FragmentPagerAdapter
    {
        String nowPlaying = getResources().getString(R.string.now_playing);
        String upcoming = getResources().getString(R.string.upcoming);
        final String tabMenu[] = {nowPlaying, upcoming};

        public menuAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new NowPlayingFragment();
                case 1:
                    return new UpcomingFragment();
            }
            return null;
        }

        @Override
        public int getCount()
        {
            return tabMenu.length;
        }

        public CharSequence getPageTitle(int position)
        {
            return tabMenu[position];
        }
    }
}
