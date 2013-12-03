<%-- 
    Document   : menu_top
    Created on : 17 เม.ย. 2556, 16:28:06
    Author     : Admin
--%>

<%@page import="net.sf.jasperreports.engine.JRException"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.sf.jasperreports.view.JasperViewer"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="java.sql.Connection"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<script type="text/javascript">
    function loadContent(url) {
        $("#content").load(url);
    }
    $(function() {
        $('.dropdown-toggle').dropdown();


        //start report_mobile_data
        //report_product_import_mobile_data_today
        $("#report_product_import_mobile_data_today").click(function() {
            $.ajax({
                url: "controller.report_product_import_mobile_data?today",
                success: function(data) {
                    if (data == "complete") {
                        alert("สมบบูรณ์");
                    }
                    else {
                        alert(data);
                    }
                }
            });
        });

        //report_product_import_mobile_data_day
        $("#report_product_import_mobile_data_day").click(function() {
            $.ajax({
                url: "controller.report_product_import_mobile_data?day",
                success: function(data) {
                    if (data == "complete") {
                        alert("สมบบูรณ์");
                    }
                    else {
                        alert(data);
                    }
                }
            });
        });

        //report_product_import_mobile_data_month
        $("#report_product_import_mobile_data_day").click(function() {
            $.ajax({
                url: "controller.report_product_import_mobile_data?month",
                success: function(data) {
                    if (data == "complete") {
                        alert("สมบบูรณ์");
                    }
                    else {
                        alert(data);
                    }
                }
            });
        });

        //report_product_import_mobile_data_year
        $("#report_product_import_mobile_data_year").click(function() {
            $.ajax({
                url: "controller.report_product_import_mobile_data?year",
                success: function(data) {
                    if (data == "complete") {
                        alert("สมบบูรณ์");
                    }
                    else {
                        alert(data);
                    }
                }
            });
        });



        //report_product_import_mobile_data_mobile_type
        $("#report_product_import_mobile_data_mobile_type").click(function() {
            $.ajax({
                url: "controller.report_product_import_mobile_data?mobile_type",
                success: function(data) {
                    if (data == "complete") {
                        alert("สมบบูรณ์");
                    }
                    else {
                        alert(data);
                    }
                }
            });
        });

        //report_product_import_mobile_data_mobile
        $("#report_product_import_mobile_data_mobile").click(function() {
            $.ajax({
                url: "controller.report_product_import_mobile_data?mobile",
                success: function(data) {
                    if (data == "complete") {
                        alert("สมบบูรณ์");
                    }
                    else {
                        alert(data);
                    }
                }
            });
        });

        //report_product_import_mobile_data_supplier
        $("#report_product_import_mobile_data_supplier").click(function() {
            $.ajax({
                url: "controller.report_product_import_mobile_data?suplier",
                success: function(data) {
                    if (data == "complete") {
                        alert("สมบบูรณ์");
                    }
                    else {
                        alert(data);
                    }
                }
            });
        });

        //end report_mobile_data




    });

    function exit() {
    <%
        /*
         session.setAttribute("fname", "");
         session.setAttribute("lname", "");
         session.setAttribute("email", "");
         session.setAttribute("tel", "");
         session.setAttribute("username", "");
         session.setAttribute("password", "");
         session.setAttribute("level", "");
         session.setAttribute("image", "");
         */
    %>
            location.href = "index.jsp";
        }


</script>


