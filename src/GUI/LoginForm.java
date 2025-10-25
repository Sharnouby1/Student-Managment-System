package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    public JPanel mainPanel;
    private JButton loginButton;
    private JLabel usernameField;
    private JLabel passwordField;
    private JTextField Password;
    private JTextField username;
    private JButton LoginButton;

    public LoginForm() {


        setTitle("Login");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,150);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username1 = username.getText().trim();
                String password = Password.getText().trim();
                if(username.getText().equals("admin") && Password.getText().equals("1234")) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "Welcome Admin");
                    new MainMenu();

                }
                else {
                    JOptionPane.showMessageDialog(null, "Wrong Username or Password");
                }
            }
        });
    }
}
