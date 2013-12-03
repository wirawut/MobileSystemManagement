<%-- 
    Document   : report_product_import_other_tool_data
    Created on : 25 เม.ย. 2556, 15:07:44
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
    function loadContent(url){
        $("#content").load(url);
    }
        
</script>

<table>

    <tr>
        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_import_other_tool_data_today.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>รายวัน</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานข้อมูลรายวัน</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_import_other_tool_data_day.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามวัน</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานข้อมูลตามวัน</strong></span>   </td>

    </tr>

    <tr>
        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_import_other_tool_data_month.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามเดือน</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานข้อมูลตามเดือน</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_import_other_tool_data_year.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามปี</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานข้อมูลตามปี</strong></span>   </td>

    </tr>

    <tr>
        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_import_other_tool_data_other_tool_type.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามประเภทอุปกรณ์</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานข้อมูลตามประเภทอุปกรณ์</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_import_other_tool_data_other_tool.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามรุ่น</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานข้อมูลตามรุ่น</strong></span>   </td>

    </tr>
    
    <tr>
        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_import_other_tool_data_employee.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามพนักงาน</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานข้อมูลตามพนักงาน</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_import_other_tool_data_supplier.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามคู่ค้า</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานข้อมูลตามคู่ค้า</strong></span>   </td>

    </tr>
</table>