<div class="navbar" style="border: #000 1px solid" >

    <div class="container" style="width: auto ; background-color:#3B5998 ">
        <a class="btn btn-navbar" data-target=".nav-collapse" data-toggle="collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>         
        <div class="nav-collapse">
            <ul class="nav" style="padding-left: 0px" >
                <!--หน้าหลัก-->
                <li>
                    <a id="home" href="home.jsp"><span><img src="img/home.png" width="25" height="25"><strong id="home3">หน้าหลัก</strong></span></a>
                </li>

                <!--ขาย-->
                <li>
                    <a id="sale" id="sale" onclick="loadContent('system/sale/sale.jsp')"><span><img src="img/sale.png" width="25" height="25"><strong>ขาย</strong></span></a>
                </li>

                <!--รายงาน-->
                <li>
                    <a id="report" id="report" onclick="loadContent('system/report/report_import_data.jsp')"><span><img src="img/report.png" width="25" height="25"><strong>รายงาน</strong></span></a>
                </li>
                <li id="stock" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">
                        <span><img src="img/stock.png" width="25" height="25"><strong>คลังสินค้า</strong></span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a onclick="loadContent('system/manage/manage_data_product_mobile.jsp')"><img src="img/mobile.png" width="16" height="16">&nbsp;โทรศัพท์</a>
                        </li>
                        <li>
                            <a onclick="loadContent('system/manage/manage_data_product_sim.jsp')"><img src="img/sim.png" width="16" height="16">&nbsp;ซิมการ์ด</a>
                        </li>
                        <li>
                            <a onclick="loadContent('system/manage/manage_data_product_other_tool.jsp')"><img src="img/other_tool.png" width="16" height="16">&nbsp;อุปกรณ์เสริม</a>
                        </li>
                    </ul>
                </li>

                <!--ตั้งค่า-->
                <li class="dropdown">
                    <a id="manage" class="dropdown-toggle" data-toggle="dropdown" >
                        <span><img src="img/manage.png" width="25" height="25"><strong>ตั้งค่าพื้นฐาน</strong></span>
                        <b class="caret"></b>
                    </a>

                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <li>
                            <a onclick="loadContent('system/manage/manage_data_company.jsp')"><img src="img/manage_data_company.png" width="16" height="16">&nbsp;บริษัท/ห้างร้าน</a>
                        </li>

                        <li>
                            <a onclick="loadContent('system/manage/manage_data_major.jsp')"><img src="img/manage_data_major.png" width="16" height="16">&nbsp;สาขา</a>
                        </li>
                        <li>
                            <a onclick="loadContent('system/manage/manage_data_level.jsp')"><img src="img/manage_data_level.png" width="16" height="16">&nbsp;ระดับผู้ใช้งาน</a>
                        </li>
                       <li>
                            <a onclick="loadContent('system/manage/manage_data_user.jsp')"><img src="img/manage_data_user.png" width="16" height="16">&nbsp;ผู้ใช้งานระบบ</a>
                        </li>
                        
                        <!--
                        <li>
                            <a onclick="loadContent('system/manage/manage_data_unit.jsp')"><img src="img/manage_data_unit.png" width="16" height="16">&nbsp;หน่วยนับสินค้า</a>
                        </li> -->
                        <!-- <li>
                             <a onclick="loadContent('system/manage/manage_data_status.jsp')"><img src="img/manage_data_status.png" width="16" height="16">&nbsp;สถานะการซ่อม</a>
                         </li> -->
                        


                        <!--<li class="dropdown-submenu">
                            <a class="dropdown-toggle" data-toggle="dropdown" >
                                <img src="img/manage_data_topic_repair.png" width="16" height="16">&nbsp;หัวข้อการซ่อม
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a onclick="loadContent('system/manage/manage_data_topic_repair_mobile.jsp')"><img src="img/mobile.png" width="16" height="16">&nbsp;โทรศัพท์</a>
                                </li>
                                <li>
                                    <a onclick="loadContent('system/manage/manage_data_topic_repair_other_tool.jsp')"><img src="img/other_tool.png" width="16" height="16">&nbsp;อุปกรณ์เสริม</a>
                                </li>
                            </ul>
                        </li>-->
                       
                        <li class="dropdown-submenu">
                            <a class="dropdown-toggle" data-toggle="dropdown">
                                <img src="img/manage_data_product_type.png" width="16" height="16">&nbsp;ประเภทสินค้า
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a onclick="loadContent('system/manage/manage_data_product_type_mobile.jsp')"><img src="img/mobile.png" width="16" height="16">&nbsp;โทรศัพท์</a>
                                </li>
                                <li>
                                    <a onclick="loadContent('system/manage/manage_data_product_type_sim.jsp')"><img src="img/sim.png" width="16" height="16">&nbsp;ซิมการ์ด</a>
                                </li>
                                <li>
                                    <a onclick="loadContent('system/manage/manage_data_product_type_other_tool.jsp')"><img src="img/other_tool.png" width="16" height="16">&nbsp;อุปกรณ์เสริม</a>
                                </li>
                            </ul>
                        </li>


                        <li>
                            <a onclick="loadContent('system/manage/manage_data_customer.jsp')"><img src="img/manage_data_customer.png" width="16" height="16">&nbsp;ลูกค้า</a>
                        </li>
                    </ul>
                </li>
                <!--คลังสินค้า-->

                <!--
                <form class="navbar-search pull-left" action="">
                    <input class="search-query span2" type="text" placeholder="ค้นหา" style="width: 200px">
                </form> -->
            </ul>


            <ul class="nav pull-right">
                <li>
                    <a onclick="loadContent('data_user.jsp')" style="padding-top: 17px " ><img src='image/${image}' style="width : 25px ; height: 25px ">&nbsp;<span><strong>คุณ</strong></span> :&nbsp;${username}&nbsp;&nbsp;<span><strong>ระดับ</strong></span>&nbsp;:&nbsp;${level}</a>
                </li>

                <li>
                    <a onclick="exit()"><span><strong><img src="img/exit.png" width="25" height="25">&nbsp;ออก</strong></span></a>
                </li>
            </ul>
        </div>
    </div>
