package carrental;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CarForm extends JFrame {

    private JTextField txtRegisterNumber;
    private JTextField txtBrand;
    private JTextField txtModel;
    private JTextField txtYear;
    private JTextField txtRentalPrice;

    private JLabel lblStatus;

    public CarForm() {
        setNimbusLookAndFeel();

        setTitle("SmartDrive Rentals - Car Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 420);
        setLocationRelativeTo(null);

        setContentPane(buildMainPanel());
        setVisible(true);
    }

    private JPanel buildMainPanel() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(new EmptyBorder(18, 18, 18, 18));

        //HEADER
        JLabel title = new JLabel("Car Registration");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));

        JLabel subtitle = new JLabel("Register a car into SmartDrive Rentals system");

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);
        header.add(title);
        header.add(Box.createVerticalStrut(4));
        header.add(subtitle);

        root.add(header, BorderLayout.NORTH);

        //FORM
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(16, 0, 10, 0));
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtRegisterNumber = new JTextField(18);
        txtBrand = new JTextField(18);
        txtModel = new JTextField(18);
        txtYear = new JTextField(18);
        txtRentalPrice = new JTextField(18);

        addRow(formPanel, gbc, 0, "Register Number", txtRegisterNumber);
        addRow(formPanel, gbc, 1, "Brand", txtBrand);
        addRow(formPanel, gbc, 2, "Model", txtModel);
        addRow(formPanel, gbc, 3, "Year", txtYear);
        addRow(formPanel, gbc, 4, "Rental Price", txtRentalPrice);

        root.add(formPanel, BorderLayout.CENTER);

        //BUTTONS
        JButton btnRegister = new JButton("Register Car");
        JButton btnClear = new JButton("Clear");
        JButton btnViewCars = new JButton("View Cars");

        btnRegister.setFont(btnRegister.getFont().deriveFont(Font.BOLD));

        btnRegister.addActionListener(e -> registerCar());
        btnClear.addActionListener(e -> clearFields());
        btnViewCars.addActionListener(e -> new ViewCarsForm());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.add(btnViewCars);
        buttons.add(btnClear);
        buttons.add(btnRegister);

        lblStatus = new JLabel(" ");
        lblStatus.setBorder(new EmptyBorder(8, 2, 0, 2));

        JPanel footer = new JPanel(new BorderLayout());
        footer.add(buttons, BorderLayout.EAST);
        footer.add(lblStatus, BorderLayout.SOUTH);

        root.add(footer, BorderLayout.SOUTH);

        return root;
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(new JLabel(label + ":"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);
    }

    //LOGIC
    private void registerCar() {
        if (isBlank(txtRegisterNumber) || isBlank(txtBrand) ||
            isBlank(txtModel) || isBlank(txtYear) || isBlank(txtRentalPrice)) {
            setStatus("Please fill in all fields.", true);
            return;
        }

        try {
            int reg = Integer.parseInt(txtRegisterNumber.getText().trim());
            String brand = txtBrand.getText().trim();
            String model = txtModel.getText().trim();
            int year = Integer.parseInt(txtYear.getText().trim());
            double price = Double.parseDouble(txtRentalPrice.getText().trim());

            Car car = new Car(reg, brand, model, year, price);
            CarDAO.insertCar(car);

            setStatus("Car registered successfully.", false);
            clearFields();

        } catch (NumberFormatException ex) {
            setStatus("Register Number, Year, and Price must be numbers.", true);
        } catch (Exception ex) {
            setStatus("Error: " + ex.getMessage(), true);
        }
    }

    private void clearFields() {
        txtRegisterNumber.setText("");
        txtBrand.setText("");
        txtModel.setText("");
        txtYear.setText("");
        txtRentalPrice.setText("");
        txtRegisterNumber.requestFocus();
    }

    private boolean isBlank(JTextField tf) {
        return tf.getText().trim().isEmpty();
    }

    private void setStatus(String msg, boolean error) {
        lblStatus.setText(msg);
        lblStatus.setForeground(error ? Color.RED : new Color(0, 128, 0));
    }

    private void setNimbusLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarForm::new);
    }
}


