package Package1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactForm extends JFrame {

    private JTextField nameField, emailField;
    private JRadioButton male, female;
    private JCheckBox agreementCheckbox;

    public ContactForm() {
        super("Контактная форма");
        setBounds(200, 100, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding for layout

        // Adding components with labels
        JLabel nameLabel = new JLabel("Введите имя:");
        nameField = new JTextField(15);
        JLabel emailLabel = new JLabel("Введите почту:");
        emailField = new JTextField(15);

        male = new JRadioButton("Мужской");
        female = new JRadioButton("Женский");
        male.setSelected(true); // Default selection

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        agreementCheckbox = new JCheckBox("Согласен с условиями", false);
        JButton sendButton = new JButton("Отправить");

        // Positioning components using GridBagConstraints
        gbc.gridx = 0; gbc.gridy = 0;
        container.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        container.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        container.add(emailLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        container.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        container.add(male, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        container.add(female, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        container.add(agreementCheckbox, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        container.add(sendButton, gbc);

        // Add ActionListener for the button
        sendButton.addActionListener(new ButtonEventManager());
    }

    // Validation method
    private boolean validateForm() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, введите ваше имя.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, введите действительный адрес электронной почты.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!agreementCheckbox.isSelected()) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, согласитесь с условиями.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    // ActionListener for the button
    class ButtonEventManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!validateForm()) {
                return; // Form validation failed, return early
            }

            String name = nameField.getText();
            String email = emailField.getText();
            String gender = male.isSelected() ? "Мужской" : "Женский";

            boolean agreed = agreementCheckbox.isSelected();

            // Display the result in a message box
            JOptionPane.showMessageDialog(null,
                    "Имя: " + name +
                            "\nПочта: " + email +
                            "\nПол: " + gender +
                            "\nСогласие: " + (agreed ? "Да" : "Нет"),
                    "Информация", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
