package com.bootcamp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AccountProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_profile);

        //Variables globales
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
    }
}
