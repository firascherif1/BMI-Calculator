package com.example.bmicalculator.model;

public class User {

    private String name;
    private String email;
    private int age;
    private float height;
    private float weight;
    private boolean gender;
    private float BMI;
    private String result;
    private boolean created = false;

    public User(){}
    public User(int age, float height, float weight, boolean gender) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        setBMI(height, weight);
        setResult(this.BMI,age,gender);
        this.created = true;
    }

    public boolean isCreated() {
        return created;
    }

    public void setBMI(float height, float weight) {

        BMI = weight / (height/100 * height/100);

    }

    public void setResult(Float bmi, int age, boolean gender) {
        if (age < 18) {
            result = "BMI interpretation for ages under 18 is not provided.";
        } else if (gender) {
            if (bmi < 18.5) {
                result = "You are Underweight";
            } else if (bmi >= 18.5 && bmi < 25) {
                result = "Normal weight ";
            } else if (bmi >= 25 && bmi < 30) {
                result = "You are Overweight";
            } else
                result = "You are Obese";
        } else {
            if (bmi < 18.5) {
                result = "You are Underweight";
            } else if (bmi >= 18.5 && bmi < 24) {
                result = "Normal weight";
            } else if (bmi >= 24 && bmi < 30) {
                result = "You are Overweight";
            } else
                result = "You are Obese";
        }
    }


    // getters
    //public int getId() {        return id;    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public boolean getGender() {
        return gender;
    }

    public float getBMI() {
        return BMI;
    }

    public String getResult() {
        return result;
    }

    //setters

    //public void setId(int id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
