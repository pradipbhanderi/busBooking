package com.example.busbooking.Fragment.AccountFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.Model.ProfileModel;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.SecurePreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.lin_add_profile)
    LinearLayout lin_add_profile;
    @BindView(R.id.lin_addEdit_profile)
    LinearLayout lin_addEdit_profile;
    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.edt_name)
    EditText edt_name;
    @BindView(R.id.txt_email)
    TextView txt_email;
    @BindView(R.id.edt_email)
    EditText edt_email;


    final Calendar cale = Calendar.getInstance();
    int year = cale.get(Calendar.YEAR);
    int month = cale.get(Calendar.MONTH);
    int day = cale.get(Calendar.DAY_OF_MONTH);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_profile, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        txt_name.setText(SecurePreferences.getStringPreference(activity, AppConstant.NAME));
        txt_email.setText(SecurePreferences.getStringPreference(activity, AppConstant.Email));

        edt_name.setText(SecurePreferences.getStringPreference(activity,AppConstant.NAME));
        edt_email.setText(SecurePreferences.getStringPreference(activity,AppConstant.Email));


    }

    @Override
    public void onBack() {

    }

    @OnClick(R.id.img_back_profile)
    void BackClick() {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.txt_edit)
    void EditClick() {
        lin_add_profile.setVisibility(View.GONE);
        lin_addEdit_profile.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.txt_Done)
    void DoneClick() {
        lin_add_profile.setVisibility(View.VISIBLE);
        lin_addEdit_profile.setVisibility(View.GONE);

        SecurePreferences.savePreferences(activity, AppConstant.NAME, edt_name.getText().toString());
        SecurePreferences.savePreferences(activity, AppConstant.Email, edt_email.getText().toString());

        initView();

        Fragment fragment = getFragmentManager().findFragmentByTag("AccountFragment");
        if (fragment != null) {
            AccountFragment accountFragment = (AccountFragment) fragment;
            accountFragment.SetProfileData();
        }

    }

    @OnClick(R.id.txt_Cancel)
    void CancelClick() {
        lin_add_profile.setVisibility(View.VISIBLE);
        lin_addEdit_profile.setVisibility(View.GONE);
    }

//    @OnClick(R.id.txt_selectDate)
//    void selectDateClick()
//    {
//
//        final Calendar calendar = Calendar.getInstance();
//        year = calendar.get(calendar.YEAR);
//        month = calendar.get(calendar.MONTH);
//        day = calendar.get(calendar.DAY_OF_MONTH);
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//                txt_selectDate.setText(dayOfMonth + " / " +(monthOfYear+1) + " / " +year);
//
//            }
//        },year,month,day);
//        datePickerDialog.show();
//    }
}