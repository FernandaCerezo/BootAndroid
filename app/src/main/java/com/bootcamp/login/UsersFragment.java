package com.bootcamp.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.Accounts.Accounts;
import com.bootcamp.login.Accounts.MyAdapter;
import com.bootcamp.login.Accounts.SAccounts;
import com.bootcamp.login.Users.Users;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsersFragment extends Fragment {

    private RecyclerView mRecycleV;
    AdapterUsers mAdapterU;
    ArrayList<Users> users = new ArrayList<Users>();
    private DatabaseReference mFirebaseDB;

    @Override
    public void onStart() {
        int id = Enum.ANDROID;

        super.onStart();
        readUserData();

    }

    public void readUserData() {
        mFirebaseDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                ArrayList<String> keys = new ArrayList<>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    keys.add(dataSnapshot1.getKey());
                    Users u = dataSnapshot1.getValue(Users.class);
                    users.add(u);
                }
                mAdapterU=new AdapterUsers(getContext(),users, keys);
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