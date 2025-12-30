package carrental;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ViewCarsForm extends JFrame {

    public ViewCarsForm() {
        setTitle("View Cars");
        setSize(700, 300);
        setLocationRelativeTo(null);

        String[] cols = {"Register Number", "Brand", "Model", "Year", "Rental Price"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        try {
            List<Car> cars = CarDAO.getAllCars();
            for (Car c : cars) {
                model.addRow(new Object[]{
                        c.getRegisterNumber(),
                        c.getBrand(),
                        c.getModel(),
                        c.getYear(),
                        c.getRentalPrice()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }
}
