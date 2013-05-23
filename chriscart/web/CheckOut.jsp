<%-- 
    Document   : CheckOut
    Created on : 28-Apr-2013, 4:55:27 PM
    Author     : Chris Wallace <chris at devocean.com>
--%>

<%@page import="store.ShopItem"%>
<%@page import="store.ShopInventory"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="beans.Customer"></jsp:useBean>
<% Map<Integer, Float> cartItems = customerBean.getCartItems();%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out</title>
    </head>
    <body>
        <h1>Check Out</h1>
        <h2> Thanks for Shopping, <%= customerBean.getName()%>. </h2>
        <table border="1">
            <th>Quantity</th><th>Item</th><th>Price</th><th>Total</th>
            <%= doCheckout(cartItems) %>
            Your order will be shipped to <%= customerBean.getAddress() %>.
        </table> 
    </body>
</html>

<%! private String doCheckout(Map<Integer, Float> items) {
    
        float finalTotal = 0;

        StringBuffer sb = new StringBuffer(1024);
        Iterator it = items.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry<Integer, Float> currrentEntry = (Map.Entry) it.next();
            
            Integer itemNumber = currrentEntry.getKey();
            Float quantity = currrentEntry.getValue();

            ShopItem currentItem = ShopInventory.getShopItem(itemNumber);
            Float unitPrice = currentItem.getPrice();

            sb.append("<tr><td>" + quantity.toString() + "</td><td>" 
                    + itemNumber.toString() + "</td><td>" 
                    + unitPrice.toString() + "</td><td>" 
                    + String.valueOf(unitPrice * quantity) + "</td><tr>");
            
            finalTotal += unitPrice * quantity;
        }
        
        sb.append("<tr><td>Grand Total: " + 
                String.valueOf(finalTotal) +"</td></tr>");
        
        return sb.toString();
    }
%>
