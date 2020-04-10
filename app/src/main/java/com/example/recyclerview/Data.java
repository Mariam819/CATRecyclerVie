package com.example.recyclerview;

import java.io.Serializable;

public class Data implements Serializable {
    private String Name ;
    private String Number;


    public Data(String name, String number ) {
        this.Name = name;
        this.Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
