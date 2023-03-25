package ru.muwa.shq.engine.shell;

import ru.muwa.shq.engine.g.Renderer;

import javax.swing.*;

public class Console {
    public static JPanel console = new JPanel();
    public static JLabel output = new JLabel();
    public static JTextField input = new JTextField();
    public static JButton button = new JButton("ввести чит-код");

    static
    {
        console.setLayout(null);
        console.add(input);
        console.add(button);
        console.add(output);
        console.setBounds(100,600,200,200);
    }
}
