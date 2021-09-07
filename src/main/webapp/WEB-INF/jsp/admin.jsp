<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8" charset="utf-8" />
        <title>Admin</title>
    </head>
    <body>
        <header>
            <div class="header">
                <h1>My Bike Service</h1>
                <p>
                    Order bicycles in our rental office!
                </p>
            </div>
        </header>
        <main>
            <div class="navbar">
           <a href="/bike/controller?command=show_user_page" class="right">Main page</a>
           <a href="/bike/controller?command=log_out" class="right">Log Out</a>
            </div>
            <h2>Admin page</h2>
            <br />
             <p class="error_message">${requestScope.information}</p>
            <br />
            <h2>Actions with users</h2>
            <br />
            <a href="/bike/controller?command=add_user">Add user</a>
            <br /><br />
            <form action="/bike/controller" method="get">
                <input type="hidden" name="command" value="get_user_by_login" />
                User Login:
                <input type="text" name="login" size="10" />
                <input type="submit" class="b1" value="show user" /><br />
            </form>
            <br />
            <br />
            <form action="/bike/controller" method="get">
                <input type="hidden" name="command" value="Get_all_users" />
                <input type="submit" class="b1" value="get all users" />
                <br />
            </form>
            <h2>Actions with bikes</h2>
            <br />
            <a href="/bike/controller?command=add_bike" class="right">Add bike</a>
            <br /><br />
            <form action="/bike/controller" method="get">
                <input type="hidden" name="command" value="get_bike_by_id" />
                Bike ID:
                <input type="text" name="bikeId" size="10" />
                <input type="submit" class="b1" value="show bike" />
                <br />
            </form>

            <br />
            <form action="/bike/controller" method="post">
                <input type="hidden" name="command" value="delete_bike" />
                Bike ID:
                <input type="text" name="bikeId" size="10" />
                <input type="submit" class="b1" value="delete bike" />
                <br />
                <br />
            </form>
            <br />
            <form action="/bike/controller" method="get">
                <input type="hidden" name="command" value="get_all_bikes" />
                <input type="submit" class="b1" value="get all bikes" />
                <br />
                <br />
            </form>
            <br />
            <form action="/bike/controller" method="get">
                <input type="hidden" name="command" value="get_bikes_by_year_desc" />
                <input type="submit" class="b1" value="get new bikes" />
            </form>
            <br />
            <br />
             <h2>Actions with orders</h2>
            <br />
            <form action="/bike/controller" method="post">
                <input type="hidden" name="command" value="add_order" />
                <input type="submit" class="b1" value="add order" />
                <br />
            </form>
            <br />
            <form action="/bike/controller" method="get">
                <input type="hidden" name="command" value="get_order_by_id" />
                Order ID:
                <input type="text" name="orderId" size="10" />
                <input type="submit" class="b1" value="show order" />
                <br />
            </form>
            <br />
            <form action="/bike/controller" method="get">
                <input type="hidden" name="command" value="get_orders_by_user_id" />
                User ID:
                <input type="text" name="id_user" size="10" />
                <input type="submit" class="b1" value="show orders" />
                <br />
            </form>
            <br />
            <form action="/bike/controller" method="get">
                <input type="hidden" name="command" value="get_orders_by_bike_id" />
                Bike ID:
                <input type="text" name="bikeId" size="10" />
                <input type="submit" class="b1" value="show orders" />
                <br />
            </form>
            <br />
            <form action="/bike/controller" method="post">
                <input type="hidden" name="command" value="delete_order" />
                Order ID:
                <input type="text" name="orderId" size="10" />
                <input type="submit" class="b1" value="delete order" />
                <br />
                <br />
            </form>
            <br />
            <form action="/bike/controller" method="get">
                <input type="hidden" name="command" value="get_all_orders" />
                <input type="submit" class="b1" value="get all orders" />
                <br />
                <br />
            </form>
           </main>
        <footer>
            <div class="footer">
                <div class="footer_content">
                    <div class="footer_aboutProject">
                        <ul>
                            <li class="footer_item">
                                <a href="jsp/mainPageJsp/aboutProject.jsp"><small class="link">About project </small></a>
                            </li>
                            <li class="footer_item">
                                <a href="jsp/mainPageJsp/legal_information.jsp"><small class="link">Legal information </small></a>
                            </li>
                            <li class="footer_item">
                                <a href="jsp/mainPageJsp/copyright_holders.jsp"><small class="link">Сopyright holders</small></a>
                            </li>
                            <li class="footer_item">
                                <a href="jsp/mainPageJsp/collaboration.jsp"><small class="link">Collaboration with MyLibrary </small></a>
                            </li>
                        </ul>
                    </div>
                    <div class="footer_aboutSubscription">
                        <ul>
                            <li class="footer_item">
                                <a href="jsp/mainPageJsp/buySubscription.jsp"><small class="link"> Buy subscription </small></a>
                            </li>
                            <li class="footer_item">
                                <a href="jsp/mainPageJsp/how_to_pay.jsp"><small class="link">How to pay </small></a>
                            </li>
                            <li class="footer_item">
                                <a href="jsp/mainPageJsp/help.jsp"><small class="link">Help </small></a>
                            </li>
                        </ul>
                    </div>
                    <div class="support"></div>
                    Support service support@myBikeProject.com
                </div>
            </div>
        </footer>
    </body>
</html>
