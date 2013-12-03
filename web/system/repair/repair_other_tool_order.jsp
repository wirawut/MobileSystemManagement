<%-- 
    Document   : repair_other_order
    Created on : 23 เม.ย. 2556, 0:14:13
    Author     : Admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(function(){
        //เรียกใช้งานฟังก์ชั่นbindManageDataCompany()
        $("#repair_other_tool_id").css("background-color","orange").css("color","black");
        $("#get_option_type_other_tool").load("controller.manage_data_product_type_other_tool?get_option");
        $("#get_option_other_tool").load("controller.manage_data_product_other_tool?get_option");
        $("#get_option_topic_repair_other_tool").load("controller.manage_data_topic_repair_other_tool?get_option");
        $("#get_option_status").load("controller.manage_data_status?get_option");
        //$("#date_get_product").datepicker();
      
        
        $("#cmd_save").click(function(){
            $.ajax({
                url:"controller.repair_other_tool_order?save",
                type:"post",
                data: $("#repair_other_tool_order").serialize(),
                success:function(data){
                    if(data == "complete"){
                        alert("บันทึกข้อมูลแล้ว");
                        clear_data();
                    }
                    else if(data == "no_complete"){
                        alert("กรุณาต้องกรอกข้อมูลให้ครบ");
                        clear_data();
                    }
                    else{
                        alert(data);
                    }
                }
            });
            return false;
        });
        
        $("#cmd_check").click(function(){
            $.ajax({
                url:"controller.repair_other_tool_order?check",
                type:"post",
                data: $("#repair_other_tool_order").serialize(),
                success:function(data){
                    if(data == "complete"){
                        alert("จากการตรวจสอบพบว่าสินค้านี้ซื้อจากทางร้านโดยคุณ"+ " " + "<%=session.getAttribute("fname")%>"+"  "+"<%=session.getAttribute("lname")%>"+"  "+"สินค้านี้หมดกำหนดประกันในวันที่"+"  "+"<%=session.getAttribute("date_insurance_end")%>" +"วันนี้วันที่"+" "+"<%=session.getAttribute("today")%>");
                        clear_data();
                    }
                    else if(data == "no_complete"){
                        alert("จากการตรวจสอบพบว่ารหัสสินค้านี้ไม่เคยซื้อสินค้าจากทางร้าน หรือลูกค้าให้รหัสอุปกรณ์เสริมที่เป็นข้อมูลเท็จ ดังนั้นไม่ว่าสินค้าจะมีอาการเป็นเช่นไรก็จะคิดค่าบริการตามความเหมาะสม");
                        clear_data();
                    }
                    else if(data == "data_null"){
                        alert("ท่านต้องกรอกข้อมูลรหัสอุปกรณ์เสริมเพื่อทำการตรวจสอบ");
                        clear_data();
                    }
                    else{
                        alert(data);
                    }
                }
            });
            return false;
        });
       
    });
    
    
    
</script>
<%
    Connection c = (Connection) getServletContext().getAttribute("conn");
    String sql = "SELECT count(repair_other_tool_id) AS count_row FROM tb_repair_other_tool_order";
    ResultSet rs = c.createStatement().executeQuery(sql);
    if (rs.next()) {
        Integer i = rs.getInt("count_row") + 1;
        session.setAttribute("i", i);
    } else {
        Integer i = 1;
        session.setAttribute("i", i);
    }
%>

<form id="repair_other_tool_order">
    <legend>แจ้งการสั่งซ่อม</legend>
    <table>
        <tr>
            <td >รหัสการซ่อม</td>
            <td ><input  type ="text" name="repair_other_tool_id" id="repair_other_tool_id" class="input-mini" style=" padding: 5px;width: 40px;font-size: 15px ;margin: 3px" value="CF<%=session.getAttribute("i")%>"></td>
        </tr>
        <tr>
            <td>รหัสการขาย</td>
            <td colspan="2"><input type="text" name="sale_other_tool_id" id="sale_other_tool_id" class="input-mini" style="margin: 3px"><input type="button" id="cmd_check" class="btn btn-primary btn-large" value="ตรวจสอบข้อมูล" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>ยี่ห้อ</td>
            <td id ="get_option_type_other_tool"></td>
        </tr>
        <tr>
            <td>รุ่น</td><td id="get_option_other_tool"></td>
        </tr>
        <tr>
            <td>อาการในประกัน</td>
            <td id="get_option_topic_repair_other_tool"></td>
        </tr>
        <tr>
            <td>อาการนอกประกัน</td>
            <td><textarea class="field span12" name ="detail" id="detail" cols ="10" rows="3" placeholder="ระบุกรณีอาการอยู่นอกประกัน" style="resize:none ;margin: 3px"></textarea></td>
        </tr>
        <tr>
            <td>ราคา</td>
            <td><input  type ="text" name="price" id="price" class="input-small" style=" padding: 5px;font-size: 15px ;margin: 3px"></td>
        </tr>
        <tr>
            <td>สถานะ</td>
            <td id="get_option_status"></td>
        </tr>
        <tr>
            <td>วันรับคืน</td>
            <td><input type ="text" name="date_get_product" id="date_get_product" class="input-small" style=" padding: 5px;font-size: 15px ;margin: 3px"></td>
        
        </tr>
        <tr>
            <td colspan="2"><input type="button" id="cmd_save" class="btn btn-success btn-large" value="บันทึกข้อมูล" style ="margin-left: 103px ; margin-bottom: 3px; margin-right: 3px ; margin-top: 3px"></td>
        </tr>         
    </table>
</form>