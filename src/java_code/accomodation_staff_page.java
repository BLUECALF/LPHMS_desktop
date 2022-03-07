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
import java.util.Calendar;
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
public class accomodation_staff_page extends JFrame implements ActionListener{
    
    
    JLabel title;
    JLabel instructions;        
    
    JLabel checkin_label;
    
    JLabel booking_id_label;
    JLabel resident_id_label;
    
    JLabel checkout_label;
    
    JTextArea booking_id_field;
    JTextArea resident_id_field;
    
    JLabel bottom_instructions;
    JButton checkin_button;
    JButton checkout_button;
    
    JButton reserve_room_button;
    
    JLabel cancel_label;
    JLabel boking_id_label;
    JTextArea booking_id_area;
    
    JButton cancel_reservation_button;
    
    JButton exit_button;
    
    java_sql_helper db;
    accomodation_staff_page(java_sql_helper db)
    {
        this.db = db;
         System.out.println("hello i am accomodation staff module");
         // constructor code
        this.setTitle("Lexx Place Hotel Staff Module ");
        this.setSize(500,800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(152,13,45));
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        //this.setResizable(false);
        
        
        // configuring components
        title =  new JLabel();
        title.setText("LEXX PLACE HOTEL");
        title.setFont(new Font("mv boli",Font.BOLD,18));
        title.setForeground(Color.WHITE);
        title.setBounds(150,50,200,50);
        title.setHorizontalTextPosition(JLabel.CENTER);
        
        instructions =  new JLabel();
        instructions.setText(" Accomodation Staff Page");
        instructions.setFont(new Font("mv boli",Font.BOLD,13));
        instructions.setForeground(Color.LIGHT_GRAY);
        instructions.setBounds(50,100,400,50);
        instructions.setHorizontalTextPosition(JLabel.CENTER);
        
        checkin_label =  new JLabel();
        checkin_label.setText("Check-in");
        checkin_label.setFont(new Font("mv boli",Font.BOLD,13));
        checkin_label.setForeground(Color.WHITE);
        checkin_label.setBounds(50,150,100,50);
        checkin_label.setHorizontalTextPosition(JLabel.CENTER);
        
        booking_id_label =  new JLabel();
        booking_id_label.setText("Booking Id");
        booking_id_label.setFont(new Font("mv boli",Font.BOLD,13));
        booking_id_label.setForeground(Color.WHITE);
        booking_id_label.setBounds(50,200,100,50);
        booking_id_label.setHorizontalTextPosition(JLabel.CENTER);
        
        booking_id_field = new JTextArea();
        booking_id_field.setBounds(150,200,100,30);
        booking_id_field.setFont(new Font("mv boli",Font.BOLD,18));
        
        // button should be on right of it
         checkin_button = new JButton("Check In");
        checkin_button.setBounds(270,200,150,30);
        checkin_button.setFont(new Font("mv boli",Font.BOLD,18));
        checkin_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        checkin_button.setFocusable(false);       
        checkin_button.addActionListener(this);
        
        
        checkout_label =  new JLabel();
        checkout_label.setText("Check-Out");
        checkout_label.setFont(new Font("mv boli",Font.BOLD,13));
        checkout_label.setForeground(Color.WHITE);
        checkout_label.setBounds(50,250,100,50);
        checkout_label.setHorizontalTextPosition(JLabel.CENTER);
        
        resident_id_label =  new JLabel();
        resident_id_label.setText("Resident Id");
        resident_id_label.setFont(new Font("mv boli",Font.BOLD,13));
        resident_id_label.setForeground(Color.WHITE);
        resident_id_label.setBounds(50,300,100,50);
        resident_id_label.setHorizontalTextPosition(JLabel.CENTER);
        
        resident_id_field = new JTextArea();
        resident_id_field.setBounds(150,300,100,30);
        resident_id_field.setFont(new Font("mv boli",Font.BOLD,18));
        
        // other button should be next
    // buttons
        checkout_button = new JButton("Check Out");
        checkout_button.setBounds(270,300,150,30);
        checkout_button.setFont(new Font("mv boli",Font.BOLD,18));
        checkout_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        checkout_button.setFocusable(false);        
        checkout_button.addActionListener(this);
        
        // reserve client button
        reserve_room_button = new JButton("Reserve Room");
        reserve_room_button.setBounds(100,350,200,30);
        reserve_room_button.setFont(new Font("mv boli",Font.BOLD,18));
        reserve_room_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        reserve_room_button.setFocusable(false);        
        reserve_room_button.addActionListener(this);
       
        // reserve client button
        cancel_reservation_button = new JButton("Cancel Reservation");
        cancel_reservation_button.setBounds(100,400,200,30);
        cancel_reservation_button.setFont(new Font("mv boli",Font.BOLD,18));
        cancel_reservation_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        cancel_reservation_button.setFocusable(false);        
        cancel_reservation_button.addActionListener(this);
                     
        
        exit_button = new JButton("Exit");
        exit_button.setBounds(50,600,100,20);
        exit_button.setFont(new Font("mv boli",Font.BOLD,18));
        exit_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        exit_button.setFocusable(false);
        exit_button.addActionListener(this);
        // adding components
        
        this.add(instructions);
        this.add(title);
        this.add(checkin_label);
        this.add(booking_id_label);
        this.add(booking_id_field);
        this.add(checkin_button);
        
        this.add(checkout_label);
        this.add(resident_id_label);
        this.add(resident_id_field);
        this.add(checkout_button);
        this.add(reserve_room_button);
        this.add(cancel_reservation_button);
        this.add(exit_button);
        
    }
   

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == checkin_button)
        {
            //disable checkin button
            checkin_button.disable();
            //we checkin a client.
            // get booking id 
            String booking_id = booking_id_field.getText();
            String resident_id;
            String room_no;
            String firstname;
            String email;
            
            String query1 = "SELECT * from residents WHERE resident_id="+"\"" +booking_id+"\"";
            ResultSet rs0 = db.query_function(query1);
            
            try {
                if(rs0.next()==true)
                {
                  JOptionPane.showMessageDialog(null, "Check in Failed,\n You have Already checked in","" , JOptionPane.INFORMATION_MESSAGE);       
                    return;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.toString() ,"SQL error", JOptionPane.INFORMATION_MESSAGE); 
                
            }
            
            String query = "SELECT * from bookings WHERE booking_id="+"\"" +booking_id+"\"";
            ResultSet rs = db.query_function(query);
            
            try {
                if(rs.next()==true)
                {
                    // the booking_id is a true staff
                    // insert his details into resident table.
                    resident_id = rs.getString("booking_id");
                    room_no = rs.getString("room_no");
                    firstname = rs.getString("firstname");
                    email = rs.getString("email");
                    
                     // insert The details Into the Database ;
            String insert_statement_prepared = "INSERT INTO residents VALUES(?,?,?,?)";
            Object[] data = new Object[4];
            data[0] = resident_id;
            data[1] = room_no;
            data[2] = firstname;
            data[3] = email;
            
           
            
            int i;
            for(i=0;i<4;i++){
            System.out.println("value in array of resident is : "+data[i]);}
            
            int rows = db.insert_function(insert_statement_prepared, 4, data);
            
            if(rows==1)
            {
                // insertion was successful ... 
                
                // email him
                String emailed_message = "Dear "+firstname+",\n"+
                       "You Have Checked In to LEXX PLACE HOTEL :"+
                       "\nTo reside at Room number: "+room_no+
                       "\nYour Resident Id is :"+resident_id+   
                       "\nBooked checkin date is :"+rs.getString("checkin_date")+ 
                       "\nYour checkin date is :"+Calendar.getInstance().getTime()+ 
                       "\nIntended check out date is :"+rs.getString("checkout_date")+ 
                       "\n\n\nNOTE:: PLEASE DO NOT DELETE THIS EMAIL IT WILL BE USED AT CHECKOUT";
                
                java_mail jm = new java_mail();
                        try {
                            jm.sendMail(email, "LEXX PLACE HOTEL: information",emailed_message);
                            JOptionPane.showMessageDialog(null, "check In Successful\ncheck your email :"+firstname,"" , JOptionPane.INFORMATION_MESSAGE); 
                        } catch (MessagingException ex) {
                           JOptionPane.showMessageDialog(null,ex.toString() ,"Email send Failed", JOptionPane.INFORMATION_MESSAGE); 
                        }
                
            }
                    
                   
                }
                else
                {
                JOptionPane.showMessageDialog(null, "Check in Failed,\n Booking Id Not Found In Database","" , JOptionPane.INFORMATION_MESSAGE);                
                
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(accomodation_staff_page.class.getName()).log(Level.SEVERE, null, ex);
            }
         checkin_button.enable();
        } 
        if(ae.getSource()== reserve_room_button)
        {
            this.dispose();
            new reserve_room(db);
        }
        if(ae.getSource()== exit_button)
        {this.dispose();
        new staff_login();
        }
           if(ae.getSource()== cancel_reservation_button)
        {
            
        }
    }
    
    
}
