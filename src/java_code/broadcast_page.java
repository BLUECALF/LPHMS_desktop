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
import java.awt.print.PrinterException;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author kiptala
 */
public class broadcast_page extends JFrame implements ActionListener {
    
    // needed strings
    String email ="";
    String subject ="";
    String body = "";
    String to="";
    java_mail jm;

        
    //labels
    JLabel title;
    
    //radio buttons
    JRadioButton clients;
    JRadioButton residents;
    JRadioButton staffs;
    ButtonGroup to_group;
  
    JLabel to_label =  new JLabel(" To :");
    JLabel subject_label = new JLabel("Subject :  "); 
    JLabel body_label = new JLabel("Body : "); 
   
    // text areas
    
    JTextArea subject_field = new JTextArea();
    JTextArea body_field = new JTextArea();
    
    
    // print buttons
    JButton send_button = new JButton("send emails");
   
    JButton back = new JButton("Back");
    
    
    java_sql_helper db;
    
    broadcast_page(java_sql_helper db)
    {
        this.db = db;
        jm = new java_mail();
        
        this.setTitle(" Broad cast Email Form ");
        this.setSize(1300,710);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setBackground(new Color(152,13,45));
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(true);
        
        // title_label 
        title =  new JLabel();
        title.setText("LEXX PLACE HOTEL broadcast email page");
        title.setFont(new Font("mv boli",Font.BOLD,20));
        title.setForeground(Color.LIGHT_GRAY);
        title.setBounds(150,0,600,50);
        title.setHorizontalTextPosition(JLabel.CENTER);
        
        
        // to label
        to_label.setBounds(100,100,300,50);
        to_label.setFont(new Font("mv boli",Font.BOLD,15));
        to_label.setForeground(Color.LIGHT_GRAY);
        
        // radio buttons for recivers.
     clients = new JRadioButton("clients");
      clients.setBounds(100,150,200,20);
     clients.setFont(new Font("mv boli",Font.BOLD,18));
      clients.addActionListener(this);     
       clients.setForeground(Color.LIGHT_GRAY);
     clients.setOpaque(false);
      
      residents = new JRadioButton("residents");
      residents.setBounds(300,150,200,20);
      residents.setFont(new Font("mv boli",Font.BOLD,18));
      residents.addActionListener(this);
      residents.setForeground(Color.LIGHT_GRAY);
      residents.setOpaque(false);
      
      staffs = new JRadioButton("staffs");
      staffs.setBounds(500,150,200,20);
      staffs.setFont(new Font("mv boli",Font.BOLD,18));
      staffs.addActionListener(this);
      staffs.setForeground(Color.LIGHT_GRAY);
      staffs.setOpaque(false);
     
      
     // button group      
      to_group = new ButtonGroup();
      to_group.add(clients);
      to_group.add(residents);
      to_group.add(staffs);
      
      
      // subject label
     
        subject_label.setBounds(100,200,150,60);
        subject_label.setFont(new Font("mv boli",Font.BOLD,17));
        subject_label.setForeground(Color.LIGHT_GRAY);
        
        // subject field
        subject_field.setBounds(260,210,440,40);
        subject_field.setFont(new Font("mv boli",Font.BOLD,17));
        subject_field.setForeground(Color.BLACK);
        
        // body label
     
        body_label.setBounds(100,270,300,50);
        body_label.setFont(new Font("mv boli",Font.BOLD,17));
        body_label.setForeground(Color.LIGHT_GRAY);
        
        // body field
        body_field.setBounds(100,280,600,200);
        body_field.setFont(new Font("mv boli",Font.BOLD,16));
        body_field.setForeground(Color.BLACK);
     
        
        send_button.setBounds(200,550,200,50);
        send_button.addActionListener(this);
        send_button.setFocusable(false);        
        
        //back button
        back.setBounds(50,600,100,20);
        back.addActionListener(this);
        
        this.add(title);    
        this.add(subject_label);
        this.add(subject_field);
        this.add(body_field);
        this.add(body_label);
        this.add(clients);
        this.add(residents);
        this.add(staffs);
        this.add(send_button);        
        this.add(back);
       
    }
    
    // on click action performed code----------

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==residents)
        {to = "residents";}
        else if(ae.getSource()==clients)
        {to = "clients";}
         else if(ae.getSource()==staffs)
        {to = "staffs";}
        
        if(ae.getSource()==send_button)
        {
            send_button.setEnabled(false);
            send();
            send_button.setEnabled(true);
            
        }  
         if(ae.getSource()==back)
        {
           this.dispose();
        } 

        
    }
    
    //methods for Send email
    public void send()
    {
        try {
            // check if all fields are filled.
            if(to.equals("")||
                    subject_field.getText().equals("")||
                    body_field.getText().equals("")
                    )
            {
                // tell him to fill all fields
                JOptionPane.showMessageDialog(null, "Fill All fields For send to occur ","Warning" , JOptionPane.ERROR_MESSAGE);        
                return;
            }
            // here all details are ok..  now we find approperiate send.
            String table  = to;
            
            String query = "SELECT * from "+table;
            ResultSet rs = db.query_function(query);
            
            while(rs.next() == true)
            {
                // there is data in this group
                
                email = rs.getString("email");
                
                // check if email is equal to "null" coz of users without emails.
               if(email.equals("null"))
               {
               continue;
               }
               // from here email is valid and can be sent information.
               subject = subject_field.getText();
               body =  body_field.getText();
               
               // now we send him email.
                jm.sendMail(email, subject, body);
            }
            
            // when all sending has happended succesfully
             JOptionPane.showMessageDialog(null, "all mails Were sent successfully to "+ to,"SUCCESS" , JOptionPane.INFORMATION_MESSAGE);        
            
            subject_field.setText("");
            body_field.setText("");
            to_group.clearSelection();
            email=""; 
            to= "";
            body ="";
            subject="";
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex.toString(),"SQlError", JOptionPane.ERROR_MESSAGE); return;       
        } catch (MessagingException ex) {
             JOptionPane.showMessageDialog(null, ex.toString()," Email Error", JOptionPane.ERROR_MESSAGE);        
        }
    
    }
       

}

        
    
    
    

