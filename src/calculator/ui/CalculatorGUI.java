package calculator.ui;

import calculator.logic.CalculatorEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private CalculatorEngine engine;
    private JTextField display;

    public CalculatorGUI() {
        engine = new CalculatorEngine();
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 32));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "CE"
        };
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(e -> onButtonClick(text));
            buttonPanel.add(btn);
        }
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setSize(400, 500);
        setLocationRelativeTo(null); // center on screen
        setVisible(true);
    }

    private void onButtonClick(String command) {
        if (command.matches("[0-9.]")) {
            engine.inputDigit(command);
        } else if (command.equals("C")) {
            engine.clearAll();
        } else if (command.equals("CE")) {
            engine.clearEntry();
        } else {
            engine.inputOperator(command);
        }
        display.setText(engine.getDisplayValue());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI());
    }
}