package com.example.pinapple_.model;

public class Subject {
    public int id;
    public String name;

    public Subject(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
