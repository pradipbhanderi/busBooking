package com.example.busbooking.Activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.busbooking.Fragment.AccountFragment.AccountFragment;
import com.example.busbooking.Fragment.HelpFragment.HelpFragment;
import com.example.busbooking.Fragment.HomeFragment.HomeFragment;
import com.example.busbooking.Fragment.MyBookingFragment.MyBookingFragment;
import com.example.busbooking.R;
import com.google.firebase.analytics.FirebaseAnalytics;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img_home)
    ImageView img_home;
    @BindView(R.id.img_booking)
    ImageView img_booking;
    @BindView(R.id.img_help)
    ImageView img_help;
    @BindView(R.id.img_account)
    ImageView img_account;

    @BindView(R.id.txt_home)
    TextView txt_home;
    @BindView(R.id.txt_booking)
    TextView txt_booking;
    @BindView(R.id.txt_help)
    TextView txt_help;
    @BindView(R.id.txt_account)
    TextView txt_account;

    FirebaseAnalytics analytics;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        analytics = FirebaseAnalytics.getInstance(this);
        HomeClick();
    }

    @OnClick(R.id.lin_home)
    public void HomeClick()
    {
        img_home.setColorFilter(getResources().getColor(R.color.red));
        img_booking.setColorFilter(getResources().getColor(R.color.black));
        img_help.setColorFilter(getResources().getColor(R.color.black));
        img_account.setColorFilter(getResources().getColor(R.color.black));

        txt_home.setTextColor(getResources().getColor(R.color.red));
        txt_booking.setTextColor(getResources().getColor(R.color.black));
        txt_help.setTextColor(getResources().getColor(R.color.black));
        txt_account.setTextColor(getResources().getColor(R.color.black));

        loadFragmentMain(new HomeFragment(),"HomeFragment");

    }


    @OnClick(R.id.lin_bookig)
    public void BookingClick()
    {
        img_home.setColorFilter(getResources().getColor(R.color.black));
        img_booking.setColorFilter(getResources().getColor(R.color.red));
        img_help.setColorFilter(getResources().getColor(R.color.black));
        img_account.setColorFilter(getResources().getColor(R.color.black));

        txt_home.setTextColor(getResources().getColor(R.color.black));
        txt_booking.setTextColor(getResources().getColor(R.color.red));
        txt_help.setTextColor(getResources().getColor(R.color.black));
        txt_account.setTextColor(getResources().getColor(R.color.black));

        loadFragmentMain(new MyBookingFragment(),"MyBookingFragment");

    }

    @OnClick(R.id.lin_help)
    public void  HelpClick()
    {
        img_home.setColorFilter(getResources().getColor(R.color.black));
        img_booking.setColorFilter(getResources().getColor(R.color.black));
        img_help.setColorFilter(getResources().getColor(R.color.red));
        img_account.setColorFilter(getResources().getColor(R.color.black));

        txt_home.setTextColor(getResources().getColor(R.color.black));
        txt_booking.setTextColor(getResources().getColor(R.color.black));
        txt_help.setTextColor(getResources().getColor(R.color.red));
        txt_account.setTextColor(getResources().getColor(R.color.black));

        loadFragmentMain(new HelpFragment(),"HelpFragment");

    }

    @OnClick(R.id.lin_acconut)
    void AccountClick(){

        img_home.setColorFilter(getResources().getColor(R.color.black));
        img_booking.setColorFilter(getResources().getColor(R.color.black));
        img_help.setColorFilter(getResources().getColor(R.color.black));
        img_account.setColorFilter(getResources().getColor(R.color.red));

        txt_home.setTextColor(getResources().getColor(R.color.black));
        txt_booking.setTextColor(getResources().getColor(R.color.black));
        txt_help.setTextColor(getResources().getColor(R.color.black));
        txt_account.setTextColor(getResources().getColor(R.color.red));

        loadFragmentMain(new AccountFragment(),"AccountFragment");

    }

    void loadFragmentMain(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_main, fragment, tag).commit();
    }

}