package Finals_OOP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


//nakakapagod n
//may comments n


//INHERITANCE
class OrderFrame extends JFrame implements ActionListener {
    private JTextField customerNameField;
    private JTextField[] quantityFields;
    private JTextField discountField;
    private JTextField cashField;
    private JTextField totalField;
    private JTextField changeField;
    private JButton calculateTotalButton, calculateChangeButton;
    private JButton saveButton;

    private final Product[] products = {
            new Product("Toblerone", 55.00),
            new Product("Snickers", 25.00),
            new Product("Hershey", 45.00),
            new Product("Baby Ruth", 68.00),
            new Product("Kit Kat", 34.00)
    };

    public OrderFrame() {
        super("Product Order");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        Color backgroundColor = new Color(250, 250, 250);
        Color fieldColor = new Color(230, 230, 230);
        Color buttonColor = new Color(60, 63, 65);
        Color buttonTextColor = new Color(250, 250, 250);


        getContentPane().setBackground(backgroundColor);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(backgroundColor );
        JLabel headerLabel = new JLabel("Order Form");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(buttonColor);
        headerPanel.add(headerLabel);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        c.insets = new Insets(10, 10, 10, 10);
        add(headerPanel, c);

        c.gridwidth = 1;

        // Customer Name
        JLabel customerNameLabel = new JLabel("Customer Name: ");
        customerNameLabel.setFont(labelFont);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 1;
        add(customerNameLabel, c);

        customerNameField = new JTextField(20);
        customerNameField.setFont(fieldFont);
        customerNameField.setBackground(fieldColor);
        customerNameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        c.gridx = 1;
        c.gridy = 1;
        add(customerNameField, c);
        
        customerNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && !Character.isWhitespace(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        // Date
        JLabel dateLabel = new JLabel("Date: ");
        dateLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 2;
        add(dateLabel, c);

        JTextField dateField = new JTextField(20);
        dateField.setFont(fieldFont);
        dateField.setBackground(fieldColor);
        dateField.setText(new Date().toString());
        dateField.setEditable(false);
        dateField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        c.gridx = 1;
        c.gridy = 2;
        add(dateField, c);

        // Product Selection & Quantity
        JLabel productLabel = new JLabel("Products: ");
        productLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(productLabel, c);

        JPanel productPanel = new JPanel(new GridBagLayout());
        productPanel.setBackground(backgroundColor);
        GridBagConstraints pc = new GridBagConstraints();
        pc.insets = new Insets(5, 5, 5, 5);

        quantityFields = new JTextField[products.length];

        for (int i = 0; i < products.length; i++) {
            JLabel productNameLabel = new JLabel(products[i].getName());
            productNameLabel.setFont(fieldFont);
            pc.gridx = 0;
            pc.gridy = i;
            pc.anchor = GridBagConstraints.WEST;
            productPanel.add(productNameLabel, pc);
            
            
            JLabel priceLabel = new JLabel(" ₱" + products[i].getPrice());
            priceLabel.setFont(fieldFont);
            pc.gridx = 1;
            pc.gridy = i;
            pc.anchor = GridBagConstraints.WEST;
            productPanel.add(priceLabel, pc);

            JLabel quantityLabel = new JLabel("    Qty: ");
            quantityLabel.setFont(fieldFont);
            pc.gridx = 2;
            pc.gridy = i;
            pc.anchor = GridBagConstraints.CENTER;
            productPanel.add(quantityLabel, pc);

            JTextField quantityField = new JTextField("0", 5);
            quantityField.setFont(fieldFont);
            quantityField.setForeground(Color.GRAY);
            quantityField.setBackground(fieldColor);
            quantityField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            pc.gridx = 3;
            pc.gridy = i;
            pc.anchor = GridBagConstraints.CENTER;
            productPanel.add(quantityField, pc);

            quantityField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (quantityField.getText().equals("0")) {
                        quantityField.setText("");
                        quantityField.setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (quantityField.getText().isEmpty()) {
                        quantityField.setText("0");
                        quantityField.setForeground(Color.GRAY);
                    }
                }
            });

            quantityField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                        e.consume();
                    }
                }
            });

            quantityFields[i] = quantityField;
        }

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        add(productPanel, c);

        // Discount
        JLabel discountLabel = new JLabel("Discount (%): ");
        discountLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        add(discountLabel, c);

        discountField = new JTextField("0", 20);
        discountField.setFont(fieldFont);
        discountField.setForeground(Color.GRAY);
        discountField.setBackground(fieldColor);
        discountField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        c.gridx = 1;
        c.gridy = 5;
        add(discountField, c);

        discountField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (discountField.getText().equals("0")) {
                    discountField.setText("");
                    discountField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (discountField.getText().isEmpty()) {
                    discountField.setText("0");
                    discountField.setForeground(Color.GRAY);
                }
            }
        });

        discountField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != '.') {
                    e.consume();
                }
            }
        });

        // note for disc --- ok
        JLabel discountNoteLabel = new JLabel("Note: Discount should be 0% or between 5% and 50%");
        discountNoteLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 12));
        discountNoteLabel.setForeground(Color.RED);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        add(discountNoteLabel, c);

        // Total
        JLabel totalLabel = new JLabel("Total: ");
        totalLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 7;
        add(totalLabel, c);

        totalField = new JTextField(20);
        totalField.setFont(fieldFont);
        totalField.setBackground(fieldColor);
        totalField.setEditable(false);
        totalField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        c.gridx = 1;
        c.gridy = 7;
        add(totalField, c);

        // Calculate Total button
        calculateTotalButton = new JButton("Calculate Total");
        calculateTotalButton.setFont(buttonFont);
        calculateTotalButton.setBackground(buttonColor);
        calculateTotalButton.setForeground(buttonTextColor);
        c.gridx = 2;
        c.gridy = 7;
        add(calculateTotalButton, c);

        // Cash
        JLabel cashLabel = new JLabel("Cash: ");
        cashLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 8;
        add(cashLabel, c);

        cashField = new JTextField("0", 20);
        cashField.setFont(fieldFont);
        cashField.setForeground(Color.GRAY);
        cashField.setBackground(fieldColor);
        cashField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        c.gridx = 1;
        c.gridy = 8;
        add(cashField, c);

        cashField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cashField.getText().equals("0")) {
                    cashField.setText("");
                    cashField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cashField.getText().isEmpty()) {
                    cashField.setText("0");
                    cashField.setForeground(Color.GRAY);
                }
            }
        });

        cashField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        // Calculate Change button
        calculateChangeButton = new JButton("Calculate Change");
        calculateChangeButton.setFont(buttonFont);
        calculateChangeButton.setBackground(buttonColor);
        calculateChangeButton.setForeground(buttonTextColor);
        c.gridx = 2;
        c.gridy = 8;
        add(calculateChangeButton, c);

        // Change
        JLabel changeLabel = new JLabel("Change: ");
        changeLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 9;
        add(changeLabel, c);

        changeField = new JTextField(20);
        changeField.setFont(fieldFont);
        changeField.setBackground(fieldColor);
        changeField.setEditable(false);
        changeField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        c.gridx = 1;
        c.gridy = 9;
        add(changeField, c);

        // Save button
        saveButton = new JButton("Save");
        saveButton.setFont(buttonFont);
        saveButton.setBackground(buttonColor);
        saveButton.setForeground(buttonTextColor);
        c.gridx = 2;
        c.gridy = 9;
        add(saveButton, c);

        // Action listeners ????? hwhhhihai
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
        double total = 0;
     
        // Calculate total without discount
        for (int i = 0; i < products.length; i++) {
            try {
                int quantity = Integer.parseInt(quantityFields[i].getText());
                total += products[i].getPrice() * quantity;
            } catch (NumberFormatException ignored) {
            }
        }

        // Apply discount if valid
        try {
            double discount = Double.parseDouble(discountField.getText().trim());
            if (!(discount == 0 || (discount >= 5 && discount <= 50))) {
                JOptionPane.showMessageDialog(this, "Discount should be either 0% or between 5% and 50%", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calculate total with discount
            total *= (1 - discount / 100.0);

        } catch (NumberFormatException ignored) {
        }

        // Update the total field
        totalField.setText(String.format("%.2f", total));
    }

    private void calculateChange() {
    	
    	
        calculateTotal();
        
        
        try {
            double total = Double.parseDouble(totalField.getText());
            double cash = Double.parseDouble(cashField.getText());

            if (cash < total) {
                JOptionPane.showMessageDialog(this, "Enter appropriate cash value", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double change = cash - total;
            changeField.setText(String.format("%.2f", change));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    // eXCEPTION HANDLING (TRY-CATCH FOR POTENTIAL ERRORS DURING FILE WRITING)
    private void saveOrder() {
        String customerName = customerNameField.getText().trim();
        String discountText = discountField.getText().trim();
        String cashText = cashField.getText().trim();
        String totalText = totalField.getText().trim();
        String changeText = changeField.getText().trim();

        // Validation if may customer name ba and minimum 1 qty
        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean hasNonZeroQuantity = false;
        for (JTextField quantityField : quantityFields) {
            if (!quantityField.getText().trim().equals("0")) {
                hasNonZeroQuantity = true;
                break;
            }
        }

        if (!hasNonZeroQuantity) {
            JOptionPane.showMessageDialog(this, "Please enter a minimum quantity of 1 for at least one product.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double tot = Double.parseDouble(totalText);
        double moolah = Double.parseDouble(cashText);

        if (moolah < tot) {
            JOptionPane.showMessageDialog(this, "Enter appropriate cash value", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save order
        double total = Double.parseDouble(totalText);
        double cash = Double.parseDouble(cashText);
        double change = Double.parseDouble(changeText);
        double discount = Double.parseDouble(discountText);
        
        //format sa txt pwede palitan pero ok na q dito tbh
        try (FileWriter writer = new FileWriter("Customer.txt", true)) {
            writer.write("Customer Name: " + customerName + "\n");
            for (int i = 0; i < products.length; i++) {
                int quantity = Integer.parseInt(quantityFields[i].getText());
                if (quantity > 0) {
                    String productName = products[i].getName();
                    writer.write("Product: " + productName + " | Quantity: " + quantity + "\n");
                }
            }

            writer.write("Discount: " + discount + "%\n");
            writer.write("Total: " + total + "\n");
            writer.write("Cash: " + cash + "\n");
            writer.write("Change: " + change + "\n");
            writer.write("Date: " + new Date().toString() + "\n");
            writer.write("-------------------------------\n");
            JOptionPane.showMessageDialog(this, "Order saved successfully.");
            clearInputs();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving order.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    // clear inputs 
    private void clearInputs() {
        customerNameField.setText("");

        discountField.setText("0");
        discountField.setForeground(Color.GRAY);

        cashField.setText("0");
        cashField.setForeground(Color.GRAY);

        totalField.setText("");
        changeField.setText("");

        for (JTextField quantityField : quantityFields) {
            quantityField.setText("0");
            quantityField.setForeground(Color.GRAY); 
        }
    }

}