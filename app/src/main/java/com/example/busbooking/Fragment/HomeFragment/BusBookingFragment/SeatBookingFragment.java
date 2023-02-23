package com.example.busbooking.Fragment.HomeFragment.BusBookingFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.Model.BusModel;
import com.example.busbooking.Model.SateBookingModel;
import com.example.busbooking.R;
import com.example.busbooking.helper.CustomTextViewSemiBold;
import com.example.busbooking.helper.CustomTextviewBold;

import java.nio.InvalidMarkException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeatBookingFragment extends BaseFragment {

    @BindView(R.id.txt_lower)
    CustomTextViewSemiBold txt_lower;
    @BindView(R.id.txt_lower_count)
    CustomTextViewSemiBold txt_lower_count;
    @BindView(R.id.txt_upper)
    CustomTextViewSemiBold txt_upper;
    @BindView(R.id.txt_upper_count)
    CustomTextViewSemiBold txt_upper_count;
    @BindView(R.id.lin_lowre)
    LinearLayout lin_lowre;
    @BindView(R.id.lin_upper)
    LinearLayout lin_upper;
    @BindView(R.id.rcy_bus_seat)
    RecyclerView rcy_bus_seat;
    @BindView(R.id.rcy_bus_seat_book_two_row)
    RecyclerView rcy_bus_seat_book_two_row;
    @BindView(R.id.lin_card)
    LinearLayout lin_card;
    @BindView(R.id.txt_sate_book_count)
    CustomTextviewBold txt_sate_book_count;
    @BindView(R.id.txt_total_price)
    CustomTextviewBold txt_total_price;
    @BindView(R.id.txt_travels_name)
    TextView txt_travels_name;
    @BindView(R.id.txtDateTime)
    TextView txtDateTime;
    @BindView(R.id.lin_lower_seats)
    LinearLayout lin_lower_seats;
    @BindView(R.id.lin_Upper_seats)
    LinearLayout lin_Upper_seats;
    @BindView(R.id.rcy_bus_seat_upper)
    RecyclerView rcy_bus_seat_upper;
    @BindView(R.id.rcy_bus_seat_book_two_row_upper)
    RecyclerView rcy_bus_seat_book_two_row_upper;


    List<SateBookingModel> list_satebooking;
    List<SateBookingModel> sateBookingList;
    List<SateBookingModel> upperSateBookingList;
    List<SateBookingModel> upperTowRowSateBookingList;
    int counter_total = 0;
    private int total_plus = 0;
    String ticket_price;
    String travels_Name;
    String date;
    String PickTime;
    String DropTime;
    String BookingEndTime;


    SeatBookingAdapter seatBookingAdapter;
    TworowSeatBookingAdapter tworowSeatBookingAdapter;

    public SeatBookingFragment(String ticketprice, String travelsname, String date, String bookingremaingtime, String picktime, String droptime) {
        super();
        this.ticket_price = ticketprice;
        this.travels_Name = travelsname;
        this.date = date;
        this.PickTime = picktime;
        this.DropTime = droptime;
        this.BookingEndTime=bookingremaingtime;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_seat_booking, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        LowerClick();
        SimpleDateFormat input = new SimpleDateFormat("EEE, dd MMM");
        SimpleDateFormat output = new SimpleDateFormat("dd, MMM");
        java.util.Date datepass= null;
        try {
            datepass = input.parse(date);                 // parse input
            txtDateTime.setText(output.format(datepass)+"-"+PickTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        txt_travels_name.setText(travels_Name);

        rcy_bus_seat.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        seatBookingAdapter = new SeatBookingAdapter();
        rcy_bus_seat.setAdapter(seatBookingAdapter);
        IniData();

        rcy_bus_seat_book_two_row.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        tworowSeatBookingAdapter = new TworowSeatBookingAdapter();
        rcy_bus_seat_book_two_row.setAdapter(tworowSeatBookingAdapter);
        iniData();

        rcy_bus_seat_upper.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcy_bus_seat_upper.setAdapter(new SeatBookingUpperAdapter());
        INIData();

        rcy_bus_seat_book_two_row_upper.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rcy_bus_seat_book_two_row_upper.setAdapter(new TwoRowUpperSeatBookingAdapter());
        INIDATA();
    }

    @Override
    public void onBack() {

    }

    @OnClick(R.id.lin_done)
    void DoneButtonClick() {

        loadFragmentFull(new SelectlocationFragment(total_plus,travels_Name,date,PickTime,DropTime,BookingEndTime), "SelectlocationFragment");

    }

    @OnClick(R.id.img_back_seat_booking)
    void SeatBookingBackClick() {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @OnClick(R.id.lin_lowre)
    void LowerClick() {
        lin_lowre.setBackgroundColor(getResources().getColor(R.color.theme_color));
        lin_upper.setBackgroundColor(getResources().getColor(R.color.gray_3));

        txt_lower.setTextColor(getResources().getColor(R.color.white));
        txt_lower_count.setTextColor(getResources().getColor(R.color.white));

        txt_upper.setTextColor(getResources().getColor(R.color.black));
        txt_upper_count.setTextColor(getResources().getColor(R.color.black));

        lin_lower_seats.setVisibility(View.VISIBLE);
        lin_Upper_seats.setVisibility(View.GONE);
    }

    @OnClick(R.id.lin_upper)
    void UpperClick() {
        lin_upper.setBackgroundColor(getResources().getColor(R.color.theme_color));
        lin_lowre.setBackgroundColor(getResources().getColor(R.color.gray_3));

        txt_lower.setTextColor(getResources().getColor(R.color.black));
        txt_lower_count.setTextColor(getResources().getColor(R.color.black));

        txt_upper.setTextColor(getResources().getColor(R.color.white));
        txt_upper_count.setTextColor(getResources().getColor(R.color.white));

        lin_lower_seats.setVisibility(View.GONE);
        lin_Upper_seats.setVisibility(View.VISIBLE);

    }

    public void IniData() {
        sateBookingList = new ArrayList<>();

        sateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
        sateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
        sateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
        sateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
        sateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
        sateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
    }

    public class SeatBookingAdapter extends RecyclerView.Adapter<SeatBookingAdapter.SeatBookingHolder>
    {
        @NonNull
        @Override
        public SeatBookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_seat, parent, false);
            return new SeatBookingHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SeatBookingHolder holder, int position) {
            SateBookingModel model = sateBookingList.get(position);

            if (model.getIs_selected()) {
                holder.img_seat.setImageResource(R.drawable.ic_seat);
            } else {
                holder.img_seat.setImageResource(R.drawable.ic_sate);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sateBookingList.get(position).setIs_selected(!model.getIs_selected());
                    counter_total = 0;
                    for (int i = 0; i < sateBookingList.size(); i++) {
                        totalPrice();
                    }
                    totalItemSelect();
                    notifyItemChanged(position);

                }
            });

        }

        @Override
        public int getItemCount() {
            return sateBookingList.size();
        }

        public class SeatBookingHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.img_seat)
            ImageView img_seat;

            public SeatBookingHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

     public void iniData()
     {
        list_satebooking = new ArrayList<>();

        list_satebooking.add(new SateBookingModel(R.drawable.ic_sate));
        list_satebooking.add(new SateBookingModel(R.drawable.ic_sate));
        list_satebooking.add(new SateBookingModel(R.drawable.ic_sate));
        list_satebooking.add(new SateBookingModel(R.drawable.ic_sate));
        list_satebooking.add(new SateBookingModel(R.drawable.ic_sate));
        list_satebooking.add(new SateBookingModel(R.drawable.ic_sate));
        list_satebooking.add(new SateBookingModel(R.drawable.ic_sate));
        list_satebooking.add(new SateBookingModel(R.drawable.ic_sate));
        list_satebooking.add(new SateBookingModel(R.drawable.ic_sate));
        list_satebooking.add(new SateBookingModel(R.drawable.ic_sate));
        list_satebooking.add(new SateBookingModel(R.drawable.ic_sate));
        list_satebooking.add(new SateBookingModel(R.drawable.ic_sate));
    }

     public class TworowSeatBookingAdapter extends RecyclerView.Adapter<TworowSeatBookingAdapter.TworowSeatBookingHolder>
     {
        @NonNull
        @Override
        public TworowSeatBookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_seat_tow, parent, false);
            return new TworowSeatBookingHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TworowSeatBookingHolder holder, int position) {
            SateBookingModel sateBooking = list_satebooking.get(position);

            holder.img_grid_sate.setImageResource(sateBooking.getImage());

            if (sateBooking.getIs_selected()) {
                holder.img_grid_sate.setImageResource(R.drawable.ic_seat);
            } else {
                holder.img_grid_sate.setImageResource(R.drawable.ic_sate);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    if (sateBooking.getIs_selected())
//                    {
//                        list_satebooking.get(position).setIs_selected(false);
//
//                    }

                    list_satebooking.get(position).setIs_selected(!sateBooking.getIs_selected());
                    counter_total = 0;
                    for (int i = 0; i < list_satebooking.size(); i++) {
                        totalPrice();
                    }
                    totalItemSelect();
                    notifyItemChanged(position);

                }
            });
        }

        @Override
        public int getItemCount() {
            return list_satebooking.size();
        }

        public class TworowSeatBookingHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.img_grid_sate)
            ImageView img_grid_sate;

            public TworowSeatBookingHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

     public void INIData()
     {
        upperSateBookingList = new ArrayList<>();

        upperSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
        upperSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
        upperSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
        upperSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
        upperSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
        upperSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
    }

     public class SeatBookingUpperAdapter extends RecyclerView.Adapter<SeatBookingUpperAdapter.SeatBookingUpperHolder>
     {
         @NonNull
         @Override
         public SeatBookingUpperHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_seat,parent,false);
             return new SeatBookingUpperHolder(view);
         }

         @Override
         public void onBindViewHolder(@NonNull SeatBookingUpperHolder holder, int position) {
             SateBookingModel model = upperSateBookingList.get(position);

             holder.img_seat.setImageResource(model.getImage());

             if (model.getIs_selected()) {
                 holder.img_seat.setImageResource(R.drawable.ic_seat);
             } else {
                 holder.img_seat.setImageResource(R.drawable.ic_sate);
             }

             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     upperSateBookingList.get(position).setIs_selected(!model.getIs_selected());
                     counter_total = 0;
                     for (int i = 0; i < upperSateBookingList.size(); i++) {
                         totalPrice();
                     }
                     totalItemSelect();
                     notifyItemChanged(position);

                 }
             });

         }

         @Override
         public int getItemCount() {
             return upperSateBookingList.size();
         }

         public class SeatBookingUpperHolder extends RecyclerView.ViewHolder {
             @BindView(R.id.img_seat)
             ImageView img_seat;
              public SeatBookingUpperHolder(@NonNull View itemView) {
                 super(itemView);
                 ButterKnife.bind(this,itemView);
             }
         }
     }

      public void INIDATA()
     {
         upperTowRowSateBookingList = new ArrayList<>();

         upperTowRowSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
         upperTowRowSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
         upperTowRowSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
         upperTowRowSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
         upperTowRowSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
         upperTowRowSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
         upperTowRowSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
         upperTowRowSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
         upperTowRowSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
         upperTowRowSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
         upperTowRowSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
         upperTowRowSateBookingList.add(new SateBookingModel(R.drawable.ic_sate));
     }

     public class TwoRowUpperSeatBookingAdapter  extends RecyclerView.Adapter<TwoRowUpperSeatBookingAdapter.TwoRowUpperSeatBookingHolder>
     {
         @NonNull
         @Override
         public TwoRowUpperSeatBookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_seat_tow,parent,false);
             return new TwoRowUpperSeatBookingHolder(view);
         }

         @Override
         public void onBindViewHolder(@NonNull TwoRowUpperSeatBookingHolder holder, int position) {
             SateBookingModel model = upperTowRowSateBookingList.get(position);

             holder.img_grid_sate.setImageResource(model.getImage());

             if (model.getIs_selected()) {
                 holder.img_grid_sate.setImageResource(R.drawable.ic_seat);
             } else {
                 holder.img_grid_sate.setImageResource(R.drawable.ic_sate);
             }

             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     upperTowRowSateBookingList.get(position).setIs_selected(!model.getIs_selected());
                     counter_total = 0;
                     for (int i = 0; i < upperTowRowSateBookingList.size(); i++) {

                         totalPrice();
                     }
                     totalItemSelect();
                     notifyItemChanged(position);

                 }
             });

         }

         @Override
         public int getItemCount() {
             return upperTowRowSateBookingList.size();
         }

         public class TwoRowUpperSeatBookingHolder extends RecyclerView.ViewHolder {
             @BindView(R.id.img_grid_sate)
             ImageView img_grid_sate;
             public TwoRowUpperSeatBookingHolder(@NonNull View itemView) {
                 super(itemView);
                 ButterKnife.bind(this,itemView);
             }
         }
     }

    void totalItemSelect() {
        if (counter_total == 0) {
            lin_card.setVisibility(View.GONE);
        } else {
            lin_card.setVisibility(View.VISIBLE);

            if (counter_total == 1) {
                txt_sate_book_count.setText(getResources().getString(R.string.seat) + "" + counter_total);
            } else {
                txt_sate_book_count.setText(getResources().getString(R.string.seat) + "" + counter_total);
            }
            total_plus = counter_total * Integer.parseInt(ticket_price);
            txt_total_price.setText(getResources().getString(R.string.rupee) + "" + total_plus);

        }
    }

    void totalPrice() {

        total_plus = 0;
        counter_total = 0;
        for (int i = 0; i < list_satebooking.size(); i++) {
            if (list_satebooking.get(i).getIs_selected()) {
                counter_total++;
            }
        }

        for (int i = 0; i < sateBookingList.size(); i++) {
            if (sateBookingList.get(i).getIs_selected()) {
                counter_total++;
            }
        }

        for (int i = 0; i < upperSateBookingList.size(); i++) {
            if (upperSateBookingList.get(i).getIs_selected()) {
                counter_total++;
            }
        }

        for (int i = 0; i < upperTowRowSateBookingList.size(); i++) {
            if (upperTowRowSateBookingList.get(i).getIs_selected()) {
                counter_total++;
            }
        }
    }

}
