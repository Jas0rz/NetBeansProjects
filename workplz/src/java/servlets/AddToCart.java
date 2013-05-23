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
 * @author Jason McConnell
 */
public class AddToCart extends HttpServlet {

    private String redirectPage;
    private String badNumPage;

    /**
     * initiates servlet.
     *     
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    public void init() throws ServletException {
        redirectPage = getInitParameter("redirectPage");
        badNumPage = getInitParameter("numberErrorPage");
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
        
        Enumeration<String> allParams = request.getParameterNames();
        
        HttpSession session = request.getSession();
        Customer customerBean = (Customer) session.getAttribute("customerBean");
        
        int item;
        float amount;
        
        while (allParams.hasMoreElements()) {
            String next = allParams.nextElement();
            
            try {
                item = Integer.parseInt(next);
            } catch (NumberFormatException e) {
                //Allows code to skip over hidden variables.
                continue;     
            }
            
            try {
                amount = Float.parseFloat(request.getParameter(next));
                if (amount < 0) {
                    throw new NumberFormatException();
                }
                customerBean.addToCart(item, amount);
            } catch (NumberFormatException nfe) {
                // To use a JSP page for errors, need to set these TWO attributes
                request.setAttribute("javax.servlet.jsp.jspException", nfe);
                request.setAttribute("javax.servlet.error.status_code", new Integer(0));

                ShopItem shopItem = ShopInventory.getShopItem(item);
                request.setAttribute("errorItem", shopItem.getName());
                RequestDispatcher rd = request.getRequestDispatcher(badNumPage);
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
        return "handles adding items to customers shopping cart, and error checking.";
    }
}