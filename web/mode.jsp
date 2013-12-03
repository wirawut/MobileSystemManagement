<%-- 
    Document   : testConnection
    Created on : 27 เม.ย. 2556, 23:47:36
    Author     : Admin
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="img/mobile.ico" rel="shortcut icon" type="image/x-icon" />
        <title>ตรวจสอบการเชื่อมต่อกับเครือข่าย</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.css" rel="stylesheet" media="screen">
        <link href="css/style.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-responsive.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="jquery/js/jquery.js"></script>
    </head>
    <body style="background-color: #5bc0de">
        <%
            try {
                ServletContext sc = getServletContext();
                Connection c = (Connection) sc.getAttribute("conn");
                String sql = "SELECT * FROM tb_manage_data_url_safe";
                ResultSet rs = c.createStatement().executeQuery(sql);
                while (rs.next()) {
                    session.setAttribute("url_safe", rs.getString("url_safe"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        %>
        <div class="container-fluid" style="margin-top: 100px">
            <div class="row span4" >
                <div class="span4 offset4 well">
                    <legend style="font-size: medium ; text-align: center"> <font style="color: black">กรุณาตรวจสอบการเชื่อมต่อกับเครือข่าย</font></legend>
                   <button type="submit" name="submit" class="btn  btn-info btn-block " id="cmdCheckConnect">ตรวจสอบการเชื่อมต่อ</button>
                   
                </div>
            </div>
        </div>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script type="text/javascript">
            var url_safe = "<%=session.getAttribute("url_safe")%>";
            $(function() {
                $("#cmdCheckConnect").click(function() {
                    $.ajax({
                        url: "controller.connect?check",
                        success: function(data) {
                            if (data == "connected") {
                                alert("สามารถใช้งานได้เนื่องจากมีการเชื่อมต่อกับอินเทอร์เน็ต");
                                location.href = "index.jsp";
                            }
                            else {
                                alert("ไม่สามารถใช้งานระบบได้เนื่องจากอินเทอร์เน็ตถูกตัดการใช้งาน");
                                //location.href = url_safe;
                            }
                        }
                    });
                });
            });
        </script>
</html>