package com.bootcamp.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class UsersFragment extends Fragment {

    RecyclerView mRecyclerView;
    AdapterUsers mAdapter;
    //private List<Users> mlist = new ArrayList<Users>();
    Button button1, button2, button3,button4;
    TextView textview;
    String [] opc1 = {"Senior", "Mid", "Junior"};
    String [] opc2 = {"Android", ".Net", "PHP", "React-native"};
    String [] opc3 = {"PO", "Scrum Master", "Desarrollador", "QA"};
    String [] opc4 = {"Disponibles", "Ocupados"};


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ArrayList<UsersFragment> model;
    RecyclerView recyclermodel;


    public UsersFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vist=inflater.inflate(R.layout.fragment_listusers, container, false);

        mRecyclerView = vist.findViewById(R.id.RecyclerV);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new AdapterUsers(this, getMylist());
        mRecyclerView.setAdapter(mAdapter);


        button1 = vist.findViewById(R.id.button1);
        button2 = vist.findViewById(R.id.button2);
        button3 = vist.findViewById(R.id.button3);
        button4 = vist.findViewById(R.id.button4);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(getContext());


                alertdialogbuilder.setTitle("Nivel");

                alertdialogbuilder.setItems(opc1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedText = Arrays.asList(opc1).get(which);

                        textview.setText(selectedText);

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

                        textview.setText(selectedText);

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

                        textview.setText(selectedText);

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

                        textview.setText(selectedText);

                    }
                });

                AlertDialog dialog = alertdialogbuilder.create();

                dialog.show();
            }
        });


        model = new ArrayList<>();
        recyclermodel = (RecyclerView) vist.findViewById(R.id.RecyclerV);
        recyclermodel.setLayoutManager(new LinearLayoutManager(getContext()));

        listuser();




        // Inflate the layout for this fragment
        return vist;
    }



    private ArrayList<ModelUsers> getMylist(){

        ArrayList<ModelUsers> models = new ArrayList<>();

        ModelUsers n = new ModelUsers();
        n.setNombre("Juan Perez");
        n.setDescripcion("Usuario");
        n.setImg(R.drawable.users);
        models.add(n);

        n = new ModelUsers();
        n.setNombre("Ulises flores");
        n.setDescripcion("Usuario");
        n.setImg(R.drawable.users);
        models.add(n);

        n = new ModelUsers();
        n.setNombre("Saian Ford");
        n.setDescripcion("Usuario");
        n.setImg(R.drawable.users);
        models.add(n);

        n = new ModelUsers();
        n.setNombre("Cesar Chavez");
        n.setDescripcion("Usuario");
        n.setImg(R.drawable.users);
        models.add(n);

        n = new ModelUsers();
        n.setNombre("Saul Soto");
        n.setDescripcion("Usuario");
        n.setImg(R.drawable.users);
        models.add(n);


        return models;
    }

    private void listuser() {
        // listusers.add(new Listusers("jorge","",R.dra wable.user));
    }
}