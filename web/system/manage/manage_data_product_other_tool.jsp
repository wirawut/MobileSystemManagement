<%-- 
    Document   : manage_data_product_other_tool
    Created on : 21 เม.ย. 2556, 17:00:42
    Author     : Admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    $(function() {
        //โหลดcomboboxเข้ามา
        //set date
        var date = new Date();
        var year = date.getFullYear();
        var month = (date.getMonth() + 1);
        var day = date.getDate();
        var date_import;
        if ((month < 10) && (day < 10)) {
            month = "0" + month;
            day = "0" + day;
            date_import = (year + '-' + month + '-' + day);
        } else if ((month < 10) && (day > 10)) {
            month = "0" + month;
            date_import = (year + '-' + month + '-' + day);
        } else if ((month > 10) && (day < 10)) {
            day = "0" + day;
            date_import = (year + '-' + month + '-' + day);
        } else {
            date_import = (year + '-' + month + '-' + day);
        }

        $("#date_import").val(date_import);
        $("#date_import").datepicker({dateFormat: 'yy-mm-dd'});
        $("#get_option_type_other_tool").load("controller.manage_data_product_type_other_tool?get_option");
        $("#get_option_unit").load("controller.manage_data_unit?get_option");

        //ซ่อน
        $("#hide").click(function() {
            $("#show_data_product_other_tool").slideUp("slow");
        });

        //โชว
        $("#show").click(function() {
            bind_data_product_other_tool();
            $("#show_data_product_other_tool").slideDown("slow");
        });

        $("#cmd_save").click(function() {
            $.ajax({
                url: "controller.manage_data_product_other_tool?save",
                type: "post",
                data: $("#manage_data_product_other_tool").serialize(),
                success: function(data) {
                    if (data == "complete") {
                        $("#content").load('system/manage/manage_data_product_other_tool.jsp');
                        alert("บันทึกข้อมูลแล้ว");
                        clear_data();
                    }
                    else if (data == "no_complete") {
                        alert("คุณกรอกไม่ได้กรอกข้อมูล หรือข้อมูลที่คุณกรอกซ้ำกัน");
                        clear_data();
                    }
                    else {
                        alert("ไม่สามารถบันทึกข้อมูลได้");
                    }
                }
            });
        });
    });

    function clear_data() {
        $("#other_tool_type_id").val("");
        $("#other_tool").val("");
        $("#image").val("");
        $("#price_buy").val("");
        $("#price_sale").val("");
        $("#quantity").val("");
        $("#date_import").val("");
        $("#supplier").val("");

    }
    function bind_data_product_other_tool() {
        $("#show_data_product_other_tool").load("controller.manage_data_product_other_tool?show_data_product_other_tool");
    }

    function open_window_upload() {
        var style_dialog = "dialogWidth:400px;dialogHeight:300px;dialogLeft:450px;";
        var w = window.showModalDialog("choose_file_dialog.jsp", null, style_dialog);
        //ถ้ามีการเปิดหน้าต่างใหม่จริงๆ

        //alert(w);
        if (w != null) {
            $("#image").val(w);
            //var image_select = getControlId("image_select");
            //image_select.innerHTML = w ;
        }

        //$("#image_select").val(w);
    }

    function delete_data_product_other_tool(id) {
        var other_tool_id = "PO" + id;

        if (confirm("กรุณายืนยันการลบอีกครั้ง !!!")) {
            $.ajax({
                url: "controller.manage_data_product_other_tool?delete",
                type: "post",
                data: {other_tool_id: other_tool_id},
                success: function(data) {
                    if (data == "complete") {
                        bind_data_product_other_tool();
                    }
                    else if (data = "no_complete") {
                        alert(data);
                    }
                }
            });
            return true;
        } else {
            return false;
        }
    }
    function edit_data_product_other_tool(id) {
        var other_tool_id = "PO" + id;
        $.ajax({
            url: "controller.manage_data_product_other_tool?edit",
            type: "post",
            data: {other_tool_id: other_tool_id},
            success: function(data) {
                //แยกคำที่รับค่ามาจากการresponse จากservletออกจากกันเพื่อนำมาใช้งาน
                var arr = data.split(",");
                //$("#other_tool_id").val(arr[0]); 
                //$("#get_option_type_other_tool").text(arr[0]); //ส่งไปเเบบhiddenเพื่อเก็บค่านี้ไว้ใช้งาน
                $("#other_tool_id").val(arr[0]);
                $("#other_tool").val(arr[2]);
                $("#image").val(arr[3]);
                $("#price_buy").val(arr[4]);
                $("#price_sale").val(arr[5]);
                // $("#get_option_unit").val(arr[6]);
                $("#quantity").val(arr[7]);
                $("#date_import").val(arr[8]);
                $("#detail").val(arr[9]);

            }
        });
    }
