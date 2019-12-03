package com.bootcamp.login.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView mRecycleTech;
    private RecyclerView mRecycleSrc;
    ArrayList<String> Size;
    String [] Name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container,false);
        mRecycleTech=root.findViewById(R.id.tech_recycle);
        mRecycleSrc=root.findViewById(R.id.src_dip);
        mRecycleTech.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        try {
        Name=new String[]{"FrontEnd","BackEnd","Movil"};
        Size = new ArrayList<String>();
        for (String tech : Name)
        {
            getUsers(tech);
        }
        }
        catch (Exception e) {
            getUsers(null);
        }

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
                mRecycleTech.setAdapter(new HorizontalAdapter(Name,null, Size));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Utils.Debug(MainActivity.class, databaseError.getMessage());
            }
        });
    }
}