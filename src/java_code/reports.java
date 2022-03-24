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

/**
 *
 * @author kiptala
 */
public class reports extends JFrame implements ActionListener {
    
    // needed strings
    String top ="";
    String line ="";
    String total ="";
    String storage ="";
        
    //labels
    JLabel title;
  
    
    JLabel clients_label = new JLabel("Clients : "); 
    JLabel residents_label = new JLabel("Residents : "); 
    JLabel staffs_label = new JLabel("Staffs : "); 
    JLabel sales_label = new JLabel("Sales : "); 
    
    // text areas
    
    JTextArea clients_field = new JTextArea();
    JTextArea residents_field = new JTextArea();
    JTextArea staffs_field = new JTextArea();
    JTextArea sales_field = new JTextArea();
    
    // print buttons
    JButton print_clients_button = new JButton("print clients");
    JButton print_residents_button = new JButton("print residents");
    JButton print_staffs_button = new JButton("print staffs");
    JButton print_sales_button = new JButton("print sales");
    JButton back = new JButton("Back");
    
    
    java_sql_helper db;
    
    reports(java_sql_helper db)
    {
        this.db = db;
        this.setTitle(" Reports To be Printed Form ");
        this.setSize(1300,710);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setBackground(Color.lightGray);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(true);
        
        // title_label 
        title =  new JLabel();
        title.setText("LEXX PLACE HOTEL print reports page");
        title.setFont(new Font("mv boli",Font.BOLD,18));
        title.setForeground(Color.BLACK);
        title.setBounds(150,0,600,50);
        title.setHorizontalTextPosition(JLabel.CENTER);
        
        //clients
        clients_label.setBounds(0,50,150,150);
        clients_field.setBounds(150,50,700,140);
        clients_field.setEditable(false);
        print_clients_button.setBounds(850,50,200,50);
        print_clients_button.addActionListener(this);
        
        //residents
        residents_label.setBounds(0,200,150,150);
        residents_field.setBounds(150,200,700,140);
        residents_field.setEditable(false);
        print_residents_button.setBounds(850,200,200,50);
        print_residents_button.addActionListener(this);
        
        //staffs
        staffs_label.setBounds(0,350,150,150);
        staffs_field.setBounds(150,350,700,140);
        staffs_field.setEditable(false);
       print_staffs_button.setBounds(850,350,200,50);
        print_staffs_button.addActionListener(this);
        
        //sales 
        sales_label.setBounds(0,500,150,150);
        sales_field.setBounds(150,500,900,140);
        sales_field.setEditable(false);
        staffs_field.setEditable(false);
        print_sales_button.setBounds(1050,500,200,50);
        print_sales_button.addActionListener(this);
        
        //back button
        back.setBounds(50,680,100,20);
        back.addActionListener(this);
        
        this.add(title);
        this.add(clients_label);
        this.add(clients_field);
        this.add(print_clients_button);
        this.add(residents_label);
        this.add(residents_field);
        this.add(print_residents_button);
        this.add(staffs_label);
        this.add(staffs_field);
        this.add(print_staffs_button);
        this.add(sales_label);
        this.add(sales_field);
        this.add(print_sales_button);
        this.add(back);
        
        clients();
        residents();
        staffs();
        sales();
        
    }
    
    // on click action performed code----------

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==print_clients_button)
        {try {
            clients_field.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, ex);               
                Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);                
            }
        }
        if(ae.getSource()==print_residents_button)
        {try {
            residents_field.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, ex);
               
                Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        if(ae.getSource()==print_staffs_button)
        {try {
            staffs_field.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, ex);               
                Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);                
            }
        }
        if(ae.getSource()==print_sales_button)
        {try {
            sales_field.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, ex);
               
                Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        if(ae.getSource()==back){this.dispose();}

        
    }
    
    //methods for JTextArea
    public void clients()
    {
            
        try {
            
            String query = "select * from clients";
            ResultSet rs =  db.query_function(query);
            
            
            System.out.println("Data in rs is "+rs.toString());
            
            top = "Firstname\tLastname\tGender\tPhone\tEmail\n";
           
                // loops
                while(rs.next())
                {
                    
                storage = storage + line;
                                
                line = rs.getString("firstname")+"\t"+rs.getString("lastname")+"\t"+rs.getString("gender")+"\t"+rs.getString("phone")+"\t"+rs.getString("email")+"\n";
                
                }
           
            total = storage + line;
            //update clients field
            clients_field.setText(top+total);
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString() ,"SQL error", JOptionPane.INFORMATION_MESSAGE); 
            Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
            
        }            
          
        } 
    
    
     public void residents()
    {
        storage="";
        line ="";
        top ="";
             
        try {
            
            String query = "select * from residents";
            ResultSet rs =  db.query_function(query);
                        
            top = "Resident ID\tRoom NO\tFirstname\tPhone\tEmail\n";
           
                // loops
                while(rs.next())
                {
                   String query1 = "select * from bookings where booking_id="+rs.getString("resident_id");
            ResultSet rs1 =  db.query_function(query1);
            rs1.next();
                    
                storage = storage + line;
                                
                line = rs.getString("resident_id")+"\t"+rs.getString("room_no")+"\t"+rs.getString("firstname")+"\t"+rs1.getString("phone")+"\t"+rs.getString("email")+"\n";
                
                }
           
            total = storage + line;
            //update residents field
            residents_field.setText(top+total);
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString() ,"SQL error", JOptionPane.INFORMATION_MESSAGE); 
            Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
            
        } 
               
    
    }
      public void staffs()
{
     storage="";
        line ="";
        top ="";
             
        try {
            
            String query = "select * from staffs";
            ResultSet rs =  db.query_function(query);
                        
            top = "Firstname\tLastname\tGender\tPhone\tNational_id\tStaff_Category\tEmail\n";
           
                // loops
                while(rs.next())
                {
                                   
                storage = storage + line;
                                
                line = rs.getString("firstname")+"\t"+rs.getString("lastname")
                        +"\t"+rs.getString("gender")+"\t"+rs.getString("phone")
                        +"\t"+rs.getString("national_id")+"\t"+rs.getString("staff_category")+"\t"+rs.getString("email")+"\n";;
                }
           
            total = storage + line;
            //update staffs field
            staffs_field.setText(top+total);
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString() ,"SQL error", JOptionPane.INFORMATION_MESSAGE); 
            Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
            
        }  
   
}    

       public void sales()
    {
         storage="";
        line ="";
        top ="";
             
        try {
            
            String query = "select * from sales";
            ResultSet rs =  db.query_function(query);
                        
            top = "Sales ID\tFirstname\tPhone\tDate\tRoom\tLaundry\tFood\tCleaning\tTotal\n";
           
                // loops
                while(rs.next())
                {
                                   
                storage = storage + line;
                                
                line = rs.getString("sales_id")+"\t"+rs.getString("firstname")
                        +"\t"+rs.getString("phone")+"\t"+rs.getDate("date")
                        +"\t"+rs.getInt("accomodation")+"\t"+rs.getInt("laundry")
                        +"\t"+rs.getInt("food")
                        +"\t"+rs.getInt("cleaning")
                        +"\t"+rs.getInt("total")+"\n";;
                }
           
            total = storage + line;
            //update sales field
            sales_field.setText(top+total);
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString() ,"SQL error", JOptionPane.INFORMATION_MESSAGE); 
            Logger.getLogger(reports.class.getName()).log(Level.SEVERE, null, ex);
            
        }  
        
    
    }
       

}

        
    
    
    

