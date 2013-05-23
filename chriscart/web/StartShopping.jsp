<%-- 
    Document   : StartShopping
    Created on : Apr 23, 2009, 8:42:16 PM
    Author     : dale

    Welcome page for the Store
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="store.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Let's Go Shopping with JSP</title>
    </head>
    <body BGCOLOR="#FFFFFF" TEXT="#000000">
        <font FACE=VERDANA></font>
        <center>
            <H2>Welcome to the Store</H2>
            <h3>Please press the button to enter the store</h3>

            <%-- 
            ShopInventory has a static constructor and static methods
            so that it can be easily used by ALL clients.  It will be
            instantiated the first time that it is used! 
            --%>

            <%
                // We use multiple pages for displaying the items, 
                // Each customer has their own session object
                session.setAttribute("itemsPerPage", new Integer(4));
            %>

            <form action="<%= config.getInitParameter("nextPage")%>" method="POST">
                <table border="0">
                    <tr>
                        <td>Your Name:</td>
                        <td><input type="text" name="name" value="Your Name"size="40" maxlength="100"></td>
                    </tr>
                    <tr>
                        <td>Address:</td>
                        <td><input type="text" name="address" value="Your Address" size="40" maxlength="100"></td>
                    </tr>
                </table>
                <input type="hidden" name="itemNumber" value="0" >
                <input type="submit" value="Start Shopping" name="go" >
            </form>
        </center>
    </body>
</html>
