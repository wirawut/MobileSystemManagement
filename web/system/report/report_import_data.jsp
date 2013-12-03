
<%-- 
    Document   : report_import_data
    Created on : Aug 19, 2013, 10:05:34 PM
    Author     : Hippo-G8
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function(){
        $("#date_start").datepicker({ dateFormat: 'yy-mm-dd' });
        $("#date_end").datepicker({ dateFormat: 'yy-mm-dd' });
        $("#get_option_year").load("controller.option_report?get_option_year");
        $("#get_option_month").load("controller.option_report?get_option_month");
        $("#get_option_type").load("controller.option_report?get_option_type");
        $("#get_option_employee").load("controller.option_report?get_option_employee");
        $("#get_option_product").load("controller.option_report?get_option_product");
        $("#get_option_customer").load("controller.option_report?get_option_customer");
        $("#get_option_forecast_start").load("controller.option_report?get_option_forecast_start");
        $("#get_option_forecast_end").load("controller.option_report?get_option_forecast_end");
    });
    function report_data_today(){
        $.ajax({
            type:"post",
            data: $("#report").serialize(),
            url: "controller.report_data?today"
            , success:function(){
                //  alert("บันทึกการขายแล้ว");
                setTimeout(function() { $("#content").load('controller.report_data?show_print_today');},3000);
               
            }
        });
    }
    function report_summary_today(){
        $.ajax({
            type:"post",
            data: $("#report").serialize(),
            url: "controller.report_summary?today"
            , success:function(){
                //  alert("บันทึกการขายแล้ว");
                setTimeout(function() {$("#content").load('controller.report_summary?show_print_today'); },3000);
                
            }
        });
        
    }
    function report_data_day(){
        $.ajax({
            type:"post",
            data: {date_start : $("#date_start").val(),
                date_end : $("#date_end").val()},
            url: "controller.report_data?day"
            , success:function(){
                //  alert("บันทึกการขายแล้ว");
                setTimeout(function() { $("#content").load('controller.report_data?show_print_day'); },3000);
               
            }
        });
    }
    function report_summary_day(){
        $.ajax({
            type:"post",
            data: {date_start : $("#date_start").val(),
                date_end : $("#date_end").val()},
            url: "controller.report_summary?day"
            , success:function(){
                //  alert("บันทึกการขายแล้ว");
                setTimeout(function() {$("#content").load('controller.report_summary?show_print_day');},3000);
                
            }
        });
    }
    function report_data_month(){
        $.ajax({
            type:"post",
            data:{m : month.value },
            url: "controller.report_data?month",
            success:function(){
                //  alert("บันทึกการขายแล้ว");
                setTimeout(function() {$("#content").load('controller.report_data?show_print_month') ; },3000);
                
            }
        });
    }
    
    function report_summary_month(){
        $.ajax({
            type:"post",
            data:{m : month.value },
            url: "controller.report_summary?month"
            , success:function(){
                //  alert("บันทึกการขายแล้ว");
                
                setTimeout(function() { $("#content").load('controller.report_summary?show_print_month');},3000);
               
            }
        });
    }
    function report_data_year(){
        $.ajax({
            type:"post",
            data:{y :  $("#year").val() },
            url: "controller.report_data?year"
            , success:function(){
                setTimeout(function() {$("#content").load('controller.report_data?show_print_year');},3000);
       
            }
        });
    }
    function report_summary_year(){
        $.ajax({
            type:"post",
            data:{y : year.value },
            url: "controller.report_summary?year"
            , success:function(){
                //  alert("บันทึกการขายแล้ว");
                setTimeout(function() {  $("#content").load('controller.report_summary?show_print_year');},3000);
              
            }
        });
    }
    function report_data_employee(){
         
        $.ajax({
            type:"post",
            data:{e : $("#employee").val()},
            url: "controller.report_data?employee"
            , success:function(){
                //  alert("บันทึกการขายแล้ว");
                setTimeout(function() { $("#content").load('controller.report_data?show_print_employee');},3000);
               
            }
        });
    }
    function report_data_customer(){
         
        $.ajax({
            type:"post",
            data:{c : $("#customer").val()},
            url: "controller.report_data?customer"
            , success:function(){
                //  alert("บันทึกการขายแล้ว");
                setTimeout(function() { $("#content").load('controller.report_data?show_print_customer');},3000);
               
            }
        });
    }
 
    function report_data_product(){
        $.ajax({
            type:"post",
            data:{p : $("#product").val()},
            url: "controller.report_data?product"
            , success:function(){
                //  alert("บันทึกการขายแล้ว");
                setTimeout(function() {$("#content").load('controller.report_data?show_print_product');},3000);
                
            }
        });
    }
 
    function report_data_type(){
        $.ajax({
            type:"post",
            data:{t : $("#type").val()},
            url: "controller.report_data?type"
            , success:function(){
                //  alert("บันทึกการขายแล้ว");
                setTimeout(function() {$("#content").load('controller.report_data?show_print_type');},3000);
                
            }
        });
    }
    
    
    
    //customerยังไม่ได้เเก้อะไร
    function report_data_customer(){
        $.ajax({
            type:"post",
            data:{c : $("#customer").val()},
            url: "controller.report_data?customer"
            , success:function(){
                setTimeout(function() {$("#content").load('controller.report_data?show_print_customer');},3000);
                
            }
        });
    }
    function report_data_forecast(){
       
             $("#content").load('controller.report_data?forecast');
            // $("#content").load('controller.report_data?show_print_forecast');
      
    }
 
</script>

