/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jdb1;

/**
 *
 * @author noura
 */
import static java.awt.Component.CENTER_ALIGNMENT;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.Connection;
//import java.sql.Connector;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author noura
 */
//con better be closed at finally block(?)
public class JDB1 extends JFrame implements ActionListener {

  
    /**
     * @param args the command line arguments
     */
    public JDB1() {
        setTitle("Pharmacy");
        setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        //etLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        //Jframe1.setLayout(new BoxLayout(Jframe1,33))
        //visablity here
        this.setVisible(true);

    }

    public static void main(String[] args) {
        //first screen
        JDB1 Jframe1 = new JDB1();
        Jframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel hiLabel = new JLabel("WELCOME");
        JButton pickbtn = new JButton("Manger");
        JButton pickbtn2 = new JButton("Doctor");
        //JPanel mainPanel = new JPanel();
        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 20));
        hiLabel.setAlignmentX(CENTER_ALIGNMENT);
        pickbtn.setAlignmentX(CENTER_ALIGNMENT);
        pickbtn2.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(hiLabel);
        mainPanel.add(pickbtn);
        mainPanel.add(pickbtn2);
        Jframe1.add(mainPanel);
        Jframe1.setVisible(true);

        //manger screen
        pickbtn.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                JDB1 Mframe1 = new JDB1();
                Mframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                JLabel hiLabel = new JLabel("Operations:");
                JButton addkbtn = new JButton("Add");//ff
                JButton deletebtn = new JButton("Delete");//ff
                JButton retbtn = new JButton("Retrieve");//f
                JButton updatebtn = new JButton("Update");//ff
                JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                addkbtn.setAlignmentX(CENTER_ALIGNMENT);
                deletebtn.setAlignmentX(CENTER_ALIGNMENT);
                retbtn.setAlignmentX(CENTER_ALIGNMENT);
                updatebtn.setAlignmentX(CENTER_ALIGNMENT);
                mainPanel.add(hiLabel);
                mainPanel.add(addkbtn);
                mainPanel.add(deletebtn);
                mainPanel.add(retbtn);
                mainPanel.add(updatebtn);
                Mframe1.add(mainPanel);

                //add frame
                addkbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDB1 addframe1 = new JDB1();
                        addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                        JLabel hiLabel = new JLabel("Pick:");
                        JButton addkbtn = new JButton("Add drug");
                        JButton add2btn = new JButton("Add employee");
                        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                        hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                        addkbtn.setAlignmentX(CENTER_ALIGNMENT);
                        add2btn.setAlignmentX(CENTER_ALIGNMENT);
                        //deletebtn.setAlignmentX(CENTER_ALIGNMENT);
                        mainPanel.add(hiLabel);
                        mainPanel.add(addkbtn);
                        mainPanel.add(add2btn);
                        addframe1.add(mainPanel);

                        //add drug frame
                        addkbtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JDB1 addframe1 = new JDB1();
                                addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
                                JLabel hiLabel = new JLabel("enter the information to add:");
                                JButton addkbtn = new JButton("Add");//need conection with DB 1
                                JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 8));
                                hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                                mainPanel.add(hiLabel);
                                JLabel fname = new JLabel("Formula name");

                                mainPanel.add(fname);

                                JTextField userfname = new JTextField(15);

                                mainPanel.add(userfname);

                                JLabel fact = new JLabel("Factory name     ");

                                mainPanel.add(fact);

                                JTextField userfact = new JTextField(15);

                                mainPanel.add(userfact);

                                JLabel tname = new JLabel("Trade name  ");

                                mainPanel.add(tname);

                                JTextField usertname = new JTextField(15);

                                mainPanel.add(usertname);

                                JLabel Expire = new JLabel("Expire date(YYYY-MM-DD)      ");

                                mainPanel.add(Expire);

                                JTextField userExpire = new JTextField(9);

                                mainPanel.add(userExpire);

                                mainPanel.add(addkbtn);
                                addframe1.add(mainPanel);
                                // adding drug to the database
                                addkbtn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            //1
                                            String q1 = "INSERT INTO DRUG VALUES ( ? , ? , ? , ?)";
                                            Class.forName("com.mysql.jdbc.Driver");
                                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmcy", "root", "1234");
                                            //might need an statement obj (dont know why)
                                            PreparedStatement pst1 = con.prepareStatement(q1);
                                            pst1.setString(1, userfname.getText());
                                            pst1.setString(2, usertname.getText());
                                            pst1.setString(3, userfact.getText());
                                            //since the expire date is from type DATE
                                            Date date = Date.valueOf(userExpire.getText());
                                            pst1.setDate(4, date);
                                            pst1.executeUpdate();
                                            con.close();
                                            JOptionPane.showMessageDialog(null, "Information added successfully ");

                                        } catch (SQLIntegrityConstraintViolationException ee) {
                                            JOptionPane.showMessageDialog(null, "Please enter all the Information ");
                                        } catch (SQLException ex) {
                                            ex.printStackTrace();

                                        } catch (ClassNotFoundException ex) {
                                            Logger.getLogger(JDB1.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                });
                                addframe1.setVisible(true);
                            }// actionpreformed add drug
                        });//add actionlistener add drug

                        //add emplyee frame
                        add2btn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JDB1 addframe1 = new JDB1();
                                addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
                                JLabel hiLabel = new JLabel("enter the information to add:");
                                JButton addkbtn = new JButton("Add");//need conection with DB 2
                                JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 0));
                                hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                                mainPanel.add(hiLabel);

                                JLabel branch = new JLabel("Branch id*");
                                mainPanel.add(branch);
                                JTextField branchuser = new JTextField(10);
                                mainPanel.add(branchuser);
                                JLabel branchname = new JLabel("Branch name*");
                                mainPanel.add(branchname);
                                JTextField bnameuser = new JTextField(10);
                                mainPanel.add(bnameuser);
                                JLabel bphone = new JLabel("Branch phone number*");
                                mainPanel.add(bphone);
                                JTextField bphoneuser = new JTextField(10);
                                mainPanel.add(bphoneuser);
                                JLabel baddress = new JLabel("Branch address*");
                                mainPanel.add(baddress);
                                JTextField baddressuser = new JTextField(10);
                                mainPanel.add(baddressuser);
                                JLabel Ename = new JLabel("Employee name* ");
                                mainPanel.add(Ename);
                                JTextField Enameuser = new JTextField(10);
                                mainPanel.add(Enameuser);
                                JLabel Eage = new JLabel("Employee age");
                                mainPanel.add(Eage);
                                JTextField Eageuser = new JTextField(10);
                                mainPanel.add(Eageuser);
                                JLabel Edegree = new JLabel("Employee degree*");
                                JTextField Edegreeuser = new JTextField(10);
                                mainPanel.add(Edegree);
                                mainPanel.add(Edegreeuser);

                                mainPanel.add(addkbtn);
                                addframe1.add(mainPanel);

                                //2
                                addkbtn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            //2
                                            String q1 = "INSERT INTO PHARMACY_BRANCH VALUES (?,?,?,?,?,?,?)";
                                            //Class.forName("oracle.jdbc.driver.OracleDriver");  
                                            Connection con = DriverManager.getConnection("", "", "");
                                            //might need an statement obj (dont know why)
                                            PreparedStatement pst1 = con.prepareStatement(q1);
                                            pst1.setString(1, branchuser.getText());
                                            pst1.setString(2, bnameuser.getText());
                                            pst1.setString(3, bphoneuser.getText());
                                            pst1.setString(4, baddressuser.getText());
                                            pst1.setString(5, bphoneuser.getText());
                                            pst1.setString(6, Enameuser.getText());
                                            //since the E_age is from type INT
                                            pst1.setInt(7, Integer.parseInt(Edegreeuser.getText()));
                                            pst1.executeUpdate();
                                            con.close();
                                            JOptionPane.showMessageDialog(null, "Information added successfully ");

                                        } catch (SQLIntegrityConstraintViolationException ee) {
                                            JOptionPane.showMessageDialog(null, "Please enter all the Information with asterisk ");
                                        } catch (Exception ex) {
                                            ex.printStackTrace();

                                        }
                                    }

                                });
                                addframe1.setVisible(true);
                            }//end actionpreformed add emp

                        });//end of addactionlistner add emp
                        addframe1.setVisible(true);
                    }//end of add button actionperfprmed

                });//end of manger add button addactionleister method

                //delete frame
                deletebtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDB1 addframe1 = new JDB1();
                        addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                        JLabel hiLabel = new JLabel("Pick:");
                        JButton delet2btn = new JButton("Delete drug");
                        JButton deletebtn = new JButton("Delete employee");
                        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                        hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                        delet2btn.setAlignmentX(CENTER_ALIGNMENT);
                        deletebtn.setAlignmentX(CENTER_ALIGNMENT);
                        mainPanel.add(hiLabel);
                        mainPanel.add(delet2btn);
                        mainPanel.add(deletebtn);
                        addframe1.add(mainPanel);

                        //delete drug frame
                        delet2btn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JDB1 addframe1 = new JDB1();
                                JLabel label = null;
                                JTextField labeluser = null;
                                addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                                JLabel hiLabel = new JLabel("enter the information to delete:");
                                JButton delet2btn = new JButton("Delete");//need conection with DB 3

                                JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                                hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                                mainPanel.add(hiLabel);

                                String[] labels = {"Formula name", "Trade name"};
                                JTextField[] userTXT = new JTextField[2];
                                for (int i = 0; i < 2; i++) {
                                    label = new JLabel(labels[i]);
                                    mainPanel.add(label);
                                    userTXT[i] = new JTextField(10);
                                    mainPanel.add(userTXT[i]);
                                }
                                mainPanel.add(delet2btn);
                                addframe1.add(mainPanel);

                                //3
                                delet2btn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            //3
                                            String q1 = "DELETE FROM DRUG WHERE Drug_fname=? AND Drug_tname=?";
                                            Connection con = DriverManager.getConnection("", "", "");
                                            //might need an statement obj (dont know why)
                                            PreparedStatement pst1 = con.prepareStatement(q1);
                                            pst1.setString(1, userTXT[0].getText());
                                            pst1.setString(2, userTXT[1].getText());

                                            pst1.executeUpdate();
                                            con.close();
                                            JOptionPane.showMessageDialog(null, "Information added successfully ");

                                        } catch (SQLIntegrityConstraintViolationException ee) {
                                            JOptionPane.showMessageDialog(null, "Please enter all the Information with asterisk ");
                                        } catch (Exception ex) {
                                            ex.printStackTrace();

                                        }
                                    }

                                });
                                addframe1.setVisible(true);
                            }//end of delete drug maneger actionlistener

                        });//end of delete drug maneger action preformed

                        //delete employee
                        deletebtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JDB1 addframe1 = new JDB1();
                                JLabel label = null;
                                //JTextField labeluser = null;
                                addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                                JLabel hiLabel = new JLabel("enter the information to delete:");
                                JButton delet2btn = new JButton("Delete");//need conection with DB 4

                                JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                                hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                                mainPanel.add(hiLabel);

                                String[] labels = {"Branch id", "Employee name"};
                                JTextField[] userTXT = new JTextField[2];
                                for (int i = 0; i < 2; i++) {
                                    label = new JLabel(labels[i]);
                                    mainPanel.add(label);
                                    userTXT[i] = new JTextField(10);
                                    mainPanel.add(userTXT[i]);
                                }
                                mainPanel.add(delet2btn);
                                addframe1.add(mainPanel);
                                //4
                                delet2btn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            //4
                                            String q1 = "DELETE FROM PHARMACY_BRANCH WHERE Branch_id=? AND E_name=?";
                                            Connection con = DriverManager.getConnection("", "", "");
                                            //might need an statement obj (dont know why)
                                            PreparedStatement pst1 = con.prepareStatement(q1);
                                            pst1.setString(1, userTXT[0].getText());
                                            pst1.setString(2, userTXT[1].getText());

                                            pst1.executeUpdate();
                                            con.close();
                                            JOptionPane.showMessageDialog(null, "Information added successfully ");

                                        } catch (SQLIntegrityConstraintViolationException ee) {
                                            JOptionPane.showMessageDialog(null, "Please enter all the Information with asterisk ");
                                        } catch (Exception ex) {
                                            ex.printStackTrace();

                                        }
                                    }

                                });

                                addframe1.setVisible(true);
                            }//end of delete emp maneger actionlistener

                        });//end of delete emp maneger action preformed
                        addframe1.setVisible(true);

                    }//end of delete maneger action preformed

                });//end of delete maneger addactionlistener

                //Retrivere frame
                retbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDB1 addframe1 = new JDB1();
                        addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                        JLabel hiLabel = new JLabel("Pick:");
                        //JButton retkbtn=new JButton ("Retrivere pharmcy branches");//needs DBC
                        JButton ret2ebtn = new JButton("Retrieve all drugs");//needs DBC
                        JButton ret3ebtn = new JButton("Retrieve patients name and phone number by a doctor and drug");//DBC
                        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                        hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                        //retkbtn.setAlignmentX(CENTER_ALIGNMENT);
                        ret2ebtn.setAlignmentX(CENTER_ALIGNMENT);
                        ret3ebtn.setAlignmentX(CENTER_ALIGNMENT);
                        //deletebtn.setAlignmentX(CENTER_ALIGNMENT);
                        mainPanel.add(hiLabel);
                        //mainPanel.add(retkbtn);
                        mainPanel.add(ret2ebtn);
                        mainPanel.add(ret3ebtn);
                        addframe1.add(mainPanel);

                        //retrieve patient by doctor id and drug trade name
                        ret3ebtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JDB1 retrieveFrame = new JDB1();
                                retrieveFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

                                JLabel doctorLabel = new JLabel("Doctor Name:");
                                JTextField doctorTextField = new JTextField(10);

                                JLabel drugLabel = new JLabel("Drug Trade Name:");
                                JTextField drugTextField = new JTextField(10);

                                JButton retrieveButton = new JButton("Retrieve Patients");

                                JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                                mainPanel.setAlignmentX(CENTER_ALIGNMENT);

                                mainPanel.add(doctorLabel);
                                mainPanel.add(doctorTextField);
                                mainPanel.add(drugLabel);
                                mainPanel.add(drugTextField);
                                mainPanel.add(retrieveButton);

                                retrieveFrame.add(mainPanel);

                                retrieveButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                       
                                        try {
                                             JDB1 retrieveFrame = new JDB1();
                                            String doctorId = doctorTextField.getText();
                                            String drugTradeName = drugTextField.getText();

                                            String query = "SELECT Name,Phone_num FROM PATIENT,DOCTOR,PRESCRIBE WHERE  Ssn=Patient_ssn AND Doctor_id=Dr_id AND Dr_name = ? AND Drug_tname = ?";
                                            //String query = "SELECT Name,Phone_num FROM PATIENT,PRESCRIBE WHERE Doctor_id = ? AND Drug_tname = ?";
                                            
                                            Connection con = DriverManager.getConnection("url", "dbuser", "password");
                                            PreparedStatement smt = con.prepareStatement(query);
                                            smt.setString(1, doctorId);
                                            smt.setString(2, drugTradeName);

                                            Vector columnNames = new Vector();
                                            Vector data = new Vector();
                                            ResultSet rs = smt.executeQuery();

                                            ResultSetMetaData md = rs.getMetaData();
                                            int Columns = md.getColumnCount();
                                            //prepare the columns 
                                            for (int i = 1; i <= Columns; i++) {
                                                columnNames.addElement(md.getColumnName(i));
                                            }
                                            //prepare the rows 
                                            while (rs.next()) {
                                                Vector row = new Vector();
                                                for (int i = 1; i <= Columns; i++) {
                                                    row.addElement(rs.getObject(i));
                                                }
                                                data.addElement(row);

                                                /*String patientName = rs.getString(1);
                                                String patientNum = rs.getString(2);
                                                resultLabel.setText(resultLabel.getText() + ", " + patientName);
                                                resultLabel.setText(resultLabel.getText() + ", " + patientNum);*/
                                            }
                                            rs.close();
                                            smt.close();
                                            con.close();
                                            JTable table = new JTable(data, columnNames) {
                                                public Class getColumnsClass(int column) {
                                                    for (int row = 0; row < getRowCount(); row++) {
                                                        Object o = getValueAt(row, column);
                                                        if (o != null) {
                                                            return o.getClass();
                                                        }
                                                    }
                                                    return Object.class;
                                                }
                                            };
                                            JScrollPane sp = new JScrollPane(table);
                                            retrieveFrame.add(sp);

                                            retrieveFrame.pack();
                                            retrieveFrame.setVisible(true);

                                        } catch (SQLException ex) {
                                            ex.printStackTrace();
                                        }

                                    }
                                });
                                retrieveFrame.setVisible(true);
                            }
                        });

                        //retrieve all drugs
                        ret2ebtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JDB1 retrieveFrame = new JDB1();
                                try {

                                    retrieveFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                                    String query = "SELECT * FROM DRUG";
                                    Connection con = DriverManager.getConnection("url", "dbuser", "password");
                                    Statement smt = con.createStatement();
                                    ResultSet rs = smt.executeQuery(query);
                                    //
                                    Vector columnNames = new Vector();
                                    Vector data = new Vector();

                                    ResultSetMetaData md = rs.getMetaData();
                                    int Columns = md.getColumnCount();
                                    //prepare the columns 
                                    for (int i = 1; i <= Columns; i++) {
                                        columnNames.addElement(md.getColumnName(i));
                                    }
                                    //prepare the rows 
                                    while (rs.next()) {
                                        Vector row = new Vector();
                                        for (int i = 1; i <= Columns; i++) {
                                            row.addElement(rs.getObject(i));
                                        }
                                        data.addElement(row);

                                    }
                                    rs.close();
                                    smt.close();
                                    con.close();
                                    JTable table = new JTable(data, columnNames) {
                                        public Class getColumnsClass(int column) {
                                            for (int row = 0; row < getRowCount(); row++) {
                                                Object o = getValueAt(row, column);
                                                if (o != null) {
                                                    return o.getClass();
                                                }
                                            }
                                            return Object.class;
                                        }
                                    };
                                    JScrollPane sp = new JScrollPane(table);
                                    retrieveFrame.add(sp);

                                    retrieveFrame.pack();
                                    retrieveFrame.setVisible(true);

                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                                retrieveFrame.setVisible(true);
                            }
                        });

                        addframe1.setVisible(true);
                    }//end of retrive maneger action preforemed

                });//end of retrive maneger addactionlistener

                //update frame
                updatebtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDB1 addframe1 = new JDB1();
                        addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                        JLabel hiLabel = new JLabel("Pick:");
                        JButton updatebtn = new JButton("Update drug price");
                        JButton update2btn = new JButton("Update employee degree");
                        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                        hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                        updatebtn.setAlignmentX(CENTER_ALIGNMENT);
                        update2btn.setAlignmentX(CENTER_ALIGNMENT);
                        //deletebtn.setAlignmentX(CENTER_ALIGNMENT);
                        mainPanel.add(hiLabel);
                        mainPanel.add(updatebtn);
                        mainPanel.add(update2btn);
                        addframe1.add(mainPanel);

                        //update drug price
                        updatebtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JDB1 addframe1 = new JDB1();
                                JLabel label = null;
                                //JTextField labeluser = null;
                                addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                                JLabel hiLabel = new JLabel("enter the information to update:");
                                JButton uu2btn = new JButton("Update");//need conection with DB

                                JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                                hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                                mainPanel.add(hiLabel);

                                String[] labels = {"Formula name", "Trade name", "New Price"};
                                JTextField[] userTXT = new JTextField[3];
                                for (int i = 0; i < 3; i++) {
                                    label = new JLabel(labels[i]);
                                    mainPanel.add(label);
                                    userTXT[i] = new JTextField(10);
                                    mainPanel.add(userTXT[i]);
                                }
                                mainPanel.add(uu2btn);
                                addframe1.add(mainPanel);

                                addframe1.setVisible(true);
                                uu2btn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            //4
                                            String q1 = "UPDATE SUPPLIED_BY SET Price=? WHERE Fname=? AND Tname=? ";
                                            Connection con = DriverManager.getConnection("", "", "");
                                            //might need an statement obj (dont know why)
                                            PreparedStatement pst1 = con.prepareStatement(q1);
                                            pst1.setString(2, userTXT[0].getText());
                                            pst1.setString(3, userTXT[1].getText());
                                            pst1.setInt(1, Integer.parseInt(userTXT[2].getText()));

                                            pst1.executeUpdate();
                                            con.close();
                                            JOptionPane.showMessageDialog(null, "Information updated successfully ");

                                        } catch (SQLIntegrityConstraintViolationException ee) {
                                            JOptionPane.showMessageDialog(null, "Please enter all the Information ");
                                        } catch (Exception ex) {
                                            ex.printStackTrace();

                                        }

                                    }

                                });

                            }//end of update price  maneger actionlistener

                        });//end of update price  maneger action preformed

                        //update drug price
                        update2btn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JDB1 addframe1 = new JDB1();
                                JLabel label = null;
                                // JTextField labeluser = null;
                                addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                                JLabel hiLabel = new JLabel("enter the information to update:");
                                JButton uu2btn = new JButton("Update");//need conection with DB

                                JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                                hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                                mainPanel.add(hiLabel);

                                String[] labels = {"Branch id", "Employee name", "New degree"};
                                JTextField[] userTXT = new JTextField[3];
                                for (int i = 0; i < 3; i++) {
                                    label = new JLabel(labels[i]);
                                    mainPanel.add(label);
                                    userTXT[i] = new JTextField(10);
                                    mainPanel.add(userTXT[i]);
                                }
                                mainPanel.add(uu2btn);
                                addframe1.add(mainPanel);

                                addframe1.setVisible(true);
                                uu2btn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            //4
                                            String q1 = "UPDATE PHARMACY_BRANCH SET E_degree=? WHERE E_name=? AND Branch_id=?";
                                            Connection con = DriverManager.getConnection("", "", "");
                                            //might need an statement obj (dont know why)
                                            PreparedStatement pst1 = con.prepareStatement(q1);
                                            pst1.setString(1, userTXT[2].getText());
                                            pst1.setString(2, userTXT[1].getText());
                                            pst1.setInt(3, Integer.parseInt(userTXT[0].getText()));

                                            pst1.executeUpdate();
                                            con.close();
                                            JOptionPane.showMessageDialog(null, "Information updated successfully ");

                                        } catch (SQLIntegrityConstraintViolationException ee) {
                                            JOptionPane.showMessageDialog(null, "Please enter all the Information ");
                                        } catch (Exception ex) {
                                            ex.printStackTrace();

                                        }

                                    }

                                });
                            }//end of update degree emp maneger actionlistener

                        });//end of update degree emp maneger action preformed

                        addframe1.setVisible(true);
                    }//end of update maneger action prefpremd

                });//end of update maneger addactionlistener

                Mframe1.setVisible(true);
            }//manger action performe method  end

        });//manger addactionlistiner end

        //doctor screen
        pickbtn2.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                JDB1 Dframe1 = new JDB1();
                Dframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                JLabel hiLabel = new JLabel("Operations:");
                JButton addkbtn = new JButton("Add prescription");//needs DBC 1
                JButton deletebtn = new JButton("Delete prescription");//needs DBC
                JButton re1tbtn = new JButton("Retrieve prescription for a patient ");//needs DBC
                JButton re2tbtn = new JButton("Retrieve patients treated by a specific doctor");//needs DBC
                JButton updatebtn = new JButton("Update prescription");//needs DBC
                JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                addkbtn.setAlignmentX(CENTER_ALIGNMENT);
                deletebtn.setAlignmentX(CENTER_ALIGNMENT);
                re1tbtn.setAlignmentX(CENTER_ALIGNMENT);
                updatebtn.setAlignmentX(CENTER_ALIGNMENT);
                re2tbtn.setAlignmentX(CENTER_ALIGNMENT);
                mainPanel.add(hiLabel);
                mainPanel.add(addkbtn);
                mainPanel.add(deletebtn);
                mainPanel.add(re1tbtn);
                mainPanel.add(re2tbtn);
                mainPanel.add(updatebtn);
                Dframe1.add(mainPanel);

                //add frame
                addkbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDB1 addframe1 = new JDB1();
                        addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                        JLabel hiLabel = new JLabel("enter the information to add:");
                        JButton addkbtn = new JButton("Add");//needs DBC
                        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                        hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                        mainPanel.add(hiLabel);
                        JLabel fname = new JLabel("Formula name");

                        mainPanel.add(fname);

                        JTextField userfname = new JTextField(8);

                        mainPanel.add(userfname);

                        JLabel Doc = new JLabel("Doctor id ");

                        mainPanel.add(Doc);

                        JTextField userDoc = new JTextField(8);

                        mainPanel.add(userDoc);

                        JLabel tname = new JLabel("Trade name");

                        mainPanel.add(tname);

                        JTextField usertname = new JTextField(8);

                        mainPanel.add(usertname);

                        JLabel Quan = new JLabel("Quantity");

                        mainPanel.add(Quan);

                        JTextField Quanuser = new JTextField(8);

                        mainPanel.add(Quanuser);

                        JLabel ssn = new JLabel("patient ssn");

                        mainPanel.add(ssn);

                        JTextField userssn = new JTextField(8);

                        mainPanel.add(userssn);

                        mainPanel.add(addkbtn);
                        addframe1.add(mainPanel);
                        //1
                        addkbtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    //1
                                    String q1 = "INSERT INTO PRESCRIBE VALUES ( ? , ? , ? , ?, ?  )";
                                    Connection con = DriverManager.getConnection("", "", "");
                                    //might need an statement obj (dont know why)
                                    PreparedStatement pst1 = con.prepareStatement(q1);
                                    pst1.setString(1, userfname.getText());
                                    pst1.setString(2, usertname.getText());
                                    pst1.setInt(3, Integer.parseInt(userDoc.getText()));
                                    //pst1.setString(3, userDoc.getText());//int
                                    pst1.setString(4, userssn.getText());
                                    pst1.setString(5, Quanuser.getText());
                                    pst1.executeUpdate();
                                    con.close();
                                    JOptionPane.showMessageDialog(null, "Information added successfully ");

                                } catch (SQLIntegrityConstraintViolationException ee) {
                                    JOptionPane.showMessageDialog(null, "Please enter all the Information with asterisk ");
                                } catch (Exception ex) {
                                    ex.printStackTrace();

                                }
                            }

                        });
                        addframe1.setVisible(true);
                    }//end of add action prefprmed frame

                });//end of add action preformed frame

                // delete frame
                deletebtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDB1 addframe1 = new JDB1();
                        JLabel label = null;
                        JTextField labeluser = null;
                        addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                        JLabel hiLabel = new JLabel("enter the information to delete:");
                        JButton delete2btn = new JButton("Delete");//need conection with DB

                        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                        hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                        mainPanel.add(hiLabel);

                        String[] labels = {"Doctor id", "Formula name", "Trade name", "Patient ssn"};
                        JTextField[] userTXT = new JTextField[4];
                        for (int i = 0; i < 4; i++) {
                            label = new JLabel(labels[i]);
                            mainPanel.add(label);
                            userTXT[i] = new JTextField(10);
                            mainPanel.add(userTXT[i]);
                        }
                        mainPanel.add(delete2btn);
                        addframe1.add(mainPanel);
                        //2
                        delete2btn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    //2
                                    String q1 = "DELETE FROM PRESCRIBE WHERE Drug_fname=? AND Drug_tname=? AND Doctor_id=? AND Patient_ssn=? ";
                                    Connection con = DriverManager.getConnection("", "", "");
                                    //might need an statement obj (dont know why)
                                    PreparedStatement pst1 = con.prepareStatement(q1);
                                    pst1.setString(1, userTXT[1].getText());
                                    pst1.setString(2, userTXT[2].getText());
                                    pst1.setString(2, userTXT[0].getText());
                                    pst1.setString(2, userTXT[3].getText());

                                    pst1.executeUpdate();
                                    con.close();
                                    //thd messge might appear after the con.close() or before it
                                    JOptionPane.showMessageDialog(null, "Information deleted successfully ");

                                } catch (SQLIntegrityConstraintViolationException ee) {
                                    JOptionPane.showMessageDialog(null, "Please enter all the Information ");
                                } catch (Exception ex) {
                                    ex.printStackTrace();

                                }
                            }

                        });
                        addframe1.setVisible(true);

                    }//end of delete pre actionlistener

                });//end of delete pre action preformed

                //update frame
                updatebtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDB1 addframe1 = new JDB1();
                        JLabel label = null;
                        //JTextField labeluser = null;
                        addframe1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                        JLabel hiLabel = new JLabel("enter the information to update:");
                        JButton up2btn = new JButton("Update");//need conection with DB

                        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                        hiLabel.setAlignmentX(CENTER_ALIGNMENT);
                        mainPanel.add(hiLabel);

                        String[] labels = {"Doctor id", "Formula name", "Trade name", "Patient ssn", "New Quantity"};
                        JTextField[] userTXT = new JTextField[5];
                        for (int i = 0; i < 5; i++) {
                            label = new JLabel(labels[i]);
                            mainPanel.add(label);
                            userTXT[i] = new JTextField(10);
                            mainPanel.add(userTXT[i]);
                        }
                        mainPanel.add(up2btn);
                        addframe1.add(mainPanel);

                        addframe1.setVisible(true);
                        up2btn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    //2
                                    String q1 = "UPDATE PRESCRIBE SET Quantity=? WHERE Drug_fname=? AND Drug_tname=?AND Doctor_id=? AND Patient_ssn=? ";
                                    Connection con = DriverManager.getConnection("", "", "");
                                    //might need an statement obj (dont know why)
                                    PreparedStatement pst1 = con.prepareStatement(q1);
                                    pst1.setString(1, userTXT[4].getText());
                                    pst1.setString(2, userTXT[1].getText());
                                    pst1.setString(3, userTXT[2].getText());
                                    pst1.setString(4, userTXT[0].getText());
                                    pst1.setString(5, userTXT[3].getText());

                                    pst1.executeUpdate();
                                    con.close();
                                    //thd messge might appear after the con.close() or before it
                                    JOptionPane.showMessageDialog(null, "Information updated successfully ");

                                } catch (SQLIntegrityConstraintViolationException ee) {
                                    JOptionPane.showMessageDialog(null, "Please enter all the Information ");
                                } catch (Exception ex) {
                                    ex.printStackTrace();

                                }
                            }

                        });
                    }//end of update pre actionlistener

                });//end of update pre action preformed

                //retrieve frame
                re1tbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDB1 retrieveFrame = new JDB1();
                        retrieveFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

                        JLabel patientLabel = new JLabel("Patient SSN:");
                        JTextField patientTextField = new JTextField(10);

                        JButton retrieveButton = new JButton("Retrieve Prescriptions");

                        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                        mainPanel.setAlignmentX(CENTER_ALIGNMENT);

                        mainPanel.add(patientLabel);
                        mainPanel.add(patientTextField);
                        mainPanel.add(retrieveButton);

                        retrieveFrame.add(mainPanel);

                        retrieveButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    String patientSSN = patientTextField.getText();

                                    String query = "SELECT * FROM PRESCRIBE WHERE Patient_ssn = ?";
                                    Connection con = DriverManager.getConnection("url", "dbuser", "password");
                                    PreparedStatement pst = con.prepareStatement(query);
                                    pst.setString(1, patientSSN);
                                    ResultSet rs = pst.executeQuery();

                                    //
                                    Vector columnNames = new Vector();
                                    Vector data = new Vector();

                                    ResultSetMetaData md = rs.getMetaData();
                                    int Columns = md.getColumnCount();
                                    //prepare the columns 
                                    for (int i = 1; i <= Columns; i++) {
                                        columnNames.addElement(md.getColumnName(i));
                                    }
                                    //prepare the rows 
                                    while (rs.next()) {
                                        Vector row = new Vector();
                                        for (int i = 1; i <= Columns; i++) {
                                            row.addElement(rs.getObject(i));
                                        }
                                        data.addElement(row);

                                    }
                                    rs.close();
                                    pst.close();
                                    con.close();
                                    JTable table = new JTable(data, columnNames) {
                                        public Class getColumnsClass(int column) {
                                            for (int row = 0; row < getRowCount(); row++) {
                                                Object o = getValueAt(row, column);
                                                if (o != null) {
                                                    return o.getClass();
                                                }
                                            }
                                            return Object.class;
                                        }
                                    };
                                    JScrollPane sp = new JScrollPane(table);
                                    mainPanel.add(sp);

                                    retrieveFrame.pack();
                                    //retrieveFrame.setVisible(true);

                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                        retrieveFrame.setVisible(true);
                    }
                });

                re2tbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDB1 retrieveFrame = new JDB1();
                        retrieveFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

                        JLabel doctorLabel = new JLabel("Doctor name:");
                        JTextField doctorTextField = new JTextField(10);

                        JButton retrieveButton = new JButton("Retrieve");

                        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                        mainPanel.setAlignmentX(CENTER_ALIGNMENT);

                        mainPanel.add(doctorLabel);
                        mainPanel.add(doctorTextField);
                        mainPanel.add(retrieveButton);

                        retrieveFrame.add(mainPanel);

                        retrieveButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    JDB1 retrieveF = new JDB1();
                                    String doctorId = doctorTextField.getText();

                                    String query = "SELECT Ssn,Name,Phone_num FROM PATIENT,TREATED_BY,DOCTOR WHERE Ssn=P_ssn AND D_id=Dr_id AND Dr_name=?";
                                    Connection con = DriverManager.getConnection("url", "dbuser", "password");
                                    PreparedStatement pst = con.prepareStatement(query);
                                    pst.setString(1, doctorId);
                                    ResultSet rs = pst.executeQuery();
                                    Vector columnNames = new Vector();
                                    Vector data = new Vector();

                                    ResultSetMetaData md = rs.getMetaData();
                                    int Columns = md.getColumnCount();
                                    //prepare the columns 
                                    for (int i = 1; i <= Columns; i++) {
                                        columnNames.addElement(md.getColumnName(i));
                                    }
                                    //prepare the rows 
                                    while (rs.next()) {
                                        Vector row = new Vector();
                                        for (int i = 1; i <= Columns; i++) {
                                            row.addElement(rs.getObject(i));
                                        }
                                        data.addElement(row);

                                    }
                                    rs.close();
                                    pst.close();
                                    con.close();
                                    JTable table = new JTable(data, columnNames) {
                                        public Class getColumnsClass(int column) {
                                            for (int row = 0; row < getRowCount(); row++) {
                                                Object o = getValueAt(row, column);
                                                if (o != null) {
                                                    return o.getClass();
                                                }
                                            }
                                            return Object.class;
                                        }
                                    };
                                    JScrollPane sp = new JScrollPane(table);
                                    retrieveF.add(sp);

                                    retrieveFrame.pack();
                                    retrieveF.setVisible(true);

                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                        retrieveFrame.setVisible(true);
                    }
                });
                Dframe1.setVisible(true);

            }//doctor action performed end

        });//doctor addactionlistener end

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
