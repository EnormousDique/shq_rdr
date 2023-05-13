package ru.muwa.shq.items;

import javax.swing.*;

public class ItemPanel extends JLabel {
    private Item item;
    public ItemPanel(Item item)
    {
        super();
        this.item = item;
        this.setIcon(new ImageIcon(item.getTexture()));
    }

    public Item getItem() {
        return item;
    }
}
