package com.bootcamp.login.Vacancies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.R;

import java.util.ArrayList;

public class AdapterVacancies extends RecyclerView.Adapter<HolderVacancies> {

    Context context;
    ArrayList<VacanciesArray> Vacancies;
    ArrayList<String> Keys;

    public AdapterVacancies(Context context, ArrayList<VacanciesArray> vacancies, ArrayList<String> keys) {
        this.context = context;
        Vacancies = vacancies;
        Keys = keys;
    }

    @NonNull
    @Override
    public HolderVacancies onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_vacancies,null,false);
        return new HolderVacancies(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderVacancies holder, int position) {
        holder.mSeniority.setText(Vacancies.get(position).getName());
        holder.mTechnology.setText(Vacancies.get(position).getTechnology());
        holder.mGrade.setText(Vacancies.get(position).getTechnologyp());
        holder.mImagenView.setImageResource(Vacancies.get(position).getImage());

        if (holder.mGrade.getText().equals("FRONTEND"))
        {
            holder.mImagenView.setImageResource(R.drawable.ic_frontend);
        }
        else if (holder.mGrade.getText().equals("BACKEND")){
            holder.mImagenView.setImageResource(R.drawable.ic_backend);
        }
        else if (holder.mGrade.getText().equals("MOBILE")){
            holder.mImagenView.setImageResource(R.drawable.ic_movil);
        }

    }

    @Override
    public int getItemCount() {
        return Vacancies.size();
    }

}
