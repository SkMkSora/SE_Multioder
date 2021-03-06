<%-- 
    Document   : profile
    Created on : Nov 14, 2018, 12:47:58 PM
    Author     : Chronical
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
    <head>
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">   
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css"></style>
        <script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Multioder - Profile</title>
        <link href="https://fonts.googleapis.com/css?family=Kanit" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="menustyle.css">
        <style>
            .sidenav {
                height: 100%;
                width: 0;
                position: fixed;
                z-index: 1;
                top: 0;
                right: 0;
                background-color: #111;
                overflow-x: hidden;
                transition: 0.5s;
                padding-top: 60px;
            }

            .sidenav a {
                padding: 8px 8px 8px 32px;
                text-decoration: none;
                font-size: 25px;
                color: #818181;
                display: block;
                transition: 0.3s;
            }

            .sidenav a:hover {
                color: #f1f1f1;
            }

            .sidenav .closebtn {
                position: absolute;
                top: 0;
                right: 25px;
                font-size: 36px;
                margin-left: 50px;
            }

            #main {
                transition: margin-left .5s;
                padding: 16px;
            }

            @media screen and (max-height: 450px) {
                .sidenav {padding-top: 15px;}
                .sidenav a {font-size: 18px;}
            }
        </style>
    </head>
    <body style="background-image: url('pic/food_menu.jpg'); background-size: auto; background-attachment: fixed;">
        <div id="mySidenav" class="sidenav">
            <a href="showProfileServlet">ข้อมูลส่วนตัว</a>
            <a href="logoutServlet">ออกจากระบบ</a>
        </div>
        <div class="back"></div>
        <div class="menu_area">
            <div class="menu">
                <a href="menuServlet"><div id="home_area"><img src="pic/logo.png" id="home"></div></a>
                <div id="choose" onclick="openNav()"></div>
                <a href="cart.jsp"><div id="shop"></div></a>
            </div>
        </div>
        <div id="mainbody" onclick="closeNav()">

            <div class="con_area">
                <div class="profile" style="width: 90%; margin: auto; padding-top: 1.5em; padding-bottom: 1.5em;">
                    <font class="head_name" size='6' color='black'>ข้อมูลส่วนตัว</font><br><br>
                    <font size='4'>
                    อีเมล : ${pro.email}<br>
                    ชื่อ : ${pro.firstname}<br>
                    นามสกุล : ${pro.lastname}<br>
                    เบอร์โทรศัพท์ : ${pro.phone}<br><br>
                    <a href="showEditProfileServlet"><button>แก้ไขข้อมูลส่วนตัว</button></a>
                    <a href="menuServlet"><button>กลับสู่หน้าหลัก</button></a>
                    </font>
                    <br><br><hr style="border-top: 2px solid #000;"><br>
                    <font class="head_name" size='6' color='black'>ใบสั่งซื้อ</font><br><br>
                    <table id="profile" border="1" class="table table-striped table-border checkout-table" style="font-size: 1.15em;">
                        <thead>
                            <tr>
                                <th>เลขออเดอร์</th>
                                <th>วันที่สั่งซื้อ</th>
                                <th>ราคาสุทธิ</th>
                                <th>ที่อยู่จัดส่ง</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ord" items="${ordlist}">
                                <tr>
                                    <td>${ord.order_id} </td>
                                    <td>${ord.buy_date} </td>
                                    <td>${ord.total}</td>
                                    <td>${ord.address}</td>
                                    <td><a href="viewOrderServlet?ord_id=${ord.order_id}" >รายละเอียด</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $('#profile').dataTable();
            });
        </script>
        <script>
            function openNav() {
                document.getElementById("mySidenav").style.width = "250px";
                document.getElementById("main").style.marginLeft = "-250px";
                document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
            }

            function closeNav() {
                document.getElementById("mySidenav").style.width = "0";
                document.getElementById("main").style.marginLeft = "0";
                document.body.style.backgroundColor = "white";
            }
        </script>

    </body>
</html>
