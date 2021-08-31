<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%>
<sun-web-app>
 <locale-charset-info default-locale="">
 <locale-charset-map locale="" charset=""/>
 <parameter-encoding form-hint-field="<charset>"/>
 </locale-charset-info>
</sun-web-app>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8" charset="utf-8" />
        <title>Add bike</title>
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
            <h1>Add bike form</h1>
            <jsp:useBean id="bike" class="bean.Bike" scope="request" />
            <form class action="/bike/controller" method="post">
                <input type="hidden" name="command" value="add_bike" />
                Name: <input type="text" name="name" /><br />
                <br />
                Year: <input type="text" name="year" /><br />
                <br />
                Description: <input type="text" name="description" /><br />
                <br />
                Price: <input type="text" name="price" /><br />
                <br />
                Image: <input type="text" name="image" /><br />
                <br />
                Deleted: <input type="text" name="deleted" /><br />
                <br />
                Ordered: <input type="text" name="ordered" /><br />
                <br />

                <input type="submit" name="save" value="Save bike" />
            </form>
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
