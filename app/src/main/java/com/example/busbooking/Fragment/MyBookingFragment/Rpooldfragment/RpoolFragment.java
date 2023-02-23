package com.example.busbooking.Fragment.MyBookingFragment.Rpooldfragment;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.busbooking.R.*;

public class RpoolFragment extends BaseFragment {
    @BindView(id.lin_upcoming)
    LinearLayout lin_upcoming;

    @BindView(id.lin_recurring)
    LinearLayout lin_recurring;

    @BindView(id.lin_past)
    LinearLayout lin_past;

    @BindView(R.id.txt_upcoming)
    TextView txt_upcoming;

    @BindView(R.id.txt_recurring)
    TextView txt_recurring;

    @BindView(R.id.txt_past)
    TextView txt_past;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layout.frag_rpool,container,false);
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

    @OnClick(id.lin_upcoming)
    void UpcomingClick()
    {
        lin_upcoming.setBackgroundColor(getResources().getColor(R.color.white));
        lin_recurring.setBackgroundColor(getResources().getColor(color.gray2));
        lin_past.setBackgroundColor(getResources().getColor(color.gray2));

        txt_upcoming.setTextColor(getResources().getColor(color.theme_color));
        txt_recurring.setTextColor(getResources().getColor(color.black));
        txt_past.setTextColor(getResources().getColor(color.black));

    }
    @OnClick(id.lin_recurring)
    void  RecurringClick()
    {
        lin_upcoming.setBackgroundColor(getResources().getColor(color.gray2));
        lin_recurring.setBackgroundColor(getResources().getColor(color.white));
        lin_past.setBackgroundColor(getResources().getColor(color.gray2));

        txt_upcoming.setTextColor(getResources().getColor(color.black));
        txt_recurring.setTextColor(getResources().getColor(color.theme_color));
        txt_past.setTextColor(getResources().getColor(color.black));

    }
    @OnClick(id.lin_past)
    void PastClick()
    {
        lin_upcoming.setBackgroundColor(getResources().getColor(color.gray2));
        lin_recurring.setBackgroundColor(getResources().getColor(color.gray2));
        lin_past.setBackgroundColor(getResources().getColor(color.white));

        txt_upcoming.setTextColor(getResources().getColor(color.black));
        txt_recurring.setTextColor(getResources().getColor(color.black));
        txt_past.setTextColor(getResources().getColor(color.theme_color));
    }

}
