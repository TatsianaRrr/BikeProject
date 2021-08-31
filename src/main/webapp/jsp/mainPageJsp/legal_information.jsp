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
             <h2>
                 Agreement (license agreement) - public offer
             </h2>
             <h3>
                 Living Library LLC, represented by General Director Anuryev Sergey Valerievich, acting on the basis of the Charter, hereinafter referred to as the “Copyright Holder” or “Seller”, on the one hand, and the Internet user,
                 hereinafter referred to as the “User” or “Subscriber”; The "Client", on the other hand, collectively referred to as the "Parties", have entered into this license agreement (hereinafter - the "Agreement") The agreement, in
                 accordance with Article 435 and part 2 of Article 437 of the Civil Code of the Republic of Belarus, is a public offer (offer) to an unlimited number of persons, Internet users.
             </h3>
             <h2>
                 1. Terms used in the contract
             </h2>
             <h3>
                 1.1. Subscriber - an Internet user who has accepted the terms of the Agreement, registered on the Seller’s Website and made an advance payment for using the Database. 1.2. Works - texts or audio recordings of literary works,
                 the rights of use of which by appropriate methods (providing for the possibility of placing them in the Catalog) belong to the Seller, submitted in electronic form in various formats as part of the Database (Catalog) posted
                 on the Seller’s Website. 1.3. Database (Catalog) - Database of literary works “FBHub” (Certificate of State Registration in the Register of Databases of the Federal Service for Intellectual Property No. 2018670018,
                 2018621154) - an information resource representing a collection of Works and providing the Subscriber with the opportunity to search for Works by the specified parameters and their ranking - for the purpose of
                 familiarization and selection, including the possibility of gaining access to the full content of the work in electronic text or audio format. The exclusive right to use the Database belongs to the Copyright Holder. 1.4.
                 Login and Password are two unique character sets that identify the Subscriber, allowing the Subscriber to access the Content. 1.5. Seller’s website is an information resource on the Internet belonging to the Seller located
                 at https://mybike.ru/ and the mobile application for the IOS or Android platform belonging to the Seller or its partners. 1.6. Using the Database (Subscription) - the Subscriber selects and opens the Works (for viewing,
                 reading, viewing, listening) through a browser or mobile application. 1.7. Billing is a payment accounting system. 1.8. Account - User data stored on the servers of the Seller’s Site.
             </h3>
             <h2>2. Subject of the contract. Intellectual Property Terms.</h2>
             <h3>
                 2.1. The Seller provides the User with the right to use the Database for free on the terms of a simple (non-exclusive) license within the limits established by the Agreement (to open the Works presented in the Catalog
                 through a browser or mobile application). 2.2. The contract establishes the conditions for using the Database. Use of the Database is permitted only on the terms of the Agreement. If the User does not accept the terms of the
                 Agreement in full, the User has no right to use the Database. 2.3. The exclusive right to use the Database belongs to the Copyright Holder. 2.4. The agreement does not provide for the transfer of rights to the Database. The
                 agreement provides the User with a limited right to use it. 2.5. The right to use the Database is granted only to the User. 2.6. The User can use the Works only in the ways specified in the Agreement
             </h3>
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
