package Finals_OOP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

//awit sauuu, awittt saaaaakiiiinnnn

//UPDATE: may comments na sha YEHEEEEY

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
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

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
        c.gridy = 1;
        add(dateLabel, c);

        JTextField dateField = new JTextField(20);
        dateField.setFont(fieldFont);
        dateField.setText(new Date().toString());
        dateField.setEditable(false);
        c.gridx = 1;
        c.gridy = 1;
        add(dateField, c);

     // Product Selection & Quantity
        JLabel productLabel = new JLabel("Products: ");
        productLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 2;
        add(productLabel, c);

        JPanel productPanel = new JPanel(new GridLayout(products.length, 3, 5, 5)); 
        quantityFields = new JTextField[products.length];
        
     
   
        for (int i = 0; i < products.length; i++) {
        	
            JLabel productNameLabel = new JLabel(products[i].getName() + " ₱"  + products[i].getPrice()); 
            productNameLabel.setFont(fieldFont);
            productPanel.add(productNameLabel);

            JTextField quantityField = new JTextField();
            quantityField.setFont(fieldFont);
            quantityField.setForeground(Color.GRAY); // Set initial text color to gray

            
            quantityField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (quantityField.getText().equals("0")) {
                        quantityField.setText("");
                        quantityField.setForeground(Color.BLACK); // Change text color to black when focused
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (quantityField.getText().isEmpty()) {
                        quantityField.setText("0");
                        quantityField.setForeground(Color.GRAY); // Change text color to gray when not focused
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
            
            for (JTextField quantityFields : quantityFields) {
                
                quantityField.setText("0");
            }


            productPanel.add(quantityField);
            quantityFields[i] = quantityField;
        }

        c.gridx = 1;
        c.gridy = 2;
        add(productPanel, c);


        // Discount
        JLabel discountLabel = new JLabel("Discount (%): ");
        discountLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 3;
        add(discountLabel, c);

        discountField = new JTextField(20);
        discountField.setText("0");
        discountField.setForeground(Color.GRAY); 
        discountField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 3;
        add(discountField, c);
        
        discountField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (discountField.getText().equals("0")) {
                	discountField.setText("");
                	discountField.setForeground(Color.BLACK); // Change text color to black when focused
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (discountField.getText().isEmpty()) {
                	discountField.setText("0");
                	discountField.setForeground(Color.GRAY); // Change text color to gray when not focused
                }
            }
        });
        
        discountField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
			}
		});
		

        // Note for Discount
        JLabel discountNoteLabel = new JLabel("Note: Discount should be 0% or between 5% and 50%");
        discountNoteLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 12));
        discountNoteLabel.setForeground(Color.RED);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        add(discountNoteLabel, c);

        // Total
        JLabel totalLabel = new JLabel("Total: ");
        totalLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 5;
        add(totalLabel, c);

        totalField = new JTextField(20);
        totalField.setFont(fieldFont);
        totalField.setEditable(false);
        c.gridx = 1;
        c.gridy = 5;
        add(totalField, c);

        // Calculate Total button
        calculateTotalButton = new JButton("Calculate Total");
        calculateTotalButton.setFont(buttonFont);
        calculateTotalButton.setBackground(new Color(144, 238, 144));
        c.gridx = 2;
        c.gridy = 5;
        add(calculateTotalButton, c);

        // Cash
        JLabel cashLabel = new JLabel("Cash: ");
        cashLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 6;
        add(cashLabel, c);

        cashField = new JTextField(20);
        cashField.setFont(fieldFont);
        c.gridx = 1;
        c.gridy = 6;
        add(cashField, c);
        
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
        calculateChangeButton.setBackground(new Color(144, 238, 144));
        c.gridx = 2;
        c.gridy = 6;
        add(calculateChangeButton, c);

        // Change
        JLabel changeLabel = new JLabel("Change: ");
        changeLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 7;
        add(changeLabel, c);

        changeField = new JTextField(20);
        changeField.setFont(fieldFont);
        changeField.setEditable(false);
        c.gridx = 1;
        c.gridy = 7;
        add(changeField, c);

        // Save button
        saveButton = new JButton("Save");
        saveButton.setFont(buttonFont);
        saveButton.setBackground(new Color(135, 206, 235));
        c.gridx = 2;
        c.gridy = 7;
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
            double total = Double.parseDouble(totalField.getText());
            double cash = Double.parseDouble(cashField.getText());
            double change = Double.parseDouble(changeField.getText());
            double discount = Double.parseDouble(discountField.getText());

            try (FileWriter writer = new FileWriter("Customer.txt", true)) {
                writer.write("Customer Name: " + customerName + "\n");
                for (int i = 0; i < products.length; i++) {
                    int quantity = Integer.parseInt(quantityFields[i].getText());
                    if (quantity > 0) {
                        String productName = products[i].getName();
                        writer.write("Product: " + productName + " | " + "Quantity: " + quantity + "\n");
                    
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
                ex.printStackTrace(); // Print the exception for debugging
                JOptionPane.showMessageDialog(this, "Error saving order.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }



        private void clearInputs() {
            customerNameField.setText("");
            discountField.setText("0");
            cashField.setText("");
            totalField.setText("");
            changeField.setText("");

            for (JTextField quantityFields : quantityFields) {
                quantityFields.setText("0");
            }
        }


}