<form id="report">
    <legend>รายงานเเละระบบพยากรณ์</legend>
    <table>
        <!-- <tr>
             <td>รายงานใบกำกับภาษีอย่างย่อ</td>
             <td><a onclick="report_data_invoice()"><img src="img/report_product_data.png" style="height: 30px ; width: 30px ;margin: 3px"></a>&nbsp;
                 <a onclick="report_summary_invoice()"><img src="img/report_product_summary.png" style="height: 30px ; width: 30px ;margin: 3px"></a></td>
         </tr> -->
        <tr>
            <td>รายงานขายประจำวัน</td>
            <td><a onclick="report_data_today()"><img src="img/report_product_data.png" title="รายงานเอกสาร" style="height: 30px ; width: 30px ;margin: 3px"></a>&nbsp;
                <a onclick="report_summary_today()"><img src="img/report_product_summary.png" title="รายงานกราฟ" style="height: 30px ; width: 30px ;margin: 3px"></a></td>
        </tr>
        <tr>
            <td>รายงานยอดขายตามวัน</td>
            <td>&nbsp;&nbsp;ระหว่าง &nbsp;<input type="text" name="date_start" id="date_start" class="input-mini" style="padding: 5px;width: 100px;font-size: 15px ;margin: 3px ; background-color: #55b932 ; color: #000 ;  height: 38px" >
                &nbsp;ถึง &nbsp;<input type="text" name="date_end" id="date_end" class="input-mini" style="padding: 5px;width: 100px;font-size: 15px ;margin: 3px ; background-color: #55b932 ; color: #000 ;  height: 38px" >
                <a onclick="report_data_day()"><img src="img/report_product_data.png" title="รายงานเอกสาร" style="height: 30px ; width: 30px ;margin: 3px"></a>&nbsp;
                <a  onclick="report_summary_day()"><img src="img/report_product_summary.png" title="รายงานกราฟ" style="height: 30px ; width: 30px ;margin: 3px"></a></td>
        </tr>
        <tr>
            <td>รายงานยอดขายตามเดือน</td>
            <td>&nbsp;<span id="get_option_month" class="input-large"></span>
                <a  onclick="report_data_month()"><img src="img/report_product_data.png" title="รายงานเอกสาร" style="height: 30px ; width: 30px ;margin: 3px"></a>&nbsp;
                <a  onclick="report_summary_month()"><img src="img/report_product_summary.png" title="รายงานกราฟ" style="height: 30px ; width: 30px ;margin: 3px"></a></td>
        </tr>
        <tr>
            <td>รายงานยอดขายตามปี</td> 
            <td>&nbsp;<span id="get_option_year" class="input-large"></span>
                <a  onclick="report_data_year()"><img src="img/report_product_data.png" title="รายงานเอกสาร" style="height: 30px ; width: 30px ;margin: 3px"></a>&nbsp;
                <a onclick="report_summary_year()"><img src="img/report_product_summary.png" title="รายงานกราฟ" style="height: 30px ; width: 30px ;margin: 3px"></a></td>
        </tr>
        <tr>
            <td>รายงานยอดขายตามพนักงาน</td>
            <td>&nbsp;<span id="get_option_employee" class="input-large"></span>
                <a  onclick="report_data_employee()"><img src="img/report_product_data.png" title="รายงานเอกสาร" style="height: 30px ; width: 30px ;margin: 3px"></a>&nbsp;
                <!-- <a  onclick="report_summary_employee()"><img src="img/report_product_summary.png" style="height: 30px ; width: 30px ;margin: 3px"></a></td> -->
        </tr>
        <tr>
            <td>รายงานยอดขายตามสินค้า</td>
            <td>&nbsp;<span id="get_option_product" class="input-large" ></span>&nbsp;<a  onclick="report_data_product()"><img src="img/report_product_data.png" title="รายงานเอกสาร" style="height: 30px ; width: 30px ;margin: 3px"></a>&nbsp;
                <!--<a  onclick="report_summary_product()"><img src="img/report_product_summary.png" style="height: 30px ; width: 30px ;margin: 3px"></a></td> -->
        </tr>
        <tr>
            <td>รายงานยอดขายตามประเภทสินค้า</td>
            <td> &nbsp;<span id="get_option_type" class="input-large"></span>&nbsp;<a  onclick="report_data_type()"><img src="img/report_product_data.png" title="รายงานเอกสาร" style="height: 30px ; width: 30px ;margin: 3px"></a>&nbsp;
                <!-- <a  onclick="report_summary_type()"><img src="img/report_product_summary.png" style="height: 30px ; width: 30px ;margin: 3px"></a></td> -->
        </tr>


        <tr>
            <td>รายงานยอดขายตามลูกค้า</td>
            <td> &nbsp;<span id="get_option_customer" class="input-large"></span>&nbsp;<a  onclick="report_data_customer()"><img src="img/report_product_data.png" title="รายงานเอกสาร" style="height: 30px ; width: 30px ;margin: 3px"></a>&nbsp;
                <!-- <a  onclick="report_summary_type()"><img src="img/report_product_summary.png" style="height: 30px ; width: 30px ;margin: 3px"></a></td> -->
        </tr>
        <tr>
            <td>ระบบพยากรณ์</td>
            <td>&nbsp;<a  onclick="report_data_forecast()"><img src="img/forecast1.png" title="พยากรณ์" style="height: 30px ; width: 30px ;margin: 3px"></a>&nbsp;
                <!-- <a  onclick="report_summary_type()"><img src="img/report_product_summary.png" style="height: 30px ; width: 30px ;margin: 3px"></a></td> -->
        </tr>
    </table>
</form>
