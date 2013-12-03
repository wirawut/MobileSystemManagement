<%-- 
    Document   : manage_data_company
    Created on : 18 เม.ย. 2556, 14:59:27
    Author     : Admin
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script type="text/javascript">
  
    $(function(){
      
        bind_data();
        $("#cmd_save").click(function(){
            $.ajax({
                url:"controller.manage_data_company?save",
                type:"post",
                data: $("#manage_data_company").serialize(),
                success:function(data){
                    if(data == "complete"){
                        alert("บันทึกข้อมูลแล้ว");
                    }
                    else if(data == "no_complete"){
                        alert("คุณจำเป็นต้องกรอกชื่อบริษัท/ห้างร้าน");
                 }
                    else{
                      alert("ไม่สามารถบันทึกข้อมูลได้");
                    }
                }
            });
            
        });
    });
    function clear_data(){
        $("#company_name").val("");
        $("#address").val("");
        $("#tel").val("");
        $("#fax").val("");
        $("#tax_number").val("");
    }
    function bind_data(company_name,address,tel,fax,tax_number){   
        $("#company_name").val(company_name);
        $("#address").val(address);
        $("#tel").val(tel);
        $("#fax").val(fax);
        $("#tax_number").val(tax_number);
        //$.getJSON("controller.manage_data_company?get_data" ,null, function(data){
        //$("#company_name").val(data.company_name);
        //$("#address").val(data.address);
        //$("#tel").val(data.tel);
        //$("#fax").val(data.fax);
        // $("#tax_number").val(data.tax_number);
        // });
    }
    
</script>
<%
    Connection c = (Connection) getServletContext().getAttribute("conn");
    String sql = "SELECT * FROM tb_manage_data_company";
    ResultSet rs = c.createStatement().executeQuery(sql);
    if(rs.next()){
    String company_name = rs.getString("company_name");
    String address = rs.getString("company_address");
    String tel = rs.getString("tel");
    String fax = rs.getString("fax");
    String tax_number = rs.getString("tax_number");
    out.println("<script>");
    out.println("bind_data('"+company_name+"','"+address+"','"+tel+"','"+fax+"','"+tax_number+"')");
    out.println("</script>");
    }

%>


<form id="manage_data_company">
    <legend>ข้อมูลบริษัท/ห้างร้าน</legend>
    <table>
        <tr>
            <td>ชื่อบริษัท/ห้างร้าน</td>
            <td><input type="text" name="company_name" id="company_name" class="input-large" style="margin: 3px ;  height: 38px" ></td>
        </tr>
        <tr>
            <td>ที่อยู่/ที่ตั้งร้าน</td>
            <td><textarea class="field span12" name ="address" id="address" cols ="14" rows="3" style="resize:none ; margin: 3px ; width: 300px"></textarea></td>
        </tr>
        <tr>
            <td>เบอร์โทร</td>
            <td><input type="text" name="tel" id="tel" class="input-medium" style="margin: 3px ; height: 38px" ></td>
        </tr>
        <tr>
            <td>แฟกซ์</td>
            <td><input type="text" name="fax" id="fax" class="input-medium" style="margin: 3px ;  height: 38px" ></td>
        </tr>
        <tr>
            <td>เลขประจำตัวผู้เสียภาษี</td>
            <td><input type="text" name="tax_number" id="tax_number" class="input-large" style="margin: 3px ;  height: 38px"></td>
        </tr>

        <tr>
           <td colspan="8"><input type="button" id="cmd_save" class="btn btn-medium btn-info" value="บันทึกข้อมูล"  style ="margin-left: 136px; margin-right: 3px ;margin-top: 3px ; margin-bottom: 3px ;padding: 5px;width: 100px;height: 45px "></td>
        </tr>
    </table>
</form>

