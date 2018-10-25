<%-- 
    Document   : register
    Created on : Oct 20, 2018, 9:17:45 PM
    Author     : Chronical
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <!--<link rel="stylesheet" type="text/css" href="jb.css">-->
        <link href="https://fonts.googleapis.com/css?family=Kanit" rel="stylesheet">
    </head>
    <body style="font-family: 'Kanit', sans-serif; background-image: url('pic/food.jpg'); background-size: cover;">
        <div class="back"></div>
        <div class="area1" style="background-image: url('pic/back.png');">
            <div class="form_signup">
                <form action="registerServlet" method="POST">
                    <font size="7" color="white"><b>สมัครสมาชิก</b></font> <br><br>
                    <font size="3" color="white">
                    ชื่อผู้ใช้ (username) <br>
                    <input type="text" name="user" value="" size="40px"/> <br>
                    รหัสผ่าน (password) <br>
                    <input type="password" name="pass" value="" size="40px"/> <br>
                    ชื่อจริง (first name) <br>
                    <input type="text" name="first" value="" size="40px"/> <br>
                    นามสกุล (last name) <br>
                    <input type="text" name="last" value="" size="40px"/> <br>
                    อีเมล์ (email) <br>
                    <input type="text" name="mail" value="" size="40px"/> <br>
                    โทรศัพท์ (phone) <br>
                    <input type="text" name="phone" value="" maxlength = "10" size="40px"/> <br>
                    ที่อยู่ (address) <br>
                    <textarea name="add" rows="4" cols="40"></textarea><br>
                    </font>

                    <input type="submit" name="register" value="Register" />
                    <!--<input type="submit" name="back" value="Back" />-->
                </form>
            </div>
        </div>
        
        <c:if test="${flag == 1}">
            <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
            <script>swal({"Username or Email has already used"});</script>  

        </c:if>
        <c:if test="${flag == 2}">
            <script src="https://unpkg.com/sweetalert2@7.8.2/dist/sweetalert2.all.js"></script>

            <script>swal({
                    title: 'Are you sure?',
                    text: 'Please enter all fields',
                    imageUrl: '',
                    type: 'info',
                    showCancelButton: true,
                    confirmButtonColor: '#DD6B55',
                    confirmButtonText: 'Cancle',
                    cancelButtonText: 'Yes'
                }).then(() => {
                    if (result.value) {
                        // handle Confirm button click
                    } else {
                        // result.dismiss can be 'cancel', 'overlay', 'esc' or 'timer'
                    }
                });</script>  
            </c:if>


    </body>
</html>
