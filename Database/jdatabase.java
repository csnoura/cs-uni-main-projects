


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author noura
 */
public class jdatabase {

public class Jdatabase  extends JFrame implements ActionListener  {
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passLabel;
    private static JPasswordField passText;
    private static JButton button;
    private static JLabel success;

    public Jdatabase() {
        
    }
   
    public static void main(String[] args) {
   

// implements ActionListener {

   

   
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        passLabel = new JLabel("Password");
        passLabel.setBounds(10, 50, 80, 25);
        panel.add(passLabel);

        passText = new JPasswordField();
        passText.setBounds(100, 50, 165, 25);
        panel.add(passText);

        button = new JButton("Login");
        button.setBounds(100, 80, 80, 25);
        //button.addActionListener(new Jdatabase());
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = String.valueOf(passText.getPassword());

        if (user.equals("noura") && password.equals("123")) {
            success.setText("Login Successful!");
        }

    }
}
/*
        Connection con=null;
         Statement stmt =null;
        // preppred statment obj
        ResultSet rs=null;
        // all obj are null
       JButton button= new JButton("Login");
        
        try{
             Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("url","dbuser","password");
            stmt=con.createStatement();
           String q1="SELECT E_name , E_degree FROM PHARMACY_BRANCH WHERE E_age>38";
           rs=stmt.executeQuery(q1);
           ResultSetMetaData rsmd = rs.getMetaData();
           DefalutTableModel rsmdd= (DefalutTableModel)rsmd.getModel();
           int Cols = rsmd.getColumnCount();
          string [] colName -new String [cols];
        for(int i =0;i<cols;i++)
           colName[i]=rsmd.getColumnName(i+1);
          model.setColumnIdentifiers(colName);
String a,b,c,d,f,g,h;
while(rs.next()){
a=rs.getString(1);
a=rs.getString(2)
a=rs.getString(3);
a=rs.getString(4);
a=rs.getString(5);

        }catch(Exception ee ){
            ee.printStackTrace();
}
       
}
}*/
    
    

}
