<%-- 
    Document   : manage_data_url_sale
    Created on : 15 พ.ค. 2556, 14:30:22
    Author     : Admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
  
    $(function(){
      
        bind_data();
        $("#cmd_save").click(function(){
            $.ajax({
                url:"controller.manage_data_url_safe?save",
                type:"post",
                data: $("#manage_data_url_safe").serialize(),
                success:function(data){
                    if(data == "complete"){
                        alert("บันทึกข้อมูลแล้ว");
                        //clear_data();
                    }
                    else if(data == "no_complete"){
                        alert("คุณจำเป็นต้องกรอกชื่อบริษัท/ห้างร้าน");
                        //clear_data();
                    }
                    else{
                        alert(data);
                    }
                }
            });
            
        });
    });
    function clear_data(){
        //$("#url_safe").val("");
    }
    function bind_data(url_safe){   
        $("#url_safe").val(url_safe);
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
    String sql = "SELECT * FROM tb_manage_data_url_safe";
    ResultSet rs = c.createStatement().executeQuery(sql);
    if(rs.next()){
    String url_safe = rs.getString("url_safe");
    out.println("<script>");
    out.println("bind_data('"+url_safe+"')");
    out.println("</script>");
    }

%>


<form id="manage_data_url_safe">
    <legend>จัดการข้อมูลบริษัท/ห้างร้าน</legend>
    <table>
        <tr>
            <td>URL</td>
            <td><input type="text" name="url_safe" id="url_safe" class="input-large" style="margin: 3px"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="button" id="cmd_save" class="btn btn-success btn-large" value="บันทึกข้อมูล" style ="margin-left: 30px ; margin-bottom: 3px; margin-right: 3px ; margin-top: 3px"></td>
        </tr>
    </table>
</form>