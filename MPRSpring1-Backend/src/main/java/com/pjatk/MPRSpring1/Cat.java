package com.pjatk.MPRSpring1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long catID;

    public Long getCatID() {
        return catID;
    }

    public String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int age;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String color;

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setCatID(Long catID) {
        this.catID = catID;
    }

    public Cat() {

    }

    public Cat(String name,int age,String color){
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public Cat(Long catID, String name, int age, String color) {
        this.catID = catID;
        this.name = name;
        this.age = age;
        this.color = color;
    }
}
