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
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AccountsFragment extends Fragment {

    //Variables globales
    private ArrayList<Accounts> AccountsList;
    private RecyclerView AccountsRecycler;
    String search = "";
    private View v;

    //Variables de Firebase
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    //Constructor
    public AccountsFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inicializar el RecyclerView
        v = inflater.inflate(R.layout.fragment_accounts, container, false);
        AccountsList = new ArrayList<Accounts>();
        AccountsRecycler = v.findViewById(R.id.countRecyclerView);
        AccountsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        //Crear barra de busqueda y metodo por cada cambio
        final EditText ET = (EditText) v.findViewById(R.id.search_section);
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
        return v;
    }

    //Metodo para consultar datos
    private void listData(final String search)
    {
        databaseReference.child("Accounts").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                //Limpieza
                AccountsList.clear();
                SAccounts account;

                //Si esta vacio
                if (search.equals(""))
                {
                    //Por cada objeto
                    for (DataSnapshot objSnapShop :dataSnapshot.getChildren())
                    {
                        account = objSnapShop.getValue(SAccounts.class);
                        AccountsList.add(new Accounts(account.getName(),account.getDescription(),R.drawable.arkus));
                    }

                    //Adaptador para desplegar datos
                    MyAdapter adapter = new MyAdapter(AccountsList);
                    AccountsRecycler.setAdapter(adapter);
                    return;
                }

                //Si no esta vacio
                for (DataSnapshot objSnapShop :dataSnapshot.getChildren())
                {
                    account = objSnapShop.getValue(SAccounts.class);
                    if (account.getName().toLowerCase().contains(search.toLowerCase()))
                    {
                        AccountsList.add(new Accounts(account.getName(),account.getDescription(),R.drawable.arkus));
                    }
                }

                //Adaptador para desplegar datos
                MyAdapter adapter = new MyAdapter(AccountsList);
                AccountsRecycler.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    //Conexión a la base de datos
    private void initializeFirebase()
    {
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    @Override
    public void onResume(){
        super.onResume();
        ((Home) getActivity()).setActionBarTitle("Cuentas");
    }
}