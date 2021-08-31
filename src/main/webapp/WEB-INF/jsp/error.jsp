<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8" charset="utf-8">
        <title>Error!</title>
    </head>
    <body>
        <br />
        <h3>Error page! </h3>
        <h4><c:out value="${requestScope.information}" /></h4>
                <footer></footer>
    </body>
</html>
