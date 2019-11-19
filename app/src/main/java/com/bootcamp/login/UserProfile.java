package com.bootcamp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bootcamp.login.Users.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {
    TextView mStartDate, mAccount, mEndDate, mName, mRol, mTechnology, mEspTechnology, mGrade;
    String mTitle, UserName, Rol, Technology, EspTechnology, Id;
    String Accounts, StartDate, EndDate, Grade;

    private FirebaseDatabase mFirebasedatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ActionBar actionBar = getSupportActionBar();

        mName=findViewById(R.id.Textname);
        mRol=findViewById(R.id.txtRol);
        mGrade=findViewById(R.id.txtGrade);
        mTechnology=findViewById(R.id.txtTech);
        mEspTechnology=findViewById(R.id.txtEspecTech);

        mAccount=findViewById(R.id.txtAccountName);
        mStartDate=findViewById(R.id.txtStartDate);
        mEndDate=findViewById(R.id.txtEndDate);

        Intent intent=getIntent();
        mTitle=intent.getStringExtra("technologyp");
        UserName=intent.getStringExtra("Name");
        Id = intent.getStringExtra("Id");

       /* byte[] mByte = getIntent().getByteArrayExtra("Image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(mByte,0, mByte.length);*/

        actionBar.setTitle(mTitle);
        //mImageView.setImageBitmap(bitmap);
        mName.setText(UserName);
        mEspTechnology.setText(mTitle);
        databaseReference=FirebaseDatabase.getInstance().getReference("user");
        getUserData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UserProfile.this, Home.class));
    }

    public void getUserData() {
        databaseReference.child(Id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Rol = dataSnapshot.child("Rol").getValue().toString();
                Technology = dataSnapshot.child("technology").getValue().toString();
                StartDate = dataSnapshot.child("start").getValue().toString();
                EndDate = dataSnapshot.child("end").getValue().toString();
                Accounts = dataSnapshot.child("Account").getValue().toString();
                EspTechnology = dataSnapshot.child("technologyp").getValue().toString();
                Grade = dataSnapshot.child("Grade").getValue().toString();

                mStartDate.setText("Fecha de Inicio: "+StartDate);
                mEndDate.setText("Fecha de Terminación: " + EndDate);
                mRol.setText("Rol: " + Rol);
                mAccount.setText("Cuenta: " + Accounts);
                mTechnology.setText("Tipo de Tecnologia: " + Technology);
                mEspTechnology.setText("Tecnologia: " + EspTechnology);
                mGrade.setText("Grado: " + Grade);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
