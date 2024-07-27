import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AccountCreateFrame extends JFrame implements ActionListener {
    
    JSONArray recordsList;
    JButton confirmButton;
    JButton randomNumberButton;
    JButton returnButton;
    JTextField accountNumberTextField;
    JTextField accountNameTextField;
    JTextField accountBalanceTextField;
    JTextField accountPasswordTextField;
    JLabel accountNumberLabel;
    JLabel accountNameLabel;
    JLabel accountBalanceLabel;
    JLabel accountPasswordLabel; 

    AccountCreateFrame(JSONArray records) {
        this.setTitle("Account Creation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(500, 500); // Temp size

        this.recordsList = records;

        configureComponents();
        registerComponents();

        this.setVisible(true);
    }

    public int generateRandomNum() {
        Random rand = new Random();
        int number = rand.nextInt(1000); // Generate number between 0 and 999
        return number;
    }

    public void configureButtons() {
        confirmButton = new JButton();
        confirmButton.setBounds(200, 400, 100, 50);
        confirmButton.addActionListener(this);
        confirmButton.setText("Confirm");
        confirmButton.setFocusable(false);

        randomNumberButton = new JButton();
        randomNumberButton.setBounds(50, 400, 100, 50);
        randomNumberButton.addActionListener(this);
        randomNumberButton.setText("Random Num");
        randomNumberButton.setFocusable(false);

        returnButton = new JButton();
        returnButton.setBounds(350, 400, 100, 50);
        returnButton.addActionListener(this);
        returnButton.setText("Return");
        returnButton.setFocusable(false);
    }

    public void configureTextFields() {
        accountNumberTextField = new JTextField();
        accountNameTextField = new JTextField();
        accountBalanceTextField = new JTextField();
        accountPasswordTextField = new JTextField();

        accountNumberTextField.setBounds(100, 100, 250, 20);
        accountNameTextField.setBounds(100, 150, 250, 20);
        accountBalanceTextField.setBounds(100, 200, 250, 20);
        accountPasswordTextField.setBounds(100, 250, 250, 20);
    }

    public void configureLabels() {
        accountNumberLabel = new JLabel("ID Number: ");
        accountNameLabel = new JLabel("Name: ");
        accountBalanceLabel = new JLabel("Balance: ");
        accountPasswordLabel = new JLabel("Password: "); 

        accountNumberLabel.setBounds(25, 100, 75, 20);
        accountNameLabel.setBounds(25, 150, 75, 20);
        accountBalanceLabel.setBounds(25, 200, 75, 20);
        accountPasswordLabel.setBounds(25, 250, 75, 20); 
    }

    public void configureComponents() {
        configureButtons();
        configureTextFields();
        configureLabels();
    }

    public void registerComponents() {
        this.add(confirmButton);
        this.add(randomNumberButton);
        this.add(accountNumberTextField);
        this.add(accountNameTextField);
        this.add(accountBalanceTextField);
        this.add(accountPasswordTextField);
        this.add(accountNumberLabel);
        this.add(accountNameLabel);
        this.add(accountBalanceLabel);
        this.add(accountPasswordLabel);
        this.add(returnButton);
    }

    public void writeToFile() {
        try (FileWriter file = new FileWriter("records.json")) {
            file.write(this.recordsList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==randomNumberButton) {
            accountNumberTextField.setText(String.valueOf(generateRandomNum()));
        }
        else if (e.getSource()==confirmButton) {
            JSONObject accountDetails = new JSONObject();
            accountDetails.put("name", accountNameTextField.getText());
            accountDetails.put("password", accountPasswordTextField.getText());
            accountDetails.put("balance", accountBalanceTextField.getText());

            JSONObject newAccount = new JSONObject();
            newAccount.put(accountNumberTextField.getText(), accountDetails);

            this.recordsList.add(newAccount);
            System.out.println(recordsList);
            writeToFile();
        }
        else if (e.getSource()==returnButton) {
            this.setVisible(false);
            this.dispose();
        }
    }
}
