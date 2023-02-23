package com.example.busbooking.Fragment.HomeFragment.BusBookingFragment;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.Model.BusModel;
import com.example.busbooking.R;
import com.example.busbooking.helper.AppConstant;
import com.example.busbooking.helper.CustomTextViewSemiBold;
import com.example.busbooking.helper.CustomTextview;
import com.example.busbooking.helper.CustomTextviewBold;
import com.example.busbooking.helper.SecurePreferences;


import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusShowFragment extends BaseFragment {

    @BindView(R.id.rcy_bus_book_ad)
    RecyclerView rcy_bus_book_ad;

    @BindView(R.id.rcy_bus_book)
    RecyclerView rcy_bus_book;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.txtLocation)
    TextView txtLocation;

    List<BusModel> list_bus;

    BusBookAdAdapter busBookAdAdapter;
    BusBookAdapter busBookAdapter;
    String date;

    public BusShowFragment(String date) {
        this.date = date;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_bus_show, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        rcy_bus_book_ad.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        busBookAdAdapter = new BusBookAdAdapter();
        rcy_bus_book_ad.setAdapter(busBookAdAdapter);

        rcy_bus_book.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        busBookAdapter = new BusBookAdapter();
        rcy_bus_book.setAdapter(busBookAdapter);
        iniData();
        setTodayDate();

        txtLocation.setText(SecurePreferences.getStringPreference(activity, AppConstant.SOURCE) + " - " + SecurePreferences.getStringPreference(activity, AppConstant.DESTINATION));
        Toast.makeText(activity, list_bus.size() + "buses found", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBack() {

    }

    void setTodayDate() {
        SimpleDateFormat input = new SimpleDateFormat("EEE, dd MMM");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM");
        Date datepass = null;
        try {
            datepass = input.parse(date);                 // parse input
            txtDate.setText(output.format(datepass));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.img_back_outstation)
    void BackClick() {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }


    public class BusBookAdAdapter extends RecyclerView.Adapter<BusBookAdAdapter.BusBookAdHolader> {
        @NonNull
        @Override
        public BusBookAdHolader onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_bus_book_ad, parent, false);
            return new BusBookAdHolader(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BusBookAdHolader holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 6;
        }

        public class BusBookAdHolader extends RecyclerView.ViewHolder {
            public BusBookAdHolader(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

    public void iniData() {
        list_bus = new ArrayList<>();
        list_bus.add(new BusModel("1", "20:00", "17:00", "9h 30m", "20", "Vishal Travels", "4.9", "85", "400"));
        list_bus.add(new BusModel("1", "21:30", "07:00", "10h 50m", "04", "Shree Ramkrupa Travels", "4.0", "82", "650"));
        list_bus.add(new BusModel("1", "20:15", "07:15", "9h 15m", "22", "Raghav Travels", "3.1", "63", "500"));
        list_bus.add(new BusModel("2", "20:30", "07:15", "11h 00m", "05", "Nand Travels (R)", "3.5", "22", "370"));
        list_bus.add(new BusModel("1", "19:30", "06:55", "11h 25m", "15", "Payal Travels", "4.5", "75", "400"));
        list_bus.add(new BusModel("2", "20:00", "07:00", "10h 345m", "30", "Samay Travels", "2.6", "51", "1092"));
        list_bus.add(new BusModel("1", "20:00", "06:15", "9h 30m", "08", "Shree patel Travels", "3.3", "56", "600"));
        list_bus.add(new BusModel("1", "17:00", "03:50", "11h 30m", "10", "Jay Khodiyar Travels", "2.3", "62", "399"));
        list_bus.add(new BusModel("1", "21:30", "06:45", "10h 20m", "14", "New Dharti Travels", "4.4", "18", "500"));
        list_bus.add(new BusModel("1", "20:30", "06:45", "9h 20m", "18", "Deep Travels", "4.2", "08", "600"));
        list_bus.add(new BusModel("", "21:15", "06:30", "9h 15m", "03", "mahasagar travels", "3.5", "25", "650"));
    }

    public class BusBookAdapter extends RecyclerView.Adapter<BusBookAdapter.BusBookHolder> {
        @NonNull
        @Override
        public BusBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_bus_book, parent, false);
            return new BusBookHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BusBookHolder holder, int position) {
            BusModel busModel = list_bus.get(position);
            holder.txt_bus_rest_stop.setText(busModel.getReststop());
            holder.txt_bus_pick_time.setText(busModel.getPicktime());
            holder.txt_bus_drop_time.setText(busModel.getDroptime());
            holder.txt_booking_remaing_time.setText(busModel.getBookingremaingtime());
            holder.txt_available_sates.setText(busModel.getAvailablesates() + " seats");
            holder.txt_rateing.setText(busModel.getRateing());
            holder.txt_travels_name.setText(busModel.getTravelsname());
            holder.txt_total_passenger.setText(busModel.getTotalpassenger());
            holder.txt_bus_ticket_price.setText(getResources().getString(R.string.rupee) + " " + busModel.getTicketprice());

            if (Double.parseDouble(busModel.getRateing()) >= 4) {
                GradientDrawable drawable = (GradientDrawable) holder.lin_rateing.getBackground();
                drawable.setColor(getResources().getColor(R.color.green2));
            } else if (Double.parseDouble(busModel.getRateing()) < 3) {
                GradientDrawable drawable = (GradientDrawable) holder.lin_rateing.getBackground();
                drawable.setColor(getResources().getColor(R.color.red));
            } else {
                GradientDrawable drawable = (GradientDrawable) holder.lin_rateing.getBackground();
                drawable.setColor(getResources().getColor(R.color.yellow2));
            }

            if (busModel.getAvailablesates() != null && !busModel.getAvailablesates().isEmpty()) {
                if (Double.parseDouble(busModel.getAvailablesates()) >= 10) {
                    holder.txt_available_sates.setTextColor(getResources().getColor(R.color.darker_gray));
                } else {
                    holder.txt_available_sates.setTextColor(getResources().getColor(R.color.yellow3));
                }
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    loadFragmentFull(new SeatBookingFragment(busModel.getTicketprice(), busModel.getTravelsname(), date, busModel.getBookingremaingtime(),
                            busModel.getPicktime(), busModel.getDroptime()), "SeatBookingFragment");
                }
            });
        }

        @Override
        public int getItemCount() {
            return list_bus.size();
        }

        public class BusBookHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.txt_bus_rest_stop)
            TextView txt_bus_rest_stop;
            @BindView(R.id.txt_bus_pick_time)
            TextView txt_bus_pick_time;
            @BindView(R.id.txt_bus_drop_time)
            TextView txt_bus_drop_time;
            @BindView(R.id.txt_booking_remaing_time)
            TextView txt_booking_remaing_time;
            @BindView(R.id.txt_available_sates)
            TextView txt_available_sates;
            @BindView(R.id.txt_travels_name)
            TextView txt_travels_name;
            @BindView(R.id.txt_rateing)
            TextView txt_rateing;
            @BindView(R.id.txt_total_passenger)
            TextView txt_total_passenger;
            @BindView(R.id.txt_bus_ticket_price)
            TextView txt_bus_ticket_price;
            @BindView(R.id.lin_rateing)
            LinearLayout lin_rateing;

            public BusBookHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

}
