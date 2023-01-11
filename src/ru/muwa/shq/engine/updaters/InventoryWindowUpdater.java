package ru.muwa.shq.engine.updaters;

public class InventoryWindowUpdater
{
    static private InventoryWindowUpdater  instance;
    private InventoryWindowUpdater (){instance = this;}
    public static InventoryWindowUpdater  getInstance()
    {if (instance == null) return new InventoryWindowUpdater(); else return instance;}
}
