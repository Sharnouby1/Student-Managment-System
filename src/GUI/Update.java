package GUI;

import database.StudentsDatabase;
import model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Update extends JFrame {
    private JPanel update;
    private JTextField IDText;
    private JTextField NameText;
    private JTextField AgeText;
    private JComboBox GenderCombo;
    private JComboBox DepartmentCombo;
    private JTextField GPAText;
    private JButton backSearchAndUpdateButton;
    private JButton updateButton;

    public Update(String str) {
        setTitle("Update Student");
        setContentPane(update);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(550, 300);
        setResizable(false);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backSearchAndUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
        GPAText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backSearchAndUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SearchAndUpdate();
            }
        });
        updateButton.addActionListener(new ActionListener() {
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
                StudentsDatabase s = new StudentsDatabase("Students.txt");
                s.readFromFile();

                Student student = new Student(IDText.getText(),NameText.getText(),Integer.parseInt(AgeText.getText())
                        , Objects.requireNonNull(GenderCombo.getSelectedItem()).toString(),
                        Objects.requireNonNull(DepartmentCombo.getSelectedItem()).toString(),
                        Float.parseFloat(GPAText.getText()));
                    if(student.addStudent(student)){
                    JOptionPane.showMessageDialog(null, "Student Updated");
                    student.deleteStudentByName(str);
                    student.deleteStudentById(str);
                    dispose();
                    new ViewStudents();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Student Not Updated");
                        dispose();
                        new SearchAndUpdate();
                    }


        }
        });
        GenderCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        DepartmentCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
