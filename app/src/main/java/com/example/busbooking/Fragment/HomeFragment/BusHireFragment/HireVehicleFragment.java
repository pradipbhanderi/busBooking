package com.example.busbooking.Fragment.HomeFragment.BusHireFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.Fragment.HomeFragment.BusHireFragment.JourneyTypeFragment.JourneyTypeFragment;
import com.example.busbooking.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HireVehicleFragment extends BaseFragment {

    @BindView(R.id.rcy_bus_hire)
    RecyclerView rcy_bus_hire;

    @BindView(R.id.rcy_easy_bus_hire)
    RecyclerView rcy_easy_bus_hirea;

    @BindView(R.id.rcy_vehicle_type)
    RecyclerView rcy_vehicle_type;

    BusHireAdpter busHireAdpter;
    EasyBusHireAdapter easyBusHireAdapter;
    VehicleTypeAdapter vehicleTypeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_hirevehicle,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        rcy_bus_hire.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        busHireAdpter = new BusHireAdpter();
        rcy_bus_hire.setAdapter(busHireAdpter);

        rcy_easy_bus_hirea.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        easyBusHireAdapter = new EasyBusHireAdapter();
        rcy_easy_bus_hirea.setAdapter(easyBusHireAdapter);

        rcy_vehicle_type.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        vehicleTypeAdapter = new VehicleTypeAdapter();
        rcy_vehicle_type.setAdapter(vehicleTypeAdapter);


    }

    @Override
    public void onBack() {

    }

    @OnClick(R.id.img_back)
    void BackClick()
    {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.but_relative)
    void ButtonClick()
    {
        loadFragmentFull(new JourneyTypeFragment(),"JourneyTypeFragment");
    }


    public class BusHireAdpter extends RecyclerView.Adapter<BusHireAdpter.BusHireHolder>{

        @NonNull
        @Override
        public BusHireHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_bus_hire,parent,false);
            return new BusHireHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BusHireHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class BusHireHolder extends RecyclerView.ViewHolder {
            public BusHireHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

    public class EasyBusHireAdapter extends RecyclerView.Adapter<EasyBusHireAdapter.EasyBusHireHolder>
    {
        @NonNull
        @Override
        public EasyBusHireHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_easy_bus_hire,parent,false);
            return new EasyBusHireHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull EasyBusHireHolder holder, int position) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.lin_easy_bus_hire.setBackgroundColor(getResources().getColor(R.color.blue2));
                    holder.txt_easy_bus_hire.setTextColor(getResources().getColor(R.color.white));
                }
            });

        }

        @Override
        public int getItemCount() {
            return 3;
        }

        public class EasyBusHireHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.lin_easy_bus_hire)
            LinearLayout lin_easy_bus_hire;
            @BindView(R.id.txt_easy_bus_hire)
            TextView txt_easy_bus_hire;
            public EasyBusHireHolder(@NonNull View itemView) {
                super(itemView);
               ButterKnife.bind(this,itemView);
            }
        }
    }

    public class VehicleTypeAdapter extends RecyclerView.Adapter<VehicleTypeAdapter.VehicleTypeHolder>
    {
        @NonNull
        @Override
        public VehicleTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_vehicle_type,parent,false);
            return new VehicleTypeHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VehicleTypeHolder holder, int position) {



        }

        @Override
        public int getItemCount() {
            return 3;
        }

        public class VehicleTypeHolder extends RecyclerView.ViewHolder {
            public VehicleTypeHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

}
