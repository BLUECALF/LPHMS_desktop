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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
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
public class reserve_room extends JFrame implements ActionListener{
    
    
        Date now_date = null;
        Date checkindate = null;
        Date checkoutdate= null;
    
    JLabel title;
    JLabel instructions;        
        
    JLabel checkin_date_label;  
    
    JLabel checkout_date_label;
    
    JTextArea checkin_date_field;
    JTextArea checkout_date_field;
    JButton search_button;
    JTextArea available_rooms_label;  
    
    JLabel room_no_label;
    JTextArea room_no_field;
    
    // client Fields
    JLabel firstname_label;
    JLabel phone_label;
    
    JTextArea firstname_field;
    JTextArea phone_field;
  
    JButton reserve_button;    
    
    JButton exit_button;
    
    java_sql_helper db;
    reserve_room(java_sql_helper db)
    {
        this.db = db;
         System.out.println("hello i am reserve room staff module");
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
        title.setBounds(150,0,200,50);
        title.setHorizontalTextPosition(JLabel.CENTER);
        
        instructions =  new JLabel();
        instructions.setText(" reserve room Page");
        instructions.setFont(new Font("mv boli",Font.BOLD,13));
        instructions.setForeground(Color.LIGHT_GRAY);
        instructions.setBounds(50,50,400,30);
        instructions.setHorizontalTextPosition(JLabel.CENTER);
        
        
        checkin_date_label =  new JLabel();
        checkin_date_label.setText("Check in date:(Year-month-day eg 2022-3-30)");
        checkin_date_label.setFont(new Font("mv boli",Font.BOLD,13));
        checkin_date_label.setForeground(Color.WHITE);
        checkin_date_label.setBounds(50,100,400,30);
        checkin_date_label.setHorizontalTextPosition(JLabel.CENTER);
        
        checkin_date_field = new JTextArea();
        checkin_date_field.setBounds(50,140,150,30);
        checkin_date_field.setFont(new Font("mv boli",Font.BOLD,18));
        
              
        
        checkout_date_label =  new JLabel();
        checkout_date_label.setText("Check-Out Date:(Year-month-day eg 2022-3-30)");
        checkout_date_label.setFont(new Font("mv boli",Font.BOLD,13));
        checkout_date_label.setForeground(Color.WHITE);
        checkout_date_label.setBounds(50,180,400,30);
        checkout_date_label.setHorizontalTextPosition(JLabel.CENTER);
              
        checkout_date_field = new JTextArea();
        checkout_date_field.setBounds(50,220,150,30);
        checkout_date_field.setFont(new Font("mv boli",Font.BOLD,18));
        
        // other button should be next
    // buttons
        search_button = new JButton("Search Available Rooms");
        search_button.setBounds(150,260,200,30);
        search_button.setFont(new Font("mv boli",Font.BOLD,14));
        search_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        search_button.setFocusable(false);        
        search_button.addActionListener(this);
        
        
       available_rooms_label =  new JTextArea();
        available_rooms_label.setText("Available rooms:");
        available_rooms_label.setFont(new Font("mv boli",Font.BOLD,18));       
        available_rooms_label.setBounds(50,300,400,100);
        available_rooms_label.setEditable(false);
        
        room_no_label =  new JLabel();
        room_no_label.setText("Enter Room number You have chossen:");
        room_no_label.setFont(new Font("mv boli",Font.BOLD,13));
        room_no_label.setForeground(Color.WHITE);
        room_no_label.setBounds(50,410,300,30);
        room_no_label.setHorizontalTextPosition(JLabel.CENTER);
        
        room_no_field = new JTextArea();
        room_no_field.setBounds(50,450,100,30);
        room_no_field.setFont(new Font("mv boli",Font.BOLD,18));
        
        // firstname and phone
        firstname_label =  new JLabel();
        firstname_label.setText("Enter Firstname");
        firstname_label.setFont(new Font("mv boli",Font.BOLD,15));
        firstname_label.setForeground(Color.WHITE);
        firstname_label.setBounds(50,490,200,30);
        firstname_label.setHorizontalTextPosition(JLabel.CENTER);
        
        firstname_field = new JTextArea();
        firstname_field.setBounds(260,490,170,30);
        firstname_field.setFont(new Font("mv boli",Font.BOLD,18));
        
        phone_label =  new JLabel();
        phone_label.setText("Enter Phone Number ");
        phone_label.setFont(new Font("mv boli",Font.BOLD,15));
        phone_label.setForeground(Color.WHITE);
        phone_label.setBounds(50,530,200,30);
        phone_label.setHorizontalTextPosition(JLabel.CENTER);
        
        phone_field = new JTextArea();
        phone_field.setBounds(260,530,170,30);
        phone_field.setFont(new Font("mv boli",Font.BOLD,18));
    
        reserve_button = new JButton("Reserve Room");
        reserve_button.setBounds(200,570,150,30);
        reserve_button.setFont(new Font("mv boli",Font.BOLD,13));
        reserve_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        reserve_button.setFocusable(false);  
        // is dissabled until you find room.
        reserve_button.addActionListener(this);
        reserve_button.setEnabled(false);
       
       
        
        exit_button = new JButton("Exit");
        exit_button.setBounds(50,650,100,20);
        exit_button.setFont(new Font("mv boli",Font.BOLD,18));
        exit_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        exit_button.setFocusable(false);
        exit_button.addActionListener(this);
        // adding components
        
        this.add(instructions);
        this.add(title);
        this.add(checkin_date_label);
        this.add(checkout_date_label);
        this.add(checkin_date_field);
        this.add(checkout_date_field);        
        this.add(search_button);
        this.add(available_rooms_label);
        this.add(room_no_label);
        this.add(room_no_field);
        this.add(firstname_label);
        this.add(phone_label);
        this.add(firstname_field);
        this.add(phone_field);
        this.add(reserve_button);        
        this.add(exit_button);
        
    }
   

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == search_button)
        {
         
          
            // code for finding room
            
        // we have clients details from the Http session
        
        //now get room he needs
        //get checkin date
       String checkin_date_string = checkin_date_field.getText();
        try {
            System.out.println("checkin date"+checkin_date_string);
            checkindate = new SimpleDateFormat("yyyy-MM-dd").parse(checkin_date_string);
            System.out.println("checkin date.to string is :"+checkindate.toString());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),"Wrong checkin date" , JOptionPane.INFORMATION_MESSAGE);                
        }
        
        //get checkin date
       String checkout_date_string = checkout_date_field.getText();
        try {
            checkoutdate = new SimpleDateFormat("yyyy-MM-dd").parse(checkout_date_string);
            System.out.println("checkout date iz:"+checkoutdate.toString());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),"Wrong checkout date" , JOptionPane.INFORMATION_MESSAGE);
        }
        
       // get date now
      now_date = new Date();
      LocalDateTime today = LocalDateTime.now();
      LocalDateTime yesterday = today.minusDays(1);
      Date yesterday_date;
      Calendar c = Calendar.getInstance();
      c.setTime(now_date);
      c.add(Calendar.DATE, -1);
      yesterday_date = c.getTime();
      //minus 1 day from now to get yesterday time like this.
      
        System.out.println("NOW :: is "+now_date.toString());
        System.out.println("Yesteday Date :: is "+yesterday_date.toString());
        System.out.println("Yesterday date using LDT :"+yesterday.toString());
       if(checkindate.before(yesterday_date))
       {
           System.out.println("checkin date is past ");
           JOptionPane.showMessageDialog(null, "checkin date is past ","Past date" , JOptionPane.INFORMATION_MESSAGE);
       }
       else if(checkoutdate.before(checkindate))
       {
          
            JOptionPane.showMessageDialog(null,  "Checkout Date Must be after Checkin date"," Wrong date" , JOptionPane.INFORMATION_MESSAGE);   
       }
       else{
            try {
                // every detail is ok now one can find room
                String query1 = "SELECT room_no FROM bookings where ("+"\""+checkin_date_string+"\""+">= checkin_date and "+"\""+checkin_date_string+"\""+"<= checkout_date)" ;
                String query2 = " OR ("+"\""+checkin_date_string+"\""+"<= checkin_date and "+"\""+checkout_date_string+"\""+">= checkin_date)";
                System.out.println("Room cash query is");
                System.out.println(query1);
                System.out.println(query2);
                String full_query = query1 + query2;
                System.out.println(full_query);
                
                ResultSet rs = db.query_function(full_query);
                System.out.println(rs.toString());
                
                // after this we have found rooms that will be bussy that period.
                String exception ="";
                while(rs.next())
                {
                    exception = exception +"room_no ="+rs.getString("room_no")+" OR ";
                }
                exception = exception +"room_no = \"\"";
                
                 System.out.println("Exception is");
                 System.out.println(exception);
                 
                 // now Fond rooms that will be available by filtering sql statement
                 String available_room_query = "SELECT * from rooms where NOT("+exception+")";
                 System.out.println("");
                 System.out.println(available_room_query);
                 ResultSet rooms_rs = db.query_function(available_room_query);
                 
                 //we can pass a string of rooms.
                 String available_rooms_string ="";
                 while(rooms_rs.next())
                 {
                     System.out.println(rooms_rs.getString("room_no"));
                     available_rooms_string = available_rooms_string + rooms_rs.getString("room_no")+". ";
                 }
                available_rooms_label.setText("Available room numbers,\n\n  " +available_rooms_string);               
                 reserve_button.setEnabled(true);
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex.toString(),"SQL Exception" , JOptionPane.INFORMATION_MESSAGE);
            }          
              
       }
            
        } 
       if(ae.getSource()== reserve_button)
        {
         // if reserve button has been clicked
        
            
            String firstname = firstname_field.getText();           
            String email = "null";
            String room_no = room_no_field.getText();           
            String phone = phone_field.getText();
            
            System.out.println("Checkin Date as an atrribute is"+checkindate);
            
            String booking_id;
            
            // generate booking id
            while (true)
            {
            try {
                Random r = new Random();
                booking_id = r.nextInt(999999) +"";
                
                String query = "SELECT * FROM bookings where booking_id="+"\""+booking_id+"\"";
                ResultSet rs = db.query_function(query);
                if(rs.next()==false)
                { break;}
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.toString() ,"SQL error", JOptionPane.INFORMATION_MESSAGE); 
            }
             
            }
            
            
            
            String insert_statement_prepared = "INSERT INTO bookings VALUES(?,?,?,?,?,?,?)";
            Object[] data = new Object[7];
            
            data[0] = booking_id;
            data[1] = firstname;
            data[2] = email;
            data[3] = room_no;
            data[4] = checkindate;
            data[5] = checkoutdate;
            data[6] = phone;
       
            
            int i;
            for(i=0;i<7;i++){
            System.out.println("value in array is : "+data[i]);}
            
            int rows = db.insert_function(insert_statement_prepared, 7, data);
            
            if (rows==1)
            {
                // record was inserted successfully. 
            
            
            String Message = "Dear "+firstname+",\n"+
                    "You Have Booked Room number :"+room_no+
                    "\nBooking Id is :"+booking_id+
                    "\nCheck-in date or arrival Date is :"+checkindate+
                    "\nCheck-Out Date or Departure date is  :"+checkoutdate;
                        
            JOptionPane.showMessageDialog(null,Message," Reservation Success ", JOptionPane.INFORMATION_MESSAGE); 
            
            }           
            
            
        
        }
        if(ae.getSource()== exit_button)
        {this.dispose();
        new accomodation_staff_page(db);
        }
        
     
    }
    
    
}
