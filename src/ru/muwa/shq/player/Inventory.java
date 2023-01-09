package ru.muwa.shq.player;

import ru.muwa.shq.entities.items.Item;

import java.util.ArrayList;

public class Inventory
{
    public static final int INVENTORY_CAPACITY = 8;
    private static Inventory instance;
    private Inventory(){instance = this; items = new ArrayList<>();}
    public static Inventory getInstance(){if(instance!=null) return instance; else return new Inventory();}

    private ArrayList<Item> items;



}
