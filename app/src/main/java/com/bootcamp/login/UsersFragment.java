package com.bootcamp.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class UsersFragment extends Fragment {
    private RecyclerView mRecycleV;
    private String search = "";
    AdapterUsers mAdapterU;
    ArrayList<Users> users = new ArrayList<Users>();
    private DatabaseReference mFirebaseDB;
    ProgressDialog progress;
    Button button1, button2, button3,button4;
    String [] opc1 = {"Senior", "Mid", "Junior"};
    String [] opc2 = {"Android", ".Net", "PHP", "React-native", "IOS"};
    String [] opc3 = {"PO", "Scrum Master", "Desarrollador", "QA"};
    String [] opc4 = {"Disponibles", "Ocupados"};

    @Override
    public void onStart() {
        super.onStart();
        progress = ProgressDialog.show(getContext(), "Cargando",
                "Espere un momento", true);
        readUserData(search);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_listusers, container,false);
        mFirebaseDB=FirebaseDatabase.getInstance().getReference().child("user");
        final EditText ET = (EditText) root.findViewById(R.id.search_section);
        ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search = ET.getText().toString();
                readUserData(search);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });



        mRecycleV=root.findViewById(R.id.RecyclerV);
        mRecycleV.setLayoutManager(new LinearLayoutManager(getActivity()));

        button1 = root.findViewById(R.id.button1);
        button2 = root.findViewById(R.id.button2);
        button3 = root.findViewById(R.id.button3);
        button4 = root.findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(getContext());


                alertdialogbuilder.setTitle("Nivel");

                alertdialogbuilder.setItems(opc1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedText = Arrays.asList(opc1).get(which);

                        switch (selectedText) {
                            case "Senior":
                                mFirebaseDB.orderByChild("Grade").equalTo("Sr").addValueEventListener(new ValueEventListener() {
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
                                        //cerrar carga
                                        progress.dismiss();
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                break;

                            case "Mid":
                                mFirebaseDB.orderByChild("Grade").equalTo("Mid").addValueEventListener(new ValueEventListener() {
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
                                        //cerrar carga
                                        progress.dismiss();
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                break;

                            case "Junior":
                                mFirebaseDB.orderByChild("Grade").equalTo("Jr").addValueEventListener(new ValueEventListener() {
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
                                        //cerrar carga
                                        progress.dismiss();
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                break;
                        }
                    }
                });

                AlertDialog dialog = alertdialogbuilder.create();

                dialog.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(getContext());


                alertdialogbuilder.setTitle("Tecnologias");

                alertdialogbuilder.setItems(opc2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedText = Arrays.asList(opc2).get(which);

                        switch (selectedText) {
                            case "Android":
                                mFirebaseDB.orderByChild("technology").equalTo("Android").addValueEventListener(new ValueEventListener() {
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
                                        //cerrar carga
                                        progress.dismiss();
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                break;
                            case ".Net":
                                mFirebaseDB.orderByChild("technology").equalTo(".NET").addValueEventListener(new ValueEventListener() {
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
                                        //cerrar carga
                                        progress.dismiss();
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                break;
                            case "PHP":
                                mFirebaseDB.orderByChild("technology").equalTo("PHP").addValueEventListener(new ValueEventListener() {
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
                                        //cerrar carga
                                        progress.dismiss();
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                break;
                            case "IOS":
                                mFirebaseDB.orderByChild("technology").equalTo("IOS").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        users.clear();
                                        ArrayList<String> keys = new ArrayList<>();

                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                            keys.add(dataSnapshot1.getKey());
                                            Users u = dataSnapshot1.getValue(Users.class);
                                            users.add(u);
                                        }
                                        mAdapterU = new AdapterUsers(getContext(), users, keys);
                                        mRecycleV.setAdapter(mAdapterU);
                                        //cerrar carga
                                        progress.dismiss();
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                    break;
                            case "React-native":
                                mFirebaseDB.orderByChild("technology").equalTo("React").addValueEventListener(new ValueEventListener() {
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
                                        //cerrar carga
                                        progress.dismiss();
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                break;
                        }
                    }
                });

                AlertDialog dialog = alertdialogbuilder.create();

                dialog.show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(getContext());


                alertdialogbuilder.setTitle("Rol");

                alertdialogbuilder.setItems(opc3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedText = Arrays.asList(opc3).get(which);

                        switch (selectedText) {
                            case "PO":
                                mFirebaseDB.orderByChild("Rol").equalTo("PO").addValueEventListener(new ValueEventListener() {
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
                                        //cerrar carga
                                        progress.dismiss();
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                break;
                            case "Scrum Master":
                                mFirebaseDB.orderByChild("Rol").equalTo("Scrum Master").addValueEventListener(new ValueEventListener() {
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
                                        //cerrar carga
                                        progress.dismiss();
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                break;
                            case "Desarrollador":
                                mFirebaseDB.orderByChild("Rol").equalTo("Developer").addValueEventListener(new ValueEventListener() {
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
                                        //cerrar carga
                                        progress.dismiss();
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                break;
                            case "QA":
                                mFirebaseDB.orderByChild("Rol").equalTo("QA").addValueEventListener(new ValueEventListener() {
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
                                        //cerrar carga
                                        progress.dismiss();
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                break;
                        }

                    }
                });

                AlertDialog dialog = alertdialogbuilder.create();

                dialog.show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(getContext());

                alertdialogbuilder.setTitle("Estatus");

                alertdialogbuilder.setItems(opc4, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedText = Arrays.asList(opc4).get(which);
                        String Status = "Ocupado";

                        if(selectedText.equals("Disponibles"))
                        {
                            Status = "Disponible";
                        }


                        final String finalStatus = Status;

                        mFirebaseDB.orderByChild("end").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                users.clear();
                                ArrayList<String> keys = new ArrayList<>();

                                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                                {
                                    Users u = dataSnapshot1.getValue(Users.class);
                                    if (GetStatus(dataSnapshot1.child("end").getValue().toString()).equals(finalStatus))
                                    {
                                        keys.add(dataSnapshot1.getKey());
                                        users.add(u);
                                    }
                                }
                                mAdapterU=new AdapterUsers(getContext(),users, keys);
                                mRecycleV.setAdapter(mAdapterU);
                                //cerrar carga
                                progress.dismiss();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });

                AlertDialog dialog = alertdialogbuilder.create();

                dialog.show();
            }
        });
        return root;
    }

    public void readUserData(final String search) {
        mFirebaseDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                ArrayList<String> keys = new ArrayList<>();

                if (search.equals(""))
                {
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        keys.add(dataSnapshot1.getKey());
                        Users u = dataSnapshot1.getValue(Users.class);
                        users.add(u);
                    }
                    mAdapterU=new AdapterUsers(getContext(),users, keys);
                    mRecycleV.setAdapter(mAdapterU);
                    //cerrar carga
                    progress.dismiss();
                    return;
                }

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Users u = dataSnapshot1.getValue(Users.class);
                    if (u.name.toLowerCase().contains(search.toLowerCase()))
                    {
                        keys.add(dataSnapshot1.getKey());
                        users.add(u);
                    }
                }
                mAdapterU=new AdapterUsers(getContext(),users, keys);
                mRecycleV.setAdapter(mAdapterU);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        ((Home) getActivity()).setActionBarTitle("Usuarios");
    }

    public String GetStatus(String Date) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int Aday = Integer.parseInt(Date.substring(0,2));
        int Amonth = Integer.parseInt(Date.substring(3,5));
        int Ayear = Integer.parseInt(Date.substring(6,10));


        if (year > Ayear)
        {
            return "Disponible";
        }

        if (month > Amonth)
        {
            return "Disponible";
        }

        if (day > Aday)
        {
            return "Disponible";
        }
        return "Ocupado";
    }
}