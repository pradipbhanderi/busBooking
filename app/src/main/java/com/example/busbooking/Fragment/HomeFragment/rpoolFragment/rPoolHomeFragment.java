package com.example.busbooking.Fragment.HomeFragment.rpoolFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class rPoolHomeFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_rpool_home,container,false);
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

    @OnClick(R.id.img_back_rpool)
    void rpoolback()
    {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.lin_verify_profile)
    void VerifyprofileClick()
    {
        loadFragmentFull(new rpoolverifiedprofileFragment(),"rpoolverifiedprofileFragment");
    }

    @OnClick(R.id.lin_rpool_preferences)
    void PreferencesClick()
    {
        loadFragmentFull(new rpoolPreferencesFragment(),"rpoolPreferencesFragment");
    }
    @OnClick(R.id.lin_chat_history)
    void ChatHoistoryClick()
    {
        loadFragmentFull(new ChatHistotyFragment(),"ChatHistotyFragment");
    }

    @OnClick(R.id.lin_rpool_rides)
    void RpoolRidesClick()
    {
        loadFragmentFull(new MyRidesFragment(),"MyRidesFragment");
    }
}

