package com.bootcamp.login;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderUsers extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView Imv;
    TextView mT;
    TextView mD;
    ItemClick itemClick;
    HolderUsers(@NonNull View itemView) {
        super(itemView);
        this.Imv = itemView.findViewById(R.id.imageUser);
        this.mT= itemView.findViewById(R.id.TextV);
        this.mD = itemView.findViewById(R.id.TextV2);

        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        this.itemClick.onItemClickListener(v, getLayoutPosition());

    }

    public void setItemClick(ItemClick ic){

        this.itemClick = ic;
    }
}
