package com.bootcamp.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.Users.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<Holder> {

    Context context;
    ArrayList<Users> Users;

    public AdapterUsers(Context context, ArrayList<Users> users) {
        this.context = context;
        Users = users;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_users,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.mName.setText(Users.get(position).getName());
        holder.mTechnology.setText(Users.get(position).getTechnology());
        Picasso.get().load(Users.get(position).getImage()).into(holder.mImagenView);
    }

    @Override
    public int getItemCount() {
        return Users.size();
    }
}
