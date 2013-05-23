<%-- 
    Document   : GetLoginData
    Created on : May 3, 2009, 5:15:16 PM
    Author     : dale

    Gets the user's sign-in info
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%-- This bean will hold the shopping cart, etc.
     It will be created if it doesn't yet exist.
     Because it has session scope, it will be attached to the session object --%>
<jsp:useBean id="customerBean" scope="session" class="beans.Customer"></jsp:useBean>

<%-- Get the properties that were sent to this page and
     call all of the bean's matching setters --%>
<jsp:setProperty name="customerBean" property="*" />

<jsp:forward page="<%= config.getInitParameter("nextPage")%>" />