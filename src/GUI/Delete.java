package GUI;

import database.StudentsDatabase;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delete extends JFrame {

    private JPanel Delete;
    private JTextField inputsearch;
    private JRadioButton deleteByIDRadioButton;
    private JRadioButton deleteByNameRadioButton;
    private JLabel search;
    private JTable Table;
    private JButton backToMainMenuButton;
    private JButton deleteButton;

    public Delete() {

        setTitle("Delete");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,450);
        setContentPane(Delete);
        setVisible(true);
        setLocationRelativeTo(null);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Serial Number");
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Gender");
        model.addColumn("Department");
        model.addColumn("GPA");
        Student[] Students = new Student().viewAllStudents();
        for(int i = 0; i < Students.length; i++) {
            model.addRow(new Object[]{ i+1 ,Students[i].getId(),Students[i].getFullName(),
                    Students[i].getAge(),Students[i].getGender(),Students[i].getDepartment(),
                    Students[i].getGpa()});
        }
        Table.setModel(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        Table.setRowSorter(sorter);
        sorter.setComparator(1, (a, b) -> {
            int id1 = Integer.parseInt(a.toString());
            int id2 = Integer.parseInt(b.toString());
            return Integer.compare(id1, id2);
        });
        sorter.toggleSortOrder(1);
        sorter.setComparator(2, (a, b) -> a.toString().compareToIgnoreCase(b.toString()));
        sorter.toggleSortOrder(2);


        deleteByIDRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
if(deleteByIDRadioButton.isSelected()){
    deleteByNameRadioButton.setSelected(false);

}
            }
        });
        deleteByNameRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(deleteByNameRadioButton.isSelected()){
                    deleteByIDRadioButton.setSelected(false);

                }
            }
        });
        inputsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenu();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
if(!deleteByIDRadioButton.isSelected() &&!deleteByNameRadioButton.isSelected()){
    JOptionPane.showMessageDialog(null,"Choose Delete by ID or by Name");
}
                Student student = new Student();
                StudentsDatabase s=new StudentsDatabase("Students.txt");
                s.readFromFile();
                if(deleteByIDRadioButton.isSelected()){
                if(s.contains(inputsearch.getText())) {
                    student.deleteStudentById(inputsearch.getText());
                    dispose();
                    JOptionPane.showMessageDialog(null, "Student deleted successfully");
                    new ViewStudents();
                }
                else{
                    JOptionPane.showMessageDialog(null,"This ID isn't Found");
                }
                } else if (deleteByNameRadioButton.isSelected()) {

                    if(s.contains(inputsearch.getText())) {
                       student.deleteStudentByName(inputsearch.getText());
                        dispose();
                        JOptionPane.showMessageDialog(null, "Student deleted successfully");
                        new ViewStudents();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"This Name isn't Found");
                    }

                }
            }
        });
    }
}
