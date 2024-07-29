import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MainFrame extends JFrame implements ActionListener{

    JButton confirmButton;
    JButton createAccount;
    JTextField accountNumberTextField;
    JTextField accountPasswordTextField;
    JLabel accountEnterLabel;
    JLabel passwordEnterLabel;
    JLabel errorMessageLabel;
    ImageIcon image = new ImageIcon("icon.png");
    JSONArray recordsList;

    MainFrame(JSONArray records) { // Frame constructor
        
        this.setTitle("Main Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(500, 500); // Temp size
        this.recordsList = records;

        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(64, 115, 255));

        configureComponents();
        registerComponents();

        this.setVisible(true);
    }
    
    public void registerComponents() {
        this.add(confirmButton);
        this.add(createAccount);
        this.add(accountNumberTextField);
        this.add(accountPasswordTextField);
        this.add(accountEnterLabel);
        this.add(passwordEnterLabel);
        this.add(errorMessageLabel);
    }

    public void configureComponents() {
        configureButtons();
        configureTextField();
        configureLabels();
    }

    public void configureButtons() {
        confirmButton = new JButton();
        confirmButton.setBounds(150, 350, 100, 50);
        confirmButton.addActionListener(this);
        confirmButton.setText("Login");
        confirmButton.setFocusable(false);

        createAccount = new JButton();
        createAccount.setBounds(250, 350, 100, 50);
        createAccount.addActionListener(this);
        createAccount.setText("Create");
        createAccount.setFocusable(false);
    }

    public void configureTextField() {
        accountNumberTextField = new JTextField();
        accountPasswordTextField = new JTextField();
        accountNumberTextField.setBounds(125, 200, 250, 20);
        accountPasswordTextField.setBounds(125, 250, 250, 20);
    }

    public void configureLabels() {
        accountEnterLabel = new JLabel();
        passwordEnterLabel = new JLabel();
        errorMessageLabel = new JLabel();

        accountEnterLabel.setBounds(50, 200, 75, 20);
        accountEnterLabel.setText("Account:");
        passwordEnterLabel.setBounds(50, 250, 75, 20);
        passwordEnterLabel.setText("Password:");
        errorMessageLabel.setBounds(100, 300, 250, 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmButton) {
            if (accountNumberTextField.getText().equals("admin") && accountPasswordTextField.getText().equals("Beans")) {
                new AdminFrame(recordsList);
                this.setVisible(false);
                this.dispose();
            }
            else {
                errorMessageLabel.setText("");
                String account = accountNumberTextField.getText();
                recordsList.forEach( rcd -> parseRecordObject( (JSONObject) rcd , account) );
                if (errorMessageLabel.getText().equals("")) {
                    errorMessageLabel.setText("Account Not In Records");
                }
            }
        }
        else if (e.getSource()==createAccount) {
            new AccountCreateFrame(this.recordsList);
        }
    }

    private void parseRecordObject(JSONObject record, String inputAccount) 
    {

        JSONObject recordObject = (JSONObject) record.get(inputAccount);

        if (recordObject != null) {
            String passwordContents = (String) recordObject.get("password");
            String inputPassword = accountPasswordTextField.getText();

            if (passwordContents.equals(inputPassword)) {
                new UserFrame(recordObject, this.recordsList, inputAccount);
                this.setVisible(false);
                this.dispose();
            }
            else {
                errorMessageLabel.setText("Password Incorrect");
            }
        }
         
    }

}
