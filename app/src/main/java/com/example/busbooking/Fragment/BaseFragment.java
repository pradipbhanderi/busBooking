package com.example.busbooking.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.busbooking.Activity.LoginActivity;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.SecurePreferences;
import com.example.busbooking.toast.ToastDialogFrag;


public abstract class BaseFragment extends Fragment {
    public AppCompatActivity activity;

    protected static void loadFragmentMain(FragmentManager supportFragmentManager, String s) {
    }

    @Nullable
    @Override
    public abstract View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public abstract void initView();

    public abstract void onBack();

    public void loadFragmentMain(Fragment fragment, String tag) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_main, fragment, tag).commit();
    }

    public void loadFragmentFull(Fragment fragment1, String tag) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in, R.anim.back_slide_in, R.anim.slide_out, R.anim.back_slide_out);
        ft.add(R.id.frame_full, fragment1, tag).addToBackStack(null).commit();
    }
//
//    public void loadragmentFromBottom(Fragment fragment, String tag) {
//        if (getFragmentMaFnager() != null) {
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_down, R.anim.slide_out_up, R.anim.slide_in_down);
//            transaction.add(R.id.frame_full, fragment, tag).addToBackStack(null).commit();
//        }

//    public void loadloginfullfragment(FragmentManager supportFragmentManager, Fragment fragment, String tag) {
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.setCustomAnimations(R.anim.slide_in, R.anim.back_slide_in, R.anim.slide_out, R.anim.back_slide_out);
//        ft.add(R.id.frame_login, fragment, tag);
//        ft.addToBackStack(null);
//        ft.commit();
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public void logout() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    SecurePreferences.savePreferences(getActivity(), AppConstant.IS_LOGIN, false);
                   Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        }, 1200);
    }

    public void showToast(int msg_type, String msg) {
        FragmentManager fragmentManager = getFragmentManager();
        ToastDialogFrag tv = new ToastDialogFrag(msg_type, msg);
        if (fragmentManager != null)
            tv.show(fragmentManager, "TOAST");
    }
}
