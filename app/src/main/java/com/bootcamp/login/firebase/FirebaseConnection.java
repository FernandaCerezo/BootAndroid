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

}
