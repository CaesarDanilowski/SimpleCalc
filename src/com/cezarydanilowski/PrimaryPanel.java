package com.cezarydanilowski;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PrimaryPanel extends JPanel {

    private JButton screen;
    private JPanel panel;
    private JButton clear;
    private String lastOption;
    private double sum;
    private boolean canIt;

    public PrimaryPanel() {
        lastOption = "=";
        sum = 0;
        canIt = true;

        screen = new JButton("0");
        screen.setEnabled(false);
        screen.setPreferredSize(new Dimension(180,40));
        screen.setBackground(Color.GRAY);
        screen.setFont(new Font("Consolas", Font.BOLD, 20));
        add(screen, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(180, 160));
        panel.setLayout(new GridLayout(4,4));
        add(panel, BorderLayout.CENTER);

        InputAction input = new InputAction();
        OptionAction options = new OptionAction();

        creatingButton("7", input);
        creatingButton("8", input);
        creatingButton("9", input);
        creatingButton("/", options);

        creatingButton("4", input);
        creatingButton("5", input);
        creatingButton("6", input);
        creatingButton("*", options);

        creatingButton("1", input);
        creatingButton("2", input);
        creatingButton("3", input);
        creatingButton("-", options);

        creatingButton("0", input);
        creatingButton(".", input);
        creatingButton("=", options);
        creatingButton("+", options);

        clear = new JButton("CLEAR");
        clear.addActionListener(options);
        clear.setPreferredSize(new Dimension(180,40));
        add(clear, BorderLayout.SOUTH);
    }

    private void creatingButton(String title, ActionListener action) {
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(50, 40));
        button.addActionListener(action);
        panel.add(button);
    }

    private class InputAction implements ActionListener  {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();
            if (canIt) {
                screen.setText("");
                canIt = false;
            }

            screen.setText(screen.getText() + input);
        }
    }

    private class OptionAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String option = event.getActionCommand();
            if (canIt) {
                if (option.equals("-")) {
                    screen.setText(option);
                    canIt = false;
                } else {
                    lastOption = option;

                    if (lastOption.equals("CLEAR")) {
                        sum = 0;
                        screen.setText("" + sum);
                        lastOption = "=";
                    }
                }
            } else {
                count(Double.parseDouble(screen.getText()));
                lastOption = option;
                canIt = true;
            }
        }
    }

    private void count(double a) {
        if (lastOption.equals("+")) sum += a;
        else if (lastOption.equals("-")) sum -= a;
        else if (lastOption.equals("*")) sum *= a;
        else if (lastOption.equals("/")) sum /= a;
        else if (lastOption.equals("=")) sum = a;
        else if (lastOption.equals("CLEAR")) sum = 0;

        screen.setText("" + sum);
    }
}
