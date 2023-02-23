package com.example.busbooking.Fragment.AccountFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.SecurePreferences;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends BaseFragment {

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtMobile)
    EditText edtMobile;
    @BindView(R.id.txtLogin)
    TextView txtLogin;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_sign_up,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView()
    {
        txtLogin.setText(Html.fromHtml("<html><body>By Signing in, you agree to our <font size=5 color=theme_color> Terms and conditions</font> \n and <font size=5 color=theme_color> Privacy Policy</font> </body></html>"));
    }
    @OnClick(R.id.img_back)
    @Override
    public void onBack() {
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.lin_sign_btn)
    void click()
    {
        SecurePreferences.savePreferences(activity, AppConstant.NAME,edtName.getText().toString());
        SecurePreferences.savePreferences(activity,AppConstant.Email, edtEmail.getText().toString());
        loadFragmentFull(new OtpFragment(edtMobile.getText().toString().trim()),"OtpFragment");
    }

}
