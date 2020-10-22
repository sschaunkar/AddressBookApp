
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class Login extends javax.swing.JFrame {
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
static final String DB_URL = "jdbc:mysql://localhost/mydata";
static final String USER = "root";
static final String PASS = "sachin30*";
int count=0;
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        NextID();
        jRadioButton2.setSelected(true);
    }
        public final void  ShowAllData(){
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)) {
      Class.forName(JDBC_DRIVER);
      PreparedStatement ps = conn.prepareStatement("select * from contactbook");
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
            String id=rs.getString(1);
            String nam = rs.getString(2);
            String add= rs.getString(3);
            String email = rs.getString(4);
            String cont = rs.getString(5);
            String rel = rs.getString(6);
            Object newrow[] = {id,nam,add,email,cont,rel};
            model.addRow(newrow);
           }          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public final void SearchByName()
    {
     String name = jTextField7.getText();
     if(name.isEmpty())
     {
    JOptionPane.showMessageDialog(rootPane, "Please Enter Name In Search Box"); 
     }else{
     DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        try( Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)) {
            Class.forName(JDBC_DRIVER);
PreparedStatement ps = conn.prepareStatement
        ("select * from contactbook where name like '"+name+"%';");
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
            String id=rs.getString(1);
            String nam = rs.getString(2);
            String add= rs.getString(3);
            String email = rs.getString(4);
            String cont = rs.getString(5);
            String rel = rs.getString(6);
            Object newrow[] = {id,nam,add,email,cont,rel};
            model.addRow(newrow);
            count++;
           } 
           JOptionPane.showMessageDialog(rootPane,""+count+" Records Found");
           count=0;
        } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
     }
    }
        public final void SearchByID()
    {
     String id1  =jTextField7.getText();
     if(id1.isEmpty())
     {
        JOptionPane.showMessageDialog(rootPane, "Please Enter ID In Search Box"); 
     }else{
     DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        try( Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)) {
            Class.forName(JDBC_DRIVER);
PreparedStatement ps = conn.prepareStatement
        ("select * from contactbook where id=?");
            ps.setString(1, id1);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
            String id=rs.getString(1);
            String nam = rs.getString(2);
            String add= rs.getString(3);
            String email = rs.getString(4);
            String cont = rs.getString(5);
            String rel = rs.getString(6);
            Object newrow[] = {id,nam,add,email,cont,rel};
            model.addRow(newrow);
            count++;
           }
           if(count==0)
           {
               JOptionPane.showMessageDialog(rootPane, "No Records Found");
           }
           count=0;
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
     }
    }
        public final void SearchByAddress()
    {
     String address   = jTextField7.getText();
     if(address.isEmpty())
     {
        JOptionPane.showMessageDialog(rootPane, "Please Enter Address In Search Box"); 
     }else{
     DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        try( Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)) {
            Class.forName(JDBC_DRIVER);
            PreparedStatement ps = conn.prepareStatement("select * from contactbook where address=?");
            ps.setString(1, address);
            
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
            String id=rs.getString(1);
            String nam = rs.getString(2);
            String add= rs.getString(3);
            String email = rs.getString(4);
            String cont = rs.getString(5);
            String rel = rs.getString(6);
            Object newrow[] = {id,nam,add,email,cont,rel};
            model.addRow(newrow);
            count++;
           }
           JOptionPane.showMessageDialog(rootPane, ""+count+" Records Found");
           count=0;
        } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
     }
    }
        public final void SearchByGroup()
    {
     String grouptype   = jTextField7.getText();
     if(grouptype.isEmpty())
     {
        JOptionPane.showMessageDialog(rootPane, "Please Enter Group In Search Box"); 
     }else{
     DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        try( Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)) {
            Class.forName(JDBC_DRIVER);
            PreparedStatement ps = conn.prepareStatement("select * from contactbook where grouptype=?");
            ps.setString(1, grouptype);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
            String id=rs.getString(1);
            String nam = rs.getString(2);
            String add= rs.getString(3);
            String email = rs.getString(4);
            String cont = rs.getString(5);
            String rel = rs.getString(6);
            Object newrow[] = {id,nam,add,email,cont,rel};
            model.addRow(newrow);
            count++;
           }          
            JOptionPane.showMessageDialog(rootPane,""+count+" Records Found");
            count=0;
        } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }   
     }
    }
        public final void ClearTable()
    {
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
    }
        public final void NextID()
        {
            try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)) {
            Class.forName(JDBC_DRIVER);
            PreparedStatement ps = conn.prepareStatement("select max(id) from contactbook");
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
           int maxid = rs.getInt(1);
           jTextField2.setText(Integer.toString(maxid+1));
           }          
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main_Window = new javax.swing.JFrame();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        Main_Window.setMinimumSize(new java.awt.Dimension(950, 670));
        Main_Window.setResizable(false);
        Main_Window.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Welcome to Contact Book");
        Main_Window.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 53));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel6.setText("Group Type");
        Main_Window.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 100, 30));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel7.setText("ID");
        Main_Window.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 60, 30));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel8.setText("Name");
        Main_Window.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 60, 30));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel9.setText("Address");
        Main_Window.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 70, 30));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel10.setText("Mail");
        Main_Window.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 70, 30));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel11.setText("Contact");
        Main_Window.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 70, 30));

        jTextField2.setEditable(false);
        Main_Window.getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 102, 140, 30));
        Main_Window.getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 142, 140, 30));
        Main_Window.getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 140, 30));
        Main_Window.getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 140, 30));
        Main_Window.getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 140, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Brother", "Friend", "Family", "Office" }));
        Main_Window.getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 294, 107, -1));

        jButton2.setText("Add New Contact");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Main_Window.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 334, 213, 33));

        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        Main_Window.getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 120, 110, 33));

        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        Main_Window.getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 213, 33));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address", "Mail", "Contact", "Group"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        Main_Window.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 620, 470));

        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        Main_Window.getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 213, 33));

        jButton6.setText("Show All Contacts");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        Main_Window.getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 213, 33));

        jButton7.setText("Exit");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        Main_Window.getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 210, 30));
        Main_Window.getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 180, 30));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("By ID");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        Main_Window.getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, -1, -1));

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("By Name");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        Main_Window.getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("By Address");
        Main_Window.getContentPane().add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, -1));

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("By Group");
        Main_Window.getContentPane().add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageicons/windows_7_new_logon_background_by_anondepressive-d4tgoc9.jpg"))); // NOI18N
        Main_Window.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 640));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Contact Book");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Contact Book");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 90));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("User Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 110, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Password");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 110, 30));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 160, 30));
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 160, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageicons/on.png"))); // NOI18N
        jButton1.setText("Login");
        jButton1.setHideActionText(true);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.setIconTextGap(0);
        jButton1.setInheritsPopupMenu(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dell\\Pictures\\login-page-background-images-hd-2.jpg")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String name = jTextField1.getText();
    String pass = new String(jPasswordField1.getPassword());
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement ps = conn.prepareStatement("select * from users where username = ? and password = ?;");
            ps.setString(1,name);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()==true)
            {
           JOptionPane.showMessageDialog(rootPane, "Successfully Logged In");
           Main_Window.setVisible(true);
                dispose();
            }else{
                JOptionPane.showMessageDialog(rootPane, "Unable to Login User not Exist","Sorry",JOptionPane.ERROR_MESSAGE );
                
            }
             

            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    String name = jTextField3.getText();
     String address = jTextField4.getText();
     String mail = jTextField5.getText();
     String contact = jTextField6.getText();
     String relation=(String)jComboBox1.getSelectedItem();
     if(name.isEmpty() && address.isEmpty() && contact.isEmpty()&& mail.isEmpty()){
         JOptionPane.showMessageDialog(rootPane, "Sorry!! Can't Add Empty Record in Contact Book","Warning",JOptionPane.WARNING_MESSAGE);
     }else{
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)) {
            Class.forName(JDBC_DRIVER);
            PreparedStatement ps = conn.prepareStatement("insert into contactbook values(?,?,?,?,?,?)");
            ps.setString(1,null);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setString(4, mail);
            ps.setString(5, contact);
            ps.setString(6, relation);
            ps.executeUpdate();
            ClearTable();
            ShowAllData();
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jComboBox1.setSelectedIndex(0);
            NextID();
            JOptionPane.showMessageDialog(rootPane, "Contact added Successfully");
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
     }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    System.exit(0);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        ClearTable();
        ShowAllData();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)) {
           int rowno=jTable1.getSelectedRow();
           int colno=jTable1.getSelectedColumn();
           String query=null;
           switch(colno){
               case 0 :
                 query="delete from contactbook where id = ?";
                 break;
               case 1 :
                 query="delete from contactbook where name = ?";
                 break;
               case 2:
                 query="delete from contactbook where address = ?";
                 break;
               case 3:
                   query="delete from contactbook where mail = ?";
                   break;
               case 4:
                   query="delete from contactbook where contact = ?";
                   break;
               case 5:
                   query="delete from contactbook where grouptype = ?";
                   break;
                          }
            Object name = jTable1.getValueAt(rowno, colno);
            int ans =JOptionPane.showConfirmDialog(this, "Are you Sure ?");
            if(ans==1){
               
            }else{
            Class.forName(JDBC_DRIVER);
               try (PreparedStatement ps = conn.prepareStatement(query)) {
                   ps.setObject(1, name);
                   ps.executeUpdate();
                   ClearTable();
                   ShowAllData();
                   JOptionPane.showMessageDialog(rootPane, "Contact Deleted Successfully");
               }
        }
   }catch (Exception e) {
          JOptionPane.showMessageDialog(rootPane, "Select the required record to delete");
       
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    if(jRadioButton1.isSelected())
    {
        ClearTable();
        SearchByID();
    } else if(jRadioButton2.isSelected())
    {
        ClearTable();
       SearchByName(); 
    }else if(jRadioButton3.isSelected())
    {
        ClearTable();
        SearchByAddress();
    }else if(jRadioButton4.isSelected())
    {
        ClearTable();
        SearchByGroup();
    } else {
        JOptionPane.showMessageDialog(rootPane, "Please select Search Criteria");
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
     String id = jTextField2.getText();
     String name = jTextField3.getText();
     String address = jTextField4.getText();
     String mail = jTextField5.getText();
     String contact = jTextField6.getText();
     String relation=(String)jComboBox1.getSelectedItem();
     if(name.isEmpty() && address.isEmpty() && contact.isEmpty()&& mail.isEmpty()){
         JOptionPane.showMessageDialog(rootPane, "Sorry!! Can't Add Empty Record in Contact Book","Warning",JOptionPane.WARNING_MESSAGE);
     }else{
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)) {
            Class.forName(JDBC_DRIVER);
            PreparedStatement ps = conn.prepareStatement("update contactbook set name=?,address=?,mail=?,contact=?,grouptype=? where id=?");
            ps.setString(1,name);
            ps.setString(2, address);
            ps.setString(3, mail);
            ps.setString(4, contact);
            ps.setString(5, relation);
            ps.setString(6, id);
            ps.executeUpdate();
            ClearTable();
            ShowAllData();
            JOptionPane.showMessageDialog(rootPane,"Record Updated Successfully");
        }catch(Exception e)
        {
            e.getMessage();
        }
     }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
    try(Connection con=DriverManager.getConnection(DB_URL,USER,PASS))
    {
    int rowno=jTable1.getSelectedRow();
    int colno=jTable1.getSelectedColumn();
    Object value=jTable1.getValueAt(rowno, colno);
    String query=null;
     switch(colno){
               case 0 :
                 query="select * from contactbook where id = ?";
                 break;
               case 1 :
                 query="select * from contactbook where name = ?";
                 break;
               case 2:
                 query="select * from contactbook where address = ?";
                 break;
               case 3:
                   query="select * from contactbook where mail = ?";
                   break;
               case 4:
                   query="select * from contactbook where contact = ?";
                   break;
               case 5:
                   query="select * from contactbook where grouptype = ?";
                   break;
                          }
               Class.forName(JDBC_DRIVER);
               PreparedStatement ps = con.prepareStatement(query);
               ps.setObject(1, value);
               ResultSet rs = ps.executeQuery();
               while(rs.next())
               {
                  jTextField2.setText(rs.getString(1));
                  jTextField3.setText(rs.getString(2));
                  jTextField4.setText(rs.getString(3));
                  jTextField5.setText(rs.getString(4));
                  jTextField6.setText(rs.getString(5));
                  jComboBox1.setSelectedItem(rs.getString(6));
               }
               
    }catch(Exception e)
    {
        System.out.println(e.getMessage());
    }
    }//GEN-LAST:event_jTable1PropertyChange

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    try(Connection con=DriverManager.getConnection(DB_URL,USER,PASS))
    {
    int rowno=jTable1.getSelectedRow();
    int colno=jTable1.getSelectedColumn();
    Object value=jTable1.getValueAt(rowno, colno);
    String query=null;
     switch(colno){
               case 0 :
                 query="select * from contactbook where id = ?";
                 break;
               case 1 :
                 query="select * from contactbook where name = ?";
                 break;
               case 2:
                 query="select * from contactbook where address = ?";
                 break;
               case 3:
                   query="select * from contactbook where mail = ?";
                   break;
               case 4:
                   query="select * from contactbook where contact = ?";
                   break;
               case 5:
                   query="select * from contactbook where grouptype = ?";
                   break;
                          }
               Class.forName(JDBC_DRIVER);
               PreparedStatement ps = con.prepareStatement(query);
               ps.setObject(1, value);
               ResultSet rs = ps.executeQuery();
               while(rs.next())
               {
                  jTextField2.setText(rs.getString(1));
                  jTextField3.setText(rs.getString(2));
                  jTextField4.setText(rs.getString(3));
                  jTextField5.setText(rs.getString(4));
                  jTextField6.setText(rs.getString(5));
                  jComboBox1.setSelectedItem(rs.getString(6));
               }
               
    }catch(Exception e)
    {
        System.out.println(e.getMessage());   
    }
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame Main_Window;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
