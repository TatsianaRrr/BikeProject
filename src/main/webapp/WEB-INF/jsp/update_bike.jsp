<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8" charset="utf-8" />
        <title>Bike update</title>
        <script></script>
    </head>
    <body>
        <h1>Update bike with ID ${bike.id}</h1>
        <jsp:useBean id="bikeId" class="bean.Bike" scope="request" />
        <form action="/bike/controller" method="post">
            <input type="hidden" name="command" value="update_bike" />
            <input type="hidden" name="bikeId" value="${bike.id}" />
            Name:<input type="text" name="name" value="${bike.name}" /><br />
            Year:<input type="text" name="year" value="${bike.year}" /><br />
            Description:<input type="text" name="description" value="${bike.description}" /><br />
            Price:<input type="text" name="price" value="${bike.price}" /><br />
            Image:<input type="text" name="image" value="${book.image}" />
            <br />Deleted:<input type="text" name="deleted" value="${bike.deleted}" /><br />
            <br />Ordered:<input type="text" name="ordered" value="${bike.ordered}" /><br />
            <br />
            <input type="submit" name="save" value="Save bike data" />
        </form>
        <br />
        <form action="/bike/controller" method="post">
            <input type="hidden" name="command" value="show_main_page" />
            <input type="submit" class="b1" value="log out " />
            <br />
        </form>
        <br />
        <form action="/bike/controller" method="post">
            <input type="hidden" name="command" value="show_admin_page" />
            <input type="submit" class="b1" value="go to admin page " />
            <br />
        </form>
        <footer>
            ***
        </footer>
    </body>
</html>
