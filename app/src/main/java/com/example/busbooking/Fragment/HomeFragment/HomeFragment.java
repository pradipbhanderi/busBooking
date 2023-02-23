package com.example.busbooking.Fragment.HomeFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.Fragment.HomeFragment.BusBookingFragment.BusShowFragment;
import com.example.busbooking.Fragment.HomeFragment.BusHireFragment.HireVehicleFragment;
import com.example.busbooking.Fragment.HomeFragment.rpoolFragment.rPoolHomeFragment;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.SecurePreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.rcy_home_ads)
    RecyclerView rcy_home_ads;
    @BindView(R.id.txt_Date)
    TextView txt_Date;
    @BindView(R.id.edtsourec)
    TextView edtsourec;
    String apiDate;
    @BindView(R.id.edtdestination)
    TextView edtdestination;
    @BindView(R.id.img_source)
    ImageView img_source;
    @BindView(R.id.img_destination)
    ImageView img_destination;
    String date;
    HomeAdsAdepter homeAdsAdepter;
    private SimpleDateFormat simpleDateFormat;
    Vibrator vibrator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        setTodayDate();
        rcy_home_ads.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        homeAdsAdepter = new HomeAdsAdepter();
        rcy_home_ads.setAdapter(homeAdsAdepter);



    }

    void setTodayDate() {
        Calendar calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("EEE, dd MMM");
        String dateTime = simpleDateFormat.format(calendar.getTime()).toString();
        txt_Date.setText(dateTime);
        date = txt_Date.getText().toString();
    }

    @Override
    public void onBack() {

    }

    @OnClick(R.id.txt_ToDay)
    void todayDateClick() {
        setTodayDate();
    }

    @OnClick(R.id.txt_tomorrow)
    void tomorrowDateClick() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("EEE, dd MMM");
        txt_Date.setText(simpleDateFormat1.format(calendar.getTime()).toString());
        date = txt_Date.getText().toString();
    }

    @OnClick(R.id.txt_Date)
    void DateSelectClick() {
        loadFragmentFull(new JourneyDataFragment(), "JourneyDataFragment");
    }

    @OnClick(R.id.lin_rpool)
    void LInRpoolClick() {
        loadFragmentFull(new rPoolHomeFragment(), "rPoolFragment");
    }

    @OnClick(R.id.lin_search)
    void SearchButtonClick() {

        if (edtsourec.getText().toString().isEmpty())
        {
            img_source.setVisibility(View.VISIBLE);
        }
        else if (edtdestination.getText().toString().isEmpty())
        {
            img_destination.setVisibility(View.VISIBLE);
        }
        else
        {
            img_source.setVisibility(View.GONE);
            img_destination.setVisibility(View.GONE);

            SecurePreferences.savePreferences(activity,AppConstant.SOURCE,edtsourec.getText().toString());
            SecurePreferences.savePreferences(activity,AppConstant.DESTINATION,edtdestination.getText().toString());

            if (edtsourec.getText().toString().equals(edtdestination.getText().toString()))
            {
                Toast.makeText(activity, " Change Location ", Toast.LENGTH_SHORT).show();
            }
            else
            {
                loadFragmentFull(new BusShowFragment(date), "BusShowFragment");
            }
        }

//        loadFragmentFull(new BusShowFragment(date), "BusShowFragment");

//        vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
//        if (Build.VERSION.SDK_INT >= 26)
//        {
//            vibrator.vibrate(VibrationEffect.createOneShot(10000,VibrationEffect.DEFAULT_AMPLITUDE));
//        }
//        else
//        {
//            vibrator.vibrate(10000);
//        }

    }

    @OnClick(R.id.lin_source)
    void SourceClick() {
        loadFragmentFull(new SearchFragment(1), "SearchFragment");
    }

    @OnClick(R.id.lin_destination)
    void DestinationClick() {
        loadFragmentFull(new SearchFragment(2), "SearchFragment");
    }

    public class HomeAdsAdepter extends RecyclerView.Adapter<HomeAdsAdepter.HomeAdsHolder> {
        @NonNull
        @Override
        public HomeAdsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_home_ads, parent, false);
            return new HomeAdsHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HomeAdsHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class HomeAdsHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.img_ads)
            ImageView img_ads;
            @BindView(R.id.txt_ads_dicription)
            TextView txt_ads_dicription;
            @BindView(R.id.txt_vaild_date)
            TextView txt_vaild_date;
            @BindView(R.id.txt_ads_coed)
            TextView txt_ads_coed;

            public HomeAdsHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    @OnClick(R.id.card_bus_hire)
    void BusHireClick() {
        loadFragmentFull(new HireVehicleFragment(), "HireVehicleFragment");
    }

    public void setSelectedDate(String mydate, String apiDate) {
        SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat output = new SimpleDateFormat("EEE, dd MMM");
        Date datepass = null;
        try {
            datepass = input.parse(mydate);                 // parse input
            txt_Date.setText(output.format(datepass));
            date = txt_Date.getText().toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
//      txt_Date.setText(AppConstant.getDateFormate(mydate));
    }

    public void setSelectedSourecLocation(String selectedLocation) {
        edtsourec.setText(selectedLocation);
        edtsourec.setTextColor(getResources().getColor(R.color.black));
        edtsourec.setTextSize(15);

    }

    public void SelectedDestinationLocation(String selectedDestination) {

        edtdestination.setText(selectedDestination);
        edtdestination.setTextColor(getResources().getColor(R.color.black));
        edtdestination.setTextSize(15);
    }

}
