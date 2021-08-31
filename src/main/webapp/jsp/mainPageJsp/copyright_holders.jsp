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
            <h3>   Belarusian cyclists will receive support at the republican level. Now this has been documented: a nationally significant Concept of Cycling has been approved in Belarus.
                   Work on the document was carried out in two main areas: monitoring safety indicators and tracking data on the share of cycling use.
                   According to the project, by 2030 the percentage of used bicycles in the republic will be greatly increased:

                       in cities with a population of more than 50,000, the figure will rise to 10%;
                       in cities and towns with a population of less than 50,000 inhabitants - up to 20%;
                       in villages, villages and agricultural cities - up to 40% and more.

                   According to Pavel Gorbunov, who heads the board of the Minsk Cycling Society, the 10% proposed for Minsk would amount to approximately 700,000 multi-purpose rides made every day during the cycle season. But this figure does not seem unrealistic. Thus, residents of Warsaw already use bicycles by 4%, and Berliners - by 13-15%. On average, in cities in the European Union 12 years from now, the indicator is likely to be 12%. The share of internal displacements in the Belarusian capital is gradually growing: at present, it has already exceeded the one percent barrier.               </h3>
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

