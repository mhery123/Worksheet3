import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Abstract class representing a sales item
abstract class SalesItem {
    protected double price;

    public SalesItem(double price) {
        this.price = price;
    }

    // Abstract method to calculate total sales
    public abstract double calculateTotal();
}

// Concrete class representing a phone
class Phone extends SalesItem {
    private int quantity;

    public Phone(double price, int quantity) {
        super(price);
        this.quantity = quantity;
    }

    // Implementation of calculateTotal method for phones
    public double calculateTotal() {
        return price * quantity;
    }
}

// Concrete class representing a repair service
class RepairService extends SalesItem {
    private int numberOfHours;

    public RepairService(double price, int numberOfHours) {
        super(price);
        this.numberOfHours = numberOfHours;
    }

    // Implementation of calculateTotal method for repair services
    public double calculateTotal() {
        return price * numberOfHours;
    }
}

public class SalesCalculator extends JFrame implements ActionListener {
    private JTextField phonePriceField, phoneQuantityField, repairPriceField, repairHoursField;
    private JButton calculateButton;
    private JLabel phoneTotalLabel, repairTotalLabel;

    public SalesCalculator() {
        setTitle("Sales Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2, 5, 5)); // Changed to 6 rows

        JLabel phonePriceLabel = new JLabel("Phone Price:");
        mainPanel.add(phonePriceLabel);

        phonePriceField = new JTextField();
        mainPanel.add(phonePriceField);

        JLabel phoneQuantityLabel = new JLabel("Quantity Sold:");
        mainPanel.add(phoneQuantityLabel);

        phoneQuantityField = new JTextField();
        mainPanel.add(phoneQuantityField);

        JLabel repairPriceLabel = new JLabel("Repair Price:");
        mainPanel.add(repairPriceLabel); // Changed label text

        repairPriceField = new JTextField();
        mainPanel.add(repairPriceField);

        JLabel repairHoursLabel = new JLabel("Number of Hours:");
        mainPanel.add(repairHoursLabel); // Changed label text

        repairHoursField = new JTextField();
        mainPanel.add(repairHoursField);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        mainPanel.add(calculateButton);

        phoneTotalLabel = new JLabel("Phone Total:");
        mainPanel.add(phoneTotalLabel);

        repairTotalLabel = new JLabel("Repair Total:");
        mainPanel.add(repairTotalLabel);

        add(mainPanel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            try {
                double phonePrice = Double.parseDouble(phonePriceField.getText());
                int phoneQuantity = Integer.parseInt(phoneQuantityField.getText());
                double repairPrice = Double.parseDouble(repairPriceField.getText());
                int repairHours = Integer.parseInt(repairHoursField.getText());

                // Create instances of Phone and RepairService
                Phone phone = new Phone(phonePrice, phoneQuantity);
                RepairService repairService = new RepairService(repairPrice, repairHours);

                // Calculate total sales for each item
                double phoneTotal = phone.calculateTotal();
                double repairTotal = repairService.calculateTotal();

                phoneTotalLabel.setText("Phone Total: ₱" + phoneTotal);
                repairTotalLabel.setText("Repair Total: ₱" + repairTotal);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SalesCalculator();
            }
        });
    }
}
