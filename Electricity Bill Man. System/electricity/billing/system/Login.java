package electricity.billing.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
// import java.awt.*;

public class Login extends JFrame implements ActionListener{

    JTextField userText, passwordText;

    Choice loginChoice;

    JButton loginButton, cancelButton, signupButton;
    Login()
    {
        super("Login");
        getContentPane().setBackground(Color.white);
        JLabel username = new JLabel("Username");
        username.setBounds(200, 35, 100, 20);
        add(username);

        userText = new JTextField();
        userText.setBounds(300,35,100,20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(200, 75,100,20);
        add(password);

        passwordText = new JTextField();
        passwordText.setBounds(300,75,100,20);
        add(passwordText);

        JLabel login = new JLabel("Login");
        login.setBounds(200, 115,100,20);
        add(login);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(300, 115,100,20);
        add(loginChoice);

          
        loginButton = new JButton("Login");
        loginButton.setBounds(250, 160, 75, 20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(350, 160, 75, 20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(300, 190, 75, 20);
        signupButton.addActionListener(this);
        add(signupButton);


        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/profile.jpg"));
        Image fprofileOne = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon profileOne2 = new ImageIcon(fprofileOne);
        JLabel imageLabel = new JLabel(profileOne2);
        imageLabel.setBounds(0, 15, 195, 225);
        add(imageLabel);






        setSize(500,350);
        setLocation(500, 100);
        setLayout(null);        
        setVisible(true);

        
    }

    public void actionPerformed(ActionEvent e){
            if(e.getSource()== loginButton){
                String lpassword = passwordText.getText();
                String luser = loginChoice.getSelectedItem();
                String lusername = userText.getText();
                

                try {
                    databaseConnection c = new databaseConnection();
                    String query = "select * from Signup where username = '"+lusername+"' and password =  '"+lpassword+"' and usertype = '"+luser+"'";
                    ResultSet resultset = c.statement.executeQuery(query);

                    if(resultset.next()){
                        String meter = resultset.getString("meterNo");
                        setVisible(false);
                        new mainClass(luser,meter);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Invalid Login Credentials");
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
            else if(e.getSource() == signupButton ){
                setVisible(false);
                new Signup();
            }
            else if(e.getSource() == cancelButton){
                setVisible(false);
            }
            
        }
    public static void main(String[] args) {
        new Login();
    }
}