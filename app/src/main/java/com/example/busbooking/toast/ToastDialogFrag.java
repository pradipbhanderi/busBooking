package com.example.busbooking.toast;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import com.example.busbooking.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToastDialogFrag extends DialogFragment {

   // @BindView(R.id.txt_msg)
    TextView txt_msg;
    @BindView(R.id.card_msg)
    CardView card_msg;

    private int type;
    private String msg;

    public ToastDialogFrag(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.toast_dialog, container,false);
        ButterKnife.bind(this, view);

        if (type == 1) {
            card_msg.setBackgroundColor(getResources().getColor(R.color.error));
        } else {
            card_msg.setBackgroundColor(getResources().getColor(R.color.success));
        }

        if (!msg.equalsIgnoreCase(""))
            txt_msg.setText(msg);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getDialog() != null && getDialog().isShowing())
                    getDialog().dismiss();
            }
        }, 1500);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
            WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
            p.width = ViewGroup.LayoutParams.MATCH_PARENT;
            p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN;
            p.x = 200;
            getDialog().getWindow().setAttributes(p);
        }
    }


    @OnClick(R.id.ic_close)
    public void onCloseClick() {
        if (getDialog() != null && getDialog().isShowing())
            getDialog().dismiss();
    }

}
