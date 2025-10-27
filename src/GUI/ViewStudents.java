package GUI;

import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewStudents extends JFrame {
    private JPanel ViewStudentsPanel;
    public JTable Table;
    private JScrollPane ScrollPane;
    private JLabel ViewStudentsMessage;
    private JButton BackButton;
    private JButton exitButton;
    private JLabel PressToSortMessage;

    public ViewStudents() {
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
            System.out.println(Students[i].getId());
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




        setTitle("View Students");
        setContentPane(ViewStudentsPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,450);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenu();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}