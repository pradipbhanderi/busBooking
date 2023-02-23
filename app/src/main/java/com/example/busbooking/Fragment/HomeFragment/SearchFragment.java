package com.example.busbooking.Fragment.HomeFragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbooking.Fragment.BaseFragment;
import com.example.busbooking.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragment extends BaseFragment {

    @BindView(R.id.EditSearch)
    EditText EditSearch;
    @BindView(R.id.rcy_search)
    RecyclerView rcy_search;

    List<String> cityList;
    SearchAdapter searchAdapter;

    int type;

    public SearchFragment(int i) {
        super();
        this.type = i;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_search, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {

        cityList = new ArrayList<>();
        cityList.add("Ahmedabad");
        cityList.add("Surat");
        cityList.add("Vadodara");
        cityList.add("Rajkot");
        cityList.add("Bhavnagar");
        cityList.add("Jamnagar");
        cityList.add("Gandhinagar");
        cityList.add("Gandhidham");
        cityList.add("Anand");
        cityList.add("Navsari");
        cityList.add("Morbi");
        cityList.add("Nadiad");
        cityList.add("Surendranagar");
        cityList.add("Bharuch");
        cityList.add("Mehsana");
        cityList.add("Bhuj");
        cityList.add("Porbandar");
        cityList.add("Palanpur");
        cityList.add("Valsad");
        cityList.add("Vapi");
        cityList.add("Gondal");
        cityList.add("Veraval");
        cityList.add("Godhra");
        cityList.add("Patan");
        cityList.add("Kalol");
        cityList.add("Dahod");
        cityList.add("Botad");
        cityList.add("Amreli");
        cityList.add("Deesa");
        cityList.add("Jetpur");
        cityList.add("Jetpur 100");
        cityList.add("ooo 000");
        cityList.add("77 jkk");
        cityList.add("xds 11");

        rcy_search.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchAdapter = new SearchAdapter();
        rcy_search.setAdapter(searchAdapter);

        EditSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                searchAdapter.filter(text.toString());
                if (text.toString().isEmpty()) {
                    rcy_search.setVisibility(View.GONE);
                } else {
                    rcy_search.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @OnClick(R.id.img_back)
    @Override
    public void onBack() {
        getFragmentManager().popBackStack();
    }

    public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {
        List<String> filter_list;

        public SearchAdapter() {
            filter_list=new ArrayList<>();
            filter_list.addAll(cityList);
        }

        @NonNull
        @Override
        public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.row_search, parent, false);
            return new SearchHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
            String cityModel = cityList.get(position);

            holder.txtCityNamel.setText(cityModel);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (type==1)
                    {
                        Fragment fragment =getFragmentManager().findFragmentByTag("HomeFragment");

                        if (fragment!=null)
                        {
                            HomeFragment homeFragment = (HomeFragment) fragment;
                            homeFragment.setSelectedSourecLocation(cityModel);
                            onBack();
                        }
                    }
                    else
                    {
                        Fragment fragment =getFragmentManager().findFragmentByTag("HomeFragment");

                        if (fragment!=null)
                        {
                            HomeFragment homeFragment = (HomeFragment) fragment;
                            homeFragment.SelectedDestinationLocation(cityModel);
                            onBack();
                        }
                    }


                }
            });
        }

        void filter (String queary)
        {
            queary = queary.toLowerCase().toString();
            cityList.clear();
            if (queary.isEmpty())
            {
                cityList.addAll(filter_list);
            } else {
                for (String cityName : filter_list) {
                    if (cityName != null && cityName.toLowerCase().contains(queary)) {
                        cityList.add(cityName);
                    }
                }
            }
            notifyDataSetChanged();
        }
        @Override
        public int getItemCount() {
            return cityList.size();
        }

        public class SearchHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.txtCityName)
            TextView txtCityNamel;

            public SearchHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

}
