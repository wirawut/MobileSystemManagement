<%-- 
    Document   : sale
    Created on : 22 เม.ย. 2556, 15:28:07
    Author     : Admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script type="text/javascript" src="pdfobject.js"></script>
<script type="text/javascript">
    $(function(){
        //$("#date_sale").datepicker({ dateFormat: 'yy-mm-dd' }); 
        //เรียกใช้งานฟังก์ชั่นbindManageDataCompany()
        $("#show_data_mobile").load("controller.showData?show_data_mobile");
        $("#get_option_type_mobile").load("controller.manage_data_product_type_mobile?get_option");
        $("#get_option_mobile").load("controller.manage_data_product_mobile?get_option");
        bind_data_product_mobile();
        $("#show_data").slideDown("slow");
        $("#list_select").css("height","0px");

        //ดักจับการกรอกข้อมูล ค้นหาราคา 
        //ถ้ากรอกข้อมูลลงในtextbox ราคาต่ำสุด ให้ทำดังนี้
        $("#price_start").keyup(function(){
            var input =$(this).val();
            var inputN = Number(input);
            if(isNaN(inputN)){
                alert("กรอกตัวเลขเท่านั้น");
            }else if ((input.length) >= 7){
                alert("ราคาสูงเกินไป"); 
            }
           
        });




        //ถ้ากรอกข้อมูลลงในtextbox ราคาต่ำสุด ให้ทำดังนี้
        $("#price_end").keyup(function(){
            var input =$(this).val();
            input = Number(input);
            if(isNaN(input)){
                alert("กรอกตัวเลขเท่านั้น");
            }else if ((input.length) >= 7){
                alert("ราคาสูงเกินไป"); 
            }
        });

        //ซ่อน
        $("#hide").click(function(){
            //$("#contr").css("height","0px");
            //$("#show_data").slideUp("slow");
            $("#contr").css("height","0px");
        });
        
        //โชว
        $("#show_mobile").click(function(){
            $("#contr").css("height","373px");
            //hide_data();
            bind_data_product_mobile();
            //$("#show_data").slideDown("slow");
        });
        $("#show_sim").click(function(){
            $("#contr").css("height","373px");
            //hide_data();
            bind_data_product_sim();
            //$("#show_data_product_sim").slideDown("slow");
        });
        $("#show_other_tool").click(function(){
            $("#contr").css("height","373px");
            //hide_data();
            bind_data_product_other_tool();
            //$("#show_data").slideDown("slow");
        });
        $("#show_all").click(function(){
            $("#contr").css("height","373px");
            //hide_data();
            bind_data_product_all();
            //$("#show_data").slideDown("slow");
        });
        $("#search_by_price").click(function(){
            
            $.ajax({
                url:"controller.sale_mobile?search",
                type:"post",
                data: {
                    search_word : $("#search_word").val(),
                    price_start : $("#price_start").val(),
                    price_end : $("#price_end").val()
                },
                success:function(data){
                    $("#contr").css("height","373px");
                    //hide_data();
                    $("#show_data").html(data);
                    // $("#show_data").slideDown("slow");
                    //alert(data);
                }
            }); 
        });
        $("#cmdSave").click(function(){
            $.ajax({
                url:"controller.sale_mobile?save",
                type:"post",
                data: $("#sale_mobile").serialize(),
                success:function(data){
                    if(data == "complete"){
                        //โชวบิล หรือปริ้นใบเสร็จ
                        $.ajax({
                            type:"post",
                            data: $("#sale_mobile").serialize(),
                            url: "controller.sale_mobile?show_bill",
                            success:function(){
                                setTimeout(function() {$("#content").load('controller.sale_mobile?show_print_bill'); },5000);
                            }
                        });
                        //เเล้วส่งค่าเเบบอาเเจกเพื่อให้ไปยังservletเพื่อลบข้อมูลจากสต๊อก
                        $.ajax({
                            url:"controller.sale_mobile?delete_quantity",
                            type:"post",
                            data: $("#sale_mobile").serialize(),
                            success:function(){         
                                bind_data_product_mobile();
                            }
                        });
                        //เรียกใช้เมธอดลบข้อมูลรายการที่เลือกให้เป็นค่าว่าง
                        //alert("บันทึกการขายเเล้ว"); 
                      
                    }else if(data == "no complete"){
                        alert("กรุณากรอกข้อมูลให้ครบ");
                    }
                    else if (data == "com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '' at line 1"){
                        alert("กรุณาเพิ่มรายการสินค้า");
                    }
                    else{
                       alert(data);
                   
                    }
                    
                    //DeleteAll();
                }
            });
        });
        $("#cmdSaveMemory").click(function(){
            $("#list_select").css("height","248px");
            $.ajax({
                url:"controller.sale_mobile?save_memory",
                type:"post",
                data: $("#sale_mobile").serialize(),
                success:function(){
                    $("#list_select").load("system/sale/show_mobile.jsp"); 
                }
            }); 
        });
        function DeleteAll(){
            $("#list_select").css("height","248px");
            $.ajax({
                url:"controller.sale_mobile?delete_all_memory",
                type:"post",
                data: $("#sale_mobile").serialize(),
                success:function(){
                    $("#list_select").load("system/sale/show_mobile.jsp"); 
                }
            }); 
        
        }
        $("#cmdDeleteAllMemory").click(function(){
            $("#list_select").css("height","248px");
            $.ajax({
                url:"controller.sale_mobile?delete_all_memory",
                type:"post",
                data: $("#sale_mobile").serialize(),
                success:function(){
    
                    $("#list_select").load("system/sale/show_mobile.jsp"); 
                    
                }
            }); 
        });
        function bind_data_product_mobile(){
            $("#show_data").load("controller.manage_data_product_mobile?show_mobile");
        }
        function bind_data_product_sim(){
            $("#show_data").load("controller.manage_data_product_sim?show_sim");
        }
        function bind_data_product_other_tool(){
            $("#show_data").load("controller.manage_data_product_other_tool?show_other_tool");
        }
        function bind_data_product_all(){
            $("#show_data").load("controller.manage_data_product_all?show_data_product_all");
        }
        function bind_data_product_search(){
            $("#show_data").load("controller.sale_mobile?search");
        }
   
        /*function hide_data(){
        $("#show_data_product_mobile").slideUp("slow");
        $("#show_data_product_other_tool").slideUp("slow");
        $("#show_data_product_sim").slideUp("slow");
        $("#show_data_product_all").slideUp("slow");
        $("#show_data_product_search").slideUp("slow");

    }*/
       
    });
