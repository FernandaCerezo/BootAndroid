package com.bootcamp.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.Users.Users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter_LifecycleAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class UsersFragment extends Fragment {

   private RecyclerView mRecycleV;
   AdapterUsers mAdapterU;
   ArrayList<Users> users;
   private DatabaseReference mFirebaseDB;

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users = new ArrayList<Users>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Users u = dataSnapshot1.getValue(Users.class);
                    users.add(u);
                }
                mAdapterU=new AdapterUsers(getContext(),users);
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
        View root = inflater.inflate(R.layout.fragment_listusers, container,false);

        mFirebaseDB=FirebaseDatabase.getInstance().getReference().child("user");

        mRecycleV=root.findViewById(R.id.RecyclerV);
        mRecycleV.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }

}