package com.bootcamp.login.firebase.interfaces;

import com.bootcamp.login.Users.Users;
import com.bootcamp.login.firebase.models.User;
import com.google.firebase.database.DatabaseError;

import java.util.List;

/**
 * Created by alvaromunoz
 * Date 2019-11-22.
 */
public interface CallbackUsersDelegate {

    void onDataChange(List<User> usersResponse);
    void onCancelled(DatabaseError databaseError);
}
