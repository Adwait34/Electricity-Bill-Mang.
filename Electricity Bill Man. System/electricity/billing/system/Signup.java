package electricity.billing.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.Image;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
// import java.sql.Statement;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;

public class Signup extends JFrame implements ActionListener{

    Choice signupChoice;
    JTextField textUsername, textName, textPassword, meterText, employerText;
    JButton create, back;

    Signup()
    {
        super("Signup");        
        getContentPane().setBackground(new Color(168, 203, 255));
        JLabel createAs = new JLabel("Create Account as");
        createAs.setBounds(200, 30, 150, 20);
        add(createAs);
        
        signupChoice = new Choice();
        signupChoice.add("Admin");
        signupChoice.add("Customer");
        signupChoice.setBounds(350, 30, 100, 20);
        add(signupChoice);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(200, 70, 150, 20);
        meterNo.setVisible(false);
        add(meterNo);

        meterText = new JTextField();
        meterText.setBounds(330, 70, 150, 20);
        meterNo.setVisible(false);
        add(meterText);

        JLabel employer = new JLabel("Employer ID");
        employer.setBounds(200, 70, 150, 20);
        employer.setVisible(true);
        add(employer);

        employerText = new JTextField();
        employerText.setBounds(330, 70, 150, 20);
        employerText.setVisible(true);
        add(employerText);
       

        JLabel userName = new JLabel("UserName");
        userName.setBounds(200, 110, 150, 20);
        add(userName);

        textUsername = new JTextField();
        textUsername.setBounds(330, 110, 150, 20);
        add(textUsername);

        JLabel name = new JLabel("Name");
        name.setBounds(200, 150, 190, 20);
        add(name);

        textName = new JTextField();
        textName.setBounds(330, 150, 150, 20);
        add(textName);

        meterText.addFocusListener(new FocusListener() {
            
         
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    databaseConnection c = new databaseConnection();
                    ResultSet resultSet = c.statement.executeQuery("select * from Signup  where meterNo = '"+meterText.getText()+"'");
                    if (resultSet.next()){
                        textName.setText(resultSet.getString("name"));
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });
        

        JLabel password = new JLabel("Password");
        password.setBounds(200, 190, 150, 20);
        add(password);

        textPassword = new JTextField();
        textPassword.setBounds(330, 190, 150, 20);
        add(textPassword);

        signupChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e){
                String user = signupChoice.getSelectedItem();
                if(user.equals("Customer"))
                {
                    employer.setVisible(false);
                    employerText.setVisible(false);
                    meterNo.setVisible(true);
                    meterText.setVisible(true);
                }
                else
                {
                    employer.setVisible(true);
                    employerText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }
        
            }
        });

        create = new JButton("Create");
        create.setBackground(new Color(66, 127, 219));
        create.setForeground(Color.BLACK);
        create.setBounds(250, 230, 80, 20);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBackground(new Color(66, 127, 219));
        back.setForeground(Color.BLACK);
        back.setBounds(400, 230, 80, 20);
        back.addActionListener(this);
        add(back);

        ImageIcon signUp = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image signupOne = signUp.getImage().getScaledInstance(160, 180, Image.SCALE_DEFAULT);
        ImageIcon signupOne2 = new ImageIcon(signupOne);
        JLabel imageLabell = new JLabel(signupOne2);
        imageLabell.setBounds(10, 15, 150, 225);
        add(imageLabell);        

        setSize(550, 350);
        setLocation(500, 100);
        setLayout(null);
        setVisible(true);

        
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==create){
            String sloginAs = signupChoice.getSelectedItem();
            String susername = textUsername.getText();
            String sname = textName.getText();
            String smeter = meterText.getText();
            String spassword = textPassword.getText(); 
            try{
                databaseConnection c= new databaseConnection();
                String query = null;
                
                
                query = "insert into Signup value('"+susername+"', '"+smeter+"','"+sname+"', '"+spassword+"','"+sloginAs+"')";
                                
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Account Created");
                setVisible(false);
                new Login();
            }
            catch(Exception E){
                E.printStackTrace();
            }

        }
        else if(e.getSource()==back) {
            setVisible(false);
            new Login();
        }
    }
    public static void main(String[] args)
    {
        new Signup();
    }
}