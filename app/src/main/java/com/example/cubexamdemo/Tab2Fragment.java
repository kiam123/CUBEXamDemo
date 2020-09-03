package com.example.cubexamdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Tab2Fragment extends Fragment {
    RecyclerView recyclerView;
    Tab2RecyclerViewAdapter tab2RecyclerViewAdapter;
    LinearLayoutManager linearLayoutManager;
    public Tab2Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        setRecyclerView();
        addDay();
    }

    private void initView() {
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
    }

    private void setRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        tab2RecyclerViewAdapter = new Tab2RecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(tab2RecyclerViewAdapter);
    }

    public void addDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(cal.getTime())+" "+getEnglishDay(cal.get(Calendar.DAY_OF_WEEK));
        tab2RecyclerViewAdapter.addItem(new Tab2DayItem("item"+1, date));

        for(int i=2;i < 7;i++) {
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-1);
            date = sdf.format(cal.getTime())+" "+getEnglishDay(cal.get(Calendar.DAY_OF_WEEK));
            tab2RecyclerViewAdapter.addItem(new Tab2DayItem("item"+i, date));
        }
    }

    public String getEnglishDay(int day) {
        String result = null;
        switch (day) {
            case 1:
                result = "Sunday";
                break;
            case 2:
                result = "Monday";
                break;
            case 3:
                result = "Tuesday";
                break;
            case 4:
                result = "Wednesday";
                break;
            case 5:
                result = "Thursday";
                break;
            case 6:
                result = "Friday";
                break;
            case 7:
                result = "Saturday";
                break;
        }
        return result;
    }
}