</div>


<!--กำหนดสิทธิ์-->
<%

    if (session.getAttribute("access").equals("1")) {
%>
<script type="text/javascript">
    $("#stock").hide();
    $("#report").hide();
    $("#manage").hide();
</script>
<%} else if (session.getAttribute("access").equals("12")) {
%>
<script type="text/javascript">
    $("#report").hide();
    $("#manage").hide();
</script>
<%} else if (session.getAttribute("access").equals("123")) {
%>
<script type="text/javascript">
    $("#manage").hide();
</script>
<%} else if (session.getAttribute("access").equals("1234")) {
%>
<script type="text/javascript">
</script>
<% } else if (session.getAttribute("access").equals("134")) {
%>
<script type="text/javascript">
    $("#stock").hide();
</script>
<% } else if (session.getAttribute("access").equals("13")) {
%>
<script type="text/javascript">
    $("#stock").hide();
    $("#manage").hide();
</script>
<% } else if (session.getAttribute("access").equals("1")) {
%>
<script type="text/javascript">
    $("#stock").hide();
    $("#report").hide();
    $("#manage").hide();
</script>
<% } else if (session.getAttribute("access").equals("124")) {
%>
<script type="text/javascript">
    $("#report").hide();
</script>
<% } else if (session.getAttribute("access").equals("14")) {
%>
<script type="text/javascript">
    $("#stock").hide();
    $("#report").hide();
</script>
<% } else if (session.getAttribute("access").equals("24")) {
%>
<script type="text/javascript">
    $("#sale").hide();
    $("#report").hide();
</script>
<% } else if (session.getAttribute("access").equals("23")) {
%>
<script type="text/javascript">
    $("#sale").hide();
    $("#manage").hide();
</script>

<% } else if (session.getAttribute("access").equals("34")) {
%>
<script type="text/javascript">
    $("#sale").hide();
    $("#stock").hide();
</script>
<% } else if (session.getAttribute("access").equals("4")) {
%>
<script type="text/javascript">
    $("#sale").hide();
    $("#stock").hide();
    $("#report").hide();
</script>
<% } else if (session.getAttribute("access").equals("3")) {
%>
<script type="text/javascript">
    $("#sale").hide();
    $("#stock").hide();
    $("#manage").hide();
</script>
<% } else if (session.getAttribute("access").equals("2")) {
%>
<script type="text/javascript">
    $("#sale").hide();
    $("#report").hide();
    $("#manage").hide();
</script>
<% } else if (session.getAttribute("access").equals("234")) {
%>
<script type="text/javascript">
    $("#sale").hide();
</script>
<% }
%>
