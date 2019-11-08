package com.bootcamp.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUsers extends RecyclerView.Adapter<HolderUsers> {

    UsersFragment c;
    // Este array list crea una lista en el array lu cual define nuestros parametros en nuestra clase model
    ArrayList<ModelUsers> models;

    public AdapterUsers(UsersFragment c, ArrayList<ModelUsers> models) {
        this.c = c;
        this.models = models;
    }


    @NonNull
    @Override
    public HolderUsers onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_users, null);
        return new HolderUsers(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HolderUsers holder, int i) {

        holder.mT.setText(models.get(i).getNombre());
        holder.mD.setText(models.get(i).getDescripcion());
        holder.Imv.setImageResource(models.get(i).getImg());

        holder.setItemClick(new ItemClick() {
            @Override
            public void onItemClickListener(View v, int position) {

            }
        });


    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return models.size();
    }
}
