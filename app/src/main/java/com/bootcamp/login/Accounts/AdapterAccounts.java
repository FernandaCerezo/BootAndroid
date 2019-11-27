package com.bootcamp.login.Accounts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.AccountProfile;
import com.bootcamp.login.ItemClick;
import com.bootcamp.login.R;

import java.util.ArrayList;

public class AdapterAccounts extends RecyclerView.Adapter<AdapterAccounts.ViewHolderAccounts>
{
    private ArrayList<Accounts> AccountsList;
    private Context context;

    public AdapterAccounts(ArrayList<Accounts> AccountsList, Context context) {
        this.AccountsList = AccountsList;
        this.context = context;
    }

    @Override
    public ViewHolderAccounts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,null,false);
        return new ViewHolderAccounts(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAccounts holder, int position) {
        holder.account_name.setText(AccountsList.get(position).getName());
        holder.account_description.setText(AccountsList.get(position).getDescription());

        holder.setItemClickListener(new ItemClick() {
            @Override
            public void onItemClickListener(View v, int position) {
                String AccountName = AccountsList.get(position).getName();
                String AccountDescription = AccountsList.get(position).getDescription();
                String AccountTechnology = AccountsList.get(position).getTechnology();

                //Llamar activity del perfil
                Intent intent = new Intent(context, AccountProfile.class);
                intent.putExtra("Name", AccountName);
                intent.putExtra("Description",AccountDescription);
                intent.putExtra("Technology",AccountTechnology);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return AccountsList.size();
    }

    public class ViewHolderAccounts extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView account_name, account_description;
        ItemClick mItemClickListener;

        public ViewHolderAccounts(@NonNull View itemView) {
            super(itemView);
            account_name = itemView.findViewById(R.id.account_name);
            account_description = itemView.findViewById(R.id.account_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.mItemClickListener.onItemClickListener(v,getLayoutPosition());
        }

        public void setItemClickListener(ItemClick ic) {
            this.mItemClickListener = ic;
        }
    }
}
