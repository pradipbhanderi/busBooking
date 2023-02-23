package com.example.busbooking.Fragment.HelpFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.Model.HelpModel;
import com.example.busbooking.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpFragment extends BaseFragment {
    @BindView(R.id.rcy_help)
    RecyclerView rcy_help;
    HelpAdapter helpAdapter;
    List<HelpModel> list_helpmodel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_help,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        rcy_help.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        helpAdapter = new HelpAdapter();
        rcy_help.setAdapter(helpAdapter);
        iniData();

    }

    @Override
    public void onBack() {

    }

    public void iniData()
    {
        list_helpmodel = new ArrayList<>();
        list_helpmodel.add(new HelpModel("new bus booking help"));
        list_helpmodel.add(new HelpModel("Safety+ feture"));
        list_helpmodel.add(new HelpModel("Technical Issues"));
        list_helpmodel.add(new HelpModel("redBus Referral Help"));
        list_helpmodel.add(new HelpModel("Offers"));
        list_helpmodel.add(new HelpModel("redBus Wallet help"));
        list_helpmodel.add(new HelpModel("rpool help"));
    }

    public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.HelpHolder> {

        @NonNull
        @Override
        public HelpHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_help,parent,false);
            return new HelpHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HelpHolder holder, int position) {
            HelpModel helpModel = list_helpmodel.get(position);

            holder.txt_help.setText(helpModel.getText());

        }

        @Override
        public int getItemCount() {
            return list_helpmodel.size();
        }

        public class HelpHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.txt_help)
            TextView txt_help;
            public HelpHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }

        }
    }

}
