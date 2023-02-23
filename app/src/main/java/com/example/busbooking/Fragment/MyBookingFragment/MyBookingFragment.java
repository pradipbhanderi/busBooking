package com.example.busbooking.Fragment.MyBookingFragment;

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
import com.example.busbooking.Fragment.MyBookingFragment.BusFragment.BusFragment;
import com.example.busbooking.Fragment.MyBookingFragment.BusHireFragment.BusHireFragment;
import com.example.busbooking.Fragment.MyBookingFragment.Rpooldfragment.RpoolFragment;
import com.example.busbooking.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBookingFragment extends BaseFragment {

    @BindView(R.id.tab_order_status)
    TabLayout tab_order_status;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    private BusFragment busFragment;
    private RpoolFragment rpoolFragment;
    private BusHireFragment busHireFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_booking,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        setUpTab();
        tab_order_status.getTabAt(0).select();
        viewPager.setCurrentItem(0);

    }

    @Override
    public void onBack() {

    }

    private void setUpTab() {
        try {
            viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
            viewPager.setAdapter(viewPagerAdapter);
            tab_order_status.setupWithViewPager(viewPager);
            tab_order_status.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getPosition() == 0)
                    {
                        busFragment = new BusFragment();
                    }
                    else if (tab.getPosition() == 1)
                    {
                        rpoolFragment = new RpoolFragment();
                    }
                    else if (tab.getPosition() == 2)
                    {
                        busHireFragment = new BusHireFragment();
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
                    busFragment = new BusFragment();
                    return busFragment;
                case 1:
                    rpoolFragment = new RpoolFragment();
                    return rpoolFragment;
                case 2:
                    busHireFragment= new BusHireFragment();
                    return busHireFragment;
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
                title = getString(R.string.Bus);
            } else if (position == 1) {
                title = getString(R.string.rpool);
            } else if (position == 2) {
                title = getString(R.string.bus_hire);
            }
            return title;
        }
    }


}
