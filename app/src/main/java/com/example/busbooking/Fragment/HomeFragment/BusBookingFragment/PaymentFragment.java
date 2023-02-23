package com.example.busbooking.Fragment.HomeFragment.BusBookingFragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.busbooking.Fragment.BaseFragment;
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

public class PaymentFragment extends BaseFragment {
    @BindView(R.id.lin_bus_details)
    LinearLayout lin_bus_details;
    @BindView(R.id.lin_payment_details)
    LinearLayout lin_payment_details;
    @BindView(R.id.lin_payment_view)
    LinearLayout lin_payment_view;
    @BindView(R.id.txtTravelsName)
    TextView txtTravelsName;
    @BindView(R.id.txtticket_prices)
    TextView txtticket_prices;
    @BindView(R.id.txtUserName)
    TextView txtUserName;
    @BindView(R.id.txtGender)
    TextView txtGender;
    @BindView(R.id.txtAge)
    TextView txtAge;
    @BindView(R.id.txtPay)
    TextView txtPay;
    @BindView(R.id.txtPickTime)
    TextView txtPickTime;
    @BindView(R.id.txtEndTime)
    TextView txtEndTime;
    @BindView(R.id.txtDropTime)
    TextView txtDropTime;
    @BindView(R.id.txtToDayDate)
    TextView txtToDayDate;
    @BindView(R.id.txtTomorrowDate)
    TextView txtTomorrowDate;
    @BindView(R.id.txt_total_payable)
    TextView txt_total_payable;
    @BindView(R.id.txtBoardingLocation)
    TextView txtBoardingLocation;
    @BindView(R.id.txtDroppingLocation)
    TextView txtDroppingLocation;

    private int Ticket_Price,Total_price=0;
    private String TravelsName,Name,Age,Gender;
    String date;
    String PickTime;
    String DropTime;
    String BookingEndTime;



    public PaymentFragment(int price, String travels_name, String name, String gender, String age, String date, String pickTime, String dropTime, String bookingEndTime) {
        super();
        this.Ticket_Price=price;
        this.TravelsName=travels_name;
        this.Name = name;
        this.Age = age;
        this.Gender = gender;
        this.date = date;
        this.PickTime = pickTime;
        this.DropTime = dropTime;
        this.BookingEndTime=bookingEndTime;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.frag_payment,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initView() {

        txtTravelsName.setText(TravelsName);
        txtticket_prices.setText(getResources().getString(R.string.rupee) +" "+Ticket_Price+".00");
        txtUserName.setText(Name);
        txtAge.setText(Age +" Years");
        txtGender.setText(Gender);
        txtPickTime.setText(PickTime);
        txtDropTime.setText(DropTime);
        txtEndTime.setText(BookingEndTime);

        txtBoardingLocation.setText(SecurePreferences.getStringPreference(activity, AppConstant.BOARDINGADDRESS));
        txtDroppingLocation.setText(SecurePreferences.getStringPreference(activity,AppConstant.DROPPING));
        SecurePreferences.savePreferences(activity,AppConstant.BOARDINGADDRESS,"");
        SecurePreferences.savePreferences(activity,AppConstant.DROPPING,"");
        SimpleDateFormat input = new SimpleDateFormat("EEE, dd MMM");
        SimpleDateFormat output = new SimpleDateFormat("dd, MMM");
        java.util.Date datepass= null;
        try {
            datepass = input.parse(date);                 // parse input
            txtToDayDate.setText(output.format(datepass));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //for the tomorrow Date
//        Calendar calendar = Calendar.getInstance();
//         calendar.add(Math.toIntExact(datepass.getTime()),1);
//        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd MMM");
//        date = simpleDateFormat1.format(calendar.getTime()).toString();
        txtTomorrowDate.setText(getNextDate(date));

        TotalPayable();

    }

    @Override
    public void onBack() {

    }


    @OnClick(R.id.img_back_payment)
    void BackClick()
    {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.txtviewdetails)
    void ViewDetails()
    {
        lin_payment_details.setVisibility(View.GONE);
        lin_payment_view.setVisibility(View.GONE);
        lin_bus_details.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.lin_hide_details)
    void HideDetails()
    {
        lin_payment_details.setVisibility(View.VISIBLE);
        lin_payment_view.setVisibility(View.VISIBLE);
        lin_bus_details.setVisibility(View.GONE);
    }

    public void TotalPayable()
    {
        Total_price = Ticket_Price + 25;
        txt_total_payable.setText(getResources().getString(R.string.rupee) +" "+Total_price+".00");
        txtPay.setText(getResources().getString(R.string.rupee) +" "+Total_price+".00");
    }

    String getNextDate(String date) {
        try {
            Date time1 = new SimpleDateFormat("EEE, dd MMM").parse(date);
            Calendar calendarmyDate = Calendar.getInstance();
            calendarmyDate.setTime(time1);
            calendarmyDate.add(Calendar.DAY_OF_YEAR, 1);
            Date tomorrow = calendarmyDate.getTime();
            String todayDate=new SimpleDateFormat("yyyy-MM-dd").format(time1);
            String tomorrowDate=new SimpleDateFormat("dd, MMM").format(tomorrow);
            return tomorrowDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
