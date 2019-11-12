package com.bootcamp.login;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {
    ImageView mImagenView;
    TextView mName, mTechnology;

    public Holder(@NonNull View itemView){
        super(itemView);
        this.mImagenView=itemView.findViewById(R.id.imageUser);
        this.mName=itemView.findViewById(R.id.Textname);
        this.mTechnology=itemView.findViewById(R.id.Textdescription);
    }
}

