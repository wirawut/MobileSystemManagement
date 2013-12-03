<%-- 
    Document   : data_user
    Created on : 8 พ.ค. 2556, 15:41:57
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    function resize_full(){
        $("#im").css("width","auto").css("height","auto");
        //$("#date_get_product").datepicker();
    }
    
</script>
<table >
    <tr align="center" >
        <td colspan="2">&nbsp;<img src='image/${image}' onclick="resize_full()" id ="im" class = "img-rounded" style="width : 100px ; height: 100px"></td>
    </tr>
    <tr><td>&nbsp;</td></tr>

    <tr>
        <td>ชื่อ-สกุล </td><td>:&nbsp; ${fname} ${lname}</td>
    </tr>
    <tr>
        <td>ระดับ</td><td>:&nbsp; ${level}</td>
    </tr>
    <tr>
        <td>อีเมล์</td><td>:&nbsp; ${email}</td>
    </tr>
    <tr>
        <td>เบอร์โทร </td><td>:&nbsp; ${tel}</td>
    </tr>
    <tr>
        <td>ชื่อผู้ใช้งาน </td><td>:&nbsp; ${username}</td>
    </tr>
    <tr>
        <td>รหัสผ่าน </td><td>:&nbsp; ${password}</td>
    </tr>


</table>
