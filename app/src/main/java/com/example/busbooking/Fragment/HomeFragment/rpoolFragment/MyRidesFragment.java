package com.example.busbooking.Fragment.HomeFragment.rpoolFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.R;
import com.example.busbooking.helper.CustomTextviewBold;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyRidesFragment extends BaseFragment {
    @BindView(R.id.lin_upcoming)
    LinearLayout lin_upcoming;

    @BindView(R.id.lin_recurring)
    LinearLayout lin_recurring;

    @BindView(R.id.lin_past)
    LinearLayout lin_past;

    @BindView(R.id.txt_upcoming)
    TextView txt_upcoming;

    @BindView(R.id.txt_recurring)
    TextView txt_recurring;

    @BindView(R.id.txt_past)
    TextView txt_past;

    @BindView(R.id.lin_create_ride)
    LinearLayout lin_create_ride;

    @BindView(R.id.txt_my_rides)
    CustomTextviewBold txt_my_rides;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_my_rides,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        UpcomingClick();
    }

    @Override
    public void onBack() {

    }

    @OnClick(R.id.img_back_my_rides)
    void MyRodesBackClick()
    {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.lin_upcoming)
    void UpcomingClick()
    {
        lin_upcoming.setBackgroundColor(getResources().getColor(R.color.white));
        lin_recurring.setBackgroundColor(getResources().getColor(R.color.gray2));
        lin_past.setBackgroundColor(getResources().getColor(R.color.gray2));

        txt_upcoming.setTextColor(getResources().getColor(R.color.theme_color));
        txt_recurring.setTextColor(getResources().getColor(R.color.black));
        txt_past.setTextColor(getResources().getColor(R.color.black));

        lin_create_ride.setVisibility(View.VISIBLE);

        txt_my_rides.setText("No Activie Redes!");

    }
    @OnClick(R.id.lin_recurring)
    void  RecurringClick()
    {
        lin_upcoming.setBackgroundColor(getResources().getColor(R.color.gray2));
        lin_recurring.setBackgroundColor(getResources().getColor(R.color.white));
        lin_past.setBackgroundColor(getResources().getColor(R.color.gray2));

        txt_upcoming.setTextColor(getResources().getColor(R.color.black));
        txt_recurring.setTextColor(getResources().getColor(R.color.theme_color));
        txt_past.setTextColor(getResources().getColor(R.color.black));

        lin_create_ride.setVisibility(View.GONE);

        txt_my_rides.setText("No Recurring Rides!");

    }
    @OnClick(R.id.lin_past)
    void PastClick()
    {
        lin_upcoming.setBackgroundColor(getResources().getColor(R.color.gray2));
        lin_recurring.setBackgroundColor(getResources().getColor(R.color.gray2));
        lin_past.setBackgroundColor(getResources().getColor(R.color.white));

        txt_upcoming.setTextColor(getResources().getColor(R.color.black));
        txt_recurring.setTextColor(getResources().getColor(R.color.black));
        txt_past.setTextColor(getResources().getColor(R.color.theme_color));

        lin_create_ride.setVisibility(View.GONE);

        txt_my_rides.setText("No Past Rides!");

    }

    @OnClick(R.id.lin_create_ride)
    void NoActivieRedesButtonClick()
    {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

}
