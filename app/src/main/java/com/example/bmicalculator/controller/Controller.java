package com.example.bmicalculator.controller;

import com.example.bmicalculator.model.User;

public final class Controller {
    private static User user;
    private static Controller instance = null;
    private Controller(){
        super();
    }
    public static final Controller getInstance(){
        if(instance == null){
            instance = new Controller();
            user = new User();
        }
        return instance;
    }
    public void createUser(int age, float height,float weight, boolean gender){
        user = new User(age, height, weight, gender);
    }
    public boolean isCreated(){
        return user.isCreated();
    }

    public String getResult(){
        return user.getResult();
    }
    public float getBMI(){
        return user.getBMI();
    }
    public String getName() {
        return user.getName();
    }

    public float getHeight() {
        return user.getHeight();
    }
}
