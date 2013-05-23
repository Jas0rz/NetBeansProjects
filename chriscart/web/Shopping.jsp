<%-- 
    Document   : Shopping
    Created on : 28-Apr-2013, 4:55:05 PM
    Author     : Chris Wallace <chris at devocean.com>
--%>
<%@page import="store.ShopInventory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="beans.Customer"></jsp:useBean>
<%! private int currentItemNumber;
    private int itemsPerPage;%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping</title>
    </head>
    <body>
        <h1>Hello <%= customerBean.getName()%></h1>

        <% try {
                currentItemNumber = Integer.parseInt(request.getParameter("itemNumber"));
                itemsPerPage = Integer.parseInt(session.getAttribute("itemsPerPage").toString());
            } catch (NumberFormatException e) {
        %>       <%= e.toString()%> <%
            }

            if (currentItemNumber < ShopInventory.getNumberOfItems()) {
        %>

        <form action="<%= config.getInitParameter("nextPage")%>" method="POST">
            <%= ShopInventory.getNextItems(currentItemNumber, itemsPerPage)%>
            <input type="submit" value="Next" ><br>
        </form>

        <%
            } else {
                // When we have gone through all of the items, check out
                String checkOutPage = config.getInitParameter("checkOutPage");
                // Redirect, rather than forward so that the back button works
                response.sendRedirect(checkOutPage);
            }
        %>
    </body>
</html>
