package com.example.busbooking.Fragment.HomeFragment.rpoolFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.Model.VehicleConfigureModel;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.CustomEdittext;
import com.example.busbooking.helper.CustomTextViewSemiBold;
import com.example.busbooking.helper.CustomTextview;
import com.example.busbooking.helper.CustomTextviewBold;
import com.example.busbooking.helper.SecurePreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class rpoolPreferencesFragment extends BaseFragment {

    @BindView(R.id.txt_company_name)
    TextView txt_company_namel;

    @BindView(R.id.txt_company_email)
    TextView txt_company_email;

    @BindView(R.id.edt_company_name)
    TextView edt_company_name;

    @BindView(R.id.edt_company_email)
    TextView edt_company_email;

    @BindView(R.id.txt_edit)
    TextView txt_edit;

    @BindView(R.id.txt_save)
    TextView txt_save;

    @BindView(R.id.txt_cancel)
    TextView txt_cancel;

    @BindView(R.id.rcy_vehicle_detail)
    RecyclerView rcy_vehicle_detail;

    VehicleDetailAdapter vehicleDetailAdapter;
    List<VehicleConfigureModel> vehicleConfigureList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_rpool_preferences, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        vehicleConfigureList = new ArrayList<>();

        vehicleConfigureList = new Gson().fromJson(SecurePreferences.getStringPreference(activity, AppConstant.VEHICLECONFIGURE), new TypeToken<List<VehicleConfigureModel>>() {
        }.getType());

        if (vehicleConfigureList == null)
            vehicleConfigureList = new ArrayList<>();

        rcy_vehicle_detail.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        vehicleDetailAdapter = new VehicleDetailAdapter();
        rcy_vehicle_detail.setAdapter(vehicleDetailAdapter);

    }

    @Override
    public void onBack() {

    }

    @OnClick(R.id.img_back_rpool)
    void BackClick() {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.txt_edit)
    void EditButtonClick() {
        edt_company_name.setVisibility(View.VISIBLE);
        edt_company_email.setVisibility(View.VISIBLE);

        txt_company_namel.setVisibility(View.GONE);
        txt_company_email.setVisibility(View.GONE);

        txt_cancel.setVisibility(View.VISIBLE);
        txt_save.setVisibility(View.VISIBLE);
        txt_edit.setVisibility(View.GONE);
    }

    @OnClick(R.id.txt_save)
    void SaveButtonClick() {

        SecurePreferences.savePreferences(activity, AppConstant.COMPANYNAME, edt_company_name.getText().toString());
        SecurePreferences.savePreferences(activity, AppConstant.COMPANYEMAIL, edt_company_email.getText().toString());

        txt_company_namel.setText(SecurePreferences.getStringPreference(activity, AppConstant.COMPANYNAME));
        txt_company_email.setText(SecurePreferences.getStringPreference(activity, AppConstant.COMPANYEMAIL));

        edt_company_name.setVisibility(View.GONE);
        edt_company_email.setVisibility(View.GONE);

        txt_company_namel.setVisibility(View.VISIBLE);
        txt_company_email.setVisibility(View.VISIBLE);

        txt_cancel.setVisibility(View.GONE);
        txt_save.setVisibility(View.GONE);
        txt_edit.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.txt_cancel)
    void CancelButtonClick() {
        edt_company_name.setVisibility(View.GONE);
        edt_company_email.setVisibility(View.GONE);

        txt_company_namel.setVisibility(View.VISIBLE);
        txt_company_email.setVisibility(View.VISIBLE);

        txt_cancel.setVisibility(View.GONE);
        txt_save.setVisibility(View.GONE);
        txt_edit.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.txt_add_new)
    void AddNewClick() {
        loadFragmentFull(new ConfigureVehicleFragment(), "ConfigureVehicleFragment");
    }

    public class VehicleDetailAdapter extends RecyclerView.Adapter<VehicleDetailAdapter.VehicleDetailHolder> {
        @NonNull
        @Override
        public VehicleDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_vehicle_detail, parent, false);
            return new VehicleDetailHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VehicleDetailHolder holder, int position) {
            VehicleConfigureModel model = vehicleConfigureList.get(position);

            holder.txt_number_type.setText(model.getVehicleNumber() + " " + model.getCarType());

            holder.txt_model_name.setText(model.getVehicleModel());
            holder.txt_Seats.setText(model.getVehicleSeats() + " " + "seats");

        }

        @Override
        public int getItemCount() {
            return vehicleConfigureList.size();
        }

        public class VehicleDetailHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.txt_number_type)
            TextView txt_number_type;
            @BindView(R.id.txt_model_name)
            TextView txt_model_name;
            @BindView(R.id.txt_Seats)
            TextView txt_Seats;
            @BindView(R.id.txt_price)
            TextView txt_price;

            public VehicleDetailHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

}
