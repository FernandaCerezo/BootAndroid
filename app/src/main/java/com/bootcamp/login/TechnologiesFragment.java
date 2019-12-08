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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.Technology.AdapterTechnology;
import com.bootcamp.login.Technology.Technology;
import com.bootcamp.login.Vacancies.AdapterVacancies;
import com.bootcamp.login.Vacancies.VacanciesArray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TechnologiesFragment extends Fragment {

    private RecyclerView mRecycleV;
    AdapterTechnology mAdapterT;
    ArrayList<Technology> techArray = new ArrayList<Technology>();
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
                techArray.clear();
                ArrayList<String> keys = new ArrayList<>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    keys.add(dataSnapshot1.getKey());
                    Technology u = dataSnapshot1.getValue(Technology.class);
                    techArray.add(u);
                }
                mAdapterT=new AdapterTechnology(getContext(),techArray, keys);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                mRecycleV.setAdapter(mAdapterT);
                mRecycleV.setLayoutManager(gridLayoutManager);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_technologies, container,false);

        mFirebaseDB= FirebaseDatabase.getInstance().getReference().child("technology");


        mRecycleV=root.findViewById(R.id.RecyclerTech);
        mRecycleV.setLayoutManager(new LinearLayoutManager(getActivity()));

        button1 = root.findViewById(R.id.btnFrontendT);
        button2 = root.findViewById(R.id.btnBackendT);
        button3 = root.findViewById(R.id.btnmovilT);
        mImageView=root.findViewById(R.id.technology_img);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDB.orderByChild("technologyp").equalTo("FrontEnd").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        techArray.clear();
                        ArrayList<String> keys = new ArrayList<>();
                        //mImageView.setImageResource(R.drawable.ic_frontend);
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            keys.add(dataSnapshot1.getKey());
                            Technology u = dataSnapshot1.getValue(Technology.class);
                            techArray.add(u);
                        }
                        mAdapterT=new AdapterTechnology(getContext(),techArray, keys);
                        mRecycleV.setAdapter(mAdapterT);
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
                mFirebaseDB.orderByChild("technologyp").equalTo("BackEnd").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        techArray.clear();
                        ArrayList<String> keys = new ArrayList<>();
                        //mImageView.setImageResource(R.drawable.ic_frontend);
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            keys.add(dataSnapshot1.getKey());
                            Technology u = dataSnapshot1.getValue(Technology.class);
                            techArray.add(u);
                        }
                        mAdapterT=new AdapterTechnology(getContext(),techArray, keys);
                        mRecycleV.setAdapter(mAdapterT);
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
                mFirebaseDB.orderByChild("technologyp").equalTo("Mobile").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        techArray.clear();
                        ArrayList<String> keys = new ArrayList<>();
                        //mImageView.setImageResource(R.drawable.ic_frontend);
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            keys.add(dataSnapshot1.getKey());
                            Technology u = dataSnapshot1.getValue(Technology.class);
                            techArray.add(u);
                        }
                        mAdapterT=new AdapterTechnology(getContext(),techArray, keys);
                        mRecycleV.setLayoutManager(new GridLayoutManager(getContext(),2));
                        mRecycleV.setAdapter(mAdapterT);
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