<%@page import="store.ShopInventory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! int currentItemNumber;
    Integer itemsPerPage;
    int nextPage;%>
<% itemsPerPage = (Integer) session.getAttribute("itemsPerPage");%>
<% currentItemNumber = Integer.parseInt(request.getParameter("itemNumber"));%>
<jsp:useBean id="customerBean" scope="session" class="beans.Customer"/>
<% if (currentItemNumber < ShopInventory.getNumberOfItems()) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping</title>
    </head>	
    <body>
        <form action="<%= config.getInitParameter("nextPage")%>" method="POST">
            <%= ShopInventory.getNextItems(currentItemNumber, itemsPerPage)%>
            <input type="submit" value="Next" ><br>
        </form>
    </body>
</html>
<%
    } else {
// When we have gone through all of the items, check out
        String checkOutPage = config.getInitParameter("checkOutPage");
// Redirect, rather than forward so that the back button works
        response.sendRedirect(checkOutPage);
    }
%>