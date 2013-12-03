<%-- 
    Document   : report_product_export_other_tool_summary
    Created on : 25 เม.ย. 2556, 15:13:21
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
            <a onclick="loadContent('system/report/report_product_export_other_tool_summary_today.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>รายวัน</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปรายวัน</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_other_tool_summary_day.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามวัน</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามวัน</strong></span>   </td>

    </tr>

    <tr>
        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_other_tool_summary_month.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามเดือน</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามเดือน</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_other_tool_summary_year.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามปี</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามปี</strong></span>   </td>

    </tr>

    <tr>
        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_other_tool_summary_other_tool_type.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามประเภทอุปกรณ์</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามประเภทอุปกรณ์</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_other_tool_summary_other_tool.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามรุ่น</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามรุ่น</strong></span>   </td>

    </tr>
    
    <tr>
        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_other_tool_summary_employee.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามพนักงาน</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามพนักงาน</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_other_tool_summary_customer.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามลูกค้า</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามลูกค้า</strong></span>   </td>

    </tr>
</table>
