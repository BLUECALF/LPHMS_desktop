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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

/**
 *
 * @author kiptala
 */
public class administrator_staff_page extends JFrame implements ActionListener{
    
    
    JLabel title;
    JLabel instructions;  
    
    JLabel add_staff_label;
    //fields labels
    JLabel firstname_label;
    JLabel lastname_label;
    JLabel gender_label;
    JLabel staff_category_label;
    JLabel email_label;
    JLabel phone_label;
    JLabel national_id_label;
    JLabel password_label;
   JLabel remove_national_id_label;
   
    //fields fields
    JTextArea firstname_field;
    JTextArea lastname_field;
    
    //gender
    JRadioButton male;
    JRadioButton female;
    ButtonGroup group;
    
    // staff _category group
    //gender
    JRadioButton food;
    JRadioButton accommodation;
     JRadioButton laundry;
      JRadioButton cleaning;
    
    ButtonGroup staff_category_group;
   
    
    JTextArea email_field;
    JTextArea phone_field;
    JTextArea national_id_field;
    JTextArea password_field;
    JTextArea remove_national_id_field;
    
    JButton add_staff_button;
    JButton remove_staff_button;
    JButton print_reports_button;
    JButton broadcast_button;
    
    JButton exit_button;
    
    java_sql_helper db;
    String staff_name;
    
