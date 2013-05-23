package servlets;

import beans.Customer;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import store.ShopInventory;
import store.ShopItem;

/**
 *
 * @author Chris Wallace <chris at devocean.com>
 */
public class AddToCart extends HttpServlet {

    private String redirectPage;
    private String badNumberPage;

    @Override
    public void init()
            throws javax.servlet.ServletException {
        redirectPage = getInitParameter("redirectPage");
        badNumberPage = getInitParameter("numberErrorPage");
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Enumeration<String> parameterNames = request.getParameterNames();
        
        int stockNumber;
        float quantity;
        
        HttpSession session = request.getSession();
        Customer customerBean = (Customer) session.getAttribute("customerBean");

        while (parameterNames.hasMoreElements()) {
            String nextElement = parameterNames.nextElement();

            try {
                stockNumber = Integer.parseInt(nextElement);

            } catch (NumberFormatException e) {
                //Element is actually the hidden form variable, so keep looking.
                continue;
            }

            try {
                quantity = Float.parseFloat(request.getParameter(nextElement));
                customerBean.addToCart(stockNumber, quantity);

            } catch (NumberFormatException nfe) {
                // To use a JSP page for errors, need to set these TWO attributes
                request.setAttribute("javax.servlet.jsp.jspException", nfe);
                request.setAttribute("javax.servlet.error.status_code", new Integer(0));

                ShopItem shopItem = ShopInventory.getShopItem(stockNumber);
                request.setAttribute("errorItem", shopItem.getName());
                RequestDispatcher rd = request.getRequestDispatcher(badNumberPage);
                rd.forward(request, response);
                return;
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher(redirectPage);
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Adds selected items to the customer's shopping cart.";
    }
}
