package com.bootcamp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bootcamp.login.Accounts.AdapterAccountProfile;
import com.bootcamp.login.Users.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class AccountProfile extends AppCompatActivity {

    //Variables globales
    ArrayList<String> User_List;
    RecyclerView ProfileRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_profile);

        //Variables
        User_List = new ArrayList<String>();
        ProfileRecycler = findViewById(R.id.Users_In_Account);
        ProfileRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        String mName;
        String mDescription;
        String mTechnology;
        TextView TV_Name = (TextView) findViewById(R.id.txtName);
        TextView TV_Description = (TextView) findViewById(R.id.txtDescription);
        TextView TV_Technology = (TextView) findViewById(R.id.txtTechnology);

        //Se recuperan los datos recibidos
        Intent intent=getIntent();
        mName = intent.getStringExtra("Name");
        mDescription=intent.getStringExtra("Description");
        mTechnology = intent.getStringExtra("Technology");

        //Se muestra la informacion en pantalla
        TV_Name.setText(mName);
        TV_Description.setText(mDescription);
        TV_Technology.setText(mTechnology);

        //Consulta los usuarios de la cuenta
        listUsers(mName);
    }

    //Metodo para consultar datos
    public void listUsers(final String mName) {

        //Referenciar el hijo "usuarios"
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference.orderByChild("Account").equalTo(mName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User_List.clear();
                Users user;

                //Por cada objeto
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    user = dataSnapshot1.getValue(Users.class);
                    User_List.add(user.getName());
                }
                AdapterAccountProfile adapter = new AdapterAccountProfile(User_List);
                ProfileRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}
