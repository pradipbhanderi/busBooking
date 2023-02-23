package com.example.busbooking.Fragment.HomeFragment.BusBookingFragment;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.SecurePreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InaformationFragment extends BaseFragment {

    @BindView(R.id.txt_total_price)
    TextView txt_total_price;
    @BindView(R.id.txtValidNameMgs)
    TextView txtValidNameMgs;
    @BindView(R.id.txtValidAgeMgs)
    TextView txtValidAgeMgs;
    @BindView(R.id.img_alert)
    ImageView img_alert;
    @BindView(R.id.editAge)
    EditText editAge;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.radio_male)
    RadioButton radio_male;
    @BindView(R.id.radio_female)
    RadioButton radio_female;
    @BindView(R.id.Editmobil_number)
    EditText Editmobil_number;
    @BindView(R.id.EditEmail)
    EditText EditEmail;
    @BindView(R.id.radio_protect)
    RadioButton radio_protect;
    @BindView(R.id.radio_NoProtect)
    RadioButton radio_NoProtect;
    @BindView(R.id.checkbox_covid)
    CheckBox checkbox_covid;
    @BindView(R.id.txtMgsSecureTrip)
    TextView txtMgsSecureTrip;
    @BindView(R.id.lin_checkBox)
    LinearLayout lin_checkBox;
    @BindView(R.id.lin_AddPassenger)
    LinearLayout lin_AddPassenger;

    private int price;
    private String Travels_Name;
    private String Name;
    private String Age;
    private String Gender;
    String Date;
    String PickTime;
    String DropTime;
    String BookingEndTime;

    public InaformationFragment(int total_price, String travelsName, String date, String pickTime, String dropTime, String bookingEndTime) {
        super();
        this.price = total_price;
        this.Travels_Name = travelsName;
        this.Date = date;
        this.PickTime = pickTime;
        this.DropTime = dropTime;
        this.BookingEndTime=bookingEndTime;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_informtion,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView() {

//        .setText(Html.fromHtml("<html><body>  <font color=them_color> ₹ 15</font></body></html>"));
        txtMgsSecureTrip.setText(Html.fromHtml("<html><body>Secure your trip just at<font size=5 color=theme_color>₹ 15</font>.0 per\npassenger </body><html>"));
        txt_total_price.setText(getResources().getString(R.string.rupee) + " " + price );
        EditEmail.setText(SecurePreferences.getStringPreference(activity,AppConstant.Email));
        Editmobil_number.setText(SecurePreferences.getStringPreference(activity, AppConstant.NUMBER));
        radio_protect.setText(Html.fromHtml("<html><body>Yes, Protect my trip at<font size=5 color=theme_color>₹ 15</font>(1 \nPassenger) </body><html>"));

        if (SecurePreferences.getStringPreference(activity,AppConstant.PASSENGERGENDER).equalsIgnoreCase("male"))
        {
            edtName.setText(SecurePreferences.getStringPreference(activity,AppConstant.PASSENGERNAME));
            editAge.setText(SecurePreferences.getStringPreference(activity,AppConstant.PASSENGERAGE));
            radio_male.setChecked(true);
        }
        else if (SecurePreferences.getStringPreference(activity,AppConstant.PASSENGERGENDER).equalsIgnoreCase("female"))
        {
            edtName.setText(SecurePreferences.getStringPreference(activity,AppConstant.PASSENGERNAME));
            editAge.setText(SecurePreferences.getStringPreference(activity,AppConstant.PASSENGERAGE));
            radio_female.setChecked(true);
        }
        else
        {
            radio_male.setChecked(false);
            radio_female.setChecked(false);
        }

        ProtectInsurance();


    }

    @Override
    @OnClick(R.id.img_back_infomtion)
    public void onBack() {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.lin_AddPassenger)
    void AddPassengerClick()
    {
        AddPassengersFragment addPassengersFragment = new AddPassengersFragment();
        addPassengersFragment.show(getFragmentManager(),"AddPassengersFragment");
    }

    @OnClick(R.id.lin_proceed)
    void ProceedClick()
    {
        if (edtName.getText().toString().isEmpty())
        {
            txtValidNameMgs.setVisibility(View.VISIBLE);
        }
        else if (editAge.getText().toString().isEmpty())
        {
            txtValidAgeMgs.setVisibility(View.VISIBLE);
        }
        else if (!radio_male.isChecked() && !radio_female.isChecked())
        {
            img_alert.setVisibility(View.VISIBLE);
        }
        else
        {
            txtValidNameMgs.setVisibility(View.GONE);
            txtValidAgeMgs.setVisibility(View.GONE);
            img_alert.setVisibility(View.GONE);

            if (radio_male.isChecked())
            {
                Name = edtName.getText().toString();
                Age = editAge. getText().toString();
                Gender = "Male";
            }
            else
            {
                Name = edtName.getText().toString();
                Age = editAge. getText().toString();
                Gender = "Female";
            }

            loadFragmentFull(new PaymentFragment(price,Travels_Name,Name,Gender,Age,Date,PickTime,DropTime,BookingEndTime),"PaymentFragment");
        }

    }

    public void ProtectInsurance()
    {
        radio_protect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (radio_protect.isChecked())
                {
                    price = price + 15;
                    initView();
                }
                else
                {
                    price = price -15;
                    initView();
                }

            }
        });

        checkbox_covid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (checkbox_covid.isChecked())
                {
                    price = price + 5;
                    initView();
                }
                else
                {
                    price = price - 5;
                    initView();
                }
            }
        });

    }

}
