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
            <h2>What it is</h2>
            <h3>
                The "My bike" project provides an opportunity for everyone who has ever been interested in bicycles,
                rode them in childhood or dreamed of an "iron horse", to plunge into this world as a whole!
            </h3>
            <h3>
                By becoming our client, you can be sure:

                    We cooperate with renowned global brands and manufacturers who have proven themselves to be reliable and provide high quality products.
            </h3>
            <h2>How MyBike works</h2>
            <h3>
We always have a large assortment of bicycles in stock.
    In our rental locations you will find models that correspond to the latest technical developments and current trends.
    The company employs people who are passionate about their business, who will advise you and help you choose a bike that suits your requirements and riding style.
    We give a full guarantee of the quality of our services and goods.
            </h3>
            <h3>
 Here you will find everything to join and love the world of bicycles.
            </h3>
            <h3>
                The company "MyBike" respects and values its customers, therefore it always meets them halfway in everything.
                By developing our network, we contribute to the development of cycling and cycling culture in the Republic of Belarus.
            </h3>
            <h2>Company details</h2>
            <h3>Living Library LLC, OGRN 1117746445640, TIN 7734658075, KPP 770301001 Postal address: 123022, Minsk, st. Tolstogo, building 13, floor 7, room 15</h3>
            <div class="fakeimg" style="height: 60px;">
                <form class="back" action="/bike/controller" method="post">
                    <input type="hidden" name="command" value="show_user_page" />
                    <input type="submit" class="b1" value=" BACK " />
                    <br />
                </form>
            </div>
            <br />
        </main>
        <footer></footer>
    </body>
</html>

