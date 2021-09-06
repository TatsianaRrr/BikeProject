<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8" charset="utf-8" />
        <title>Get bike</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <c:set var="userRole" value="${sessionScope.userRole}"/>
        <c:set var="admin" value="admin"/>
        <c:set var="user" value="user"/>
        <style>
            * {
                box-sizing: border-box;
            }
            .column {
                float: left;
                width: 33.33%;
                padding: 5px;
            }
            /* Clearfix (clear floats) */
            .row::after {
                content: "";
                clear: both;
                display: table;
            }
        </style>
            </head>
    <body>
        <div class="header">
            <h1>My Bike Service</h1>
            <p>
                Order bicycles in our rental office!
            </p>
        </div>
        <div class="navbar">
            <form id="get_all_bikes" action="/bike/controller" method="get">
                <input type="hidden" name="command" value="get_all_bikes" />
                <a href="/bike/controller?command=get_all_bikes">All bikes</a>
            </form>

            <form id="get_bikes_by_year_desc" action="/bike/controller" method="get">
                <input type="hidden" name="command" value="get_bikes_by_year_desc" />
                <a href="/bike/controller?command=get_bikes_by_year_desc">New bikes</a>
            </form>
            <a href="/bike/controller?command=log_out" class="right">Log Out</a>
            <a href="/bike/controller?command=show_user_page" class="right">Main page</a>
            <c:choose>
                           <c:when test="${userRole eq(admin)}">
                              <a href="/bike/controller?command=show_admin_page" class="right">Admin page</a>
                           </c:when>
                        </c:choose>
                   </div>
        <div class="row">
            <jsp:useBean id="bike" class="bean.Bike" scope="request" />
            <div class="side"></div>
            <div class="main">
                <h1>${bike.name}</h1>
                <img src="${pageContext.request.contextPath}/pics/bikes/${bike.image}" style="height: 300px; float: left; margin: 7px 7px 7px 0;" id="image_bike" />
                <p><span id="name">${name}Name: ${bike.name}</span></p>
                <p><span id="year">${year}Year: ${bike.year}</span></p>
                <p><span id="price">${price}Price: ${bike.price}</span></p>
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <p>${bike.description}</p>
                <br />
                <h2>
                    Related bikes
                    <p>We select bikes to your taste based on what you want</p>
                </h2>
                <h5>***</h5>
                <div class="row">
                    <div class="column">
                        <a href="/bike/controller?command=get_bike_by_id&bikeId=2"><img src="${pageContext.request.contextPath}/pics/bikes/image2.jpg" alt="image3" style="width: 100%;" /></a>
                    </div>
                    <div class="column">
                        <a href="/bike/controller?command=get_bike_by_id&bikeId=3"><img src="${pageContext.request.contextPath}/pics/bikes/image3.jpg" alt="image2" style="width: 100%;" /></a>
                    </div>
                    <div class="column">
                        <a href="/bike/controller?command=get_bike_by_id&bikeId=1"><img src="${pageContext.request.contextPath}/pics/bikes/image1.jpg" alt="image1" style="width: 100%;" /></a>
                    </div>
                </div>
            </div>
        </div>
                <div class="footer">
            <div class="footer_content">
                <div class="footer_aboutProject">
                    <li class="footer_item">
                        <a href="jsp/mainPageJsp/aboutProject.jsp"><small class="link">About project</small></a>
                    </li>
                </div>
                <div class="footer_aboutSubscription">
                    <li class="footer_item">
                        <a href="jsp/mainPageJsp/buySubscription.jsp"><small class="link"> Buy subscription</small></a>
                    </li>
                </div>
                <div class="support"></div>
                Support service support@myBikeProject.com
            </div>
        </div>
    </body>
</html>
