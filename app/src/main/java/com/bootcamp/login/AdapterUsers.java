package com.bootcamp.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
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
    ArrayList<String> Keys;

    public AdapterUsers(Context context, ArrayList<Users> users, ArrayList<String> keys) {
        this.context = context;
        Users = users;
        Keys = keys;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_users,null,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.mName.setText(Users.get(position).getName());
        holder.mTechnology.setText(Users.get(position).getTechnology());
        Picasso.get().load(Users.get(position).getImage()).into(holder.mImagenView);

        holder.setItemClickListener(new ItemClick() {
            @Override
            public void onItemClickListener(View v, int position) {
                String UserName = Users.get(position).getName();
                String Technology = Users.get(position).getTechnology();
                String KeyId = Keys.get(position);
                //BitmapDrawable bitmapDrawable = (BitmapDrawable)holder.mImagenView.getDrawable();

                //get our data with intent
                Intent intent = new Intent(context, UserProfile.class);
                intent.putExtra("Name",UserName);
                intent.putExtra("technologyp",Technology);
                intent.putExtra("Id",KeyId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Users.size();
    }
}