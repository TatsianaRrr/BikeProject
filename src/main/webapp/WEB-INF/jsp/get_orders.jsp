<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8" charset="utf-8" />
        <title>Get orders</title>
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
                <a href="/bike/controller?command=show_admin_page" class="right">Admin page</a>
                <a href="/bike/controller?command=log_out" class="right">Log Out</a>
            </div>
            <table border="1">
                <br />
                <br />
                <h1>All orders list</h1>
                <caption></caption>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">bike_id</th>
                    <th scope="col">user_id</th>
                    <th scope="col">description</th>
                </tr>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <th scope="row">${order.id}</th>
                        <td>${order.bikeId}</td>
                        <td>${order.userId}</td>
                        <td>${order.description}</td>
                    </tr>
                </c:forEach>
            </table>
            <br />
            <br />
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
                                <a href="jsp/mainPageJsp/copyright_holders.jsp"><small class="link">??opyright holders</small></a>
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
