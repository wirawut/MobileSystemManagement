<%-- 
    Document   : sale_sim
    Created on : 22 เม.ย. 2556, 15:33:49
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(function(){
        //เรียกใช้งานฟังก์ชั่นbindManageDataCompany()
        $("#show_data_sim").load("controller.showData?show_data_sim");
        $("#get_option_type_sim").load("controller.manage_data_product_type_sim?get_option");
        $("#get_option_sim").load("controller.manage_data_product_sim?get_option");
        
        //ซ่อน
        $("#hide").click(function(){
            $("#show_data_product_sim").slideUp("slow");
        });
        
        //โชว
        $("#show").click(function(){
            bind_data_product_sim();
            $("#show_data_product_sim").slideDown("slow");
        });
        
        $("#cmdSave").click(function(){
            $.ajax({
                url:"controller.sale_sim?save",
                type:"post",
                data: $("#sale_sim").serialize(),
                success:function(data){
                    if(data == "complete"){
                        alert("บันทึกข้อมูลแล้ว");
                        
                    }
                    else if(data == "no_complete"){
                        alert("ท่านต้องกรอกชื่อบริษัท/ห้างร้าน");
                     
                    }
                    else{
                        alert(data);
                    }
                }
            });
        });
    });
    
    function bind_data_product_sim(){
        $("#show_data_product_sim").load("controller.manage_data_product_sim?show_data_product_sim");
    }
    
</script>

<form id="sale_sim">
    <legend>ขายซิมการ์ด</legend>
    <input type="button" class="btn-info btn-large" value="แสดง" id="show">&nbsp;<input type="button" class="btn-info btn-large" value="ซ่อน" id="hide">
    <div id="show_data_product_sim">
    </div>
    <table>
        <tr>
            <td>ชื่อ</td>
            <td><input type="text" name="fname" id="fname" class="input-xlarge"></td>
        </tr>
        <tr>
            <td>สกุล</td>
            <td><input type="text" name="lname" id="lname" class="input-xlarge"></td>
        </tr>

        <tr>
            <td>เครือข่าย</td>
            <td><div id="get_option_type_sim"></td>
        </tr>
        <tr>
            <td>รุ่น</td>
            <td><div id="get_option_sim"></td>
        </tr>
        <tr>
            <td>เบอร์โทร</td>
            <td><input type="text" name="tel" id="tel" class="input-xlarge"></td>
        </tr>
        <tr>
            <td>ราคาขาย/หน่วย</td>
            <td><input type="text" name="price_sale" id="price_sale" class="input-xlarge"></td>
        </tr>
        <tr>
            <td>จำนวน</td>
            <td><input type="text" name="quantity" id="quantity" class="input-xlarge"><td>
        </tr>
        <tr>
            <td>รวม</td>
            <td><input type="text" name="price_total" id="price_total" class="input-xlarge"><td>
        </tr>
        <tr>
            <td>วันที่ทำรายการ</td>
            <td><input type="text" name="date_sale" id="date_sale" class="input-xlarge"></td>
        </tr>

        <tr>
            <td><input type="button" id="cmdSave" class="btn btn-success btn-large" value="ขาย"  ></td>
            <td><input type="button" id="cmdBill" class="btn btn-success btn-large" value="ออกใบเสร็จ"  ></td>
        </tr>
    </table>
</form>


