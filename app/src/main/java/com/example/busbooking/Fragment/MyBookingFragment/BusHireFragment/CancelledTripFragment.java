package com.example.busbooking.Fragment.MyBookingFragment.BusHireFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.R;

public class CancelledTripFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_cancelled_trips,container,false);
        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onBack() {

    }
}
