package com.example.busbooking.Fragment.HomeFragment.BusBookingFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.Model.Copassenger;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.SecurePreferences;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPassengersFragment extends BottomSheetDialogFragment {
    @BindView(R.id.rcyAddPassenger)
    RecyclerView rcyAddPassenger;
    AddPassengersAdapter addPassengersAdapter;
    List<Copassenger> list_copassenger;

    BottomSheetBehavior bottomSheetBehavior;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_add_passenger,container,false);
        ButterKnife.bind(this,view);
        iniView();
        return view;
    }

    private void iniView()
    {
        list_copassenger = new ArrayList<>();
        list_copassenger = new Gson().fromJson(SecurePreferences.getStringPreference(getActivity(), AppConstant.CoPassenger),new TypeToken<List<Copassenger>>() {}.getType());


        if (list_copassenger==null)
            list_copassenger = new ArrayList<>();

        rcyAddPassenger.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        addPassengersAdapter = new AddPassengersAdapter();
        rcyAddPassenger.setAdapter(addPassengersAdapter);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;

                FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);

//                bottomSheetBehavior.setPeekHeight((int)getResources().getDimension(R.dimen._150sdp)) ;

                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
            }
        });
        return dialog;
    }
    public class AddPassengersAdapter extends RecyclerView.Adapter<AddPassengersAdapter.AddPassengersHolder>
    {
        @NonNull
        @Override
        public AddPassengersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_add_passenger,parent,false);
            return new AddPassengersHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AddPassengersHolder holder, int position) {
            Copassenger copassenger = list_copassenger.get(position);

            holder.txtName.setText(copassenger.getName());
            holder.txtGender.setText(copassenger.getGender());

            holder.txtAge.setText(copassenger.getAge() + " Years");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SecurePreferences.savePreferences(getActivity(), AppConstant.PASSENGERNAME,copassenger.getName());
                    SecurePreferences.savePreferences(getActivity(), AppConstant.PASSENGERGENDER,copassenger.getGender());
                    SecurePreferences.savePreferences(getActivity(), AppConstant.PASSENGERAGE,copassenger.getAge());
                    Fragment fragment = getFragmentManager().findFragmentByTag("InaformationFragment");
                    InaformationFragment inaformationFragment = (InaformationFragment) fragment;
                    inaformationFragment.initView();
                    dismiss();
                }
            });

        }

        @Override
        public int getItemCount() {
            return list_copassenger.size();
        }

        public class AddPassengersHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.txtName)
            TextView txtName;
            @BindView(R.id.txtGender)
            TextView txtGender;
            @BindView(R.id.txtAge)
            TextView txtAge;
            public AddPassengersHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }

}
