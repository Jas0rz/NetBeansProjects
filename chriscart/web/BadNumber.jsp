<%-- 
    Document   : BadNumber
    Created on : May 3, 2009, 6:42:01 PM
    Author     : dale

    An error page that displays the name of an item with an invalid quantity
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Store</title>
    </head>
    <body>
        <center>
        <h3>Error Entering a Quantity</h3>
        <h4>
        <%
          if( exception instanceof NumberFormatException ){
        %>
            Please press the back button and enter a valid
            number for <%= request.getAttribute("errorItem") %>
        <% } else { %>
            <%= exception %>
        <% } %>
        </h4>
        </center>
    </body>
</html>
