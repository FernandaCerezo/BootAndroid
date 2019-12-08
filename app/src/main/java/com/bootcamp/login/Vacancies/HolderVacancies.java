package com.bootcamp.login.Vacancies;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.R;

public class HolderVacancies extends RecyclerView.ViewHolder {
    ImageView mImagenView;
    TextView mSeniority, mTechnology, mGrade;

    public HolderVacancies(@NonNull View itemView){
        super(itemView);
        this.mImagenView=itemView.findViewById(R.id.imageUserVacancy);
        this.mSeniority=itemView.findViewById(R.id.seniority);
        this.mTechnology=itemView.findViewById(R.id.nametech);
        this.mGrade=itemView.findViewById(R.id.grade_text);
    }

}
