package com.example.busbooking.Fragment.MyBookingFragment.BusHireFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusHireFragment extends BaseFragment {

    @BindView(R.id.tab_bus_hire)
    TabLayout tab_bus_hire;

    @BindView(R.id.viewPager_bus_hire)
    ViewPager viewPager_bus_hire;
    ViewPagerAdapter viewPagerAdapter;
    private UpcomingTripFragment upcomingTripFragment;
    private CompetedTripFragment competedTripFragment;
    private CancelledTripFragment cancelledTripFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_bus_hire, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        setUpTab();
        tab_bus_hire.getTabAt(0).select();
        viewPager_bus_hire.setCurrentItem(0);

    }

    @Override
    public void onBack() {

    }

    private void setUpTab() {
        try {

            viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
            viewPager_bus_hire.setAdapter(viewPagerAdapter);
            tab_bus_hire.setupWithViewPager(viewPager_bus_hire);
            tab_bus_hire.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getPosition() == 0) {
                        upcomingTripFragment = new UpcomingTripFragment();
                    } else if (tab.getPosition() == 1) {
                        competedTripFragment = new CompetedTripFragment();
                    } else if (tab.getPosition() == 2) {
                        cancelledTripFragment = new CancelledTripFragment();
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    upcomingTripFragment = new UpcomingTripFragment();
                    return upcomingTripFragment;
                case 1:
                    competedTripFragment = new CompetedTripFragment();
                    return competedTripFragment;
                case 2:
                    cancelledTripFragment = new CancelledTripFragment();
                    return cancelledTripFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = getString(R.string.Upcoming_Trip);
            } else if (position == 1) {
                title = getString(R.string.Competed_Trip);
            } else if (position == 2) {
                title = getString(R.string.Cancelled_Trip);
            }
            return title;
        }
    }
}
