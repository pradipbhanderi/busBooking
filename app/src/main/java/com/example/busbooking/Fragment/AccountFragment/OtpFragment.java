package com.example.busbooking.Fragment.AccountFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.busbooking.Activity.MainActivity;
import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.SecurePreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class OtpFragment extends BaseFragment {
    @BindView(R.id.edt1)
    EditText edt1;
    @BindView(R.id.edt2)
    EditText edt2;
    @BindView(R.id.edt3)
    EditText edt3;
    @BindView(R.id.edt4)
    EditText edt4;
    @BindView(R.id.edt5)
    EditText edt5;
    @BindView(R.id.edt6)
    EditText edt6;
    static KProgressHUD kProgressHUD;
    FirebaseAuth mAuth;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private EditText [] editTexts;
    String mobileno;

    public OtpFragment(String mobileno) {
        super();
        this.mobileno = mobileno;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_otp, container, false);
        ButterKnife.bind(this, view);
        FirebaseApp.initializeApp(activity);
        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("en");
        initView();
        return view;
    }

    @Override
    public void initView() {

        if (AppConstant.isOnline(activity))
        {
            editTexts = new EditText[]{edt1,edt2,edt3,edt4,edt5,edt6};

            edt1.addTextChangedListener(new PinTextWatcher(0));
            edt2.addTextChangedListener(new PinTextWatcher(1));
            edt3.addTextChangedListener(new PinTextWatcher(2));
            edt4.addTextChangedListener(new PinTextWatcher(3));
            edt5.addTextChangedListener(new PinTextWatcher(4));
            edt6.addTextChangedListener(new PinTextWatcher(5));

            edt1.setOnKeyListener(new PinOnKeyListener(0));
            edt2.setOnKeyListener(new PinOnKeyListener(1));
            edt3.setOnKeyListener(new PinOnKeyListener(2));
            edt4.setOnKeyListener(new PinOnKeyListener(3));
            edt5.setOnKeyListener(new PinOnKeyListener(4));
            edt6.setOnKeyListener(new PinOnKeyListener(5));

            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    signInWithPhoneAuthCredential(phoneAuthCredential);
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    hideProgressDialog();
                        if (e instanceof FirebaseAuthInvalidCredentialsException)
                        {

                            Toast.makeText(activity, "In valid number", Toast.LENGTH_SHORT).show();
                        }
                        else if (e instanceof FirebaseTooManyRequestsException)
                        {
                            Toast.makeText(activity, "sms limit over", Toast.LENGTH_SHORT).show();
                        }
                        else 
                        {

                            Toast.makeText(activity, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                }

                @Override
                public void onCodeSent(@NonNull String verificationId,
                                       @NonNull PhoneAuthProvider.ForceResendingToken token) {

                    // Save verification ID and resending token so we can use them later
                    hideProgressDialog();
                    mVerificationId = verificationId;
                    mResendToken = token;
                    Toast.makeText(activity, "Code Send: "+mobileno, Toast.LENGTH_SHORT).show();
                }

            };
            showProgressDialog();

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91" + mobileno,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    activity,               // Activity (for callback binding)
                    mCallbacks);
        }

    }

    public class PinTextWatcher implements TextWatcher {

        private int currentIndex;
        private boolean isFirst = false, isLast = false;
        private String newTypedString = "";

        PinTextWatcher(int currentIndex) {
            this.currentIndex = currentIndex;

            if (currentIndex == 0)
                this.isFirst = true;
            else if (currentIndex == editTexts.length - 1)
                this.isLast = true;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            newTypedString = s.subSequence(start, start + count).toString().trim();

            if (currentIndex == 5 && !(edt6.getText().toString().matches(""))) {
            } else {
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

            String text = newTypedString;

            /* Detect paste event and set first char */
            if (text.length() > 1)
                text = String.valueOf(text.charAt(0)); // TODO: We can fill out other EditTexts

            editTexts[currentIndex].removeTextChangedListener(this);
            editTexts[currentIndex].setText(text);
            editTexts[currentIndex].setSelection(text.length());
            editTexts[currentIndex].addTextChangedListener(this);

            if (text.length() == 1)
                moveToNext();
            else if (text.length() == 0)
                moveToPrevious();
        }

        private void moveToNext() {
            if (!isLast)
                editTexts[currentIndex + 1].requestFocus();

            if (isAllEditTextsFilled() && isLast) { // isLast is optional
                editTexts[currentIndex].clearFocus();
                hideKeyboard();
            }
        }

        private void moveToPrevious() {
            if (!isFirst)
                editTexts[currentIndex - 1].requestFocus();
        }

        private boolean isAllEditTextsFilled() {
            for (EditText editText : editTexts)
                if (editText.getText().toString().trim().length() == 0)
                    return false;
            return true;
        }

        private void hideKeyboard() {
            if (getActivity().getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        }

    }

    public class PinOnKeyListener implements View.OnKeyListener {

        private int currentIndex;

        PinOnKeyListener(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (editTexts[currentIndex].getText().toString().isEmpty() && currentIndex != 0)
                    editTexts[currentIndex - 1].requestFocus();
            }
            return false;
        }
    }

    @OnClick(R.id.img_back)
    @Override
    public void onBack() {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.lin_confirm)
    void VerifyOtp()
    {
        if (edt1.getText().toString().isEmpty() || edt2.getText().toString().isEmpty() || edt3.getText().toString().isEmpty() || edt4.getText().toString().isEmpty() || edt5.getText().toString().isEmpty() || edt6.getText().toString().isEmpty())
        {
            Toast.makeText(activity, "please enter valid code", Toast.LENGTH_SHORT).show();
        }
        else
        {
            showProgressDialog();
            String otp = edt1.getText().toString().trim() + edt2.getText().toString().trim() + edt3.getText().toString().trim() + edt4.getText().toString().trim() + edt5.getText().toString().trim() + edt6.getText().toString().trim();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
            // [END verify_with_code]
            signInWithPhoneAuthCredential(credential);
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideProgressDialog();
                        if (task.isSuccessful())
                        {
                            onBack();
                            onBack();
                            onBack();
                            SecurePreferences.savePreferences(activity,AppConstant.IS_LOGIN,true);
                            ((MainActivity)activity).HomeClick();
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }

                        }
                    }
                });
    }

    void showProgressDialog() {
        kProgressHUD = KProgressHUD.create(activity).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(5)
                .setDimAmount(0.5f);
        if (!kProgressHUD.isShowing())
            kProgressHUD.show();
    }

    void hideProgressDialog() {
        if (kProgressHUD.isShowing())
            kProgressHUD.dismiss();
    }

}
