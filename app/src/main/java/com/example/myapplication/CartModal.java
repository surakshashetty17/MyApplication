package com.example.myapplication;

public class CartModal
{
    public int id;
    public String name;
    public String subject;
    public String percentage;
    public String college;


//    public CartModal(int id, String name, String college) {
//        this.id = id;
//        this.name = name;
//        this.college = college;
//    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percetage) {
        this.percentage = percetage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
