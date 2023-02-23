package com.example.busbooking.Fragment.MyBookingFragment.BusFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class BusFragment extends BaseFragment {

    @BindView(R.id.tab_bus_status)
    TabLayout tab_bus_status;

    @BindView(R.id.viewPagerBus)
    ViewPager viewPagerBus;

    @BindView(R.id.txt_bus)
    TextView txt_bus;

    ViewPagerAdapter viewPagerAdapter;
    private CompletedFragment completedFragment;
    private CancelledFragment cancelledFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_bus,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        setUpTab();
        tab_bus_status.getTabAt(0).select();
        viewPagerBus.setCurrentItem(0);
        txt_bus.setText(R.string.msg_bus);
    }

    @Override
    public void onBack() {

    }

    private void setUpTab() {
        try {
            viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
            viewPagerBus.setAdapter(viewPagerAdapter);
            tab_bus_status.setupWithViewPager(viewPagerBus);
            tab_bus_status.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getPosition() == 0)
                    {
                        completedFragment = new CompletedFragment();
                        txt_bus.setText(R.string.mgs_completed);
                    }
                    else if (tab.getPosition() == 1)
                    {
                        cancelledFragment = new CancelledFragment();
                        txt_bus.setText(R.string.mgs_cancelled);
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
                    completedFragment = new CompletedFragment();
                    return completedFragment;
                case 1:
                    cancelledFragment = new CancelledFragment();
                    return cancelledFragment;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = getString(R.string.completed);
            } else if (position == 1) {
                title = getString(R.string.cancelled);
            }
            return title;
        }
    }
}
