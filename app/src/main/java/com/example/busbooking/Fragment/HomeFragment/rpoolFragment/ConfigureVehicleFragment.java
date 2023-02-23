package com.example.busbooking.Fragment.HomeFragment.rpoolFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.Model.VehicleConfigureModel;
import com.example.busbooking.R;
import com.example.busbooking.adapter.SpinnerMain;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.SecurePreferences;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfigureVehicleFragment extends BaseFragment {

    @BindView(R.id.radio_car)
    RadioButton radio_car;
    @BindView(R.id.radio_bike)
    RadioButton radio_bike;
    @BindView(R.id.lin_car)
    LinearLayout lin_car;
    @BindView(R.id.lin_bike)
    LinearLayout lin_bike;
    @BindView(R.id.spinner_car)
    Spinner spinner_car;
    @BindView(R.id.spinner_bike)
    Spinner spinner_bike;
    @BindView(R.id.spinner_seats)
    Spinner spinner_seats;
    @BindView(R.id.lin_car_price)
    LinearLayout lin_car_price;
    @BindView(R.id.lin_bike_price)
    LinearLayout lin_bike_price;
    @BindView(R.id.edt_vehicle_number)
    EditText edt_vehicle_number;
    @BindView(R.id.edt_vehicle_mode)
    EditText edt_vehicle_mode;
    @BindView(R.id.edt_vehicle_color)
    EditText edt_vehicle_color;
    @BindView(R.id.txt_color_mgs)
    TextView txt_color_mgs;
    @BindView(R.id.txt_model_mgs)
    TextView txt_model_mgs;
    @BindView(R.id.txt_number_mgs)
    TextView txt_number_mgs;


    List<String> typeList;
    List<String> seatsList;
    List<String> bikeList;
    List<VehicleConfigureModel> vehicleConfigureList;

    SpinnerMain spinnerType;
    SpinnerMain spinnerSeats;
    SpinnerMain spinnerBike;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_configure_vehicle, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        typeList = new ArrayList<>();
        typeList.add("HatchBack");
        typeList.add("Suv");
        typeList.add("Sedan");
        typeList.add("Premium");

        seatsList = new ArrayList<>();
        seatsList.add("1");
        seatsList.add("2");
        seatsList.add("3");
        seatsList.add("4");
        seatsList.add("5");
        seatsList.add("6");

        bikeList = new ArrayList<>();
        bikeList.add("Ride taker to carry helmet");
        bikeList.add("Extra helmet available with you");

        spinnerType = new SpinnerMain(activity, R.layout.simple_spinner_design, typeList);
        spinner_car.setAdapter(spinnerType);

        spinnerSeats = new SpinnerMain(activity, R.layout.simple_spinner_design, seatsList);
        spinner_seats.setAdapter(spinnerSeats);

        spinnerBike = new SpinnerMain(activity, R.layout.simple_spinner_design, bikeList);
        spinner_bike.setAdapter(spinnerBike);

        vehicleConfigureList = new ArrayList<>();


        radio_car.setChecked(true);
        radio_car.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                lin_bike.setVisibility(View.VISIBLE);
                lin_car.setVisibility(View.GONE);
                lin_car_price.setVisibility(View.GONE);
                lin_bike_price.setVisibility(View.VISIBLE);

            }
        });

        radio_bike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                lin_bike.setVisibility(View.GONE);
                lin_car.setVisibility(View.VISIBLE);
                lin_car_price.setVisibility(View.VISIBLE);
                lin_bike_price.setVisibility(View.GONE);


            }
        });


    }

    @Override
    public void onBack() {

    }

    @OnClick(R.id.img_configure_vehicle_back)
    void ConfigureVehicleBackClick() {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.lin_update)
    void UpdateVehicleDetailClick()
    {

        if (edt_vehicle_number.getText().toString().isEmpty())
        {
            txt_number_mgs.setVisibility(View.VISIBLE);
        }
        else if (edt_vehicle_mode.getText().toString().isEmpty())
        {
            txt_model_mgs.setVisibility(View.VISIBLE);
        }
        else if (edt_vehicle_color.getText().toString().isEmpty())
        {
            txt_color_mgs.setVisibility(View.VISIBLE);
        }
        else
        {
            txt_number_mgs.setVisibility(View.GONE);
            txt_model_mgs.setVisibility(View.GONE);
            txt_color_mgs.setVisibility(View.GONE);

            if (radio_car.isChecked())
            {
                vehicleConfigureList.add(new VehicleConfigureModel(
                        edt_vehicle_number.getText().toString(),"Car",
                        edt_vehicle_color.getText().toString(),
                        spinner_seats.getSelectedItem().toString(),
                        edt_vehicle_mode.getText().toString(),
                        spinner_car.getSelectedItem().toString()));

                SecurePreferences.savePreferences(activity, AppConstant.VEHICLECONFIGURE,new Gson().toJson(vehicleConfigureList));
            }
            else
            {
                vehicleConfigureList.add(new VehicleConfigureModel(
                        edt_vehicle_number.getText().toString(),"Bike",
                        edt_vehicle_color.getText().toString(),
                        spinner_seats.getSelectedItem().toString(),
                        edt_vehicle_mode.getText().toString(),
                        spinner_car.getSelectedItem().toString()));

                SecurePreferences.savePreferences(activity, AppConstant.VEHICLECONFIGURE,new Gson().toJson(vehicleConfigureList));
            }
        }

    }


}
