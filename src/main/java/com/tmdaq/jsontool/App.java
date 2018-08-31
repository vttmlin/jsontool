package com.tmdaq.jsontool;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.tmdaq.jsontool.Cache.*;
import static com.tmdaq.jsontool.StringUtil.isNotBlank;

@Data
public class App {
    private JTextArea source;
    private JTextPane target;
    private JButton format;
    private JPanel panel;
    private JButton compress;
    private JButton transference;
    private JPanel targetPanel;
    private JPanel sourcePanel;
    private JPanel buttonPanel;
    private JButton toRight;
    private JButton toLeft;
    private JButton reTransference;
    private JScrollPane sourceScroll;
    private JScrollPane targetScroll;
    private JButton toXmlJson;
    private JButton toBean;

    @SuppressWarnings("unchecked")
    public App() {
        format.addActionListener(new ButtonActionListener(source, target) {
            @Override
            public Object doSomething() {
                try {
                    Map<String, Object> map = getObjectMapper().readValue(source.getText(), Map.class);
                    Map<String, Color> keys = new HashMap<>();
                    getKeys(map, keys, "");
                    String s = getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
                    Random random = new Random();
                    target.setText("");
                    String[] split = s.split("\n");
                    for (String s1 : split) {
                        setDocs(target, s1 + "\n", new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    }
                } catch (Exception e) {
                    target.setText(e.getMessage());
                }
                return null;
            }
        });
        compress.addActionListener(new ButtonActionListener(source, target) {
            @Override
            public Object doSomething() {
                try {
                    target.setSelectedTextColor(new Color(0, 0, 0));
                    Map<String, Object> map = getObjectMapper().readValue(source.getText(), Map.class);
                    String s = getObjectMapper().writeValueAsString(map);
                    target.setText(s);
                    target.setSelectedTextColor(new Color(0, 0, 0));
                } catch (Exception e) {
                    target.setText(e.getMessage());
                }
                return null;
            }
        });
        transference.addActionListener(new ButtonActionListener(source, target) {
            @Override
            public Object doSomething() {
                try {
                    Map<String, Object> map = getObjectMapper().readValue(source.getText(), Map.class);
                    String s = getObjectMapper().writeValueAsString(getObjectMapper().writeValueAsString(map));
                    target.setText(s);
                } catch (Exception e) {
                    target.setText(e.getMessage());
                }
                return null;
            }
        });
        toLeft.addActionListener(new ButtonActionListener(source, target) {
            @Override
            public Object doSomething() {
                target.setSelectedTextColor(new Color(0, 0, 0));
                source.setText(target.getText());
                return null;
            }
        });
        toRight.addActionListener(new ButtonActionListener(source, target) {
            @Override
            public Object doSomething() {
                target.setSelectedTextColor(new Color(0, 0, 0));
                target.setText(source.getText());
                return null;
            }
        });
        reTransference.addActionListener(new ButtonActionListener(source, target) {
            @Override
            public Object doSomething() {
                target.setSelectedTextColor(new Color(0, 0, 0));
                String text = source.getText().trim();
                if (text.substring(0, 1).equals("\"")) {
                    String replace = text.substring(1, text.length() - 1).replace("\\", "");
                    target.setText(replace.trim());
                } else {
                    target.setText(text.trim());
                }
                return null;
            }
        });
        toXmlJson.addActionListener(new ButtonActionListener(source, target) {
            @Override
            public Object doSomething() {
                String text = source.getText().trim();
                String result = null;
                switch (text.substring(0, 1)) {
                    case "<":
                        result = convert(text, getXmlHandler());
                        break;
                    case "{":
                        result = convert(text, getJsonHandler());
                        break;
                    default:
                        break;
                }
                target.setText(result);
                return null;
            }
        });
        toBean.addActionListener(new ButtonActionListener(source, target) {
            @Override
            public Object doSomething() {
                String result = "这个功能暂时先停段时间....";
                target.setText(result);
                return null;
            }
        });
        source.setLineWrap(true);
    }

    @SuppressWarnings("unchecked")
    private void getKeys(Map<String, Object> map, Map<String, Color> keys, String prefix) {
        String s = isNotBlank(prefix) ? prefix + "." : prefix;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof List) {
                List value = (List) entry.getValue();
                for (Object o : value) {
                    getKeys(((Map) o), keys, s + entry.getKey());
                }
            } else if (entry.getValue() instanceof Map) {
                getKeys(((Map<String, Object>) entry.getValue()), keys, s + entry.getKey());
            } else {
                Random random = new Random();
                keys.put(s + entry.getKey(), new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            }
        }
    }
}
