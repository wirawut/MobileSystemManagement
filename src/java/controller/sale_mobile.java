/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mysql.jdbc.PreparedStatement;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.sale_mobile_model;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class sale_mobile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("save") != null) {
                save(request, response);
            } else if (request.getParameter("show_bill") != null) {
                show_bill(request, response);
            } else if (request.getParameter("search") != null) {
                search(request, response);
            } else if (request.getParameter("save_memory") != null) {
                save_memory(request, response);
            } else if (request.getParameter("delete_memory") != null) {
                delete_memory(request, response);
            } else if (request.getParameter("delete_all_memory") != null) {
                delete_all_memory(request, response);
            } else if (request.getParameter("delete_quantity") != null) {
                delete_quantity(request, response);
            } else if (request.getParameter("show_print_bill") != null) {
                show_print_bill(request, response);
            } else if (request.getParameter("print_bill") != null) {
                print_bill(request, response);
            }
        } catch (Exception e) {
            out.print(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String tax_rate = request.getParameter("tax_rate");
            String sale_id = request.getParameter("sale_id");
            String customer_id = request.getParameter("customer_id");
            String user_id = request.getParameter("user_id");
            String no_invoice = request.getParameter("no_invoice");
            //sale_mobile_model arr = new sale_mobile_model();
            ArrayList<sale_mobile_model> arr_sale_mobile = (ArrayList) request.getSession().getAttribute("arr_sale_mobile");

            if ((sale_id != "") && (customer_id != "") && (user_id != "") && (tax_rate != "") && (no_invoice != "")) {
                Connection c2 = (Connection) getServletContext().getAttribute("conn");
                Statement stmt = c2.createStatement();
                String sql2 = "INSERT INTO tb_sale_detail(sale_id,mobile_id,mobile_type,mobile,quantity,price_sale,price_buy,price_sum,price_sum_cost,tax_rate,date_sale) VALUES";
                //เอาข้อมูลจากอาเรย์ลิส ของรายการที่เลือกมา มาวนลูปเเล้วเก็บไว้เป็นสตริงเพื่อประกอบกันเป็นคำสั่งเดียวลงดาต้าเบส

                for (sale_mobile_model sale_mobile : arr_sale_mobile) {
                    sql2 = sql2 + "('" + sale_id + "','" + sale_mobile.getMobile_id() + "','" + sale_mobile.getMobile_type() + "','" + sale_mobile.getMobile() + "','" + sale_mobile.getNumber() + "','" + sale_mobile.getPrice_sale() + "','" + sale_mobile.getPrice_buy() + "','" + sale_mobile.getSum() + "','" + sale_mobile.getTotal_cost() + "','" + tax_rate + "',NOW()),";

                }


                //ผลลัพทธ์ที่ได้จากตัวเเปร sql2 จะเป็นดังนี้ Insert int tb () value(x,x,x,xx,,), ซึ่งเกินมาเราต้องตัดทิ้ง
                int count = sql2.length();
                //ตัดเอกาตำเเหน่งที่ต้องการ เพื่อนำไปใช้บันทึกลงดาต้าเบส
                String sql = sql2.substring(0, count - 1);
                stmt.executeUpdate(sql);


                if (customer_id.charAt(1) == 'G') {
                    Connection c3 = (Connection) getServletContext().getAttribute("conn");
                    String sql3 = "INSERT INTO tb_manage_data_customer(customer_id,customer_fname,customer_lname,sex,age,birthdate,email,tel,card_number,address,date_save) VALUES('" + customer_id + "','ลูกค้าทั่วไป','','','0','','','','','',NOW())";
                    Statement stmt3 = c3.createStatement();
                    stmt3.executeUpdate(sql3);
                }

                //บันทึกข้อมูลลงในtb_sale_product
                Connection c = (Connection) getServletContext().getAttribute("conn");
                String sql1 = "INSERT INTO tb_sale_product(sale_id,customer_id,user_id,no_invoice) VALUES(?,?,?,?)";
                PreparedStatement pre1 = (PreparedStatement) c.prepareStatement(sql1);
                pre1.setString(1, sale_id);
                pre1.setString(2, customer_id);
                pre1.setString(3, user_id);
                pre1.setString(4, no_invoice);
                pre1.executeUpdate();//tb_sale_product
                response.getWriter().print("complete");
            } else {
                response.getWriter().print("no complete");
            }

        } catch (java.lang.NullPointerException n) {
            response.getWriter().append("null");
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void show_bill(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        try {
            String sale_id = request.getParameter("sale_id");
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String reportFileName = "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\report\\bill.jrxml";
            Map parameters = new HashMap();

            JasperDesign jd = JRXmlLoader.load(reportFileName);
            String sql = "SELECT * , ROUND((SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.sale_id = '" + sale_id + "'),2) AS price_total, "
                    + "ROUND(((SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.sale_id = '" + sale_id + "') + ( (SELECT SUM( price_sum )FROM tb_sale_detail WHERE tb_sale_detail.sale_id = '" + sale_id + "') * (SELECT tb_sale_detail.tax_rate /100 ))),2) AS price_end, "
                    + "ROUND(((SELECT SUM( price_sum ) FROM tb_sale_detail WHERE tb_sale_detail.sale_id = '" + sale_id + "') * (SELECT tb_sale_detail.tax_rate /100 )),2) AS price_tax "
                    + "FROM ((((tb_sale_product JOIN tb_sale_detail ON tb_sale_product.sale_id = tb_sale_detail.sale_id)JOIN tb_manage_data_user ON tb_sale_product.user_id = tb_manage_data_user.user_id) "
                    + "JOIN tb_manage_data_company ON tb_manage_data_user.company_id = tb_manage_data_company.company_id) "
                    + "JOIN tb_manage_data_major ON tb_manage_data_user.major_id = tb_manage_data_major.major_id) "
                    + "WHERE tb_sale_product.sale_id = '" + sale_id + "' ";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);


            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Hippo-G8\\Desktop\\cloud\\web\\show_report\\bill.pdf");
            //response.getWriter().print("complete");

            //JasperViewer.viewReport(jasperPrint);
           //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:/Users/Hippo-G8/Desktop/ใบเสร็จ.pdf");
          
        } catch (JRException e) {
            response.getWriter().print(e);
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        String search_word = request.getParameter("search_word");
        String price_start = request.getParameter("price_start");
        String price_end = request.getParameter("price_end");
        try {
            String sql = "";
            Connection c = (Connection) getServletContext().getAttribute("conn");
            if ((search_word == "") && ((price_start == "") && (price_end == ""))) {
                StringBuffer str = new StringBuffer();
                str.append("<table class='table table-hover'>");
                str.append("<thead>");
                str.append("<tr>");
                str.append("<th style='background-color : #98FB98'>" + "รายการ" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "รหัสสินค้า" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "ยี่ห้อ" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "รุ่น" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "รูปภาพ" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "ราคาต่อหน่วย" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "จำนวน" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "คงเหลือ" + "</th>");
                str.append("</tr>");
                str.append("</thead>");
                str.append("<tbody>");
                str.append("<tr style='background-color : #E0FFFF'>");
                str.append("<td colspan='8'><center>ไม่มีข้อมูล</center></td>");
                str.append("</tr>");
                str.append("</tbody>");
                str.append("</table>");

                response.getWriter().print(str);
            } else if ((search_word != "") || (price_start != "") || (price_end != "")) {
                if ((search_word != "") && ((price_start == "") && (price_end == ""))) {
                    sql = "SELECT * "
                            + "FROM ( "
                            + "tb_manage_data_product_mobile "
                            + "JOIN tb_manage_data_product_type_mobile "
                            + "ON tb_manage_data_product_mobile.mobile_type_id = tb_manage_data_product_type_mobile.mobile_type_id "
                            + ") "
                            + "JOIN tb_manage_data_unit "
                            + "ON tb_manage_data_product_mobile.unit_id = tb_manage_data_unit.unit_id "
                            + "WHERE tb_manage_data_product_type_mobile.mobile_type = '" + search_word + "' "
                            + "OR tb_manage_data_product_type_mobile.mobile_type LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_mobile.mobile =  '" + search_word + "'"
                            + "OR tb_manage_data_product_mobile.mobile LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_mobile.mobile_id =  '" + search_word + "'"
                            + "OR tb_manage_data_product_mobile.mobile_id LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_mobile.price_sale =  '" + search_word + "'"
                            + "OR tb_manage_data_product_mobile.price_sale LIKE '" + search_word + "%' "
                            + "UNION ALL SELECT * "
                            + "FROM ( "
                            + "tb_manage_data_product_sim "
                            + "JOIN tb_manage_data_product_type_sim ON tb_manage_data_product_sim.sim_type_id = tb_manage_data_product_type_sim.sim_type_id "
                            + ") "
                            + "JOIN tb_manage_data_unit "
                            + "ON tb_manage_data_product_sim.unit_id = tb_manage_data_unit.unit_id "
                            + "WHERE tb_manage_data_product_type_sim.sim_type ='" + search_word + "' "
                            + "OR tb_manage_data_product_type_sim.sim_type LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_sim.sim =  '" + search_word + "'"
                            + "OR tb_manage_data_product_sim.sim LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_sim.sim_id =  '" + search_word + "'"
                            + "OR tb_manage_data_product_sim.sim_id LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_sim.price_sale =  '" + search_word + "'"
                            + "OR tb_manage_data_product_sim.price_sale LIKE '" + search_word + "%' "
                            + "UNION ALL SELECT * "
                            + "FROM ( "
                            + "tb_manage_data_product_other_tool "
                            + "JOIN tb_manage_data_product_type_other_tool "
                            + "ON tb_manage_data_product_other_tool.other_tool_type_id = tb_manage_data_product_type_other_tool.other_tool_type_id "
                            + ") "
                            + "JOIN tb_manage_data_unit "
                            + "ON tb_manage_data_product_other_tool.unit_id = tb_manage_data_unit.unit_id "
                            + "WHERE tb_manage_data_product_type_other_tool.other_tool_type = '" + search_word + "' "
                            + "OR tb_manage_data_product_type_other_tool.other_tool_type LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_other_tool.other_tool =  '" + search_word + "'"
                            + "OR tb_manage_data_product_other_tool.other_tool LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_other_tool.other_tool_id =  '" + search_word + "'"
                            + "OR tb_manage_data_product_other_tool.other_tool_id LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_other_tool.price_sale =  '" + search_word + "'"
                            + "OR tb_manage_data_product_other_tool.price_sale LIKE '" + search_word + "%' "
                            + "ORDER BY mobile_id ASC";
                    //
                } else if ((search_word == "") && (price_start != "") && (price_end != "")) {
                    sql = "SELECT * "
                            + "FROM ( "
                            + "tb_manage_data_product_mobile "
                            + "JOIN tb_manage_data_product_type_mobile "
                            + "ON tb_manage_data_product_mobile.mobile_type_id = tb_manage_data_product_type_mobile.mobile_type_id "
                            + ") "
                            + "JOIN tb_manage_data_unit "
                            + "ON tb_manage_data_product_mobile.unit_id = tb_manage_data_unit.unit_id "
                            + "WHERE  tb_manage_data_product_mobile.price_sale BETWEEN '" + price_start + "' AND '" + price_end + "'"
                            + "UNION ALL SELECT * "
                            + "FROM ( "
                            + "tb_manage_data_product_sim "
                            + "JOIN tb_manage_data_product_type_sim ON tb_manage_data_product_sim.sim_type_id = tb_manage_data_product_type_sim.sim_type_id "
                            + ") "
                            + "JOIN tb_manage_data_unit "
                            + "ON tb_manage_data_product_sim.unit_id = tb_manage_data_unit.unit_id "
                            + "WHERE  tb_manage_data_product_sim.price_sale BETWEEN '" + price_start + "' AND '" + price_end + "'"
                            + "UNION ALL SELECT * "
                            + "FROM ( "
                            + "tb_manage_data_product_other_tool "
                            + "JOIN tb_manage_data_product_type_other_tool "
                            + "ON tb_manage_data_product_other_tool.other_tool_type_id = tb_manage_data_product_type_other_tool.other_tool_type_id "
                            + ") "
                            + "JOIN tb_manage_data_unit "
                            + "ON tb_manage_data_product_other_tool.unit_id = tb_manage_data_unit.unit_id "
                            + "WHERE  tb_manage_data_product_other_tool.price_sale BETWEEN '" + price_start + "' AND '" + price_end + "'"
                            + "ORDER BY mobile_id ASC";
                } else if ((search_word != "") && ((price_start != "") && (price_end != ""))) {
                    sql = "SELECT * "
                            + "FROM ( "
                            + "tb_manage_data_product_mobile "
                            + "JOIN tb_manage_data_product_type_mobile "
                            + "ON tb_manage_data_product_mobile.mobile_type_id = tb_manage_data_product_type_mobile.mobile_type_id "
                            + ") "
                            + "JOIN tb_manage_data_unit "
                            + "ON tb_manage_data_product_mobile.unit_id = tb_manage_data_unit.unit_id "
                            + "WHERE (tb_manage_data_product_type_mobile.mobile_type = '" + search_word + "' "
                            + "OR tb_manage_data_product_type_mobile.mobile_type LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_mobile.mobile =  '" + search_word + "'"
                            + "OR tb_manage_data_product_mobile.mobile LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_mobile.mobile_id =  '" + search_word + "'"
                            + "OR tb_manage_data_product_mobile.mobile_id LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_mobile.price_sale =  '" + search_word + "'"
                            + "OR tb_manage_data_product_mobile.price_sale LIKE '" + search_word + "%' )"
                            + "AND tb_manage_data_product_mobile.price_sale BETWEEN '" + price_start + "' AND '" + price_end + "'"
                            + "UNION ALL SELECT * "
                            + "FROM ( "
                            + "tb_manage_data_product_sim "
                            + "JOIN tb_manage_data_product_type_sim ON tb_manage_data_product_sim.sim_type_id = tb_manage_data_product_type_sim.sim_type_id "
                            + ") "
                            + "JOIN tb_manage_data_unit "
                            + "ON tb_manage_data_product_sim.unit_id = tb_manage_data_unit.unit_id "
                            + "WHERE (tb_manage_data_product_type_sim.sim_type ='" + search_word + "' "
                            + "OR tb_manage_data_product_type_sim.sim_type LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_sim.sim =  '" + search_word + "'"
                            + "OR tb_manage_data_product_sim.sim LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_sim.sim_id =  '" + search_word + "'"
                            + "OR tb_manage_data_product_sim.sim_id LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_sim.price_sale =  '" + search_word + "'"
                            + "OR tb_manage_data_product_sim.price_sale LIKE '" + search_word + "%' )"
                            + "AND tb_manage_data_product_sim.price_sale BETWEEN '" + price_start + "' AND '" + price_end + "'"
                            + "UNION ALL SELECT * "
                            + "FROM ( "
                            + "tb_manage_data_product_other_tool "
                            + "JOIN tb_manage_data_product_type_other_tool "
                            + "ON tb_manage_data_product_other_tool.other_tool_type_id = tb_manage_data_product_type_other_tool.other_tool_type_id "
                            + ") "
                            + "JOIN tb_manage_data_unit "
                            + "ON tb_manage_data_product_other_tool.unit_id = tb_manage_data_unit.unit_id "
                            + "WHERE (tb_manage_data_product_type_other_tool.other_tool_type = '" + search_word + "' "
                            + "OR tb_manage_data_product_type_other_tool.other_tool_type LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_other_tool.other_tool =  '" + search_word + "'"
                            + "OR tb_manage_data_product_other_tool.other_tool LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_other_tool.other_tool_id =  '" + search_word + "'"
                            + "OR tb_manage_data_product_other_tool.other_tool_id LIKE '" + search_word + "%' "
                            + "OR tb_manage_data_product_other_tool.price_sale =  '" + search_word + "'"
                            + "OR tb_manage_data_product_other_tool.price_sale LIKE '" + search_word + "%' )"
                            + "AND tb_manage_data_product_other_tool.price_sale BETWEEN '" + price_start + "' AND '" + price_end + "'"
                            + "ORDER BY mobile_id ASC";
                }

                ResultSet rs = c.createStatement().executeQuery(sql);
                StringBuffer str = new StringBuffer();


                str.append("<table class='table table-hover'>");
                str.append("<thead>");
                str.append("<tr>");
                str.append("<th style='background-color : #98FB98'>" + "รายการ" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "รหัสสินค้า" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "ยี่ห้อ" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "รุ่น" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "รูปภาพ" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "ราคาต่อหน่วย" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "จำนวน" + "</th>");
                str.append("<th style='background-color : #98FB98'>" + "คงเหลือ" + "</th>");
                str.append("</tr>");
                str.append("</thead>");
                str.append("<tbody>");

                // if (rs.getRow() > 0) {
                while (rs.next()) {
                    String mobile_id_get = rs.getString("mobile_id");
                    if (mobile_id_get.length() == 3) {
                        String mobile_id = (mobile_id_get.charAt(2)) + "";
                        str.append("<tr style='background-color : #ffffff'>");
                        str.append("<td><input type = 'checkbox' id='select' name='select' value ='" + rs.getString("mobile_id") + "'></td>");
                        str.append("<td>" + rs.getString("mobile_id") + "</td>");
                        str.append("<td>" + rs.getString("mobile_type") + "</td>");
                        str.append("<td>" + rs.getString("mobile") + "</td>");
                        str.append("<td>" + "<img src='image/" + rs.getString("image") + "' style='width:50px ; height: 50px'>" + "</td>");
                        str.append("<td>" + rs.getString("price_sale") + "</td>");
                        str.append("<td><input type = 'text' class='input-mini' value='1' name='number" + mobile_id + "' style='background-color : LightBlue ; color : black'></td>");
                        str.append("<td>" + rs.getString("quantity") + "</td>");
                        str.append("</tr>");
                    } else if (mobile_id_get.length() > 3) {
                        int mobile_length = ((mobile_id_get.length()));
                        String mobile_id = mobile_id_get.substring(2, mobile_length);

                        str.append("<tr style='background-color : #ffffff'>");
                        str.append("<td><input type = 'checkbox' id='select' name='select' value ='" + rs.getString("tb_manage_data_product_mobile.mobile_id") + "'></td>");
                        str.append("<td>" + rs.getString("mobile_id") + "</td>");
                        str.append("<td>" + rs.getString("mobile_type") + "</td>");
                        str.append("<td>" + rs.getString("mobile") + "</td>");
                        str.append("<td>" + "<img src='image/" + rs.getString("image") + "' style='width:50px ; height: 50px'>" + "</td>");
                        str.append("<td>" + rs.getString("price_sale") + "</td>");
                        str.append("<td><input type = 'text' class='input-mini' value='1' name='number" + mobile_id + "' style='background-color : LightBlue ; color : black'></td>");
                        str.append("<td>" + rs.getString("quantity") + "</td>");
                        str.append("</tr>");
                    }
                }

                /*} else {
                 str.append("<tr style='background-color : #E0FFFF'>");
                 str.append("<td colspan='8'><center>ไม่มีข้อมูล</center></td>");
                 str.append("</tr>");
                 } */
                str.append("</tbody>");
                str.append("</table>");

                response.getWriter().print(str);
            }

        } catch (java.sql.SQLException ex) {

            StringBuffer str = new StringBuffer();
            str.append("<table class='table table-hover'>");
            str.append("<thead>");
            str.append("<tr>");
            str.append("<th style='background-color : #98FB98'>" + "รายการ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รหัสสินค้า" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ยี่ห้อ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รุ่น" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "รูปภาพ" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "ราคาต่อหน่วย" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "จำนวน" + "</th>");
            str.append("<th style='background-color : #98FB98'>" + "คงเหลือ" + "</th>");
            str.append("</tr>");
            str.append("</thead>");
            str.append("<tbody>");
            str.append("<tr style='background-color : #E0FFFF'>");
            str.append("<td colspan='8'><center>ไม่มีข้อมูล</center></td>");
            str.append("</tr>");
            str.append("</tbody>");
            str.append("</table>");

            response.getWriter().print(str);

        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void save_memory(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");

            //เอาค่าจากvalue ที่ส่มาจากselect มาเก็บไว้ในอาเรย์
            String[] select = request.getParameterValues("select");
            int sum = 0; //ราคารวม
            int result = 0;
            int cost = 0;
            int sum_cost = 0;
            String sql;
            ArrayList arr_sale_mobile = null;
            //ตรวจสอบsessionว่ามีข้อมูลไม๊ ถ้าไม่มีให้สร้างใหม่ ถ้ามีเเล้วไม่ต้องสร้าง new
            if (request.getSession().getAttribute("arr_sale_mobile") != null) {
                arr_sale_mobile = (ArrayList) request.getSession().getAttribute("arr_sale_mobile");
            } else {
                arr_sale_mobile = new ArrayList();
            }

            for (String id_select : select) {//วนตั้งเเต่รอบเเรก PMที่เลือก เช่น ถ้าเลือกมาเป็น PM1 PM2 PM4 iอบเเรกจะเป็น PM1
//ข้อมูลที่ส่งมาเช่น PM1 PO1
                //mobile

                if (id_select.charAt(1) == 'M') { //เชคว่าตัวอักษรอักขระที่2 ของid เช่น PM1 PS1 PO1 ว่าเท่ากับตัวM ไม๊ ถ้าใช่เเสดงว่าเป็นโทรศัพท์ เลยให้ทำงานเกี่ยวกับdbของโทรสับเลย อิอิ
                    sql = "SELECT * FROM tb_manage_data_product_mobile "
                            + "JOIN tb_manage_data_product_type_mobile "
                            + "ON tb_manage_data_product_mobile.mobile_type_id = tb_manage_data_product_type_mobile.mobile_type_id "
                            + "WHERE tb_manage_data_product_mobile.mobile_id = '" + id_select + "' ";

                    ResultSet rs = c.createStatement().executeQuery(sql);

                    while (rs.next()) {
                        if (id_select.length() == 3) { //ถ้าตัวที่PM1 มีขนาดตัวอักษรเท่ากับ 3ให้ทำ
                            String id = (id_select.charAt(2)) + "";//เอาตัวเลขท้ายมาหลังจากตัวที่2
                            String number = request.getParameter("number" + id);//numberx
                            //1หาผลรวม
                            result = rs.getInt("price_sale") * Integer.parseInt(number);//ราคาขายรวม
                            cost = rs.getInt("price_buy") * Integer.parseInt(number);//ราคาต้นทุนรวม
                            sum = sum + result;//1
                            sum_cost = sum_cost + cost;

                            model.sale_mobile_model sm = new model.sale_mobile_model();


                            sm.setMobile_id(rs.getString("mobile_id"));
                            sm.setMobile_type(rs.getString("mobile_type"));
                            sm.setMobile(rs.getString("mobile"));
                            sm.setPrice_sale(rs.getInt("price_sale"));
                            sm.setPrice_buy(rs.getInt("price_buy"));
                            sm.setQuantity(rs.getInt("quantity"));
                            sm.setNumber(Integer.parseInt(number));
                            sm.setSum(result);
                            sm.setTotal(sum);
                            sm.setTotal_cost(cost);
                            arr_sale_mobile.add(sm);

                            //sum.add(result);
                        } else if (id_select.length() > 3) { //ถ้าตัวที่PM1 มีขนาดตัวอักษรมากว่า 3ให้ทำ
                            int id_length = ((id_select.length()));
                            String id = id_select.substring(2, id_length);//เอาตัวเลขท้ายมาหลังจากตัวที่2
                            String number = request.getParameter("number" + id);//รับค่าจำนวนที่กรอกมา
                            //1หาผลรวม
                            result = rs.getInt("price_sale") * Integer.parseInt(number);//ราคาขายรวม
                            cost = rs.getInt("price_buy") * Integer.parseInt(number);//ราคาต้นทุนรวม
                            sum = sum + result;//รวมราคาขาย
                            sum_cost = sum_cost + cost; //รวมต้นทุน


                            //setค่าลงในจาวาbean ซึ่งอยู่ในpackage model ชื่อ sale_model
                            model.sale_mobile_model sm = new model.sale_mobile_model();
                            sm.setMobile_id(rs.getString("mobile_id"));
                            sm.setMobile_type(rs.getString("mobile_type"));
                            sm.setMobile(rs.getString("mobile"));
                            sm.setPrice_buy(rs.getInt("price_buy"));
                            sm.setPrice_sale(rs.getInt("price_sale"));
                            sm.setQuantity(rs.getInt("quantity"));
                            sm.setNumber(Integer.parseInt(number));
                            sm.setSum(result);
                            sm.setTotal(sum);
                            sm.setTotal_cost(cost);
                            arr_sale_mobile.add(sm);
                        }

                        //response.getWriter().print("id_select = " + id_select);
                        //response.getWriter().print("value of number " + id + " = " + number);

                    }

                    //sim
                } else if (id_select.charAt(1) == 'S') {
                    sql = "SELECT * FROM tb_manage_data_product_sim "
                            + "JOIN tb_manage_data_product_type_sim "
                            + "ON tb_manage_data_product_sim.sim_type_id = tb_manage_data_product_type_sim.sim_type_id "
                            + "WHERE tb_manage_data_product_sim.sim_id = '" + id_select + "' ";

                    ResultSet rs = c.createStatement().executeQuery(sql);

                    while (rs.next()) {
                        if (id_select.length() == 3) { //ถ้าตัวที่PM1 มีขนาดตัวอักษรเท่ากับ 3ให้ทำ
                            String id = (id_select.charAt(2)) + "";//เอาตัวเลขท้ายมาหลังจากตัวที่2
                            String number = request.getParameter("number" + id);//numberx
                            //1หาผลรวม
                            result = rs.getInt("price_sale") * Integer.parseInt(number);//ราคาขายรวม
                            cost = rs.getInt("price_buy") * Integer.parseInt(number);//ราคาต้นทุนรวม
                            sum = sum + result;//รวมราคาขาย
                            sum_cost = sum_cost + cost; //รวมต้นทุน


                            model.sale_mobile_model sm = new model.sale_mobile_model();
                            sm.setMobile_id(rs.getString("sim_id"));
                            sm.setMobile_type(rs.getString("sim_type"));
                            sm.setMobile(rs.getString("sim"));
                            sm.setPrice_buy(rs.getInt("price_buy"));
                            sm.setPrice_sale(rs.getInt("price_sale"));
                            sm.setQuantity(rs.getInt("quantity"));
                            sm.setNumber(Integer.parseInt(number));
                            sm.setSum(result);
                            sm.setTotal(sum);
                            sm.setTotal_cost(cost);
                            arr_sale_mobile.add(sm);

                            //sum.add(result);
                        } else if (id_select.length() > 3) { //ถ้าตัวที่PM1 มีขนาดตัวอักษรมากว่า 3ให้ทำ
                            int id_length = ((id_select.length()));
                            String id = id_select.substring(2, id_length);//เอาตัวเลขท้ายมาหลังจากตัวที่2
                            String number = request.getParameter("number" + id);//รับค่าจำนวนที่กรอกมา
                            //1หาผลรวม
                            result = rs.getInt("price_sale") * Integer.parseInt(number);//ราคาขายรวม
                            cost = rs.getInt("price_buy") * Integer.parseInt(number);//ราคาต้นทุนรวม
                            sum = sum + result;//รวมราคาขาย
                            sum_cost = sum_cost + cost; //รวมต้นทุน



                            //setค่าลงในจาวาbean ซึ่งอยู่ในpackage model ชื่อ sale_model
                            model.sale_mobile_model sm = new model.sale_mobile_model();
                            sm.setMobile_id(rs.getString("sim_id"));
                            sm.setMobile_type(rs.getString("sim_type"));
                            sm.setMobile(rs.getString("sim"));
                            sm.setPrice_buy(rs.getInt("price_buy"));
                            sm.setPrice_sale(rs.getInt("price_sale"));
                            sm.setQuantity(rs.getInt("quantity"));
                            sm.setNumber(Integer.parseInt(number));
                            sm.setSum(result);
                            sm.setTotal(sum);
                            sm.setTotal_cost(cost);
                            arr_sale_mobile.add(sm);


                        }

                        //response.getWriter().print("id_select = " + id_select);
                        //response.getWriter().print("value of number " + id + " = " + number);

                    }
//other_tool
                } else if (id_select.charAt(1) == 'O') {
                    sql = "SELECT * FROM tb_manage_data_product_other_tool "
                            + "JOIN tb_manage_data_product_type_other_tool "
                            + "ON tb_manage_data_product_other_tool.other_tool_type_id = tb_manage_data_product_type_other_tool.other_tool_type_id "
                            + "WHERE tb_manage_data_product_other_tool.other_tool_id = '" + id_select + "' ";

                    ResultSet rs = c.createStatement().executeQuery(sql);

                    while (rs.next()) {
                        if (id_select.length() == 3) { //ถ้าตัวที่PM1 มีขนาดตัวอักษรเท่ากับ 3ให้ทำ
                            String id = (id_select.charAt(2)) + "";//เอาตัวเลขท้ายมาหลังจากตัวที่2
                            String number = request.getParameter("number" + id);//numberx
                            //1หาผลรวม
                            result = rs.getInt("price_sale") * Integer.parseInt(number);//ราคาขายรวม
                            cost = rs.getInt("price_buy") * Integer.parseInt(number);//ราคาต้นทุนรวม
                            sum = sum + result;//รวมราคาขาย
                            sum_cost = sum_cost + cost; //รวมต้นทุน

                            model.sale_mobile_model sm = new model.sale_mobile_model();
                            sm.setMobile_id(rs.getString("other_tool_id"));
                            sm.setMobile_type(rs.getString("other_tool_type"));
                            sm.setMobile(rs.getString("other_tool"));
                            sm.setPrice_buy(rs.getInt("price_buy"));
                            sm.setPrice_sale(rs.getInt("price_sale"));
                            sm.setQuantity(rs.getInt("quantity"));
                            sm.setNumber(Integer.parseInt(number));
                            sm.setSum(result);
                            sm.setTotal(sum);
                            sm.setTotal_cost(cost);
                            arr_sale_mobile.add(sm);

                            //sum.add(result);
                        } else if (id_select.length() > 3) { //ถ้าตัวที่PM1 มีขนาดตัวอักษรมากว่า 3ให้ทำ
                            int id_length = ((id_select.length()));
                            String id = id_select.substring(2, id_length);//เอาตัวเลขท้ายมาหลังจากตัวที่2
                            String number = request.getParameter("number" + id);//รับค่าจำนวนที่กรอกมา
                            //1หาผลรวม
                            result = rs.getInt("price_sale") * Integer.parseInt(number);//ราคาขายรวม
                            cost = rs.getInt("price_buy") * Integer.parseInt(number);//ราคาต้นทุนรวม
                            sum = sum + result;//รวมราคาขาย
                            sum_cost = sum_cost + cost; //รวมต้นทุน



                            //setค่าลงในจาวาbean ซึ่งอยู่ในpackage model ชื่อ sale_model
                            model.sale_mobile_model sm = new model.sale_mobile_model();
                            sm.setMobile_id(rs.getString("other_tool_id"));
                            sm.setMobile_type(rs.getString("other_tool_type"));
                            sm.setMobile(rs.getString("other_tool"));
                            sm.setPrice_buy(rs.getInt("price_buy"));
                            sm.setPrice_sale(rs.getInt("other_tool_sale"));
                            sm.setQuantity(rs.getInt("quantity"));
                            sm.setNumber(Integer.parseInt(number));
                            sm.setSum(result);
                            sm.setTotal(sum);
                            sm.setTotal_cost(cost);
                            arr_sale_mobile.add(sm);


                        }

                        //response.getWriter().print("id_select = " + id_select);
                        //response.getWriter().print("value of number " + id + " = " + number);

                    }
//other_tool
                }
                //response.getWriter().print("id_select = " + id_select);
                //response.getWriter().print("value of number " + id + " = " + number);


            }

            //หลังจากทำเสรจ
            //model.sale_mobile_model total = new model.sale_mobile_model();
            request.getSession().setAttribute("arr_sale_mobile", arr_sale_mobile);

        } catch (java.lang.NullPointerException en) {
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void delete_memory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ArrayList arr = (ArrayList) request.getSession().getAttribute("arr_sale_mobile");
            int index = Integer.parseInt(request.getParameter("index")); //รับค่าลำดับมาโดยเริ่มจาก0
            //ลบเเถวที่เลือก
            arr.remove(index);

            //เซตค่าใหม่ให้เซสชั่น
            request.getSession().setAttribute("arr_sale_mobile", arr);
            response.getWriter().print("complete");
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void delete_all_memory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ArrayList arr = (ArrayList) request.getSession().getAttribute("arr_sale_mobile");
            //นับเเถวทั้งหมด
            // int count_row_all = arr.size();

            //ลบเเถวที่เลือก
            //for (int i = 0; i <= count_row_all; i++) { // 0<=3
            arr.removeAll(arr);
            // }

            //เซตค่าใหม่ให้เซสชั่น
            request.getSession().setAttribute("arr_sale_mobile", arr);
            response.getWriter().print("complete");
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void delete_quantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Connection c = (Connection) getServletContext().getAttribute("conn");
            String sql = "SELECT * FROM tb_sale_detail";
            ResultSet rs = c.createStatement().executeQuery(sql);
            Statement stmt = c.createStatement();
            /*while (rs.next()) {
             String mobile_id = rs.getString("mobile_id");//m[0]=PM1 m[1]=PM2 m[2] = PM3
             String[] arr_mobile_id = mobile_id.split(",");
                
                
             String quantity = rs.getString("quantity");//q[0]=1 q[1]=2 q[2] = 1
             String[] arr_quantity = quantity.split(",");
             */
            ArrayList<sale_mobile_model> arr_sale_mobile = (ArrayList) request.getSession().getAttribute("arr_sale_mobile");
            for (sale_mobile_model sale_mobile : arr_sale_mobile) {
                //String mobile_id  = sale_mobile.getMobile_id();


                //int count = arr_mobile_id.length; //2

                //0<=2ทำ3รอบ
                //for (int i = 0; i <= count; i++) {
                //เช็คว่าmobile_id เป็นประเภทไหนจะได้อัพเดทถูกtable
                if (sale_mobile.getMobile_id().charAt(1) == 'M') { //เป็นโทรศัพท์
                    String sql2 = "SELECT * FROM tb_manage_data_product_mobile WHERE mobile_id = '" + sale_mobile.getMobile_id() + "'";
                    ResultSet rs2 = c.createStatement().executeQuery(sql2);
                    while (rs2.next()) {

                        int quan = (rs2.getInt("quantity")) - (sale_mobile.getNumber());// quan = จำนวนในดาต้าเบส
                        String sql3 = "UPDATE tb_manage_data_product_mobile SET quantity = " + quan + "  WHERE mobile_id = '" + sale_mobile.getMobile_id() + "'";
                        stmt.executeUpdate(sql3);

                    }
                } else if (sale_mobile.getMobile_id().charAt(1) == 'S') { //เป็นโทรศัพท์
                    String sql2 = "SELECT * FROM tb_manage_data_product_sim WHERE sim_id = '" + sale_mobile.getMobile_id() + "'";
                    ResultSet rs2 = c.createStatement().executeQuery(sql2);
                    while (rs2.next()) {

                        int quan = rs2.getInt("quantity") - (sale_mobile.getNumber());// quan = จำนวนในดาต้าเบส
                        String sql3 = "UPDATE tb_manage_data_product_sim SET quantity = " + quan + "  WHERE sim_id = '" + sale_mobile.getMobile_id() + "'";
                        stmt.executeUpdate(sql3);

                    }
                } else if (sale_mobile.getMobile_id().charAt(1) == 'O') { //เป็นอุปกรณ์เสริม
                    String sql2 = "SELECT * FROM tb_manage_data_product_other_tool WHERE other_tool_id = '" + sale_mobile.getMobile_id() + "'";
                    ResultSet rs2 = c.createStatement().executeQuery(sql2);
                    while (rs2.next()) {

                        int quan = rs2.getInt("quantity") - (sale_mobile.getNumber());// quan = จำนวนในดาต้าเบส
                        String sql3 = "UPDATE tb_manage_data_product_other_tool SET quantity = " + quan + "  WHERE other_tool_id = '" + sale_mobile.getMobile_id() + "'";
                        stmt.executeUpdate(sql3);

                    }
                }
                //}
            }
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }

    private void print_bill(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:/Users/Hippo-G8/Desktop/ใบเสร็จ.pdf");
    }

    private void show_print_bill(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuffer str = new StringBuffer();
            str.append("<center>");
            str.append("<object data='show_report/bill.pdf' type='application/pdf' width='750' height='748'>");
            //str.append("<a href='report/ใบเสร็จ.pdf'>พิมพ์ใบเสร็จ</a>");
            str.append("</object>");
             str.append("</center>");
            response.getWriter().print(str);
        } catch (Exception e) {
            response.getWriter().print(e);
        }
    }
}