package com.example.loginapplicationdemo;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> userList;
    private static UserRepository instance;

    public static UserRepository UserRepositoryInstance(){
        if(instance != null) return instance;
        instance =  new UserRepository();
        return instance;
    }

    private UserRepository() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User newUser){
        userList.add(newUser);
    }

    public boolean authenticateUser(User user){
        return userList.contains(user);
    }
}
