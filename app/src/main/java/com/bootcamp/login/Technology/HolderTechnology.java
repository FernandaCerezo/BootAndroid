package com.bootcamp.login.Technology;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.R;

public class HolderTechnology extends RecyclerView.ViewHolder {
    ImageView mImagenView;
    TextView mName, mTechnology;

    public HolderTechnology(@NonNull View itemView){
        super(itemView);
        this.mImagenView=itemView.findViewById(R.id.technology_img);
        this.mName=itemView.findViewById(R.id.technology_name);
        this.mTechnology=itemView.findViewById(R.id.technology_name1);
    }
}
