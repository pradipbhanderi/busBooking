package com.example.busbooking.Fragment.HomeFragment.BusHireFragment.JourneyTypeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocalFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_local,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onBack() {

    }

    @OnClick(R.id.img_back_local)
    void LocalBackClick()
    {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

}
