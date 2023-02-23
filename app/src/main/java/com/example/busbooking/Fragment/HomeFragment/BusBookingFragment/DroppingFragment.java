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
import com.example.busbooking.Model.DroppingBoardingModel;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.SecurePreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DroppingFragment extends BaseFragment {

    @BindView(R.id.rcy_dropping)
    RecyclerView rcy_dropping;
    List<BoardingModel> droppingList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dropping,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        rcy_dropping.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcy_dropping.setAdapter(new DroppingAdapter());
        IniData();
    }

    @Override
    public void onBack() {

    }

    public void IniData()
    {
        droppingList = new ArrayList<>();
        droppingList.add(new BoardingModel("Adajan Gujarat Gas","Adajan Gujarat Gas Circle","07:00"));
        droppingList.add(new BoardingModel("Bus stend","Bus stend","07:00"));
        droppingList.add(new BoardingModel("Railway Station","Railway Station","07:00"));
        droppingList.add(new BoardingModel("Hirabaugh","Hirabaugh","07:00"));
        droppingList.add(new BoardingModel("Delhi Gate","Delhi Gate","07:00"));
        droppingList.add(new BoardingModel("Varachha","Varachha","07:00"));
    }

    public class DroppingAdapter extends RecyclerView.Adapter<DroppingAdapter.DroppingHolder>
    {
        @NonNull
        @Override
        public DroppingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_dropping,parent,false);
            return new DroppingHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DroppingHolder holder, int position) {
            BoardingModel model = droppingList.get(position);

            holder.txt_drop_location.setText(model.getLocation());
            holder.txt_drop_address.setText(model.getAddress());
            holder.txt_drop_time.setText(model.getTime());

            if (model.getIs_selected())
            {
                holder.radio_dropping.setChecked(true);
            }
            else
            {
                holder.radio_dropping.setChecked(false);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SecurePreferences.savePreferences(activity, AppConstant.DROPPING,model.getAddress());

//                    Fragment fragment = getChildFragmentManager().findFragmentByTag("SelectlocationFragment");
//                    SelectlocationFragment selectlocationFragment = (SelectlocationFragment) fragment;
//                    selectlocationFragment.setVisibility();

                    if (model.getIs_selected())
                    {
                        holder.radio_dropping.setChecked(false);
                    }
                    else
                    {
                        for (int i = 0; i <droppingList.size(); i++) {

                            droppingList.get(i).setIs_selected(false);
                        }
                       model.setIs_selected(true);
                    }
                    notifyDataSetChanged();

                }
            });

        }

        @Override
        public int getItemCount() {
            return droppingList.size();
        }

        public class DroppingHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.radio_dropping)
            RadioButton radio_dropping;
            @BindView(R.id.txt_drop_location)
            TextView txt_drop_location;
            @BindView(R.id.txt_drop_address)
            TextView txt_drop_address;
            @BindView(R.id.txt_drop_time)
            TextView txt_drop_time;
            public DroppingHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }

}
