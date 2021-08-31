<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8" charset="utf-8"/>
    <title>Main page</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <style type="text/css">
            #popupbox {
                margin: 0;
                margin-left: 40%;
                margin-right: 40%;
                margin-top: 10px;
                padding-top: 10px;
                width: 40%;
                height: 200px;
                position: absolute;
                background: #fbfbf0;
                border: solid #000000 2px;
                z-index: 9;
                font-family: arial;
                visibility: hidden;
            }

    </style>
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
        <h1>My Bike Service</h1>
        <p>
            Order bicycles in our rental office!
        </p>
    </div>
</header>
<main>
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
            <br/>
            <center><a href="javascript:login('hide');">Close</a></center>
        </div>

        <p><a href="javascript:login('show');" class="right">Sign up</a></p>
        <div>
            <form id="sign_in" style="float: right;" class="right" action="/bike/controller" method="post">
                <input type="hidden" name="command" value="sign_in"/>
                Login: <input type="text" placeholder="login..." name="login"/> Password: <input type="password"
                                                                                                 placeholder="password..."
                                                                                                 name="password"/>
                <a href="" class="right" onClick="document.getElementById('sign_in').submit(); return false;">Sign
                    in</a>
            </form>
            <a class="error_message">${requestScope.information}</a>
        </div>
    </div>
    <div class="row">
        <div class="side">
            <h2>MyBike - the best bike models for all ages just for you!</h2>

            <img src="${pageContext.request.contextPath}/pics/bikes/image3.jpg" alt="image3" style="height: 180px;">

            <p>Classic and time-tested bestsellers always free</p>
            <h3>All types of bikes:</h3>
            <p>The best new models!</p>
            <img src="${pageContext.request.contextPath}/pics/bikes/image2.jpg" alt="image2" style="height: 180px;">
            <br/>
            <p>Children and parents</p>
            <p>The best bikes all types for children of all ages and their parents!</p>
            <img src="${pageContext.request.contextPath}/pics/bikes/image1.jpg" alt="image1" style="height: 100px;">
            <br/>
            <p>City and sports bikes</p>
            <p>Our bikes give you a great way to get to work or office at no extra cost!</p>
               <img src="${pageContext.request.contextPath}/pics/bikes/image3.jpg" alt="img3" style="height: 180px;">
                </div>
        <div class="main">
            <h2>
                All bikes are available at the same time.
            </h2>
            <h5>***</h5>
            <div class="row">
                <div class="column">
                    <a href="/bike/controller?command=get_bike_by_id&bikeId=2"><img
                            src="${pageContext.request.contextPath}/pics/bikes/image1.jpg" alt="image1"
                            style="width: 100%;"/></a>
                </div>
                <div class="column">
                    <a href="/bike/controller?command=get_bike_by_id&bikeId=3"><img
                            src="${pageContext.request.contextPath}/pics/bikes/image2.jpg" alt="image2"
                            style="width: 100%;"/></a>
                </div>
                <div class="column">
                    <a href="/bike/controller?command=get_bike_by_id&bikeId=1"><img
                            src="${pageContext.request.contextPath}/pics/bikes/image3.jpg"
                            alt="image3" style="width: 100%;"/></a>
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
                                <a href="/bike/controller?command=get_bike_by_id&bikeId=2"><img
                                        src="${pageContext.request.contextPath}/pics/bikes/image2.jpg" alt="image2"
                                        style="width: 100%;"/></a>
                            </div>
                            <div class="column">
                                <a href="/bike/controller?command=get_bike_by_id&bikeId=3"><img
                                        src="${pageContext.request.contextPath}/pics/bikes/image3.jpg"
                                        alt="image3" style="width: 100%;"/></a>
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
