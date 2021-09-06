<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set value="alert" var="alert"/>
<c:set var="command" value="${command}"/>
<!DOCTYPE html>


<html>
   <head>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" type="text/css"/>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8" charset="utf-8"/>
      <title>Main page</title>
      <fmt:setLocale value="${sessionScope.local}"/>
      <fmt:setBundle basename="localization.local" var="loc"/>
      <fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
      <fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
      <fmt:message bundle="${loc}" key="local.message" var="message"/>
      <fmt:message bundle="${loc}" key="local.message.caption" var="message_caption"/>
      <fmt:message bundle="${loc}" key="local.message.text1" var="text1"/>
      <fmt:message bundle="${loc}" key="local.message.text2" var="text2"/>
      <fmt:message bundle="${loc}" key="local.word.sign_in" var="word_sign_in"/>
      <fmt:message bundle="${loc}" key="local.word.sign_up" var="word_sign_up"/>
      <fmt:message bundle="${loc}" key="local.word.sign_in_on_website" var="sign_in_on_website"/>
      <fmt:message bundle="${loc}" key="local.word.sign_up_on_website" var="sign_up_on_website"/>
      <meta charset="UTF-8"/>
      <meta name="viewport" content="width=device-width, initial-scale=1"/>
      <script language="JavaScript" type="text/javascript">
         function login(showhide) {
             if (showhide == "show") {
                 document.getElementById("popupbox").style.visibility = "visible";
             } else if (showhide == "hide") {
                 document.getElementById("popupbox").style.visibility = "hidden";
             }
         }
      </script>
   </head>
   <body>
      <header>
         <div class="header">
            <h1>
               <c:out value="${message}"/>
            </h1>
            <p>
               <c:out value="${message_caption}"/>
            </p>
            <div class="container-buttons">
               <form action="/bike/controller" method="post">
                  <input type="hidden" name="command" value="change_locale"/>
                  <input type="hidden" name="local" value="ru"/>
                  <input type="submit" value="${ru_button}"/>
               </form>
               <form action="/bike/controller" method="post">
                  <input type="hidden" name="command" value="change_locale"/>
                  <input type="hidden" name="local" value="en"/>
                  <input type="submit" value="${en_button}"/>
               </form>
            </div>
         </div>
      </header>
      <main>
         <div id="main">
            <div class="navbar">
               <div id="popupbox">
                  <form class action="/bike/controller" method="post">
                     <input type="hidden" name="command" value="sign_up"/>
                     <label for="login"><b>Login</b></label>
                     <input type="text" placeholder="Enter login" name="login" size="10" required/>
                     <br/>
                     <label for="password"><b>Password</b></label>
                     <input type="password" placeholder="Enter password" name="password" size="10" required/>
                     <br/>
                     <label for="name"><b>Name</b></label>
                     <input type="text" placeholder="Enter name" name="name" size="10" required/>
                     <br/>
                     <label for="email"><b>Email</b></label>
                     <input type="text" placeholder="Enter email" name="email" size="10" required/>
                     <br/>
                     <br/>
                     <center><input type="submit" name="submit" value=" Sign Up "/></center>
                  </form>
                  <p class="error_message">${requestScope.information}</p>
                  <br/>
                  <center><a href="javascript:login('hide');" > Close </a></center>
               </div>
               <p><a href="javascript:login('show');" class="right" > ${word_sign_up} </a></p>
               <div>
                  <form id="sign_in" style="float: right;" class="right" action="/bike/controller" method="post">
                     <input type="hidden" name="command" value="sign_in"/>
                     Login: <input type="text" placeholder="login..." name="login"/> Password: <input type="password"
                        placeholder="password..."
                        name="password"/>
                     <a href="" class="right" onClick="document.getElementById('sign_in').submit(); return false;"> ${word_sign_in} </a>
                  </form>
                  <a class="error_message">${requestScope.information}</a>
               </div>
            </div>
         </div>
         <div class="row">
            <div class="side">
               <h2>
                  <c:out value="${text1}"/>
               </h2>
               <img src="${pageContext.request.contextPath}/pics/bikes/image3.jpg" alt="image3" style="height: 180px;">
               <p>Classic and time-tested bestsellers always free</p>
               <h3>All types of bikes:</h3>
               <p>The best new models!</p>
               <img src="${pageContext.request.contextPath}/pics/bikes/image2.jpg" alt="image2" style="height: 180px;">
               <br/>
               <p>Children and parents</p>
               <p>The best bikes all types for children of all ages and their parents!</p>
               <img src="${pageContext.request.contextPath}/pics/bikes/image1.jpg" alt="image1" style="height: 180px;">
               <br/>
               <p>City and sports bikes</p>
               <p>Our bikes give you a great way to get to work or office at no extra cost!</p>
               <img src="${pageContext.request.contextPath}/pics/bikes/image3.jpg" alt="img3" style="height: 180px;">
            </div>
            <div class="main">
               <h2>
                  <c:out value="${text2}"/>
               </h2>
               <h5>***</h5>
               <div class="row">
                  <div class="column">
                     <img src="${pageContext.request.contextPath}/pics/bikes/image1.jpg" alt="image1"
                        style="width: 100%;"/>
                  </div>
                  <div class="column">
                     <img src="${pageContext.request.contextPath}/pics/bikes/image2.jpg" alt="image2"
                        style="width: 100%;"/>
                  </div>
                  <div class="column">
                     <img src="${pageContext.request.contextPath}/pics/bikes/image3.jpg"
                        alt="image3" style="width: 100%;"/>
                  </div>
               </div>
               <p>Use a subscription - an unlimited subscription to the entire catalog.</p>
               <br/>
               <h2>Personal recommendations.</h2>
               <h5>***</h5>
               <div class="row">
                  <div class="column">
                     <img src="${pageContext.request.contextPath}/pics/bikes/image1.jpg" alt="image1"
                        style="width: 100%;"/>
                  </div>
                  <div class="column">
                     <img src="${pageContext.request.contextPath}/pics/bikes/image2.jpg" alt="image2"
                        style="width: 100%;"/>
                  </div>
                  <div class="column">
                     <img src="${pageContext.request.contextPath}/pics/bikes/image3.jpg"
                        alt="image3" style="width: 100%;"/>
                  </div>
               </div>
               <p>We select bikes to your taste based on what you wishes!</p>
            </div>
         </div>
      </main>
      <footer>
         <div class="footer">
            <div class="footer_content">
               <div class="footer_aboutProject">
               </div>
               <div class="support"></div>
               Support service support@myBikeProject.com
            </div>
         </div>
      </footer>
   </body>
</html>

