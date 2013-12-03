
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="img/mobile.ico" rel="shortcut icon" type="image/x-icon" />
        <title>เข้าสู่ระบบ</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.css" rel="stylesheet" media="screen">
        <link href="css/style.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-responsive.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="jquery/js/jquery.js"></script>
        <script type="text/javascript">
            $(function() {
                $("#username").keyup(function() {
                    var username = $(this).val();
                    if (isFinite(username)) {
                        $("#error").css("color", "red").text("กรุณากรอกชื่อผู้ใช้งานและรหัสผ่านให้ถูกต้อง");
                    }

                });
                $("#cmdLogin").click(function() {
                    //เอาค่าในปุ่มtextมาเก็บไว้ในตัวเเปร
                    var input_username = $("#username").val();
                    var input_password = $("#password").val();
                    //ส่งตัวแปรทั้ง2ไปservletชื่อUserServlet.javaเเละไปยังmethodชื่อcheckLogin
                    $.ajax({
                        url: "controller.UserServlet?checkLogin",
                        //ระบุข้อมูลที่จะส่งไปยังservlet ข้อมูลที่ส่งไปก็คือตัวแปรนั่นเเหละ
                        data: {
                            username: input_username,
                            password: input_password
                        },
                        //เมื่อservletประมวลผลเสร็จเเล้วมันresponseอะไรกลับมามันจะทำตรงนี้
                        success: function(data) {
                            if (data == "pass") {
                                location.href = "home.jsp";
                            }
                            else {
                                $("#error").css("color", "red").text("กรุณากรอกชื่อผู้ใช้งานและรหัสผ่านให้ถูกต้อง");
                            }
                        }
                    });
                });
            });
        </script>
    </head>
    <body style="background-color: #5bc0de">  
        <div class="container-fluid" style="margin-top: 100px">
            <div class="row span4" >
                <div class="span4 offset4 well">
                    <legend style="font-size: medium ; text-align: center; color: black">กรุณาล็อกอินเพื่อเข้าสู่ระบบ</legend>
                    <div class="alert alert-info" id="error">
                        <a class="close" data-dismiss="alert" href="#">×</a><font style="color: blue">กรอกข้อมูลเพื่อเข้าสู่ระบบ</font>
                    </div>
                    <br>
                    <input type="text" id="username" class="span4" name="username" placeholder="ชื่อผู้ใช้งาน">
                    <input type="password" id="password" class="span4" name="password" placeholder="รหัสผ่าน">
                    <button type="submit" name="submit" class="btn btn-info btn-block" id="cmdLogin" style="height: 40px ;">เข้าสู่ระบบ</button>
                </div>
            </div>
        </div>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>