package com.bootcamp.login.Technology;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.R;

import java.util.ArrayList;

public class AdapterTechnology extends RecyclerView.Adapter<HolderTechnology> {
    Context context;
    ArrayList<Technology> technology;
    ArrayList<String> Keys;

    public AdapterTechnology(Context context, ArrayList<Technology> technologies, ArrayList<String> keys) {
        this.context = context;
        technology = technologies;
        Keys = keys;
    }

    @NonNull
    @Override
    public HolderTechnology onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_technology,null,false);
        return new HolderTechnology(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderTechnology holder, int position) {
        holder.mName.setText(technology.get(position).getName());
        holder.mTechnology.setText(technology.get(position).getTechnologyp());
        holder.mImagenView.setImageResource(technology.get(position).getImage());


        if (holder.mTechnology.getText().equals("FrontEnd"))
        {
            holder.mImagenView.setImageResource(R.drawable.ic_frontend);
        }
        else if (holder.mTechnology.getText().equals("BackEnd")){
            holder.mImagenView.setImageResource(R.drawable.ic_backend);
        }
        else if (holder.mTechnology.getText().equals("Mobile")){
            holder.mImagenView.setImageResource(R.drawable.ic_movil);
        }

    }

    @Override
    public int getItemCount() {
        return technology.size();
    }
}
