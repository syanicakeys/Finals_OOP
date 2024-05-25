package Finals_OOP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


//may  comments na guys, i have evolved
public class OrderFrame extends JFrame implements ActionListener {
    private JTextField customerNameField;
    private JComboBox<Product> productComboBox;
    private JTextField quantityField;
    private JTextField discountField;
    private JTextField cashField;
    private JTextField totalField;
    private JTextField changeField;
    private JButton calculateTotalButton, calculateChangeButton;
    private JButton saveButton;

    public OrderFrame() {
        super("Product Order");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Font labelFont = new Font("Verdana", Font.BOLD, 16);
        Font fieldFont = new Font("Verdana", Font.PLAIN, 16);
        Font buttonFont = new Font("Verdana", Font.BOLD, 14);

        // Customer Name
        JLabel customerNameLabel = new JLabel("Customer Name: ");
        customerNameLabel.setFont(labelFont);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        add(customerNameLabel, c);

        customerNameField = new JTextField(20);
        customerNameField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 0;
        add(customerNameField, c);

        // Date (automatically set sa current dat)
        JLabel dateLabel = new JLabel("Date: ");
        dateLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 1;
        add(dateLabel, c);

        JTextField dateField = new JTextField(20);
        dateField.setFont(fieldFont);
        dateField.setText(new Date().toString());
        dateField.setEditable(false);
        c.gridx = 1;
        c.gridy = 1;
        add(dateField, c);

        // Product
        JLabel productLabel = new JLabel("Product: ");
        productLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 2;
        add(productLabel, c);

        productComboBox = new JComboBox<>(new Product[]{

            new Product("Toblerone", 55.00),
            new Product("Snickers", 25.00),
            new Product("Hershey", 45.00),
            new Product("Baby Ruth", 68.00),
            new Product("Kit Kat", 34.00)
        });


        productComboBox.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 2;
        add(productComboBox, c);

        // Quantity
        JLabel quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 3;
        add(quantityLabel, c);

        quantityField = new JTextField(20);
        quantityField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 3;
        add(quantityField, c);

        // Discount
        JLabel discountLabel = new JLabel("Discount (%): ");
        discountLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 4;
        add(discountLabel, c);

        discountField = new JTextField(20);
        discountField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 4;
        add(discountField, c);

        // Note for Discount
        JLabel discountNoteLabel = new JLabel("Note: Discount should be between 5% and 50%");
        discountNoteLabel.setFont(new Font("Verdana", Font.ITALIC, 12));
        discountNoteLabel.setForeground(Color.RED);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        add(discountNoteLabel, c);

        // Total
        JLabel totalLabel = new JLabel("Total: ");
        totalLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 6;
        add(totalLabel, c);

        totalField = new JTextField(20);
        totalField.setFont(fieldFont);
        totalField.setEditable(false);
        c.gridx = 1;
        c.gridy = 6;
        add(totalField, c);

        // Calculate Total button
        calculateTotalButton = new JButton("Calculate Total");
        calculateTotalButton.setFont(buttonFont);
        calculateTotalButton.setBackground(new Color(144, 238, 144));
        c.gridx = 2;
        c.gridy = 6;
        add(calculateTotalButton, c);
        
     // Cash
        JLabel cashLabel = new JLabel("Cash: ");
        cashLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 8;
        add(cashLabel, c);

        cashField = new JTextField(20);
        cashField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 8;
        add(cashField, c);

        // Change
        JLabel changeLabel = new JLabel("Change: ");
        changeLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 9;
        add(changeLabel, c);

        changeField = new JTextField(20);
        changeField.setFont(fieldFont);
        changeField.setEditable(false);
        c.gridx = 1;
        c.gridy = 9;
        add(changeField, c);
        
     // Buttons       
        calculateChangeButton = new JButton("Calculate Change");
        calculateChangeButton.setFont(buttonFont);
        calculateChangeButton.setBackground(new Color(144, 238, 144));
        c.gridx = 0;
        c.gridy = 10;
        add(calculateChangeButton, c);

        saveButton = new JButton("Save");
        saveButton.setFont(buttonFont);
        saveButton.setBackground(new Color(135, 206, 235));
        c.gridx = 2;
        c.gridy = 10;
        add(saveButton, c);

        // Action listeners
        calculateTotalButton.addActionListener(this);
        calculateChangeButton.addActionListener(this);
        saveButton.addActionListener(this);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateTotalButton) {
            calculateTotal();
        } else if (e.getSource() == calculateChangeButton) {
            calculateChange();
        } else if (e.getSource() == saveButton) {
            saveOrder();
        }
    }


    private void calculateTotal() {
        try {
            Product product = (Product) productComboBox.getSelectedItem();
            int quantity = Integer.parseInt(quantityField.getText());
            double discount = Double.parseDouble(discountField.getText());

            if (discount < 5 || discount > 50) {
                JOptionPane.showMessageDialog(this, "Discount should be between 5% and 50%", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double total = product.getPrice() * quantity * (1 - discount / 100.0);
            totalField.setText(String.format("%.2f", total));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateChange() {
        try {
            double total = Double.parseDouble(totalField.getText());
            double cash = Double.parseDouble(cashField.getText());

            if (cash < total) {
                JOptionPane.showMessageDialog(this, "Cash value should be higher or equal to total amount", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double change = cash - total;
            changeField.setText(String.format("%.2f", change));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveOrder() {
        String customerName = customerNameField.getText();
        String productName = ((Product) productComboBox.getSelectedItem()).getName();
        int quantity = 0;
        double discount = 0.0;
        double total = 0.0;
        double cash = 0.0;
        double change = 0.0;

        try {
            quantity = Integer.parseInt(quantityField.getText());
            discount = Double.parseDouble(discountField.getText());
            total = Double.parseDouble(totalField.getText());
            cash = Double.parseDouble(cashField.getText());
            change = Double.parseDouble(changeField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (discount < 5 || discount > 50) {
            JOptionPane.showMessageDialog(this, "Discount should be between 5% and 50%", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cash < total) {
            JOptionPane.showMessageDialog(this, "Cash value should be higher or equal to total amount", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (FileWriter writer = new FileWriter("Customer.txt", true)) {
            writer.write("Customer Name: " + customerName + "\n");
            writer.write("Product: " + productName + "\n");
            writer.write("Quantity: " + quantity + "\n");
            writer.write("Discount: " + discount + "%\n");
            writer.write("Total: " + total + "\n");
            writer.write("Cash: " + cash + "\n");
            writer.write("Change: " + change + "\n");
            writer.write("Date: " + new Date().toString() + "\n");
            writer.write("-------------------------------\n");
            JOptionPane.showMessageDialog(this, "Order saved successfully.");
            clearInputs();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving order.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputs() {
        customerNameField.setText("");
        quantityField.setText("");
        discountField.setText("");
        cashField.setText("");
        totalField.setText("");
        changeField.setText("");
    }

    public static void main(String[] args) {
        new OrderFrame();
    }
}

