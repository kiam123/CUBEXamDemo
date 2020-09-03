package com.example.cubexamdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tab2RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    ArrayList arrayList;

    public Tab2RecyclerViewAdapter(Context context) {
        mContext = context;
        arrayList = new ArrayList<Tab2DayItem>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomePageViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.tab2_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Tab2DayItem tab2DayItem = (Tab2DayItem) arrayList.get(position);
        ((HomePageViewHolder) holder).bindToSelf(tab2DayItem);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addItem(Tab2DayItem tab2DayItem){
        arrayList.add(tab2DayItem);
        notifyDataSetChanged();
    }

    class HomePageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtItemName;
        TextView txtDate;
        public HomePageViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int page = getLayoutPosition();

            Toast.makeText(mContext, "",Toast.LENGTH_SHORT).show();
        }

        public void bindToSelf(Tab2DayItem tab2DayItem) {
            txtItemName = (TextView) itemView.findViewById(R.id.itemName);
            txtDate = (TextView) itemView.findViewById(R.id.date);
            txtItemName.setText(tab2DayItem.getItemName());
            txtDate.setText(tab2DayItem.getDate());
        }
    }
}
