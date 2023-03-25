package ru.muwa.shq.items;

import javax.swing.*;

public class ItemPanel extends JLabel {
    private Item item;
    public ItemPanel(Item item)
    {
        super();
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
