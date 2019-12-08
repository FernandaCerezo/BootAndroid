package com.bootcamp.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.Vacancies.AdapterVacancies;
import com.bootcamp.login.Vacancies.VacanciesArray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VacanciesFragment extends Fragment {

    private RecyclerView mRecycleV;
    AdapterVacancies mAdapterU;
    ArrayList<VacanciesArray> vacanciesArrays = new ArrayList<VacanciesArray>();
    private DatabaseReference mFirebaseDB;
    Button button1, button2, button3;
    ImageView mImageView;


    @Override
    public void onStart() {
        super.onStart();
        readUserData();

    }

    public void readUserData() {
        mFirebaseDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vacanciesArrays.clear();
                ArrayList<String> keys = new ArrayList<>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    keys.add(dataSnapshot1.getKey());
                    VacanciesArray u = dataSnapshot1.getValue(VacanciesArray.class);
                    vacanciesArrays.add(u);
                }
                mAdapterU=new AdapterVacancies(getContext(),vacanciesArrays, keys);
                mRecycleV.setAdapter(mAdapterU);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vacancies, container,false);

        mFirebaseDB= FirebaseDatabase.getInstance().getReference().child("vacancies");


        mRecycleV=root.findViewById(R.id.RecyclerVacancies);
        mRecycleV.setLayoutManager(new LinearLayoutManager(getActivity()));

        button1 = root.findViewById(R.id.btnFrontend);
        button2 = root.findViewById(R.id.btnBackend);
        button3 = root.findViewById(R.id.btnmovil);
        mImageView=root.findViewById(R.id.imageUserVacancy);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDB.orderByChild("technologyp").equalTo("FRONTEND").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        vacanciesArrays.clear();
                        ArrayList<String> keys = new ArrayList<>();
                        //mImageView.setImageResource(R.drawable.ic_frontend);
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            keys.add(dataSnapshot1.getKey());
                            VacanciesArray u = dataSnapshot1.getValue(VacanciesArray.class);
                            vacanciesArrays.add(u);
                        }
                        mAdapterU=new AdapterVacancies(getContext(),vacanciesArrays, keys);
                        mRecycleV.setAdapter(mAdapterU);
                        return;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDB.orderByChild("technologyp").equalTo("BACKEND").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        vacanciesArrays.clear();
                        ArrayList<String> keys = new ArrayList<>();
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            keys.add(dataSnapshot1.getKey());
                            VacanciesArray u = dataSnapshot1.getValue(VacanciesArray.class);
                            vacanciesArrays.add(u);
                        }
                        mAdapterU=new AdapterVacancies(getContext(),vacanciesArrays, keys);
                        mRecycleV.setAdapter(mAdapterU);
                        return;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDB.orderByChild("technologyp").equalTo("MOBILE").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        vacanciesArrays.clear();
                        ArrayList<String> keys = new ArrayList<>();
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            keys.add(dataSnapshot1.getKey());
                            VacanciesArray u = dataSnapshot1.getValue(VacanciesArray.class);
                            vacanciesArrays.add(u);
                        }
                        mAdapterU=new AdapterVacancies(getContext(),vacanciesArrays, keys);
                        mRecycleV.setAdapter(mAdapterU);
                        return;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        return root;
    }
}
