<%-- 
    Document   : consignment_mobile
    Created on : 23 เม.ย. 2556, 16:17:45
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(function(){
        $("#get_option_mobile").load("controller.manage_data_product_mobile?get_option");
        $("#get_option_type_mobile").load("controller.manage_data_product_type_mobile?get_option");
        
   
        $("#cmd_save").click(function(){
            $.ajax({
                url:"controller.consignment_mobile?save",
                type:"post",
                data: $("#consignment_mobile").serialize(),
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
        });
        
        $("#cmd_cal").click(function(){
            var quantity = $("#quantity").val();
            var price_sale = $("#price_sale").val();
            var rate_consignment =$("#rate_consignment").val();       
            var price_consignment = ((quantity)*((price_sale) * (rate_consignment/100)));
            $("#price_consignment").val(price_consignment);
        });
    
        
        
    });
    
   
    function clear_data(){
        /*$("#sim_type_id").val("");
        $("#sim").val("");
        $("#price_buy").val("");
        $("#price_sale").val("");
        $("#quantity").val("");
        $("#date_import").val("");
         */
        
    }
    
</script>

<form id="consignment_mobile">
    <table>
        <legend>ฝากขายโทรศัพท์</legend>

        <tr>
            <td>บริษัท/ห้างร้าน</td>
            <td><input type="text" name="company_consignment" id="company_consignment" class="input-large" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>เบอร์โทร</td>
            <td><input type="text" name="tel" id="tel" class="input-medium" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>ยี่ห้อ</td>
            <td><div id="get_option_type_mobile"></div></td>
        </tr>
        <tr>
            <td>รุ่น</td>
            <td><div id ="get_option_mobile"></div></td>
        </tr>
        <tr>
            <td>จำนวน</td>
            <td><input type="text" name="quantity" id="quantity" class="input-small" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>ราคาขาย/หน่วย</td>
            <td><input type="text" name="price_sale" id="price_sale" class="input-small" style="margin: 3px"></td>
        </tr>
        <tr>
            <td>อัตราค่าบริการ(เปอร์เซ็น)</td>
            <td><input type="text" name="rate_consignment" id="rate_consignment" class="input-small" style="margin: 3px">&nbsp;<input type="button" id="cmd_cal" name ="cmd_cal" class="btn btn-primary btn-large" value="คำนวณค่าบริการ"  ></td>
        </tr>
        <tr>
            <td>ค่าบริการ(บาท)</td>
            <td><input type="text" name="price_consignment" id="price_consignment" class="input-small" style="margin: 3px"></td>
        </tr> 
        <tr>
            <td colspan="2"><input type="button" id="cmd_save" class="btn btn-success btn-large" value="บันทึกข้อมูล" style="margin-left: 147px ; margin-bottom: 3px ; margin-right: 3px ; margin-top: 3px"></td>
        </tr>
    </table>
</form>