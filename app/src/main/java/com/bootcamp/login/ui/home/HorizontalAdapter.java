package com.bootcamp.login.ui.home;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.R;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder>{

    private String[] items;
    //private String[] prices;
    private Drawable[] drawables;

    public HorizontalAdapter(String[] items, Drawable[] drawables) {
        this.items = items;
        //this.prices = prices;
        this.drawables = drawables;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.template_rvtech,parent,false);

        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {

        //holder.techphoto.setBackground(drawables[position]);
        holder.technumber.setText(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder{

        //View techphoto;
        TextView technumber;
        TextView techname;

        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);

            //techphoto=itemView.findViewById(R.id.tech_photo);
            techname=itemView.findViewById(R.id.tech_name);
            technumber=itemView.findViewById(R.id.tech_number);

        }
    }
}