    String firstname;
    String lastname;
    String gender ="";
    String email;
    String phone;
    String national_id;
    String password;
    String staff_category="";
    String remove_national_id="";
     
    
    administrator_staff_page(java_sql_helper db,String staff_name)
    {
        this.db = db;
        this.staff_name = staff_name;
         System.out.println("hello i am administrator staff module");
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
        title.setBounds(150,50,200,50);
        title.setHorizontalTextPosition(JLabel.CENTER);
        
        instructions =  new JLabel();
        instructions.setText(" Administrator Staff Page");
        instructions.setFont(new Font("mv boli",Font.BOLD,13));
        instructions.setForeground(Color.LIGHT_GRAY);
        instructions.setBounds(50,100,400,50);
        instructions.setHorizontalTextPosition(JLabel.CENTER);
        
     // staff fields for inserting a new staff;    
        add_staff_label =  new JLabel();
        add_staff_label.setText(" Add Staff ");
        add_staff_label.setFont(new Font("mv boli",Font.BOLD,18));
        add_staff_label.setForeground(Color.LIGHT_GRAY);
        add_staff_label.setBounds(50,150,300,50);
        add_staff_label.setHorizontalTextPosition(JLabel.CENTER);  
        
        firstname_label =  new JLabel();
        firstname_label.setText(" First Name ");
        firstname_label.setFont(new Font("mv boli",Font.BOLD,15));
        firstname_label.setForeground(Color.LIGHT_GRAY);
        firstname_label.setBounds(50,200,150,50);
        firstname_label.setHorizontalTextPosition(JLabel.CENTER); 
        
        firstname_field =  new JTextArea();        
        firstname_field.setFont(new Font("mv boli",Font.BOLD,15));
        firstname_field.setForeground(Color.BLACK);
        firstname_field.setBounds(200,200,300,30);
        
        // lastname
        lastname_label =  new JLabel();
        lastname_label.setText(" Last Name  ");
        lastname_label.setFont(new Font("mv boli",Font.BOLD,15));
        lastname_label.setForeground(Color.LIGHT_GRAY);
        lastname_label.setBounds(50,250,150,50);
        lastname_label.setHorizontalTextPosition(JLabel.CENTER); 
        
        lastname_field =  new JTextArea();        
        lastname_field.setFont(new Font("mv boli",Font.BOLD,15));
        lastname_field.setForeground(Color.BLACK);
        lastname_field.setBounds(200,250,300,30);
        
        // gender
        gender_label =  new JLabel();
        gender_label.setText(" Gender ");
        gender_label.setFont(new Font("mv boli",Font.BOLD,15));
        gender_label.setForeground(Color.LIGHT_GRAY);
        gender_label.setBounds(50,300,200,50);
        gender_label.setHorizontalTextPosition(JLabel.CENTER);
        
        //gender radio button
      male = new JRadioButton("male");
      male.setBounds(50,350,100,20);
      male.addActionListener(this);
      male.setBackground(Color.green);
      
      female = new JRadioButton("female");
      female.setBackground(Color.green);
      female.setBounds(150,350,100,20);
      female.addActionListener(this);
      //add to group
      group = new ButtonGroup();
      group.add(male);
      group.add(female);
        
      // email
      // email
        email_label =  new JLabel();
        email_label.setText(" Email  ");
        email_label.setFont(new Font("mv boli",Font.BOLD,15));
        email_label.setForeground(Color.LIGHT_GRAY);
        email_label.setBounds(50,380,150,50);
        email_label.setHorizontalTextPosition(JLabel.CENTER); 
        
        email_field =  new JTextArea();        
        email_field.setFont(new Font("mv boli",Font.BOLD,15));
        email_field.setForeground(Color.BLACK);
        email_field.setBounds(200,380,300,30);
        
        // phone
        phone_label =  new JLabel();
        phone_label.setText(" Phone  ");
        phone_label.setFont(new Font("mv boli",Font.BOLD,15));
        phone_label.setForeground(Color.LIGHT_GRAY);
        phone_label.setBounds(50,420,150,50);
        phone_label.setHorizontalTextPosition(JLabel.CENTER); 
        
        phone_field =  new JTextArea();        
        phone_field.setFont(new Font("mv boli",Font.BOLD,15));
        phone_field.setForeground(Color.BLACK);
        phone_field.setBounds(200,420,300,30);
        
        // national_id
        national_id_label =  new JLabel();
        national_id_label.setText(" National ID");
        national_id_label.setFont(new Font("mv boli",Font.BOLD,15));
        national_id_label.setForeground(Color.LIGHT_GRAY);
        national_id_label.setBounds(50,470,150,50);
        national_id_label.setHorizontalTextPosition(JLabel.CENTER); 
        
        national_id_field =  new JTextArea();        
        national_id_field.setFont(new Font("mv boli",Font.BOLD,15));
        national_id_field.setForeground(Color.BLACK);
        national_id_field.setBounds(200,470,300,25);
        
        // password
        password_label =  new JLabel();
        password_label.setText(" Password ");
        password_label.setFont(new Font("mv boli",Font.BOLD,15));
        password_label.setForeground(Color.LIGHT_GRAY);
        password_label.setBounds(50,500,150,50);
        password_label.setHorizontalTextPosition(JLabel.CENTER); 
        
        password_field =  new JTextArea();        
        password_field.setFont(new Font("mv boli",Font.BOLD,15));
        password_field.setForeground(Color.BLACK);
        password_field.setBounds(200,500,300,30);
        
        // staff_category
        staff_category_label =  new JLabel();
        staff_category_label.setText(" Staff Category ");
        staff_category_label.setFont(new Font("mv boli",Font.BOLD,15));
        staff_category_label.setForeground(Color.LIGHT_GRAY);
        staff_category_label.setBounds(50,530,300,50);
        staff_category_label.setHorizontalTextPosition(JLabel.CENTER); 
        
       //staff category radio button
      food = new JRadioButton("food");
      food.setBounds(50,580,100,20);
      food.addActionListener(this);
      food.setBackground(Color.green);
      
      accommodation = new JRadioButton("accomodation");
      accommodation.setBackground(Color.green);
      accommodation.setBounds(150,580,150,20);
      accommodation.addActionListener(this);
      
      laundry = new JRadioButton("laundry");
      laundry.setBackground(Color.green);
      laundry.setBounds(50,600,100,20);
      laundry.addActionListener(this);
      
      cleaning = new JRadioButton("cleaning");
      cleaning.setBackground(Color.green);
      cleaning.setBounds(150,600,100,20);
      cleaning.addActionListener(this);
      //add to group
      staff_category_group = new ButtonGroup();
      staff_category_group.add(food);
      staff_category_group.add(accommodation);
       staff_category_group.add(laundry);
        staff_category_group.add(cleaning);
       
        // add staff button 
        add_staff_button = new JButton(" Add Staff");
        add_staff_button.setBounds(50,620,300,30);
        add_staff_button.setFont(new Font("mv boli",Font.BOLD,18));
        add_staff_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        add_staff_button.setFocusable(false);
        add_staff_button.addActionListener(this);
       
        
        // DFeatails for removing staff
        // remove_national_id
        remove_national_id_label =  new JLabel();
        remove_national_id_label.setText(" Remove Staff by National ID ");
        remove_national_id_label.setFont(new Font("mv boli",Font.BOLD,17));
        remove_national_id_label.setForeground(Color.WHITE);
        remove_national_id_label.setBounds(550,200,300,30);
        remove_national_id_label.setHorizontalTextPosition(JLabel.CENTER); 
        
        remove_national_id_field =  new JTextArea();        
        remove_national_id_field.setFont(new Font("mv boli",Font.BOLD,17));
        remove_national_id_field.setForeground(Color.RED);
        remove_national_id_field.setBackground(Color.GRAY);
        remove_national_id_field.setOpaque(true);
        remove_national_id_field.setBounds(550,250,200,30);
        
        remove_staff_button = new JButton("Remove Staff");
        remove_staff_button.setBounds(550,300,150,30);
        remove_staff_button.setFont(new Font("mv boli",Font.BOLD,18));
        remove_staff_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        remove_staff_button.setFocusable(false);
        remove_staff_button.addActionListener(this);
        
        print_reports_button = new JButton(" Print Reports");
        print_reports_button.setBounds(550,350,300,30);
        print_reports_button.setFont(new Font("mv boli",Font.BOLD,18));
        print_reports_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        print_reports_button.setFocusable(false);
        print_reports_button.addActionListener(this);
        
        broadcast_button = new JButton(" Broadcast to Customers ");
        broadcast_button.setBounds(550,400,300,20);
        broadcast_button.setFont(new Font("mv boli",Font.BOLD,18));
        broadcast_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        broadcast_button.setFocusable(false);
        broadcast_button.addActionListener(this);
        
        exit_button = new JButton("Exit");
        exit_button.setBounds(550,100,100,20);
        exit_button.setFont(new Font("mv boli",Font.BOLD,18));
        exit_button.setBorder(BorderFactory.createLineBorder(Color.yellow));
        exit_button.setFocusable(false);
        exit_button.addActionListener(this);
        // adding components
        
     this.add(instructions);
     this.add(title);
      this.add(add_staff_label);
    //fields labels
    this.add(firstname_label);
    this.add(lastname_label);
    this.add(gender_label);
     this.add(male);
    this.add(female);
    this.add(staff_category_label);
    this.add(email_label);
    this.add(phone_label);
    this.add(password_label);
   
    //fields fields
    this.add(firstname_field);
    this.add(lastname_field);
   
    this.add(food);
    this.add(accommodation);
    this.add(laundry);
    this.add(cleaning);
    
    this.add(email_field);
    this.add(phone_field);
    this.add(national_id_field);
    this.add(national_id_label);
    this.add(password_field);
    this.add(remove_national_id_label);
    this.add(remove_national_id_field);
    
    this.add(add_staff_button);
    this.add(remove_staff_button);
    this.add(print_reports_button);
    this.add(broadcast_button);
    
    this.add(exit_button);
        
    }
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==male)
        {gender = "male";}
        else if(ae.getSource()==female)
        {gender = "female";}
        
        // staff category
        if(ae.getSource()==food)
        {staff_category = "food";}
        else if(ae.getSource()==accommodation)
        {staff_category = "accommodation";}
        else if(ae.getSource()==laundry)
        {staff_category = "laundry";}
        else if(ae.getSource()==cleaning)
        {staff_category = "cleaning";}
        
       if(ae.getSource()==add_staff_button)
       {add_staff_button.setEnabled(false);
       add_staff();
       add_staff_button.setEnabled(true);
       }
       if(ae.getSource() == exit_button)
       {this.dispose();
       new staff_login();
       }
       // if he pressed remove staff
       if(ae.getSource()==remove_staff_button)
       {remove_staff_button.setEnabled(false);
       remove_staff();
       remove_staff_button.setEnabled(true);
       }
       // if he pressed print reports.
       if(ae.getSource()==print_reports_button)
       {print_reports_button.setEnabled(false);
        new reports(db);
       print_reports_button.setEnabled(true);
       }
       // if he pressed broadcast
       if(ae.getSource()==broadcast_button)
       {broadcast_button.setEnabled(false);
        new broadcast_page(db);
       broadcast_button.setEnabled(true);
       }
        
    }
    
    // add staff function
    public void add_staff()
    {
               // check for null.
        if("".equals(firstname_field.getText())||                
           lastname_field.getText()==""|| 
            "".equals(gender)||
            email_field.getText()==""||
            phone_field.getText()==""||
            national_id_field.getText()==""||
            password_field.getText()==""||
            "".equals(staff_category)
                )
        {
         JOptionPane.showMessageDialog(null, "Please Fill all the Fields To Add Staff ","Warning" , JOptionPane.ERROR_MESSAGE);        
         return;
        }
        // all fields are filled now we put data
        firstname = firstname_field.getText();
        lastname =  lastname_field.getText();
        // we have gender
        email = email_field.getText();
        phone = phone_field.getText();
        national_id = national_id_field.getText();
        password = password_field.getText();
        // we have staff_category
    
        // find id email phone and id are unique
         String query = "SELECT * from staffs WHERE email="+"\"" +email+"\""+"or phone ="+"\""+phone+"\""+"or national_id ="+"\""+national_id+"\""; ; 
       ResultSet rs = db.query_function(query);
       
        System.out.println("Query of uniqueness is ::"+query);
        try {
            if(rs.next())
            {
                // the email,phone or national id has been used  .. reject
                JOptionPane.showMessageDialog(null, "Phone ,email Or  National id entered has already been used.","ADD Staff Error" , JOptionPane.ERROR_MESSAGE); return;
            }
            
            else
            {
            // all is good
              // INSERT TO DB 
        
         String insert_statement_prepared = "INSERT INTO staffs VALUES(?,?,?,?,?,?,?,?)";
            Object[] data = new Object[8];
            
            data[0] = firstname;
            data[1] = lastname;
            data[2] = gender;
            data[3] = email;
            data[4] = phone;
            data[5] = national_id;
            data[6] = password;
            data[7] = staff_category;
       
            
            int i;
            for(i=0;i<8;i++){
            System.out.println("value in array of staff Data  is : "+data[i]);}
            
            int rows = db.insert_function(insert_statement_prepared, 8, data);
            
            if (rows==1)
            {
                // record was inserted successfully. 
                JOptionPane.showMessageDialog(null, "STAFF : "+firstname+" was added Successfuly to System"," ADD STAFF SUCCESS" , JOptionPane.INFORMATION_MESSAGE);        
                // reset all fields
                firstname_field.setText("");
            lastname_field.setText("");
            gender="";  
            group.clearSelection();
            email_field.setText("");
            phone_field.setText("");
            national_id_field.setText("");
            password_field.setText("");
            staff_category = "";
            staff_category_group.clearSelection();
            }  
            
            
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),"SQL ERROR" , JOptionPane.ERROR_MESSAGE); return;}
        }
        
        
            
    

    private void remove_staff() {
        
        // here we need the national id 
        if(remove_national_id_field.getText().equals(""))
        {
        JOptionPane.showMessageDialog(null, "Please Fill National id , So that staff can be removed ","Warning" , JOptionPane.ERROR_MESSAGE); return;}
        
        // all is well
        remove_national_id = remove_national_id_field.getText();
        
        // we find  name,gender,phone
        String query = "SELECT * from staffs WHERE national_id="+"\"" +remove_national_id+"\""; 
       ResultSet rs = db.query_function(query);
       
        System.out.println("Query offinding details is ::"+query);
        try {
            if(rs.next()==true)
            {
                 String Message1 = "ARE YOU SURE TO REMOVE ?"+
                    "\nname : "+rs.getString("firstname")+" "+ rs.getString("lastname")+
                    "\nGender: "+rs.getString("gender")+
                    "\nPhone number :"+rs.getString("phone")+
                    "\nNational ID number:"+rs.getString("national_id")+
                         "\nEmail: "+rs.getString("email");
                // the email,phone or national id has been used  .. reject
                  int response = JOptionPane.showConfirmDialog(this, Message1, "Choose to continue ", JOptionPane.YES_NO_OPTION);
            if(response==JOptionPane.NO_OPTION){return;}
            }
            else
            {
            JOptionPane.showMessageDialog(null, "Staff Of provided national id does NOT exist","REMOVE STAFF ERROR" , JOptionPane.ERROR_MESSAGE); return;
            }
            
            // code here for removing the staff.
            String delete = "DELETE from staffs where national_id="+"\""+remove_national_id+"\""; 
                System.out.println("Delete query is: "+delete);
                int affected = db.update_function(delete);
                
                if(affected>0)
                {
                JOptionPane.showMessageDialog(null,"REMOVE STAFF SUCCESSFUL"," Removing staff Success",JOptionPane.INFORMATION_MESSAGE);
                remove_national_id_field.setText("");
                }
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),"SQL ERROR" , JOptionPane.ERROR_MESSAGE); return;}
        
    }

    
}
