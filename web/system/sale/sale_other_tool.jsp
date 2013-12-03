<%-- 
    Document   : sale_other_tool
    Created on : 22 เม.ย. 2556, 15:34:01
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(function(){
        //เรียกใช้งานฟังก์ชั่นbindManageDataCompany()
        $("#show_data_other_tool").load("controller.showData?show_data_other_tool");
        $("#get_option_type_other_tool").load("controller.manage_data_product_type?get_option");
        $("#get_option_other_tool").load("controller.manage_data_product_other_tool?get_option");
        
        //ซ่อน
        $("#hide").click(function(){
            $("#show_data_product_other_tool").slideUp("slow");
        });
        
        //โชว
        $("#show").click(function(){
            bind_data_product_other_tool();
            $("#show_data_product_other_tool").slideDown("slow");
        });
        
        $("#cmdSave").click(function(){
            $.ajax({
                url:"controller.sale_other_tool?save",
                type:"post",
                data: $("#sale_other_tool").serialize(),
                success:function(data){
                    if(data == "complete"){
                        alert("บันทึกข้อมูลแล้ว");
                        clearData();
                    }
                    else if(data == "no_complete"){
                        alert("ท่านต้องกรอกชื่อบริษัท/ห้างร้าน");
                        clearData();
                    }
                    else{
                        alert(data);
                    }
                }
            });
        });
    });
    function bind_data_product_other_tool(){
        $("#show_data_product_other_tool").load("controller.manage_data_product_other_tool?show_data_product_other_tool");
    }
   
    
</script>

<form id="sale_other_tool">
    <legend>ขายอปกรณ์เสริม</legend>
    <input type="button" class="btn-info btn-large" value="แสดง" id="show">&nbsp;<input type="button" class="btn-info btn-large" value="ซ่อน" id="hide">
    <div id="show_data_product_other_tool">
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
            <td>ประเภทสินค้า</td>
            <td><div id="get_option_type_other_tool"></div></td>
        </tr>

        <tr>
            <td>รุ่น</td>
            <td><div id="get_option_other_tool"></div></td>
        </tr>

        <tr>
            <td>ราคาขาย/เครื่อง</td>
            <td><input type="text" name="price_sale" id="price_sale" class="input-xlarge"></td>
        </tr>
        <tr>
            <td>จำนวน</td>
            <td><input type="text" name="quantity" id="quantity" class="input-xlarge"></td>
        </tr>

        <tr>
            <td>รวม</td>
            <td><input type="text" name="price_total" id="price_total" class="input-xlarge"></td>
        </tr>

        <tr>
            <td>วันที่ทำรายการ</td>
            <td><input type="text" name="date_sale" id="date_sale" class="input-xlarge"></td>
        </tr>
        <tr> 
            <td>วันหมดประกัน</td>
            <td><input type="text" name="date_insurance_end" id="date_insurance_end" class="input-xlarge"></td>
        </tr>

        <tr><td>การชำระเงิน</td></tr>
        <tr><td>การคิดภาษี</td></tr>
        <tr><td>เงื่อนไขการขาย</td></tr>

        <tr>
            <td><input type="button" id="cmdSave" class="btn btn-success btn-large" value="ขาย"  ></td>
            <td><input type="button" id="cmdBill" class="btn btn-success btn-large" value="ออกใบเสร็จ"  ></td>
        </tr>
    </table>
</form>



