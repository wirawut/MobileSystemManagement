package Form;

import java.awt.Color;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

//packageที่importเข้ามาทั้งหมด
import java.text.MessageFormat;
import java.awt.print.*;
import java.io.File;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class sales_forecasting_system extends javax.swing.JFrame {
    private DefaultTableModel tableModel = new DefaultTableModel();
    private Object[] data = new Object[0];
    //คอนสตรัคเจ้อ ของไฟล์เอาไว้เก็บตัวเเปล
    public sales_forecasting_system() {
        //สตรัคเจอร์ของคลาส เอาไว้ประกาศค่าตัวแปร
        initComponents();
        //สร้างโมเดล เเละรับค่ามาจากตารางชื่อtableForeast
        tableModel = (DefaultTableModel) tableForcast.getModel();
    }
    //คลาสเอาไว้สำหรับเคลียร์เเถวในตารางเพราะมันโผล่ออกมาเกิน
    public void clearData() {
        //methodที่เอาไว้เคลียร์ให้แถวในตารางtableForcastไม่เกิน
        int row = tableForcast.getRowCount() - 1;
        while (row > -1) {
            tableModel.removeRow(row);
            row--;
        }
    }
   /**
    * ดึงข้อมูลออกมาแสดงครับ
    */
    //คลาสที่เอาไว้เเสดงข้อมูลลงในตาราง
    public void bindData() {
        //เเสดงผลลงในตารางtableForcast
        clearData();
        //เก็บคำสั่งคิวรี่จากDB ไว้ในสตริงก่อน
        String sql = "SELECT * FROM forecast ORDER BY Range ASC";
        // clear รายการทั้งหมดที่มีอยู่เดิม
        try {
            //เรียกใช้คลาสการเชื่อมต่อเเล้วสร้างResultSet เเล้วเอาคำสั่งสคิวรี่มาexecute
            Connection c = new MyConnect().getConnection();
            ResultSet rs = c.createStatement().executeQuery(sql);
            // loop อ่านข้อมูลมาใส่หน่วยความจำ ทีละแถว
            int row = 0;
            //ใช้whileในการวนลูปจากDB มาลงในตารางทีละเเถว
            while (rs.next()) {
                tableModel.addRow(data);
                //เซตค่าที่รับมาจากDB ลงไปตามแถวเเละคอลัมที่ต้องการ และเพิ่มลงไปในtaableForcast โดยเริ่มจากแถวที่1เเละเพิ่มค่าเเถวไปเรื่อยๆ
                tableForcast.setValueAt(rs.getString("Range"), row, 0);
                tableForcast.setValueAt(rs.getInt("Cost"), row, 1);
                tableForcast.setValueAt(rs.getInt("Sale"), row, 2);
                tableForcast.setValueAt(rs.getInt("Sale") - rs.getInt("Cost"), row, 3);
                tableForcast.setValueAt(rs.getString("UnitCost"), row, 4);
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //กำหนดค่าให้ textbox ที่ชื่อ lbCostForecast
        Integer CostForecast = tableForcast.getRowCount() + 1;
        lbCostForecast.setText("ค่าใช้จ่ายเดือน/ปีที่" + " " + CostForecast);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmdInsert = new javax.swing.JButton();
        txtRange = new javax.swing.JTextField();
        txtCost = new javax.swing.JTextField();
        txtSale = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbUnitCost = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmdClear = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdUpdate = new javax.swing.JButton();
        cmdDeleteAll = new javax.swing.JButton();
        cmdPrint = new javax.swing.JButton();
        cmdHelp = new javax.swing.JButton();
        cmdChart = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableForcast = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtForecast = new javax.swing.JTextField();
        lbCostForecast = new javax.swing.JLabel();
        cmdForecast = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        mnExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        mnHelp = new javax.swing.JMenu();
        mHelp = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "การจัดการข้อมูลที่ต้องการพยากรณ์", 2, 2, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 153, 153))); // NOI18N

        cmdInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/add.png"))); // NOI18N
        cmdInsert.setToolTipText("เพิ่ม");
        cmdInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdInsertActionPerformed(evt);
            }
        });

        txtRange.setBackground(new java.awt.Color(255, 102, 51));
        txtRange.setToolTipText("กรอกลำดับที่ของเดือนหรือปี");

        txtCost.setBackground(new java.awt.Color(255, 255, 51));
        txtCost.setToolTipText("กรอกค่าใช้จ่ายรวมทั้งหมดโดยค่านี้ได้มาจากการสรุปยอดรวมค่าใช้จ่ายจากงบทางบัญชีหรือแหล่งข้อมูลที่ได้มีการตรวจสอบว่าถูกต้องเรียบร้อยเเล้ว");
        txtCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostActionPerformed(evt);
            }
        });

        txtSale.setBackground(new java.awt.Color(255, 0, 204));
        txtSale.setToolTipText("กรอกยอดขายรวมทั้งหมดโดยค่านี้ได้มาจากการสรุปยอดรวมยอดขายจากงบทางบัญชีหรือแหล่งข้อมูลที่ได้มีการตรวจสอบว่าถูกต้องเรียบร้อยเเล้ว");
        txtSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaleActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("ต้นทุนขาย");

        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("ยอดขาย");

        cbUnitCost.setBackground(new java.awt.Color(255, 153, 0));
        cbUnitCost.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "บาท", "ดอลลาห์", "ยูโร", "เยน", "ฟรังก์", "วอน", "รูเปียห์" }));
        cbUnitCost.setToolTipText("หน่วยของเงินต้องเป็นประเภทเดียวกันทั้งหมดเพื่อให้ง่ายต่อการคำนวน");

        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("เดือน/ปี");

        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("สกุลเงิน");

        cmdClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/remove.png"))); // NOI18N
        cmdClear.setToolTipText("เคลียร์");
        cmdClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearActionPerformed(evt);
            }
        });

        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/delete.png"))); // NOI18N
        cmdDelete.setToolTipText("ลบ");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        cmdUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/process.png"))); // NOI18N
        cmdUpdate.setToolTipText("แก้ไข");
        cmdUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpdateActionPerformed(evt);
            }
        });

        cmdDeleteAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/new.png"))); // NOI18N
        cmdDeleteAll.setToolTipText("เริ่มใหม่");
        cmdDeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteAllActionPerformed(evt);
            }
        });

        cmdPrint.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        cmdPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/printer.png"))); // NOI18N
        cmdPrint.setToolTipText("พิมพ์");
        cmdPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPrintActionPerformed(evt);
            }
        });

        cmdHelp.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        cmdHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/help.png"))); // NOI18N
        cmdHelp.setToolTipText("คู่มือ");
        cmdHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHelpActionPerformed(evt);
            }
        });

        cmdChart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/chart_bar.png"))); // NOI18N
        cmdChart.setToolTipText("กราฟ");
        cmdChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdChartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmdInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdClear))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmdDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdDelete))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdChart, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRange, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbUnitCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSale, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbUnitCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdClear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdChart, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tableForcast.setBackground(new java.awt.Color(0, 204, 255));
        tableForcast.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "เดือน/ปี", "ต้นทุนขาย", "ยอดขาย", "กำไร", "หน่วย"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableForcast.setToolTipText("ตารางแสดงข้อมูลที่ต้องการนำมาพยากรณ์ยอดขาย");
        jScrollPane1.setViewportView(tableForcast);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("ระบบพยากรณ์ยอดขาย");

        txtSearch.setBackground(new java.awt.Color(153, 255, 0));
        txtSearch.setToolTipText("เดือน/ปี");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(153, 0, 51));
        jLabel6.setText("ค้นหา");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "การพยากรณ์", 0, 0, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        txtForecast.setBackground(new java.awt.Color(0, 255, 51));

        lbCostForecast.setForeground(new java.awt.Color(0, 0, 153));
        lbCostForecast.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lbCostForecastPropertyChange(evt);
            }
        });

        cmdForecast.setFont(new java.awt.Font("2555 edcrub huajuckTH", 0, 24)); // NOI18N
        cmdForecast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/chart_line.png"))); // NOI18N
        cmdForecast.setToolTipText("พยากรณ์ยอดขาย");
        cmdForecast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdForecastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCostForecast, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtForecast, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(cmdForecast, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCostForecast, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtForecast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cmdForecast, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenu1.setText("เมนู");

        jMenuItem2.setText("ใหม่");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        mnExit.setText("ออก");
        mnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnExitActionPerformed(evt);
            }
        });
        jMenu1.add(mnExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("รายงาน");

        jMenuItem1.setText("รายงาน");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem3.setText("กราฟ");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        mnHelp.setText("ช่วยเหลือ");

        mHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        mHelp.setText("คู่มือ");
        mHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mHelpActionPerformed(evt);
            }
        });
        mnHelp.add(mHelp);

        jMenuItem4.setText("เกี่ยวกับเรา");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        mnHelp.add(jMenuItem4);

        jMenuBar1.add(mnHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("จัดการข้อมูลที่ต้องการพยากรณ์");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaleActionPerformed

    private void cmdInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdInsertActionPerformed

        try {
            //ถ้าข้อความ Integerที่รับมาทั้งสามช่องมันว่าง(ไม่มีอะไรเลยหรือไม่ได้กรอก) หรือพูดง่ายๆคือห้ามว่างนั่นเองต้องกรอกให้ครบทุกช่องให้เเสดงข้อความให้หรอกใหม่
            if (txtRange.getText().isEmpty() || txtCost.getText().isEmpty() || txtSale.getText().isEmpty()) {
                MessageBox.show(this, "กรุณากรอกข้อมูลให้ครบทุกช่อง", "ผลการตรวจสอบข้อมูล");
            } //ถ้าข้อความ Integerที่รับมามีมากกว่า2หลัก หรือมากกว่าหลักสิบ จะเเสดงข้อความว่าเพื่อให้กรอกใหม่เพราะเดือนจะมีหลักร้อยไม่ได้
            else if (txtRange.getText().length() > 2) {
                MessageBox.show(this, "ข้อมูลที่กรอกมากกว่าที่กำหนดไว้", "ผลการตรวจสอบข้อมูล");
            } else {
                //เพิ่มข้อมูลที่รับมาจากtextbox มาบันทึกลงdb เเล้วให้ bindData()ดึงไปแสดงบนตาราง
                String sql = "INSERT INTO forecast(Range,Cost,Sale,UnitCost) VALUES(?, ?, ?, ?)";
                Connection c = new MyConnect().getConnection();
                
                //รับค่ามาจากtextboxที่กรอกมาเเล้วนำมาเก็บไว้ในตัวแปล
                String Range = txtRange.getText();
                String Cost = txtCost.getText();
                String Sale = txtSale.getText();
                String UnitCost = cbUnitCost.getSelectedItem().toString();
                
                //ใช้preparedเพื่อส่งค่าไปยังเครื่องหมาย ?(อะไรเอ่ย) เช่น pre.setString(1, Range) ส่งค่าจากตัวแปลRange ไปใส่ใน ? ตัวที่1
                PreparedStatement pre = c.prepareStatement(sql);
                pre.setString(1, Range);
                pre.setString(2, Cost);
                pre.setString(3, Sale);
                pre.setString(4, UnitCost);
                if (pre.executeUpdate() != -1) {
                    MessageBox.show(this, "บันทึกข้อมูลแล้ว", "ผลการบันทึกข้อมูล");
                    bindData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmdInsertActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //กรณีที่เปิดโปรแกรมขึ้นมา
        bindData();//ให้ดึงข้อมูลมาจากDBมาเเสดงลงตารางทันที
        //set พื้นหลัง
        txtRange.setBackground(Color.PINK);
        txtCost.setBackground(Color.PINK);
        txtSale.setBackground(Color.PINK);
        cbUnitCost.setBackground(Color.ORANGE);
        txtSearch.setBackground(Color.cyan);
        tableForcast.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_formWindowOpened

    private void cmdClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearActionPerformed
        //เมื่อกดปุ่มนี้เเล้วให้ค่าในtextbox เป็นค่าว่าง
        txtRange.setText("");
        txtSale.setText("");
        txtCost.setText("");
        cbUnitCost.setSelectedIndex(0);
    }//GEN-LAST:event_cmdClearActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        //ลบข้อมูลตามเเถวที่เลือก
        //เลือกแถวไหนให้เก็บค่าไว้ในตัวแปร
        int rowSelected = tableForcast.getSelectedRow();
        
        //เเถวที่เลือกนั้นคอลัมที่0จะเก็บไว้ในตัวแปรใหม่
        String Range = tableForcast.getValueAt(rowSelected, 0).toString();
        
        //คำสั่งลบข้อมูลที่ดาต้าเบสตัวที่เท่ากับตัวที่เราเลือก
        String sql = "DELETE FROM forecast WHERE Range= '" + Range + "' ";
        try {
            Connection c = new MyConnect().getConnection();//เชื่อมต่อกับDB

            //นำ sql มาคิวรี่ เเละถ้าจริงให้ทำ
            if (c.createStatement().executeUpdate(sql) != -1) { 
                MessageBox.show(this, "ลบข้อมูลแล้ว", "ผลการลบข้อมูล");
                bindData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void cmdUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpdateActionPerformed

        //เเก้ไขตัวที่ต้องการ
        //คำสั่งเพื่อupdate
        String sql = "UPDATE forecast SET Range = ?, Cost = ?,Sale = ? ,"
                + "UnitCost = ? WHERE Range = ?";
        try {
            //เชื่อมต่อกับDB โดยเรียกใช้class Myconnect เเละ
            //เรียกใช้ methodgetConnection ที่อยู่ในคลาสMyMonnect
            Connection c = new MyConnect().getConnection();

            //ข้อมูลที่ต้องการส่งมีหลายตัวคือค่า ? ? ? ?จึงต้องใช้prepared 
            //ให้ใส่ค่าคำสั่งsql เเล้วส่งไปเเมร่งครั้งเดียวโลด 
            PreparedStatement pre = c.prepareStatement(sql);
            //ส่งข้อมูลจากtextboxทุกอันลงไปใน{ko-hv,^]grnjvggmomuj ????? 
            //ทุกตัวตามลำดับ 1,2,3,4,5
            pre.setString(1, txtRange.getText());
            pre.setString(2, txtCost.getText());
            pre.setString(3, txtSale.getText());
            pre.setString(4, cbUnitCost.getSelectedItem().toString());
            pre.setString(5, txtRange.getText());

            //ถ้ามันทำการอัพเดทเเล้วใหเเสดงข้ออมูลเเล้วทำการดึงข้อมูลที่อยู่ในDBมาเเสดงทันที
            if (pre.executeUpdate() != -1) {
                MessageBox.show(this, "อัพเดทข้อมูลแล้ว", "ผลการอัพเดทข้อมูล");
                bindData();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_cmdUpdateActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // actionนี้จะทำงานเมื่อมีการคลิกเลือกเเถวข้อมูลในตาราง 
        //ซึ่งถ้าคลิกข้อมูลที่อยู่ในเเถวในตารางจะไปเเสดงในtext
        int rowSelected = tableForcast.getSelectedRow();
        
        //เมื่อคลิ๊กเม้าส์ให้ดึงข้อมูลจากตารางไปใส่txt
        String Range = tableForcast.getValueAt(rowSelected, 0).toString();
        String Cost = tableForcast.getValueAt(rowSelected, 1).toString();
        String Sale = tableForcast.getValueAt(rowSelected, 2).toString();
        String UnitCost = tableForcast.getValueAt(rowSelected, 3).toString();

        //ให้ค่าในtextกลายเป็นข้อความที่คลิ๊ก
        txtRange.setText(Range);
        txtCost.setText(Cost);
        txtSale.setText(Sale);
        cbUnitCost.setSelectedItem(UnitCost);
    }//GEN-LAST:event_formMouseClicked

    private void mnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnExitActionPerformed
        // ออก
        System.exit(0);
    }//GEN-LAST:event_mnExitActionPerformed

    private void lbCostForecastPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lbCostForecastPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_lbCostForecastPropertyChange

    private void cmdForecastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdForecastActionPerformed
        //กำหนดตัวแปลเพื่อเอาไว้นับเเถวให้เริ่มต้นที่0
        int row = 0;
        int count = tableForcast.getRowCount();//นับเเถว
        //กำหนดตัวเเปรเพื่อเอาไว้คำนวนสมการการพยากรณ์
        Integer sumX = 0;
        Integer sumY = 0;
        Integer sumXY = 0;
        Integer sumXX = 0;
        Integer sumYY = 0;
        Integer a = 0;
        Integer b = 0;
        Integer xForecast = 0;
        Integer yForecast = 0; 
        //รับค่าเเถวที่1เเละ2ซึ่งก็คือ ค่าใช้จ่าย กับ ยอดขาย มาเก็บไว้ในตัวแปรเพื่อเข้าสู่สมการการวิเคราะห์เชิงถดถอย 
        for (int i = 0; i < count; i++) {
            Integer X = (Integer) tableForcast.getValueAt(row, 1);
            Integer Y = (Integer) tableForcast.getValueAt(row, 2);
            sumX = sumX + X;//ผลรวมของX
            sumY = sumY + Y;//ผลรวมของY
            sumXY = sumXY + (X * Y);
            //เรียกใช้คลาสMathซึ่งในความหมายของบรรทัดนี้คือ Xยกกำลัง2 
            //เเล้วเอาไปบวกกับตัวแปรsumXXเเล้วเอาไปเก็บไว้ในsumXXใหม่
            sumXX = sumXX + (X * X);
            sumYY = sumYY + (Y * Y);
            row++;
        }
        Integer averageX = 0;
        Integer averageY = 0;
        averageX = sumX / count;
        averageY = sumY / count;
        //สมการถดถอยเเทนด้วยสมการ a = averageY - (b*averageX)
        //ขั้นตอนเเรกให้หา bก่อน
        b = (sumXY -((sumX*sumY)/count))/(sumXX - ((sumX * sumX)/count));

        //เอาb ไปเเทนลงในสมการa = averageY - (b*averageX)
        a = averageY - (b * averageX);

        // จากสมการพยากรณ์ yForecast = a+(b*xForecast)
        xForecast = Integer.parseInt(txtForecast.getText());
        yForecast = (a + (b * xForecast));

        //DecimalFormat formatter = new DecimalFormat("#0.00");
        //Double formatter.format(yForecast);
        MessageBox.show(this,"ถ้าบริษัทจะเพิ่มค่าใช้จ่ายเป็น " + " " + " "+ xForecast 
                + " " + " " + tableForcast.getValueAt(0, 4)+ " " + " " + 
                "แนวโน้มของยอดขายมีค่าเท่ากับ" + " " + " " + yForecast + " " 
                + " " + tableForcast.getValueAt(0, 4), "ผลการพยากรณ์ยอดขาย");
        
        //เพิ่มเข้าในตาราง เป็นปีที่13
        String sql = "INSERT INTO forecast(Range,Cost,Sale,UnitCost) VALUES(?, ?, ?, ?)";
        Connection c = new MyConnect().getConnection();

        Integer CostForecast = tableForcast.getRowCount() + 1;

        try {
     PreparedStatement       pre = c.prepareStatement(sql);
            //เพิ่มข้อมูลในปีที่พยากรณืเข้าไปในตารางเพื่อเอาไว้ทำรีพอท
            pre.setInt(1, CostForecast);
            pre.setInt(2, xForecast);
            pre.setInt(3, yForecast);

            pre.setString(4, tableForcast.getValueAt(0, 4).toString());
            if (pre.executeUpdate() != -1) {
                bindData();
            }
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }//GEN-LAST:event_cmdForecastActionPerformed

    private void cmdDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteAllActionPerformed

        //คำสั่งลบข้อมูลจากตารางซึ่งเก็บไว้เป็นสตริงเเล้วเตรียมนำไปexecute
        String sql = "DELETE FROM forecast WHERE Range BETWEEN 0 AND 50";
        try {
            Connection c = new MyConnect().getConnection();//เชื่อมต่อกับDB

            //ใช้executeUpdateเพราะการเเก้ไข ปรับเปลี่ยน อะไรก็เเล้วเเต่ต้องใช้Update 
            //ยกเว้นเเต่ การดึงข้อมูลมาเเสดงหรือselect ต้องใช้executeQeurey
            c.createStatement().executeUpdate(sql);

            //เรียกใช้คลาส bindData()มาเเสดงเพื่อให้ตารางเเสดงข้อมูลทันทีเมื่อมีการลบเเล้ว
            //ถ้าหากไม่เรียกใช้มันก็จะไม่เห็นการเปลี่ยนเเปลงในทันที
            bindData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmdDeleteAllActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        try {
            //เมื่อมีการกรอกตัวเลขของช่วงเป็นเดือนในช่องค้นหา ถ้ามันมีเดือนหรือปีนั้นจริง 
            //จะเเสดงข้อมูลทุกอย่างในtextทุกช่อง
            String sql = "SELECT * FROM forecast WHERE Range = ?";
            Connection c = new MyConnect().getConnection();
            ResultSet rs = c.createStatement().executeQuery(sql);
          PreparedStatement     pre = c.prepareStatement(sql);
            pre.setString(1, txtSearch.getText());
            rs = pre.executeQuery();
            if (rs.next()) {
                String add1 = rs.getString("Range");
                txtRange.setText(add1);
                String add2 = rs.getString("Cost");
                txtCost.setText(add2);
                String add3 = rs.getString("Sale");
                txtSale.setText(add3);
                String add4 = rs.getString("UnitCost");
                cbUnitCost.setSelectedItem(add4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void mHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mHelpActionPerformed
        try {
            //เปิดไฟล์ Helpที่อยู่ในDesktop เเละต้องมี \\ 2ครั้ง จะมีครั้งเดียวไม่ได้
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " 
            + "Help.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_mHelpActionPerformed

    private void cmdPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrintActionPerformed
        //สร้างReport PrintจากตารางtableForcast
        MessageFormat Header = new MessageFormat("รายงาน");
        MessageFormat Footer = new MessageFormat("Page{0,number,integer}");
        try {
            tableForcast.print(JTable.PrintMode.NORMAL, Header, Footer);
        } catch (PrinterException e) {
            System.err.format("Cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_cmdPrintActionPerformed

    private void cmdHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHelpActionPerformed
        try {
            //เปิดไฟล์ Help
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " 
            + "Help.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmdHelpActionPerformed

    private void cmdChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdChartActionPerformed
        //กราฟ
        DefaultCategoryDataset Dataset = new DefaultCategoryDataset();
        int row = 0;
        for (int i = 0; i < tableForcast.getRowCount(); i++) {
            String Range = tableForcast.getValueAt(row, 0).toString();
            String Sale = tableForcast.getValueAt(row, 2).toString();
            String Cost = tableForcast.getValueAt(row, 1).toString();
            Integer S = new Integer(Sale);
            Integer C = new Integer(Cost);
            Integer Result = S - C;
            if (i < tableForcast.getRowCount() - 1) {
                Dataset.setValue(Result, "กำไร(ต่อเดือน/ปี)", "เดือน/ปีที่" + " " + 
                Range);
                row++;
            } else if (i == tableForcast.getRowCount() - 1) {
                Dataset.setValue(Result, "กำไร(ต่อเดือน/ปี)", "เดือน/ปี พยากรณ์");
            }
        }
        JFreeChart chart = ChartFactory.createBarChart3D("กราฟแสดงกำไร", 
        "เดือน/ปี", "กำไร", Dataset, PlotOrientation.VERTICAL, true, true, false);
        
        //3d
        chart.setBackgroundPaint(Color.LIGHT_GRAY);
        chart.getTitle().setPaint(Color.BLUE);
        CategoryPlot P = chart.getCategoryPlot();
        P.setRangeGridlinePaint(Color.black);
        ChartFrame frame = new ChartFrame("กราฟแสดงจำนวนยอดขาย", chart);
        frame.setVisible(true);
        frame.setSize(750, 650);

        //save file นามสกุล .png ให้ออโต้
        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new 
            StandardEntityCollection());
            final File file = new File("Chart.png");
            ChartUtilities.saveChartAsPNG(file, chart, 600, 400, info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmdChartActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // เหมือนกันกับโค๊ดที่เคยอธิบายไว้เเต่ตัวนี้จะเอามาใช้ในmenubarด้านบน
        String sql = "DELETE FROM forecast WHERE Range BETWEEN 0 AND 50";
        try {
            Connection c = new MyConnect().getConnection();//เชื่อมต่อกับDB
            c.createStatement().executeUpdate(sql);
            bindData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       
        //กราฟ เหมือนกันกับด้านบนที่เคยอธิบายไว้
        DefaultCategoryDataset Dataset = new DefaultCategoryDataset();
        int row = 0;
        for (int i = 0; i < tableForcast.getRowCount(); i++) {
            String Range = tableForcast.getValueAt(row, 0).toString();
            String Sale = tableForcast.getValueAt(row, 2).toString();
            String Cost = tableForcast.getValueAt(row, 1).toString();
            Integer S = new Integer(Sale);
            Integer C = new Integer(Cost);
            Integer Result = S - C;
            if (i < tableForcast.getRowCount() - 1) {
                Dataset.setValue(Result, "กำไร(ต่อเดือน/ปี)", "เดือน/ปีที่" + " " + 
                Range);
                row++;
            } else if (i == tableForcast.getRowCount() - 1) {
                Dataset.setValue(Result, "กำไร(ต่อเดือน/ปี)", "เดือน/ปี พยากรณ์");
            }
        }

        JFreeChart chart = ChartFactory.createBarChart("กราฟแสดงกำไร", 
        "เดือน/ปี", "กำไร",Dataset,PlotOrientation.VERTICAL, true, true, false);
        
        ///3d
        chart.setBackgroundPaint(Color.GREEN);
        chart.getTitle().setPaint(Color.BLUE);

        CategoryPlot P = chart.getCategoryPlot();
        P.setRangeGridlinePaint(Color.PINK);
        ChartFrame frame = new ChartFrame("กราฟแสดงจำนวนยอดขาย", chart);
        //เซตค่าหน้าจอให้แสดงผลจริง
        frame.setVisible(true);
        frame.setSize(450, 500);

        //save file นามสกุล .png 
        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(
            new StandardEntityCollection());
            final File file = new File("Chart.png");
            ChartUtilities.saveChartAsPNG(file, chart, 600, 400, info);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       
        //รายงาน เหมือนกันกับด้านบนที่เคยอธิบายไว้เเต่นำมาใช้ในเมนูบาร์ซ้ำเสยๆ
        MessageFormat Header = new MessageFormat("รายงาน");
        MessageFormat Footer = new MessageFormat("Page{0,number,integer}");
        try {
            tableForcast.print(JTable.PrintMode.NORMAL, Header, Footer);
        } catch (PrinterException e) {
            System.err.format("Cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostActionPerformed
        
    }//GEN-LAST:event_txtCostActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
       
        MessageBox.show(this, "นายวิรวุฒิ คำพันธ์", "เกี่ยวกับเรา");

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

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
            java.util.logging.Logger.getLogger(sales_forecasting_system.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sales_forecasting_system.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sales_forecasting_system.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sales_forecasting_system.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sales_forecasting_system().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbUnitCost;
    private javax.swing.JButton cmdChart;
    private javax.swing.JButton cmdClear;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdDeleteAll;
    private javax.swing.JButton cmdForecast;
    private javax.swing.JButton cmdHelp;
    private javax.swing.JButton cmdInsert;
    private javax.swing.JButton cmdPrint;
    private javax.swing.JButton cmdUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCostForecast;
    private javax.swing.JMenuItem mHelp;
    private javax.swing.JMenuItem mnExit;
    private javax.swing.JMenu mnHelp;
    private javax.swing.JTable tableForcast;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextField txtForecast;
    private javax.swing.JTextField txtRange;
    private javax.swing.JTextField txtSale;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
