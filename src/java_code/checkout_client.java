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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author kiptala
 */
public class checkout_client extends JFrame implements ActionListener{
    
    
       
        Date checkindate = null;
        Date checkoutdate= null;
        java_sql_helper db;
        String resident_id;
        String staff_name;
        String firstname;
        String phone;
               
        int accommodation_price;
        int laundry_price = 0;
        int food_price = 0;
        int cleaning_price = 0;
        int total_price = 0;
    
    JLabel title;
    JLabel instructions;        
        
    JLabel resident_id_label;         
    
    JTextArea resident_id_field;
    
    JButton display_bill_button;
    JTextArea bill_field; 
    
   JLabel total_price_label;         
    
    JTextArea total_price_field;
        
    JButton checkout_button;    
    
    JButton exit_button;
    
    
    checkout_client(java_sql_helper db,String resident_id,String staff_name)
    {
        this.db = db;
        this.resident_id = resident_id;
        this.staff_name = staff_name;
        
        
         System.out.println("hello i am checkout client module");
         // constructor code
        this.setTitle("Lexx Place Hotel Staff Module ");
        this.setSize(1000,800);
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
        instructions.setText(" Checkout Client ");
        instructions.setFont(new Font("mv boli",Font.BOLD,13));
        instructions.setForeground(Color.LIGHT_GRAY);
        instructions.setBounds(50,50,400,30);
        instructions.setHorizontalTextPosition(JLabel.CENTER);
        
        
        resident_id_label =  new JLabel();
        resident_id_label.setText("Resident Id :");
        resident_id_label.setFont(new Font("mv boli",Font.BOLD,13));
        resident_id_label.setForeground(Color.WHITE);
        resident_id_label.setBounds(50,100,400,30);
        resident_id_label.setHorizontalTextPosition(JLabel.CENTER);
        
        resident_id_field = new JTextArea(resident_id);
        resident_id_field.setBounds(50,140,150,30);
        resident_id_field.setFont(new Font("mv boli",Font.BOLD,18));
        resident_id_field.setEditable(false);
        
               
        // other button should be next
    // buttons
        display_bill_button = new JButton(" Display Bill");
        display_bill_button.setBounds(150,180,200,30);
        display_bill_button.setFont(new Font("mv boli",Font.BOLD,14));
        display_bill_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        display_bill_button.setFocusable(false);        
        display_bill_button.addActionListener(this);
        
        
       bill_field =  new JTextArea();
        bill_field.setText("Current Bill:");
        bill_field.setFont(new Font("mv boli",Font.BOLD,18));       
        bill_field.setBounds(10,220,980,300);
        bill_field.setEditable(false);
        
        
       total_price_label =  new JLabel();
        total_price_label.setText("Total Price");
        total_price_label.setFont(new Font("mv boli",Font.BOLD,13));
        total_price_label.setForeground(Color.WHITE);
        total_price_label.setBounds(50,530,300,30);
        total_price_label.setHorizontalTextPosition(JLabel.CENTER);
        
        total_price_field = new JTextArea();
        total_price_field.setBounds(350,530,150,30);
        total_price_field.setFont(new Font("mv boli",Font.BOLD,18));
        total_price_field.setEditable(false);
        
    
        checkout_button = new JButton(" Checkout Client");
        checkout_button.setBounds(200,570,150,30);
        checkout_button.setFont(new Font("mv boli",Font.BOLD,13));
        checkout_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        checkout_button.setFocusable(false);  
        // is dissabled until you find room.
        checkout_button.addActionListener(this);
        checkout_button.setEnabled(false);       
       
        
        exit_button = new JButton("Exit");
        exit_button.setBounds(50,650,100,20);
        exit_button.setFont(new Font("mv boli",Font.BOLD,18));
        exit_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        exit_button.setFocusable(false);
        exit_button.addActionListener(this);
        // adding components
        
        this.add(instructions);
        this.add(title);
        this.add(resident_id_label);
        this.add(resident_id_field);
        this.add(display_bill_button);
        this.add(bill_field); 
        this.add(total_price_field);
        this.add(total_price_label);
        this.add(checkout_button);        
        this.add(exit_button);
        
    }
   

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == display_bill_button)
        {
            display_bill_button.setEnabled(false);
            view_bill();
            display_bill_button.setEnabled(true);
            checkout_button.setEnabled(true);
        }
        if(ae.getSource() == exit_button)
        {
            this.dispose();
        }
        if(ae.getSource() == checkout_button)
        {
           checkout_button.setEnabled(false);
           checkout_client();
           this.dispose();
        }
        
        
    }
    
    public void view_bill()
    {
        
            try {
                // find his no of days and total price and add it to his bills
                String query = "SELECT * from bookings WHERE booking_id="+"\""+resident_id+"\"";
                System.out.println("Query is::"+query);
                ResultSet rs = db.query_function(query);
                rs.next();
                Date checkin_date = rs.getDate("checkin_date");
                Date checkout_date =rs.getDate("checkout_date");
              firstname = rs.getString("firstname");
                 phone  = rs.getString("phone");
                
                long diff = checkout_date.getTime() - checkin_date.getTime();
                
                System.out.println("The diff is:::"+diff);
                int days = (int)diff/(1000*24*60*60); 
                System.out.println("the days are:::"+days);
                
                // calculate price for accomodation
                
               accommodation_price = days * 1500; // is accomodation price 
                String description = "room @ = 1500/day,Days:"+days+",in date:"+checkin_date+"out_date:"+checkout_date;
                System.out.println("Description is::"+ description);
                              
                               
                // add  Accomodation as bill in bills table
                String Message1 = "ARE YOU SURE TO CHECKOUT ?"+
                    "\n Client Name : "+firstname;
                    
                
                 int response = JOptionPane.showConfirmDialog(null, Message1, "Choose to continue ", JOptionPane.YES_NO_OPTION);
            if(response==JOptionPane.NO_OPTION){return;}
                
//            
//            data[0] = resident_id;
//            data[1] = "accommodation";
//            data[2] = "room";
//            data[3] = description;
//            data[4] = price;
//            data[5] = staff_name;
         String accommodation_line = "accommodation"+"\t"+"room"+"\t"+staff_name+"\t"+accommodation_price+"\t"+description+"\n";
                System.out.println("Accommodation line is ::"+accommodation_line);
                // we have found days  now we make a bill
                get_bills_from_db(accommodation_line,accommodation_price,laundry_price,food_price,cleaning_price);                
               
              
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.toString() ,"SQL error", JOptionPane.INFORMATION_MESSAGE);
                Logger.getLogger(checkout_client.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void get_bills_from_db(String accommodation_line,int accommodation_price,int lp,int fp, int cp)
    {
        try {
            String query = "SELECT * from bills where resident_id="+"\""+resident_id+"\"";
            System.out.println("bill of client query is :"+query);
            
            ResultSet rs = db.query_function(query);
            
            int price = 0;
            String Line_string = "";
            String title = "Order Category\t Item name \t staff_name  \t Price \t Description \n";
            
            int local_lp = lp;
            int local_fp = fp;
            int local_cp = cp;
            
            while(rs.next())
            {
                // put the data into a line
                //in format room_no,firstname,item name,description
                Line_string = Line_string +rs.getString("order_category")+"\t"+rs.getString("item_name")+"\t"
                        +rs.getString("staff_name")+"\t"+rs.getString("price")+"\t"+rs.getString("description")+"\n"; 
                
                // calculate total prices.
                price = price + Integer.parseInt(rs.getString("price"));
                
                if(rs.getString("order_category").equals("laundry"))
                { local_lp = local_lp + Integer.parseInt(rs.getString("price"));}
                else if(rs.getString("order_category").equals("food"))
                { local_fp = local_fp + Integer.parseInt(rs.getString("price"));}
                else if(rs.getString("order_category").equals("cleaning"))
                { local_cp = local_cp +Integer.parseInt(rs.getString("price"));}

            }
            // finding total price respectively
            laundry_price = local_lp;
            food_price = local_fp;
            cleaning_price = local_cp;
            // we have accomodation price
            
            String all_orders = title +Line_string+accommodation_line;
           bill_field.setText(all_orders);
           total_price = price+accommodation_price;
           total_price_field.setText(total_price+"");
           
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),"SQL ERROR",JOptionPane.ERROR_MESSAGE);
        }
       
    }
    
