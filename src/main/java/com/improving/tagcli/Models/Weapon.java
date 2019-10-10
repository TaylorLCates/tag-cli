package com.improving.tagcli.Models;

public class Weapon {
    private String name;
    private int id;
    private String area;
    private String itemType;

    public Weapon(int id, String name, String area, String itemType) {
        this.name = name;
        this.id = id;
        this.area = area;
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public String getItemType() {
        return itemType;
    }
}
