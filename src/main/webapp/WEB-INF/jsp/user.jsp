<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>


<html>
   <head>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" type="text/css" />
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8" charset="utf-8" />
      <title>Add bike</title>
      <c:set var="userRole" value="${sessionScope.userRole}"/>
      <c:set var="admin" value="admin"/>
      <c:set var="user" value="user"/>
      <c:set value="alert" var="alert"/>
      <c:set var="command" value="${command}"/>
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
               <c:when test="${userRole eq(user)}">
                  <form id="get_all_bikes" action="/bike/controller" method="post">
                     <input type="hidden" name="command" value="show_user_account" />
                     <a href="/bike/controller?command=show_user_account">User account</a>
                  </form>
               </c:when>
            </c:choose>
            <a href="/bike/controller?command=get_all_bikes">All bikes</a>
            <a href="/bike/controller?command=get_bikes_by_year_desc">New bikes</a>
            <c:choose>
               <c:when test="${userRole eq(admin)}">
                  <a href="/bike/controller?command=show_admin_page" class="right">Admin page</a>
               </c:when>
            </c:choose>
            <a href="/bike/controller?command=log_out" class="right">Log Out</a>
         </div>
         <div class="row">
            <div class="side">
               <h2>MyBike - the best bikes available to you!</h2>
               <img src="${pageContext.request.contextPath}/pics/bikes/image3.jpg" alt="image3" style="height: 180px;">
               <p>City and sports bikes just for you!</p>
               <h3>All types:</h3>
               <p>News and bestsellers</p>
               <img src="${pageContext.request.contextPath}/pics/bikes/image2.jpg" alt="image2" style="height: 180px;">
               <br/>
               <p>Children and parents.</p>
               <p>The best bikes for children of all ages and their parents.</p>
               <img src="${pageContext.request.contextPath}/pics/bikes/image1.jpg" alt="image1" style="height: 100px;">
               <br/>
               <p>Rent a bike and get free minutes!</p>
               <p>Bicycles of all types of famous world manufacturers: city, sports, adults, children, scooters.</p>
               <img src="${pageContext.request.contextPath}/pics/bikes/image3.jpg" alt="image3" style="height: 180px;">
            </div>
            <div class="main">
               <h2>
                  All bikes are available at the same time.
               </h2>
               <h5>***</h5>
               <div class="row">
                  <div class="column">
                     <a href="/bike/controller?command=get_bike_by_id&bikeId=2"><img
                        src="${pageContext.request.contextPath}/pics/bikes/image2.jpg" alt="image2"
                        style="width: 100%;"/></a>
                  </div>
                  <div class="column">
                     <a href="/bike/controller?command=get_bike_by_id&bikeId=3"><img
                        src="${pageContext.request.contextPath}/pics/bikes/image3.jpg" alt="image3"
                        style="width: 100%;"/></a>
                  </div>
                  <div class="column">
                     <a href="/bike/controller?command=get_bike_by_id&bikeId=1"><img
                        src="${pageContext.request.contextPath}/pics/bikes/image1.jpg"
                        alt="image1" style="width: 100%;"/></a>
                  </div>
               </div>
               <p>Use a subscription - an unlimited subscription to the entire catalog.</p>
               <br/>
               <h2>Personal recommendations.</h2>
               <h5>***</h5>
               <div class="row">
                  <div class="column">
                     <a href="/bike/controller?command=get_bike_by_id&bikeId=1"><img
                        src="${pageContext.request.contextPath}/pics/bikes/image1.jpg" alt="image1"
                        style="width: 100%;"/></a>
                  </div>
                  <div class="column">
                     <a href="/bike/controller?command=get_bike_by_id&bikeId=3"><img
                        src="${pageContext.request.contextPath}/pics/bikes/image3.jpg" alt="image3"
                        style="width: 100%;"/></a>
                  </div>
                  <div class="column">
                     <a href="/bike/controller?command=get_bike_by_id&bikeId=2"><img
                        src="${pageContext.request.contextPath}/pics/bikes/image2.jpg"
                        alt="image2" style="width: 100%;"/></a>
                  </div>
               </div>
               <p>We select bikes to your taste based on what you selest!</p>
            </div>
         </div>
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
                        <a href="jsp/mainPageJsp/legal_information.jsp"><small class="link">Legal
                        information </small></a>
                     </li>
                     <li class="footer_item">
                        <a href="jsp/mainPageJsp/copyright_holders.jsp"><small class="link">Сopyright
                        holders</small></a>
                     </li>
                     <li class="footer_item">
                        <a href="jsp/mainPageJsp/collaboration.jsp"><small class="link">Collaboration with
                        MyLibrary </small></a>
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

