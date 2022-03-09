/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_code;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

/**
 *
 * @author kiptala
 */
public class staff_login extends JFrame implements ActionListener{
    
    JLabel title;
    JLabel instructions;    
    
    
    JLabel email_label;
    JLabel password_label;
    
    JTextArea email_field;
    JPasswordField password_field;
    
    JLabel bottom_instructions;
    JButton login_button;
    JButton signup_button;
    
    JButton exit_button;
    
    java_sql_helper db;
    staff_login()
    {
        
         // constructor code
        this.setTitle("Lexx Place Hotel Staff Module ");
        this.setSize(500,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(152,13,45));
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        
        
        // configuring components
        title =  new JLabel();
        title.setText("LEXX PLACE HOTEL");
        title.setFont(new Font("mv boli",Font.BOLD,18));
        title.setForeground(Color.WHITE);
        title.setBounds(150,50,200,50);
        title.setHorizontalTextPosition(JLabel.CENTER);
        
        instructions =  new JLabel();
        instructions.setText("Staff please login to continue ");
        instructions.setFont(new Font("mv boli",Font.BOLD,13));
        instructions.setForeground(Color.LIGHT_GRAY);
        instructions.setBounds(50,100,400,50);
        instructions.setHorizontalTextPosition(JLabel.CENTER);
        
        email_label =  new JLabel();
        email_label.setText("Email : ");
        email_label.setFont(new Font("mv boli",Font.BOLD,13));
        email_label.setForeground(Color.WHITE);
        email_label.setBounds(50,150,100,50);
        email_label.setHorizontalTextPosition(JLabel.CENTER);
        
        email_field = new JTextArea();
        email_field.setBounds(150,150,200,30);
        email_field.setFont(new Font("mv boli",Font.BOLD,18));
        
        password_label =  new JLabel();
        password_label.setText("Password : ");
        password_label.setFont(new Font("mv boli",Font.BOLD,13));
        password_label.setForeground(Color.WHITE);
        password_label.setBounds(50,200,100,50);
        password_label.setHorizontalTextPosition(JLabel.CENTER);
        
        
        password_field = new JPasswordField();
        password_field.setBounds(150,200,200,30);
        password_field.setFont(new Font("mv boli",Font.BOLD,18));
        
        // buttons
        login_button = new JButton("Login");
        login_button.setBounds(150,250,100,30);
        login_button.setFont(new Font("mv boli",Font.BOLD,18));
        login_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        login_button.setFocusable(false);
        login_button.setBackground(Color.cyan);
        login_button.addActionListener(this);
       
                     
        
        exit_button = new JButton("Exit");
        exit_button.setBounds(50,300,100,20);
        exit_button.setFont(new Font("mv boli",Font.BOLD,18));
        exit_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        exit_button.setFocusable(false);
        exit_button.addActionListener(this);
        // adding components
        
        this.add(instructions);
        this.add(title);
        this.add(email_label);
        this.add(email_field);
        this.add(password_label);
        this.add(password_field);
        
        this.add(login_button);       
        this.add(exit_button);
        
    }
    
    public static void main(String[]args)
    {
        System.out.println("hello i am customer module");
        
      staff_login object =  new staff_login();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == login_button)
        {
            // check if his details are same in staff records
            
            String email = email_field.getText();
            String password = password_field.getText();
            db = new java_sql_helper();
            
            String query = "SELECT * from staffs WHERE firstname="+"\"" +email+"\""+"and password ="+"\""+password+"\""; 
            ResultSet rs = db.query_function(query);
            
            try {
                if(rs.next()==true)
                {
                    // the staff is a true staff
                    //find hi category
                    if(rs.getString("staff_category").equals("accomodation"))
                    {
                        this.dispose();
                     accomodation_staff_page a_page = new accomodation_staff_page(db,rs.getString("firstname"));
                    }
                    String sc =rs.getString("staff_category");
                    if(sc.equals("food")||sc.equals("laundry")||sc.equals("cleaning"))
                    {
                        this.dispose();
                     common_staff_page f_page = new common_staff_page(db,rs.getString("staff_category"),rs.getString("firstname"));
                    }
                    
                
                }
                else
                {
                JOptionPane.showMessageDialog(null, "Staff Not Found In Database","" , JOptionPane.INFORMATION_MESSAGE);                
                
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(staff_login.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }        
        if(ae.getSource()== exit_button)
        {this.dispose();}
    }
    
    
}
