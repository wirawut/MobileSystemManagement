<%-- 
    Document   : manage_data_major
    Created on : 18 เม.ย. 2556, 15:02:27
    Author     : Admin
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(function(){
        $("#get_option_company").load("controller.manage_data_company?get_option_company");
        
        //bindData();
        bind_data();
        $("#cmd_save").click(function(){
            $.ajax({
                url:"controller.manage_data_major?save",
                type:"post",
                data: $("#manage_data_major").serialize(),
                success:function(data){
                    if(data == "complete"){
                        alert("บันทึกข้อมูลแล้ว");
                        //bind_data(major_name,company_id,address,tel,email,fax);
                    }
                    else if(data == "no_complete"){
                        alert("กรุณากรอกข้อมูลให้ครบ");
                    }else if(data=="java.sql.SQLException: Incorrect integer value: '' for column 'company_id' at row 1"){
                         alert("กรุณาเลือกบริษัท/ห้างร้าน");
                    }
                    else{
                        alert("ไม่สามารถบันทึกข้อมูลได้");
                    }
                }
            });
            return false;
        });
        
    });
    function clear_data(){
        $("#major_name").val("");
        $("#get_option_company").val("");
        $("#address").val("");
        $("#tel").val("");
        $("#email").val("");
        $("#fax").val("");
    }
    function bind_data(major_name,company_id,address,tel,email,fax){ 
        $("#major_name").val(major_name);
        $("#get_option_company").val(company_id);
        $("#address").val(address);
        $("#tel").val(tel);
        $("#email").val(email);
        $("#fax").val(fax);
        /*$.getJSON("controller.manage_data_major?get_data" ,null, function(data){
            $("#major_name").val(data.major_name);
           
        });
        */
    }
    
</script>
<%
    Connection c = (Connection) getServletContext().getAttribute("conn");
    String sql = "SELECT * FROM tb_manage_data_major";
    ResultSet rs = c.createStatement().executeQuery(sql);
    if(rs.next()){
    String major_name = rs.getString("major_name");
    String company_id = rs.getString("company_id");
    String address = rs.getString("major_address");
    String tel = rs.getString("tel");
    String email = rs.getString("email");
    String fax = rs.getString("fax");
    out.println("<script>");
    out.println("bind_data('"+major_name+"','"+company_id+"','"+address+"','"+tel+"','"+email+"','"+fax+"')");
    out.println("</script>");
    }

%>

<form id="manage_data_major">
    <legend>จัดการข้อมูลสาขา</legend>
    <table>
        <tr>
            <td>สาขา</td>
            <td><input type="text" name="major_name" id="major_name" class="input-large" style="margin: 3px ;  height: 38px" ></td>
        </tr>
        <tr>
            <td>บริษัท/ห้างร้าน</td>
            <td><div id="get_option_company" class="input-large" ></div></td>
        </tr>
        <tr>
            <td>ที่อยู่/ที่ตั้งร้าน</td>
            <td><textarea class="field span12" name ="address" id="address" cols ="14" rows="3" style="resize:none ; margin: 3px ; width: 300px"></textarea></td>
        </tr>
        <tr>
            <td>เบอร์โทร</td>
            <td><input type="text" name="tel" id="tel" class="input-medium" style="margin: 3px ;  height: 38px" ></td>
        </tr>
        <tr>
            <td>อีเมล์</td>
            <td><input type="text" name="email" id="email" class="input-large" style="margin: 3px ;  height: 38px" ></td>
        </tr>
        <tr>
            <td>แฟกซ์</td>
            <td><input type="text" name="fax" id="fax" class="input-medium" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
           <td colspan="8"><input type="button" id="cmd_save" class="btn btn-medium btn-info" value="บันทึกข้อมูล"  style ="margin-left: 91px; margin-right: 3px ;margin-top: 3px ; margin-bottom: 3px ;padding: 5px;width: 100px;height: 45px "></td>
        </tr>
    </table>
</form>

