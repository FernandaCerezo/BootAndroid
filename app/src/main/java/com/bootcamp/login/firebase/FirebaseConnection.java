package com.bootcamp.login.firebase;

import androidx.annotation.NonNull;

import com.bootcamp.login.Users.Users;
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

/**
 * Created by alvaromunoz
 * Date 2019-11-22.
 */
public class FirebaseConnection {

    private static final String USERS_REF_DATABASE = "user";
    private static final String TECHNOLOGY_REF_DATABASE = "technologyp";
    public static DatabaseReference getDatabaseReference(String ref){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference(ref);
    }


    public static void getUsers(final String technologyp, final CallbackUsersDelegate delegate){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<User> listUser = new ArrayList<>();
                    for (DataSnapshot userDataSnapshot: dataSnapshot.getChildren()) {
                        User obj = userDataSnapshot.getValue(User.class);
                        if(obj != null && obj.getTechnologyp().equalsIgnoreCase(technologyp)){
                            listUser.add(obj);
                        }
                    }
                    delegate.onDataChange(listUser);

                }catch (Exception e){
                    delegate.onDataChange(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                delegate.onCancelled(databaseError);
            }
        };

        getDatabaseReference(USERS_REF_DATABASE).addValueEventListener(valueEventListener);
    }

    public static void getAvailable(final String end, final CallbackUsersDelegate delegate){
        String  Status = "Disponible";
        final String finalStatus = Status;
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<User> listUsers = new ArrayList<>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        User u = dataSnapshot1.getValue(User.class);
                        if (GetStatus(dataSnapshot1.child("end").getValue().toString()).equals(finalStatus))
                        {
                            listUsers.add(u);
                        }
                    }
                    delegate.onDataChange(listUsers);

                }catch (Exception e){
                    delegate.onDataChange(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                delegate.onCancelled(databaseError);
            }
        };

        getDatabaseReference(USERS_REF_DATABASE).addValueEventListener(valueEventListener);
    }
    public static String GetStatus(String Date) {
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
