package com.tmdaq.jsontool;

import javax.swing.*;

public class Start {
    public static void main(String[] args) {
        JFrame frame = new JFrame("json 工具");
        new App();
        App app = new App();
        frame.setContentPane(app.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 800);
        frame.setVisible(true);
    }
}
