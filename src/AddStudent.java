import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddStudent extends JFrame {
    private JPanel AddStudentPanel;
    private JLabel StudentID;
    private JLabel Title;
    private JLabel StudentName;
    private JLabel StudentAge;
    private JLabel StudentGender;
    private JLabel StudentGPA;
    private JLabel StudentDepartment;
    private JTextField IDText;
    private JTextField NameText;
    private JTextField AgeText;
    private JComboBox GenderCombo;
    private JComboBox DepartmentCombo;
    private JTextField GPAText;
    private JButton SaveButton;
    private JButton CancelButton;

    public AddStudent() {

        setTitle("Add a New Student");
        setContentPane(AddStudentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550,300);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        IDText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        NameText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        AgeText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });
        GenderCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gender = Objects.requireNonNull(GenderCombo.getSelectedItem()).toString();
            }
        });
        DepartmentCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String department = Objects.requireNonNull(DepartmentCombo.getSelectedItem()).toString();
            }
        });
        GPAText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenu();
            }
        });
        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(IDText.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the student ID");
                    return;
                }
                try {
                    int ID = Integer.parseInt(IDText.getText());
                    if(ID < 1){
                        JOptionPane.showMessageDialog(null, "Please enter a valid student ID");
                        return;
                    }
                }catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid student ID");
                    return;
                }

                if (NameText.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the student Name");
                    return;
                }
                if (NameText.getText().matches(".*\\d.*")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid student Name");
                    return;
                } else {
                    String name = NameText.getText();
                }

                if(AgeText.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the student Age");
                    return;
                }
                try {
                    int Age = Integer.parseInt(AgeText.getText());
                    if(Age < 3 || Age > 40){
                        JOptionPane.showMessageDialog(null, "Please enter a valid student Age");
                        return;
                    }
                }catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid student Age");
                    return;
                }

                if(GPAText.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the student GPA");
                    return;
                }
                try {
                    float GPA = Float.parseFloat(GPAText.getText());
                    if(GPA < 0 || GPA > 4) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid student GPA");
                        return;
                    }
                }catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid student GPA");
                    return;
                }
                dispose();
                JOptionPane.showMessageDialog(null, "Student added successfully");
            }
        });
    }

}
