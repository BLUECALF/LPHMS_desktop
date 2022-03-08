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
import javax.swing.JTextArea;

/**
 *
 * @author kiptala
 */
public class common_staff_page  extends JFrame implements ActionListener{
    java_sql_helper db;
    String order_category;
    String staff_name;
    
    JLabel title;
    JLabel instructions; 
    
    JLabel orders_label;
    JTextArea orders_field;
    
    JLabel billing_label;
    JLabel order_id_label;
    JTextArea order_id_field;
    JLabel price_label;
    JTextArea price_field;
    
    JButton bill_button;
    JButton refresh_button;
    JButton exit_button;
        
        
    common_staff_page(java_sql_helper db,String order_category,String staff_name)
       {
           // constructor
            this.db = db;
            this.order_category = order_category;
            this.staff_name = staff_name;
            
         System.out.println("hello i am "+order_category+" staff module");
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
        instructions.setText(" Common Staff Page");
        instructions.setFont(new Font("mv boli",Font.BOLD,18));
        instructions.setForeground(Color.LIGHT_GRAY);
        instructions.setBounds(50,100,400,50);
        instructions.setHorizontalTextPosition(JLabel.CENTER);
        
        orders_label =  new JLabel();
        orders_label.setText(" Current Orders in "+order_category);
        orders_label.setFont(new Font("mv boli",Font.BOLD,15));
        orders_label.setForeground(Color.LIGHT_GRAY);
        orders_label.setBounds(50,150,250,50);
        orders_label.setHorizontalTextPosition(JLabel.CENTER);
        
        refresh_button = new JButton("Refresh orders");
        refresh_button.setBounds(300,150,150,20);
        refresh_button.setFont(new Font("mv boli",Font.BOLD,15));
        refresh_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        refresh_button.setFocusable(false);
        refresh_button.addActionListener(this);
        
        orders_field =  new JTextArea();
        orders_field.setText("No current Orders :");
        orders_field.setFont(new Font("mv boli",Font.BOLD,10));       
        orders_field.setBounds(20,200,460,200);
        orders_field.setEditable(false);
        
        billing_label =  new JLabel();
        billing_label.setText(" Bill A Client ");
        billing_label.setFont(new Font("mv boli",Font.BOLD,18));
        billing_label.setForeground(Color.LIGHT_GRAY);
        billing_label.setBounds(50,400,200,50);
        billing_label.setHorizontalTextPosition(JLabel.CENTER);
        
        order_id_label =  new JLabel();
        order_id_label.setText("Enter Order Id ");
        order_id_label.setFont(new Font("mv boli",Font.BOLD,15));
        order_id_label.setForeground(Color.LIGHT_GRAY);
        order_id_label.setBounds(50,450,200,30);
        order_id_label.setHorizontalTextPosition(JLabel.CENTER);
        
        order_id_field =  new JTextArea();       
        order_id_field.setFont(new Font("mv boli",Font.BOLD,18));       
        order_id_field.setBounds(250,460,200,30);
        
        
        price_label =  new JLabel();
        price_label.setText("Enter price ");
        price_label.setFont(new Font("mv boli",Font.BOLD,15));
        price_label.setForeground(Color.LIGHT_GRAY);
        price_label.setBounds(50,500,200,50);
        price_label.setHorizontalTextPosition(JLabel.CENTER);
        
        price_field =  new JTextArea();        
        price_field.setFont(new Font("mv boli",Font.BOLD,18));       
        price_field.setBounds(250,510,200,30);
       
        bill_button = new JButton("Bill Client");
        bill_button.setBounds(150,550,150,30);
        bill_button.setFont(new Font("mv boli",Font.BOLD,18));
        bill_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        bill_button.setFocusable(false);
        bill_button.addActionListener(this);
        
        exit_button = new JButton("Exit");
        exit_button.setBounds(50,650,100,20);
        exit_button.setFont(new Font("mv boli",Font.BOLD,18));
        exit_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        exit_button.setFocusable(false);
        exit_button.addActionListener(this);
        
        
        // adding components
        this.add(title);
        this.add(instructions);
        this.add(orders_label);
        this.add(orders_field);
        this.add(billing_label);
        this.add(order_id_label);
        this.add(order_id_field);
        this.add(price_label);
        this.add(price_field);
        this.add(refresh_button);
        this.add(bill_button);
        this.add(exit_button);
        
        get_orders_from_db();               
        
           
       }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()== refresh_button)
        {get_orders_from_db();}
        if(ae.getSource()== exit_button)
        {this.dispose();
        new staff_login();
        }
        if(ae.getSource()==bill_button)
        {
            bill_button.setEnabled(false);
            bill_client();
            bill_button.setEnabled(true);
            price_field.setText("");
            order_id_field.setText("");
            get_orders_from_db();
        }
        
        
    }
    
    public void get_orders_from_db()
    {
        try {
            String query = "SELECT * from orders where order_category="+"\""+order_category+"\"";
            System.out.println("Order query is :"+query);
            
            ResultSet rs = db.query_function(query);
            
            String Line_string = "";
            String title = "Room no \t Firstname\t item name \t description \n";
            
            while(rs.next())
            {
                // put the data into a line
                //in format room_no,firstname,item name,description
                Line_string = Line_string +rs.getString("room_no")+"\t"+rs.getString("firstname")+"\t"
                        +rs.getString("item_name")+"\t"+rs.getString("description")+"\n";
                
            }
            
            String all_orders = title +Line_string;
            orders_field.setText(all_orders);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),"SQL ERROR",JOptionPane.ERROR_MESSAGE);
        }
       
    }

    private void bill_client() {
        try {
            //  get order id and price
            String order_id = order_id_field.getText();
            int price  =  Integer.parseInt(price_field.getText());
            //validate
            String query = "SELECT * from orders where order_id="+"\""+order_id+"\"";
            System.out.println("Order validation query is :"+query);
            
            ResultSet rs = db.query_function(query);
            if(rs.next()==true)
            {
                // the order id is valid and can be billed.
             String room_no = rs.getString("room_no");
               
                
                // get details that will be put in bills table.
                String resident_id = rs.getString("resident_id");
                String order_category = rs.getString("order_category");
                String item_name = rs.getString("item_name");
                String description = rs.getString("description");
                //we have price
                // we have staff name
                
                // get confirmation
                String Message1 = "ARE YOU SURE TO BILL?"+
                    "\nbill amount :KSH "+price+
                    "\nFor Item "+item_name+
                    "\nroom number :"+room_no+
                    "\n thank you";
                
                 int response = JOptionPane.showConfirmDialog(this, Message1, "Choose to continue ", JOptionPane.YES_NO_OPTION);
            if(response==JOptionPane.NO_OPTION){return;}
                
                // insert into billing table details
                
                String insert_statement_prepared = "INSERT INTO bills VALUES(?,?,?,?,?,?)";
            Object[] data = new Object[6];
            
            data[0] = resident_id;
            data[1] = order_category;
            data[2] = item_name;
            data[3] = description;
            data[4] = price;
            data[5] = staff_name;
         
       
            
            int i;
            for(i=0;i<6;i++){
            System.out.println("value in array of bill details is : "+data[i]);}
            
            int rows = db.insert_function(insert_statement_prepared, 6, data);
            
            if (rows==1)
            {
                // billing Success
                
                //remove from orders
                String delete = "DELETE from orders where order_id="+"\""+order_id+"\""; 
                System.out.println("Delete query is: "+delete);
                int affected = db.update_function(delete);
                
                if(affected>0)
                {
                    // delete suceess
                    //find email
                String query2 = "SELECT * from residents where resident_id="+"\""+resident_id+"\"";           
                ResultSet rs2 = db.query_function(query2);
                rs2.next();
                String email = rs2.getString("email");
                    System.out.println("Email is::"+email);
                // email client they were billed well.
                String Message = "Dear Resident"+
                    "\nYou have been billed amount :KSH "+price+
                    "\nFor Item "+item_name+
                    "\nBy Employee : "+staff_name+
                    "\nthank you,";
                
                java_mail jm = new java_mail();
                jm.sendMail(email, "information",Message);
                
                JOptionPane.showMessageDialog(null,"BILLING SUCCESS\ntell customer to check email","Billing Success",JOptionPane.YES_OPTION);
                    
                }
                
                
            }
                
            }
            else{JOptionPane.showMessageDialog(null, "Billing Failed\nOrderId is not in database","Billing of Order Failed",JOptionPane.ERROR_MESSAGE);}
            //enter into db
            //send confirmation msg and email
        } catch (SQLException ex) {
            Logger.getLogger(common_staff_page.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, ex.toString(),"SQL ERROR",JOptionPane.ERROR_MESSAGE);
        } catch (MessagingException ex) {
           JOptionPane.showMessageDialog(null, ex.toString()," Email error",JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e.toString(),"Unknown type of error",JOptionPane.ERROR_MESSAGE);}
    }
    
}
