package com.bootcamp.login.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.login.Accounts.Accounts;
import com.bootcamp.login.Home;
import com.bootcamp.login.MainActivity;
import com.bootcamp.login.R;
import com.bootcamp.login.Users.Users;
import com.bootcamp.login.Utils;
import com.bootcamp.login.firebase.FirebaseConnection;
import com.bootcamp.login.firebase.interfaces.CallbackUsersDelegate;
import com.bootcamp.login.firebase.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {
    //Variables locales
    private RecyclerView mRecycleTech;
    private RecyclerView mRecycleSrc;
    private ArrayList<String> Technologies;
    private ArrayList<Integer> Tech_Count;
    ArrayList<String> Size;
    String [] Name;
    TextView mAvailable;
    int totalav;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container,false);
        mRecycleTech=root.findViewById(R.id.tech_recycle);
        mRecycleSrc=root.findViewById(R.id.src_dip);
        mRecycleTech.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mAvailable = root.findViewById(R.id.av_number);
        Name=new String[]{"FrontEnd","BackEnd","Mobile"};
        Size = new ArrayList<String>();
        for (String tech : Name)
        {
            getUsers(tech);
        }
        getAvailable("Disponible");

        Technologies = new ArrayList<>();
        Tech_Count = new ArrayList<>();
        mRecycleSrc.setLayoutManager(new LinearLayoutManager(getContext()));
        GetPercents();


        return root;
    }
    @Override
    public void onResume(){
        super.onResume();
        ((Home) getActivity()).setActionBarTitle("Inicio");
    }
    private void getUsers(String technologyp){
        FirebaseConnection.getUsers(technologyp, new CallbackUsersDelegate() {
            @Override
            public void onDataChange(List<User> usersResponse) {
                if(usersResponse == null) return;

                //Only data for example
                Utils.Debug(FirebaseConnection.class, "TOTAL USERS:" + usersResponse.size());
                Utils.Debug(MainActivity.class, usersResponse.get(0).getName());
                int total = usersResponse.size();
                Size.add(Integer.toString(total));
                mRecycleTech.setAdapter(new HorizontalAdapter(Name, null,Size));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Utils.Debug(MainActivity.class, databaseError.getMessage());
            }
        });
    }

    private void getAvailable (String end){
        FirebaseConnection.getAvailable(end, new CallbackUsersDelegate() {
            @Override
            public void onDataChange(List<User> usersResponse) {
                if(usersResponse == null) return;

                //Only data for example
                Utils.Debug(FirebaseConnection.class, "TOTAL USERS:" + usersResponse.size());
                Utils.Debug(MainActivity.class, usersResponse.get(0).getName());
                totalav = usersResponse.size();
                mAvailable.setText(Integer.toString(totalav));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Utils.Debug(MainActivity.class, databaseError.getMessage());
            }
        });
    }

    private void GetPercents()
    {
        //Referenciar el hijo "usuarios"
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference.orderByChild("technology").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Limpieza
                Technologies.clear();
                Tech_Count.clear();
                String tech;
                int index;
                int total = 0;

                //Por cada objeto
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    tech = dataSnapshot1.child("technology").getValue().toString();

                    if(tech.equals("")){ }
                    else
                    {
                        if (Technologies.contains(tech))
                        {
                            index = Technologies.indexOf(tech);
                            Tech_Count.set(index, Tech_Count.get(index) + 1);
                        }
                        else
                        {
                            Technologies.add(tech);
                            Tech_Count.add(1);
                        }
                        total += 1;
                    }
                }
                //Mandar listas al adapter
                AdapterHome adapter = new AdapterHome(Technologies,Tech_Count,total);
                mRecycleSrc.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}