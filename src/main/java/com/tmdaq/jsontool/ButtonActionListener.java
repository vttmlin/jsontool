package com.tmdaq.jsontool;

import lombok.Data;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public abstract class ButtonActionListener implements ActionListener {
    private JTextArea source;
    private JTextPane target;

    @Override
    public void actionPerformed(ActionEvent e) {
        doSomething();
    }

    public abstract Object doSomething();

    public ButtonActionListener(JTextArea source, JTextPane target) {
        this.source = source;
        this.target = target;
    }

    public void setDocs(JTextPane target, String str, Color col) {
        SimpleAttributeSet attrSet = new SimpleAttributeSet();
        StyleConstants.setForeground(attrSet, col);
        Document doc = target.getDocument();
        try {
            doc.insertString(doc.getLength(), str, attrSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public String convert(String s, TypeHandler toOtherHangler) {
        return toOtherHangler.convert(s);
    }
}