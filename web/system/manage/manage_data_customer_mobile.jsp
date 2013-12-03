<%-- 
    Document   : manage_data_customer_mobile
    Created on : 21 เม.ย. 2556, 17:37:18
    Author     : Admin
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    function loadContent(url){
        $("#content").load(url);
    }
        
</script>
<form id="manage_data_major">
    <legend>ข้อมูลลูกค้า(โทรศัพท์)</legend>
    <table>
        <tr>
            <td>ชื่อ</td>
            <td><input type="text" name="major_name" id="major_name" class="input-large" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>สกุล</td>
            <td><input type="text" name="major_name" id="major_name" class="input-large" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>ที่อยู่</td>
            <td><textarea class="field span12" name ="address" id="address" cols ="14" rows="4" style="resize:none ; margin: 3px ; width: 300px"></textarea></td>
        </tr>
        <tr>
            <td>เบอร์โทร</td>
            <td><input type="text" name="tel" id="tel" class="input-medium" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>อีเมล์</td>
            <td><input type="text" name="email" id="email" class="input-large" style="margin: 3px"></td>
        </tr>
        
        <tr>
            <td colspan="2"><input type="button" id="cmd_save" class="btn btn-success btn-large" value="บันทึกข้อมูล" style ="margin-left: 55px ; margin-bottom: 3px; margin-right: 3px ; margin-top: 3px"></td>
        </tr>
    </table>
</form>


