package GUI;

import database.StudentsDatabase;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchAndUpdate extends JFrame {
    private JPanel SearchAndUpdatePanel;
    public JTable Table;
    private JScrollPane ScrollPane;
    private JLabel SearchAndUpdateMessage;
    private JButton BackButton;
    private JButton exitButton;
    private JLabel PressToSortMessage;
    private JTextField SearchField;
    private JLabel SearchMessage;
    public JRadioButton SearchByNameRadioButton;
    private JButton SearchButton;
    public JRadioButton SearchByIDRadio;

    public SearchAndUpdate() {
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

        setTitle("Search And Update Students");
        setContentPane(SearchAndUpdatePanel);
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


        SearchByIDRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SearchByIDRadio.isSelected()) {
                    SearchByNameRadioButton.setSelected(false);
                }
            }
        });
        SearchByNameRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SearchByNameRadioButton.isSelected()) {
                    SearchByIDRadio.setSelected(false);
                }
            }
        });
        SearchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentsDatabase s=new StudentsDatabase("Students.txt");
                s.readFromFile();
                if(!SearchByIDRadio.isSelected()&&!SearchByNameRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please Select a Search Button");

                }
                if(SearchByIDRadio.isSelected()) {
                    if(s.contains(SearchField.getText())) {
                    dispose();
                    new Update(SearchField.getText());}
                    else
                        JOptionPane.showMessageDialog(null, "The ID Not Found");
                }
                else if(SearchByNameRadioButton.isSelected()) {
                    if(s.contains(SearchField.getText())) {
                        new Update(SearchField.getText());
                        dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "The Name Not Found");
            }}
        });
    }
}