</script>
<%
    //เพิ่มค่าตัวเลข repair_id บวกเข้ากับสตริง
    Connection c = (Connection) getServletContext().getAttribute("conn");
    String sql = " SELECT MAX( other_tool_id ) AS other_tool_id FROM tb_manage_data_product_other_tool ";//PM3
    ResultSet rs = c.createStatement().executeQuery(sql);
    if (rs.next()) {
        String other_tool = rs.getString("other_tool_id"); //PM30
        if (other_tool.length() == 3) { // = 2 เพราะ 0 , 1 , 2  ก็คือ ถ้ามันมี3ตัวอักษร
            session.setAttribute("i", (Integer.parseInt(other_tool.substring(2, other_tool.length()))) + 1); //ได้3  เอาไปเก็บไว้ใน i
        } else if (other_tool.length() > 3) { //ถ้ามันมี 3ตัวอักษรขึ้นไป
            session.setAttribute("i", (Integer.parseInt(other_tool.substring(2, other_tool.length()))) + 1); //ต้องตัดสตริงตามำเเหน่งที่ต้องการ คือ  2 - 3 เช่น PM30
        }
    } else {
        Integer i = 1;
        session.setAttribute("i", i);
    }
%></script>

<form id="manage_data_product_other_tool">
    <legend>ข้อมูลสินค้า(อุปกรณ์เสริม)</legend>
    <input type="button" class="btn-info btn-medium" value="แสดง" id="show">&nbsp;<input type="button" class="btn-info btn-medium" value="ซ่อน" id="hide">
    <div id="show_data_product_other_tool">
    </div>
    <table>
        <tr>
            <td>รหัสสินค้า</td>
            <td colspan="2"><input type="text" name="other_tool_id" id="other_tool_id" class="input-mini" style="padding: 5px;width: 60px;font-size: 15px ;margin: 3px ; background-color: orange ; color: #000 ;  height: 38px"  value="PO<%=session.getAttribute("i")%>"></td>
        </tr>

        <tr>
            <td>ยี่ห้อ</td>
            <td><div id="get_option_type_other_tool"></div></td>
        </tr>
        <tr>
            <td>รุ่น</td>
            <td><input type="text" name="other_tool" id="other_tool" class="input-large" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
            <td>รูปภาพ</td>
            <td colspan="2"><input type="text" class="input-medium" id="image" name="image" style="margin: 3px ;  height: 38px">&nbsp;<input type="button" value="เลือกรูปภาพ" class="btn btn-info" onClick="open_window_upload()" style="margin: 3px;padding: 6px"></td>
        </tr>
        <tr>
            <td>ราคาซื้อ</td>
            <td><input type="text" name="price_buy" id="price_buy" class="input-small" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
            <td>ราคาขาย</td>
            <td><input type="text" name="price_sale" id="price_sale" class="input-small" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
            <td>หน่วยนับ</td>
            <td><div id="get_option_unit"></div></td>
        </tr>
        <tr>
            <td>จำนวน</td>
            <td><input type="text" name="quantity" id="quantity" class="input-small" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
            <td>วันที่นำเข้า</td>
            <td><input type="text" name="date_import" id="date_import" class="input-medium" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
            <td>รายละเอียด</td>
            <td><input type="text" name="detail" id="detail" class="input-large" style="margin: 3px ;  height: 38px"></td>
        </tr>
        <tr>
            <td colspan="8"><input type="button" id="cmd_save" class="btn btn-medium btn-info" value="บันทึกข้อมูล"  style ="margin-left: 70px; margin-right: 3px ;margin-top: 3px ; margin-bottom: 3px ;padding: 5px;width: 100px;height: 45px "></td>
        </tr>
    </table>
</form>