public void checkout_client()
{
// then insert his record to sales table now.
//        sales_id;
//        firstname;
//        date;
//        phone;
//        accomodation;
//        laundry;
//        food;
//        cleaning;
//        totals

String sales_id;
Date date = new Date();
            
            // generate booking id
            while (true)
            {
            try {
                Random r = new Random();
                sales_id = r.nextInt(99999999) +"";
                
                String query = "SELECT * FROM sales where sales_id="+"\""+sales_id+"\"";
                ResultSet rs = db.query_function(query);
                if(rs.next()==false)
                { break;}
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.toString() ,"SQL error", JOptionPane.INFORMATION_MESSAGE); 
            }
             
            }
                       
            
            String insert_statement_prepared = "INSERT INTO sales VALUES(?,?,?,?,?,?,?,?,?)";
            Object[] data = new Object[9];
            
            data[0] = sales_id;
            data[1] = firstname;
            data[2] = phone;
            data[3] = date;            
            data[4] = accommodation_price;
            data[5] = laundry_price;
            data[6] = food_price;
            data[7] = cleaning_price;
            data[8] = total_price;
       
            
            int i;
            for(i=0;i< 9;i++){
            System.out.println("value in array is : "+data[i]);}
            
            int rows = db.insert_function(insert_statement_prepared, 9, data);
            
            if (rows==1)
            {
                // we have taken the money and now we delete his records from  -bills-booking-residents - from orders also and then
 String delete = "DELETE from orders where resident_id="+"\""+resident_id+"\""; 
                System.out.println("Delete query in resident is: "+delete);
                db.update_function(delete);
        delete = "DELETE from residents where resident_id="+"\""+resident_id+"\""; 
                System.out.println("Delete query in bills is: "+delete);
                db.update_function(delete);
 delete = "DELETE from bookings where booking_id="+"\""+resident_id+"\""; 
                System.out.println("Delete query in Booking is: "+delete);
                db.update_function(delete);
 delete = "DELETE from bills where resident_id="+"\""+resident_id+"\""; 
                System.out.println("Delete query in bills is: "+delete);
                db.update_function(delete);
                
                
                // record was inserted successfully. 
             JOptionPane.showMessageDialog(null,"Checkout successful. Please Wait for Reciept" ,"CHECKOUT SUCCESS", JOptionPane.INFORMATION_MESSAGE);              
             
             // eddit bill to add sales_id total and date 
             String bill_data  = bill_field.getText();
             String sales_id_string  = "SALES ID ::"+sales_id+"\n\n";
             String date_string  = "DATE ::"+date+"\n\n";
             String last_string  = "\n\n\tTOTALS : "+total_price;
             
             
             bill_field.setText(sales_id_string+date_string+bill_data+last_string);
                System.out.println(bill_field.getText());
                
             JOptionPane.showMessageDialog(null,"Printing reciept" ,"information", JOptionPane.INFORMATION_MESSAGE);
             
     try {
         bill_field.print();
     } catch (PrinterException ex) {
         JOptionPane.showMessageDialog(null,ex.toString(),"PRINTING ERROR", JOptionPane.INFORMATION_MESSAGE);
         Logger.getLogger(checkout_client.class.getName()).log(Level.SEVERE, null, ex);
     }
             
            }
    
}
    
}
