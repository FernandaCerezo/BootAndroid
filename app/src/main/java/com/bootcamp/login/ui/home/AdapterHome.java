package com.bootcamp.login.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.R;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolderHome> {

    private ArrayList<String> Technologies;
    private ArrayList<Integer> Tech_Count;
    private int total;

    public AdapterHome(ArrayList<String> Technologies, ArrayList<Integer> Tech_Count, int total) {
        this.Technologies = Technologies;
        this.Tech_Count = Tech_Count;
        this.total = total;
    }

    @NonNull
    @Override
    public ViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_technologies,null,false);
        return new ViewHolderHome(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHome holder, int position) {
        holder.Technology.setText(Technologies.get(position));
        String Scount = Integer.toString(Tech_Count.get(position));
        double d = Double.parseDouble(Scount) / total * 100;
        int percent = (int) d;
        String Spercent = percent + "%";
        holder.Percent.setText(Spercent);
    }

    @Override
    public int getItemCount() {
        return Technologies.size();
    }

    public class ViewHolderHome extends RecyclerView.ViewHolder {
        TextView Technology;
        TextView Percent;

        public ViewHolderHome(@NonNull View itemView) {
            super(itemView);
            Technology = (TextView) itemView.findViewById(R.id.tech_name);
            Percent = (TextView) itemView.findViewById(R.id.tech_percent);
        }
    }
}