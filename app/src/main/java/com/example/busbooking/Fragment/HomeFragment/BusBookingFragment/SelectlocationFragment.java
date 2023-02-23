package com.example.busbooking.Fragment.HomeFragment.BusBookingFragment;

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
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.SecurePreferences;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectlocationFragment extends BaseFragment {

    @BindView(R.id.tab_select_location)
    TabLayout tab_select_location;

    @BindView(R.id.viewPager_select_location)
    ViewPager viewPager_select_location;
    @BindView(R.id.txt_next)
    TextView txt_next;

    private int total_price;
    private String travelsName;
    String Date;
    String PickTime;
    String DropTime;
    String BookingEndTime;

    ViewPagerAdapter viewPagerAdapter;
    private BoardingFragment boardingFragment;
    private DroppingFragment droppingFragment;


    public SelectlocationFragment(int total_plus, String travels_name, String date, String pickTime, String dropTime, String bookingEndTime) {
        super();
        this.total_price = total_plus;
        this.travelsName = travels_name;
        this.Date = date;
        this.PickTime = pickTime;
        this.DropTime = dropTime;
        this.BookingEndTime = bookingEndTime;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_selectlocation, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        setUpTab();
        tab_select_location.getTabAt(0).select();
        viewPager_select_location.setCurrentItem(0);
        txt_next.setVisibility(View.VISIBLE);

    }

    @Override
    @OnClick(R.id.img_back_location)
    public void onBack() {
        if (getFragmentManager() != null) ;
        getFragmentManager().popBackStack();
    }

    @OnClick(R.id.txt_next)
    void NextButtonClick() {
        onBack();
        loadFragmentFull(new InaformationFragment(total_price, travelsName, Date, PickTime, DropTime, BookingEndTime), "InaformationFragment");
        SecurePreferences.savePreferences(activity, AppConstant.PASSENGERNAME, "");
        SecurePreferences.savePreferences(activity, AppConstant.PASSENGERGENDER, "");
        SecurePreferences.savePreferences(activity, AppConstant.PASSENGERAGE, "");
    }

    private void setUpTab() {
        try {

            viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
            viewPager_select_location.setAdapter(viewPagerAdapter);
            tab_select_location.setupWithViewPager(viewPager_select_location);
            tab_select_location.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getPosition() == 0) {
                        boardingFragment = new BoardingFragment();
                    } else if (tab.getPosition() == 1) {
                        droppingFragment = new DroppingFragment();
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
                    boardingFragment = new BoardingFragment();
                    return boardingFragment;
                case 1:
                    droppingFragment = new DroppingFragment();
                    return droppingFragment;

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
                title = getString(R.string.boarding);
            } else if (position == 1) {
                title = getString(R.string.dropping);
            }

            return title;
        }
    }

    public void tabSelect() {
        tab_select_location.getTabAt(1).select();
        viewPager_select_location.setCurrentItem(1);
    }

    public void setVisibility() {

        txt_next.setVisibility(View.VISIBLE);
    }
}
