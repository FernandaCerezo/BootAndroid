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
import com.bootcamp.login.Accounts.AdapterAccounts;
import com.bootcamp.login.Accounts.SAccounts;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class AccountsFragment extends Fragment {

    //Variables globales
    private DatabaseReference databaseReference;
    private ArrayList<Accounts> AccountsList;
    private ArrayList<String> FilterList;
    private RecyclerView AccountsRecycler;
    private String search = "";
    AdapterAccounts adapter;

    //Constructor
    public AccountsFragment() { }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Inicializar el RecyclerView
        View viewAccounts;
        viewAccounts = inflater.inflate(R.layout.fragment_accounts, container, false);
        AccountsList = new ArrayList<Accounts>();
        AccountsRecycler = viewAccounts.findViewById(R.id.Accounts_RecyclerView);
        AccountsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        //Crear barra de busqueda y metodo por cada cambio
        final EditText ET = (EditText) viewAccounts.findViewById(R.id.search_section);
        ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search = ET.getText().toString();
                listData(search);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        //Inicia la base de datos y consulta
        initializeFirebase();
        listData(search);
        return viewAccounts;
    }

    //Metodo para consultar datos
    private void listData(final String search) {
        databaseReference.child("Accounts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Limpieza
                AccountsList.clear();
                SAccounts account;
                //Si esta vacio
                if (search.equals("")) {

                    //Por cada objeto
                    for (DataSnapshot objSnapShop :dataSnapshot.getChildren()) {
                        account = objSnapShop.getValue(SAccounts.class);
                        AccountsList.add(new Accounts(Objects.requireNonNull(account).getName(),
                                account.getDescription(),account.getTechnology(),R.drawable.arkus));
                    }
                    SetCustomAdapter();
                    return;
                }

                //Si no esta vacio
                for (DataSnapshot objSnapShop :dataSnapshot.getChildren()) {
                    account = objSnapShop.getValue(SAccounts.class);
                    if (Objects.requireNonNull(account).getName().toLowerCase().contains(search.toLowerCase())) {
                        AccountsList.add(new Accounts(Objects.requireNonNull(account).getName(),
                                account.getDescription(),account.getTechnology(),R.drawable.arkus));
                    }
                }
                SetCustomAdapter();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    //Conexión a la base de datos
    private void initializeFirebase() {
        FirebaseDatabase firebaseDatabase;
        FirebaseApp.initializeApp(Objects.requireNonNull(getContext()));
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    //Establecer adaptador
    private void SetCustomAdapter() {
        adapter = new AdapterAccounts(AccountsList, getContext());
        AccountsRecycler.setAdapter(adapter);
    }
}