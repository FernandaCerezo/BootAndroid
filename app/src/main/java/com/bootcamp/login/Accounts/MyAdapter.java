package com.bootcamp.login.Accounts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolderCounts>
{
    ArrayList<Accounts> AccountsList;

    public MyAdapter(ArrayList<Accounts> AccountsList) {
        this.AccountsList = AccountsList;
    }

    @Override
    public ViewHolderCounts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,null,false);
        return new ViewHolderCounts(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCounts holder, int position) {
        holder.count_name.setText(AccountsList.get(position).getName());
        holder.count_description.setText(AccountsList.get(position).getDescription());
        holder.count_image.setImageResource(AccountsList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return AccountsList.size();
    }

    public class ViewHolderCounts extends RecyclerView.ViewHolder {
        TextView count_name, count_description;
        ImageView count_image;

        public ViewHolderCounts(@NonNull View itemView) {
            super(itemView);
            count_name = (TextView) itemView.findViewById(R.id.account_name);
            count_description = (TextView) itemView.findViewById(R.id.account_description);
            count_image = (ImageView) itemView.findViewById(R.id.account_image);
        }
    }
}
