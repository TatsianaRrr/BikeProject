<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8" charset="utf-8" />
        <title>Get users</title>
    </head>
    <body>
        <header>
            <div class="header">
                <h1>My Bike Service</h1>
                <p>
                    Order bicycles in our rental office!
                </p>
            </div>
            <h1>All users list</h1>
        </header>
        <main>
            <div class="navbar">
                <a href="/bike/controller?command=show_admin_page" class="right">Admin page</a>
               <a href="/bike/controller?command=log_out" class="right">Log Out</a>
            </div>
            <table border="1">
                <br />
                <br />
                <caption>
                    USER LIST :
                </caption>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">login</th>
                    <th scope="col">password</th>
                    <th scope="col">name</th>
                    <th scope="col">email</th>
                    <th scope="col">userRole</th>
                </tr>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <th scope="row">${user.id}</th>
                        <td>${user.login}</td>
                        <td>${user.password}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.userRole}</td>
                    </tr>
                </c:forEach>
            </table>
            <br />
            <br />
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