</script>
<%
    //เพิ่มค่าตัวเลข repair_id บวกเข้ากับสตริง
    Connection c = (Connection) getServletContext().getAttribute("conn");
    String sql = "SELECT count(sale_id) AS count_row FROM tb_sale_product";
    ResultSet rs = c.createStatement().executeQuery(sql);
    if (rs.next()) {
        int i = rs.getInt("count_row") + 1;
        session.setAttribute("i", i);
    } else {
        int i = 1;
        session.setAttribute("i", i);
    }
%>
<%
    Connection c2 = (Connection) getServletContext().getAttribute("conn");
    String sql2 = "SELECT count(customer_id) AS count_row FROM tb_sale_product WHERE customer_id LIKE 'CG%'";
    ResultSet rs2 = c2.createStatement().executeQuery(sql2);
    if (rs2.next()) {
        int j = rs2.getInt("count_row") + 1;
        session.setAttribute("j", j);
    } else {
        int j = 1;
        session.setAttribute("j", j);
    }
%>

<form id="sale_mobile">

    <legend>ขายโทรศัพท์ ซิมการ์ด และอุปกรณ์เสริม</legend>
    <input type="button" class="btn-info btn-medium" value="โทรศัพท์" id="show_mobile" style="margin: 3px;">&nbsp;<input type="button" class="btn-info btn-medium" value="ซิมการ์ด" id="show_sim" style="margin: 3px;">&nbsp;<input type="button" class="btn-info btn-medium" value="อุปกรณ์เสริม" id="show_other_tool" style="margin: 3px;"><!--<input type="button" class="btn-success btn-medium" value="ทั้งหมด" id="show_all">-->&nbsp;<!--<input type="button" class="btn-warning btn-medium" value="ซ่อน" id="hide">-->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="input-large"  id="search_word" name="search_word" placeholder="ระบุคำที่ต้องการค้นหา" style="margin: 3px ; width: 180px ; height: 38px">&nbsp;หรือ&nbsp; <input type="text" class="input-small"  id="price_start"   name="price_start" placeholder="ราคาต่ำสุด" style="margin: 3px ; width: 100px ; height: 38px">&nbsp;ถึง&nbsp; <input type="text" class="input-small"  id="price_end" name="price_end" placeholder="ราคาสูงสุด" style="margin: 3px ; width: 100px ; height: 38px">&nbsp;&nbsp;<input type="button" class="btn-info btn-medium" value="ค้นหา" name ="search_by_price" id="search_by_price" style="margin: 3px;">
    <div id="contr" style=" height: 373px;overflow: auto;">
        <div id="show_data">
        </div>
    </div>
    <div style=" margin-top: 5px;">
        <input type="button" id="cmdSaveMemory" class="btn-info btn-medium" value="เพิ่มรายการ">&nbsp;
        <input type="button" id="cmdDeleteAllMemory" class="btn-info btn-medium" value="ล้างรายการ">
    </div>
    <div id="list_select" style=" height: 373px;overflow: auto;">
    </div>


    <table>
        <tr>
            <td colspan="2">เลขที่ขาย</td>
            <td><input type="text" name="sale_id" id="sale_id" class="input-mini" style="padding: 5px;width: 60px;font-size: 15px ;margin: 3px ; background-color: #ffffff ; color: #000 ; width: 100px ; height: 38px"  value="SN<%=session.getAttribute("i")%>">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="2">รหัสลูกค้า</td>
            <td><input type="text" name="customer_id" id="customer_id" class="input-mini" style="padding: 5px;width: 60px;font-size: 15px ;margin: 3px ; background-color: #ffffff ; color: #000 ;width: 100px ; height: 38px"  value="CG<%=session.getAttribute("j")%>">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>อัตราภาษี (%)</td>
            <td><input type="text" name="tax_rate" id="tax_rate" class="input-mini" style="padding: 5px;width: 60px;font-size: 15px ;margin: 3px ; background-color: #ffffff ; color: #000; width: 100px ; height: 38px" value="7">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>รหัสผู้ขาย</td>
            <td><input type="text" name="user_id" id="user_id" class="input-mini" style="padding: 5px;width: 60px;font-size: 15px ;margin: 3px ; background-color: #ffffff ; color: #000; width: 100px ; height: 38px"  value="${user_id}">&nbsp;&nbsp;&nbsp;&nbsp;<td>
                <!--<td>วันที่ขาย</td>
                <td><input type="text" name="date_sale" id="date_sale" class="input-medium" style="margin: 3px" >&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
            <td colspan="2">เลขที่ใบกำกับภาษี</td>
            <td><input type="text" name="no_invoice" id="no_invoice" class="input-mini" style="padding: 5px;width: 60px;font-size: 15px ;margin: 3px ; background-color: #ffffff ; color: #000 ; width: 100px ; height: 38px"  value="IV<%=session.getAttribute("i")%>">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="8"><input type="button" id="cmdSave" class="btn btn-medium btn-info" value="บันทึกการขาย"  style ="margin: 3px ;padding: 5px;width: 100px;height: 45px "></td>
        </tr>
       <!-- <tr>
            <td><strong><font font style="color: #327E04 ; font-style: italic">หมายเหตุ : </font></strong></td><td colspan="12"><font font style="color: #327E04 ; font-style: italic">เลขที่ขาย (SNX) : S = Serial , N = Number , X = ลำดับ</font></td>
        </tr>
        <tr>
            <td></td><td colspan="12"><font font style="color: #327E04 ; font-style: italic">รหัสลูกค้า 2 ส่วน 1.รหัสลูกค้าทั่วไป(CGX) : C = Customer , G = General , X = ลำดับ 2.รหัสลูกค้าสมาชิก (CMX) : C = Customer , M = Memmer , X = ลำดับ</font></td>
        </tr> -->
    </table>
</form>
