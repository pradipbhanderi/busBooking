package com.example.busbooking.Fragment.AccountFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.Model.Copassenger;
import com.example.busbooking.Model.ProfileModel;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.LinearLayoutManagerWrapper;
import com.example.busbooking.helper.SecurePreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoPassengersFragment extends BaseFragment {
    @BindView(R.id.lin_add_copassnger)
    LinearLayout lin_add_copassnger;
    @BindView(R.id.texnofound)
    TextView texnofound;
    @BindView(R.id.rcy_coPassenger)
    RecyclerView rcy_coPassenger;
    @BindView(R.id.edt_name)
    EditText edt_name;
    @BindView(R.id.edt_age)
    EditText edt_age;
    @BindView(R.id.radioMale)
    RadioButton radioMale;
    @BindView(R.id.radioFemale)
    RadioButton radioFemale;
    @BindView(R.id.txt_mgs_name)
    TextView txt_mgs_name;
    @BindView(R.id.txt_mgs_age)
    TextView txt_mgs_age;
    List<Copassenger> list_copassenger;
    List<ProfileModel> profileList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_copassengers,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        list_copassenger = new ArrayList<>();
        list_copassenger = new Gson().fromJson(SecurePreferences.getStringPreference(activity,AppConstant.CoPassenger),new TypeToken<List<Copassenger>>() {}.getType());


        if (list_copassenger==null)
            list_copassenger = new ArrayList<>();

        radioMale.setChecked(true);

        if (list_copassenger.size()>0)
        {
            texnofound.setVisibility(View.GONE);
            rcy_coPassenger.setLayoutManager(new LinearLayoutManager(getActivity()));
            rcy_coPassenger.setAdapter(new CoPassengerAdapter());
        }
        else
        {
            texnofound.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBack() {

    }

    @OnClick(R.id.img_back_passenger)
    void BackClick()
    {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.lin_plus)
    void PlusClick()
    {
        lin_add_copassnger.setVisibility(View.VISIBLE);
        texnofound.setVisibility(View.GONE);
        txt_mgs_name.setVisibility(View.GONE);
        txt_mgs_age.setVisibility(View.GONE);
        rcy_coPassenger.setVisibility(View.GONE);
        edt_name.setText("");
        edt_age.setText("");
        radioMale.setChecked(true);
        radioFemale.setChecked(false);

    }
    @OnClick(R.id.txtDelete)
    void DeleteClick()
    {
        lin_add_copassnger.setVisibility(View.GONE);
        rcy_coPassenger.setVisibility(View.VISIBLE);

        if(list_copassenger.size()>0)
        {
            rcy_coPassenger.setVisibility(View.VISIBLE);
            texnofound.setVisibility(View.GONE);
        }
        else
        {
            rcy_coPassenger.setVisibility(View.GONE);
            texnofound.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.txtsave)
    void SaveClick()
    {
        if (edt_name.getText().toString().isEmpty())
        {
            txt_mgs_age.setVisibility(View.GONE);
            txt_mgs_name.setVisibility(View.VISIBLE);
        }
        else if (edt_age.getText().toString().isEmpty())
        {
            txt_mgs_name.setVisibility(View.GONE);
            txt_mgs_age.setVisibility(View.VISIBLE);
        }
        else
        {
            lin_add_copassnger.setVisibility(View.GONE);
            rcy_coPassenger.setVisibility(View.VISIBLE);

            if(radioMale.isChecked())
            {
                list_copassenger.add(new Copassenger(edt_name.getText().toString(),edt_age.getText().toString(),"Male"));
                SecurePreferences.savePreferences(activity, AppConstant.CoPassenger, new Gson().toJson(list_copassenger));
            }
            else
            {
                list_copassenger.add(new Copassenger(edt_name.getText().toString(),edt_age.getText().toString(),"Female"));
                SecurePreferences.savePreferences(activity, AppConstant.CoPassenger, new Gson().toJson(list_copassenger));
            }

        }

        initView();

    }

    class CoPassengerAdapter extends RecyclerView.Adapter<CoPassengerAdapter.ItemHolder>
    {
        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_copassenger,parent,false);
            return new ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            Copassenger copassenger = list_copassenger.get(position);

            holder.txtName.setText(copassenger.getName());
            holder.txtGender.setText(copassenger.getGender());

            holder.txtAge.setText(copassenger.getAge() + " Years");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lin_add_copassnger.setVisibility(View.VISIBLE);
                    SetData(copassenger);
                }
            });

        }

        @Override
        public int getItemCount() {
            return list_copassenger.size();
        }

        class ItemHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.txtName)
            TextView txtName;
            @BindView(R.id.txtGender)
            TextView txtGender;
            @BindView(R.id.txtAge)
            TextView txtAge;
            public ItemHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
    void SetData(Copassenger copassenger)
    {
        rcy_coPassenger.setVisibility(View.GONE);
        edt_name.setText(copassenger.getName());
        edt_age.setText(copassenger.getAge());
        if (copassenger.getGender().equalsIgnoreCase("Male"))
        {
            radioMale.setChecked(true);
        }
        else
        {
            radioFemale.setChecked(true);
        }
    }
}
