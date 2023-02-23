package com.example.busbooking.Fragment.AccountFragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.GoalRow;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbooking.Activity.MainActivity;
import com.example.busbooking.Activity.SplashActivity;
import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.Fragment.HomeFragment.HomeFragment;
import com.example.busbooking.Model.ProfileModel;
import com.example.busbooking.Model.accountModel;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.SecurePreferences;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class AccountFragment extends BaseFragment {
    @BindView(R.id.rcy_account)
    RecyclerView rcy_account;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtEmail)
    TextView txtEmail;
    @BindView(R.id.lin_logout)
    LinearLayout lin_logout;
    @BindView(R.id.lin_login)
    LinearLayout lin_login;
    @BindView(R.id.lin_profile)
    LinearLayout lin_profile;
    AccountAdapter accountAdapter;
    List<accountModel> list_accountModel;
    List<ProfileModel> profileList;
    GoogleSignInClient mGoogleSignInClient;
    private KProgressHUD kProgressHUD;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_account,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        if (SecurePreferences.getBooleanPreference(activity,AppConstant.IS_LOGIN))
        {
            lin_login.setVisibility(View.GONE);
            lin_logout.setVisibility(View.VISIBLE);
            lin_profile.setVisibility(View.VISIBLE);
        }
        else
        {
            lin_login.setVisibility(View.VISIBLE);
            lin_logout.setVisibility(View.GONE);
            lin_profile.setVisibility(View.GONE);
        }

        SetProfileData();
        rcy_account.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        accountAdapter = new AccountAdapter();
        rcy_account.setAdapter(accountAdapter);
        iniData();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        lin_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.custom_dialog_layout);
                    TextView msg = dialog.findViewById(R.id.txt_dialog_msg);
                    TextView yes = dialog.findViewById(R.id.txt_dialog_yes);
                    TextView no = dialog.findViewById(R.id.txt_dialog_no);
                    msg.setText(R.string.exit_msg);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            signOut();
                            dialog.dismiss();
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    //Creating dialog box
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();

                }

            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(activity);
        if (acct != null)
        {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();

           SecurePreferences.savePreferences(activity,AppConstant.NAME,personName);
           SecurePreferences.savePreferences(activity,AppConstant.Email,personEmail);

           SetProfileData();

            lin_login.setVisibility(View.GONE);
            lin_profile.setVisibility(View.VISIBLE);
        }
        else
        {
            lin_login.setVisibility(View.VISIBLE);
            lin_profile.setVisibility(View.GONE);
        }


    }

    @Override
    public void onBack()
    {

    }

    void showProgressDialog() {
        kProgressHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(false).setAnimationSpeed(5).setDimAmount(0.5f);
        if (!kProgressHUD.isShowing())
            kProgressHUD.show();
    }

    void hideProgressDialog() {
        if (kProgressHUD.isShowing())
            kProgressHUD.dismiss();
    }

// Google SignOut
private void signOut() {
    mGoogleSignInClient.signOut()
            .addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                       Toast.makeText(activity, "Logout", Toast.LENGTH_SHORT).show();

                        lin_login.setVisibility(View.VISIBLE);
                        lin_profile.setVisibility(View.GONE);
                        lin_logout.setVisibility(View.GONE);
                        ((MainActivity)activity).HomeClick();
                        SecurePreferences.savePreferences(activity,AppConstant.IS_LOGIN,false);
                    }
                }
            });
}

   public void SetProfileData()
    {
        txtName.setText(SecurePreferences.getStringPreference(activity,AppConstant.NAME));
        txtEmail.setText(SecurePreferences.getStringPreference(activity,AppConstant.Email));
    }

    @OnClick(R.id.lin_profile)
    void  ProfileClick()
    {
        loadFragmentFull(new ProfileFragment(),"ProfileFragment");
    }

    @OnClick(R.id.txtLogin)
    void LoginClick()
    {
        loadFragmentFull(new LoginFragment(),"LoginFragment");
    }

    @OnClick(R.id.txtSignUp)
    void SignUpClick()
    {
        loadFragmentFull(new LoginFragment(),"LoginFragment");
    }

    public void iniData()
    {
        list_accountModel = new ArrayList<>();

        list_accountModel.add(new accountModel("My Bookings"));
        list_accountModel.add(new accountModel("Wallet"));
        list_accountModel.add(new accountModel("Cards"));
        list_accountModel.add(new accountModel("Co-Passengers (Bus)"));
        list_accountModel.add(new accountModel("Refer & Earn"));
        list_accountModel.add(new accountModel("Offers"));
        list_accountModel.add(new accountModel("Help"));
        list_accountModel.add(new accountModel("Call Support"));
        list_accountModel.add(new accountModel("About As"));
        list_accountModel.add(new accountModel("Settings"));
    }

    public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountHolder>{

        @NonNull
        @Override
        public AccountHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_account,parent,false);
            return new AccountHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AccountHolder holder, int position) {

            accountModel accountModel = list_accountModel.get(position);

            holder.txt_flid_name.setText(accountModel.getText());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (accountModel.getText().equalsIgnoreCase("My Bookings"))
                    {
                        ((MainActivity)activity).BookingClick();
                    }
                    else if (accountModel.getText().equalsIgnoreCase("Help"))
                    {
                        ((MainActivity)activity).HelpClick();
                    }
                    else if (accountModel.getText().equalsIgnoreCase("Wallet"))
                    {
                        loadFragmentFull(new WalletFragment(),"WalletFragment");
                    }
                    else if (accountModel.getText().equalsIgnoreCase("Cards"))
                    {
                        loadFragmentFull(new CardsFragment(),"CardsFragment");
                    }
                    else if (accountModel.getText().equalsIgnoreCase("Refer & Earn"))
                    {
                        loadFragmentFull(new ReferandEarnFragment(),"ReferandEarnFragment");
                    }
                    else if (accountModel.getText().equalsIgnoreCase("Co-Passengers (Bus)"))
                    {
                        loadFragmentFull(new CoPassengersFragment(),"CoPassengersFragment");
                    }
                    else if (accountModel.getText().equalsIgnoreCase("Settings"))
                    {
                        loadFragmentFull(new SettingsFragment(),"SettingsFragment");
                    }
                    else if (accountModel.getText().equalsIgnoreCase("Call Support"))
                    {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:9265911240"));
                        startActivity(intent);
                    }

                }
            });

        }

        @Override
        public int getItemCount() {
            return list_accountModel.size();
        }

        public class AccountHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.txt_flid_name)
            TextView txt_flid_name;
            public AccountHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }

}
