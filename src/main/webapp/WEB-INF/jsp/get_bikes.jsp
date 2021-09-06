<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8" charset="utf-8" />
        <title>Get bikes</title>
        <c:set var="userRole" value="${sessionScope.userRole}"/>
        <c:set var="admin" value="admin"/>
        <c:set var="user" value="user"/>
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
<c:choose>
               <c:when test="${userRole eq(admin)}">
               <a href="/bike/controller?command=show_admin_page" class="right">Admin page</a>
               </c:when>
               </c:choose>
               <a href="/bike/controller?command=show_user_page" class="right">Main page</a>
               <a href="/bike/controller?command=log_out" class="right">Log Out</a>
            </div>
            <table border="1">
                <br />
                <br />
                <h1>All bikes list</h1>
                <caption>
                    BIKE LIST :
                </caption>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">name</th>
                    <th scope="col">year</th>
                    <th scope="col">description</th>
                    <th scope="col">price</th>
                    <th scope="col">image</th>
                    <th scope="col">deleted</th>
                    <th scope="col">ordered</th>
                </tr>
                <c:forEach var="bike" items="${bikeList}">
                    <tr>
                        <th scope="row">${bike.id}</th>
                        <td>${bike.name}</td>
                        <td>${bike.year}</td>
                        <td>${bike.description}</td>
                        <td>${bike.price}</td>
                        <td>${bike.image}</td>
                        <td>${bike.deleted}</td>
                        <td>${bike.ordered}</td>
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
