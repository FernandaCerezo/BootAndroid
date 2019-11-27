package com.bootcamp.login.Accounts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.R;

import java.util.ArrayList;

public class AdapterAccountProfile extends RecyclerView.Adapter<AdapterAccountProfile.ViewHolder> {

    ArrayList<String> users;

    public AdapterAccountProfile(ArrayList<String> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_account_profile,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setName((position+1) + ". " + users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_user = (TextView) itemView.findViewById(R.id.txtUser_name);

        }

        public void setName(String Name) {
            textView_user.setText(Name);
        }
    }
}
