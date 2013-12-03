<%-- 
    Document   : report_product_export_sim_summary
    Created on : 25 เม.ย. 2556, 15:12:48
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
            <a onclick="loadContent('system/report/report_product_export_sim_summary_today.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>รายวัน</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปรายวัน</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_sim_summary_day.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามวัน</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามวัน</strong></span>   </td>

    </tr>

    <tr>
        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_sim_summary_month.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามเดือน</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามเดือน</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_sim_summary_year.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามปี</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามปี</strong></span>   </td>

    </tr>

    <tr>
        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_sim_summary_sim_type.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามเครือข่าย</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามเครือข่าย</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_sim_summary_sim.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามรุ่น</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามรุ่น</strong></span>   </td>

    </tr>
    
    <tr>
        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_sim_summary_employee.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามพนักงาน</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามพนักงาน</strong></span>  </td>

        <td style="width: 200px; text-align: center">
            <a onclick="loadContent('system/report/report_product_export_sim_summary_suctomer.jsp')" class="btn btn-danger" style="width:200px; height:60px ; margin: 10px">
                <i class="icon-search icon-white"></i><br>
                <span ><strong>ตามลูกค้า</strong></span>        	
            </a>
        </td>
        <td style="width: 350px"><span ><strong>รายงานสรุปตามลูกค้า</strong></span>   </td>

    </tr>
</table>

