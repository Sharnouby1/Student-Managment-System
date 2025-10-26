package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewStudents extends JFrame {
    private JPanel ViewStudentsPanel;
    public JTable Table;
    private JScrollPane ScrollPane;
    private JLabel ViewStudentsMessage;
    private JButton SaveButton;
    private JButton exitButton;

    public ViewStudents() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Serial Number");
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Gender");
        model.addColumn("Department");
        model.addColumn("GPA");
        model.addColumn("Edit");
        model.addColumn("Delete");
        model.addRow(new Object[]{"1", "9123" ,"Ahmed Salah", "20", "Male", "CSE", "3.5", "Edit","Delete"});
        model.addRow(new Object[]{"2", "1234", "Sara Ali", "21", "Female", "ECE", "3.8", "Edit","Delete"});
        model.addRow(new Object[]{"3", "12322" ,"Omar Hassan", "22", "Male", "ME", "3.2", "Edit","Delete"});
        Table.setModel(model);
        Table.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        Table.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        Table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), "Edit", Table));
        Table.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), "Delete", Table));


        setTitle("View Students");
        setContentPane(ViewStudentsPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,450);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JOptionPane.showMessageDialog(null,"Successfully Saved");
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
class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

// Editor (handles the button click)
class ButtonEditor extends DefaultCellEditor {
    private String label;
    private JButton button;
    private boolean clicked;

    public ButtonEditor(JCheckBox checkBox, String type, JTable Table) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);

        button.addActionListener(e -> {
            if (clicked) {
                int row = Table.getSelectedRow();
                String name = Table.getValueAt(row, 2).toString();

                if (type.equals("Edit")) {
                    JOptionPane.showMessageDialog(null, "Edit student: " + name);
                } else {
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to delete " + name + "?",
                            "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        ((DefaultTableModel) Table.getModel()).removeRow(row);
                    }
                }
            }
            clicked = false;
        });
    }
}