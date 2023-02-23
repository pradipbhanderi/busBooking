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

public class JourneyTypeFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.frag_journey_type, container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onBack() {

    }

    @OnClick(R.id.img_back_journey)
    void JourneyBackClick()
    {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.lin_outsation)
    void OutsationClick()
    {
        loadFragmentFull(new OutstationFragment(),"OutstationFragment");
    }

    @OnClick(R.id.lin_local)
    void LocalClick()
    {
        loadFragmentFull(new LocalFragment(),"LocalFragment");
    }

    @OnClick(R.id.lin_airport)
    void AirportClick()
    {
        loadFragmentFull(new AirportFragment(),"AirportFragment");
    }


}
