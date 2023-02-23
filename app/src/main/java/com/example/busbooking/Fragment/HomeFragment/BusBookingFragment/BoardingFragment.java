package com.example.busbooking.Fragment.HomeFragment.BusBookingFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.Model.BoardingModel;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.SecurePreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BoardingFragment extends BaseFragment {

    @BindView(R.id.rcy_boaring)
    RecyclerView rcy_boaring;

    List<BoardingModel> list_boarding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_boarding, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        rcy_boaring.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcy_boaring.setAdapter(new BoaringAdapter());
        iniData();
    }

    @Override
    public void onBack() {

    }

    public void iniData() {
        list_boarding = new ArrayList<>();
        list_boarding.add(new BoardingModel("Madhapar Chokdi", "Madhapar Chokdi, Rajkot", "20:30"));
        list_boarding.add(new BoardingModel("Nand Travels Limda Chowk", "Sudarshan Complex,Opp-\nShastrinagar Stadium ,limda\nChowk,Rajkot", "20:35"));
        list_boarding.add(new BoardingModel("Greenland Chokdi", "Upasana Travels, Greenland\nChokdi, Rajkot", "21:00"));
        list_boarding.add(new BoardingModel("Big Bazaar Vishal Travels", "Under Neples Pizza, Opp. Reliance \n Mail", "19:15"));
        list_boarding.add(new BoardingModel("Kuvadava Road Kishan ", "Kuvadava Road Patel Travels", "20:25"));
    }

    public class BoaringAdapter extends RecyclerView.Adapter<BoaringAdapter.BoaringHolder> {
        @NonNull
        @Override
        public BoaringHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_boaring, parent, false);
            return new BoaringHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BoaringHolder holder, int position) {
            BoardingModel boarding = list_boarding.get(position);

            holder.txt_address.setText(boarding.getAddress());
            holder.txt_laocation.setText(boarding.getLocation());
            holder.txt_time.setText(boarding.getTime());

            if (boarding.getIs_selected()) {
                holder.radio_btn.setChecked(true);
            } else {
                holder.radio_btn.setChecked(false);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SecurePreferences.savePreferences(activity,AppConstant.BOARDINGADDRESS,boarding.getAddress());

                    if (boarding.getIs_selected()) {
                        boarding.setIs_selected(false);
                    } else {
                        for (int i = 0; i < list_boarding.size(); i++) {
                            list_boarding.get(i).setIs_selected(false);
                        }
                        boarding.setIs_selected(true);

                    }
                    notifyDataSetChanged();

                    Fragment fragment = getChildFragmentManager().findFragmentByTag("SelectlocationFragment");
                    if (fragment != null)
                    {
                        SelectlocationFragment selectlocationFragment = (SelectlocationFragment)fragment;
                        selectlocationFragment.tabSelect();
                    }

                }
            });


        }

        @Override
        public int getItemCount() {
            return list_boarding.size();
        }

        public class BoaringHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.radio_btn)
            RadioButton radio_btn;
            @BindView(R.id.txt_laocation)
            TextView txt_laocation;
            @BindView(R.id.txt_address)
            TextView txt_address;
            @BindView(R.id.txt_time)
            TextView txt_time;

            public BoaringHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

}
