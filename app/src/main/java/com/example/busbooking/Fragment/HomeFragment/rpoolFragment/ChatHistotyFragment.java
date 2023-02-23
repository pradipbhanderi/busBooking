package com.example.busbooking.Fragment.HomeFragment.rpoolFragment;

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


public class ChatHistotyFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_chat_histoty,container,false);
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

    @OnClick(R.id.img_back_chat_history)
    void ChatHistoryBackClick()
    {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

}
