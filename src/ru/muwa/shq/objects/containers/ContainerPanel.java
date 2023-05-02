package ru.muwa.shq.objects.containers;

import ru.muwa.shq.items.Item;

import javax.swing.*;

public class ContainerPanel extends JLabel {
    private Item item;
    public ContainerPanel(Item item)
    {
        super();
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
