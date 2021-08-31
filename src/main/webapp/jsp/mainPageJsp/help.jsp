<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" type="text/css" />
        <title>About project</title>
    </head>
    <body>
        <div class="header">
            <h1>My Bike Service</h1>
            <p>
                Order bicycles in our rental office!
            </p>
        </div>
        <h3>Information about project MyBike</h3>
        <hr />
        ***
        <hr />
        <main>
            <h1>About MyBike Rental Club</h1>
            <h2></h2>
            <h3>                  </h3>
            <h3>                </h3>
            <h2></h2>
            <h3>                </h3>
            <h3>                 </h3>
            <h3>  </h3>
            <h2></h2>
            <h3></h3>
            <div class="fakeimg" style="height: 60px;">
                <form class="back" action="/bike/controller" method="post">
                    <input type="hidden" name="command" value="show_main_page" />
                    <input type="submit" class="b1" value=" back " />
                    <br />
                </form>
            </div>
            <br />
        </main>
        <footer></footer>
    </body>
</html>

