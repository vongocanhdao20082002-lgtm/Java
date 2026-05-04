package com.example.model;
//class Student (id, name, age, email)
public class Student {
    //Khai báo biến
    private String id;
    private String name;
    private int age;
    private String email;
    //Getter
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getEmail() {
        return email;
    }
    //Setter
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    //showInfo
    @Override
    public String toString(){
        return "ID: " + getId() + "\nName: " + getName() + "\nTuoi: " + getAge() + "\nEmail: " + getEmail();
    }
}
