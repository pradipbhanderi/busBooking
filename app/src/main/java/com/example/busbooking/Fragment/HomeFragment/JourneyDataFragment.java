package com.example.busbooking.Fragment.HomeFragment;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.R;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JourneyDataFragment extends BaseFragment {

    @BindView(R.id.calendar)
    CalendarView calendar;
    @BindView(R.id.txt_today_date)
    TextView txt_today_date;
    @BindView(R.id.txt_tomorrow_date)
    TextView txt_tomorrow_date;

    String currDate;
    private String dateTime;
    private String TomorrowDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_journeydata,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        calendar.setMinDate(System.currentTimeMillis()-1000);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Fragment fragment=getFragmentManager().findFragmentByTag("HomeFragment");
                if (fragment!=null)
                {
                    String apiDate=(year +"-"+(month+1)+"-"+dayOfMonth);
                    HomeFragment homeFragment=(HomeFragment) fragment;
                    homeFragment.setSelectedDate((dayOfMonth +"/"+(month+1)+"/"+year),apiDate);
                    onBack();
                }

            }
        });

        Calendar calendar = Calendar.getInstance();

        //for the toDay Date
        calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM,yy");
        dateTime = simpleDateFormat.format(calendar.getTime()).toString();
        txt_today_date.setText(dateTime);

        //for the tomorrow Date
        calendar.add(Calendar.DAY_OF_YEAR,1);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd MMM,yy");
        TomorrowDate = simpleDateFormat1.format(calendar.getTime()).toString();
        txt_tomorrow_date.setText(TomorrowDate);


    }

    @Override
    @OnClick(R.id.img_back_journey)
    public void onBack() {
        if (getFragmentManager()!=null) getFragmentManager().popBackStack();
    }

}
