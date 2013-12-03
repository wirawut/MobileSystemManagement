<%-- 
    Document   : index
    Created on : 31 มี.ค. 2556, 23:03:38
    Author     : Admin
--%>


<%@page import="groovy.lang.Script"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="background-color: #ffffff">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="img/mobile.ico" rel="shortcut icon" type="image/x-icon" />
        <title>ระบบบริหารร้านโทรศัพท์มือถือ</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-responsive.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link href="css/style.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="jquery/js/jquery.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script type="text/javascript" src="jquery/js/jquery-ui.js"></script>
        <script src="js/modernizr.custom.63321.js"></script>
        <script type="text/javascript" src="jquery/js/i18n/ui.datepicker-th.js"></script>
        <link rel="stylesheet" type="text/css" href="jquery/css/redmond/jquery-ui-1.7.1.custom.css" />
        <link rel="stylesheet" type="text/css" href="checkbox/css/style.css" />

        <script type="text/javascript">
            $(function() {
                $("#mi-slider").catslider();
                $("#menu_top").load("menu_top.jsp"); 
            });
                   
        </script>
    </head>
    <body style="background-color: #ffffff"> 
        <!--menu-->
        <div class="container-fluid" style="margin-top: 10px ;background-color: #ffffff">
            <div class="row-fluid"
                 id="menu_top">
            </div>
        </div>
        <div class="container-fluid"style="background-color: #ffffff" >
            <div class="row-fluid">
                <!--เนื้อหา-->
                <div class="span12">
                    <div class="well">
                        <div id="content" style="color:#000" >
                            ยินดีต้อนรับ
                    </div>   
                </div>
            </div>
        </div>
        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.min.js"></script>      
        <script src="js/jquery.catslider.js"></script>
    </body>
</html>

