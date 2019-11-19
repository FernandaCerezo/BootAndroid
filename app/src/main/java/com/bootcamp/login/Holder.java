package com.bootcamp.login;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView mImagenView;
    TextView mName, mTechnology, mRol, mTechnologyP, mAccount, mStart, mEnd, mGrade;
    ItemClick mItemClickListener;

    public Holder(@NonNull View itemView){
        super(itemView);
        this.mImagenView=itemView.findViewById(R.id.imageUser);
        this.mName=itemView.findViewById(R.id.Textname);
        this.mTechnology=itemView.findViewById(R.id.txtEspecTech);
        this.mTechnologyP=itemView.findViewById(R.id.txtTech);
        this.mAccount=itemView.findViewById(R.id.txtAccountName);
        this.mStart=itemView.findViewById(R.id.txtStartDate);
        this.mEnd=itemView.findViewById(R.id.txtEndDate);
        this.mGrade=itemView.findViewById(R.id.txtGrade);

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

