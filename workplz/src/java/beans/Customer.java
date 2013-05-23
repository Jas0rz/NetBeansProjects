package beans;

import java.util.HashMap;

/**
 * Keeps track of the information for this customer, including
 * name, address, and shopping cart.
 *
 * @author dale
 */
public class Customer {
    /** Customer name */
    private String name;
    /** Customer address */
    private String address;
    /** Use a hash table so that changes in quantities will replace old quantities */
    private HashMap<Integer,Float> cartItems;

    /**
     * Constructor creates the data structure
     */
    public Customer() {
        cartItems  = new HashMap<Integer,Float>();
    }

    /**
     * Add the item to the cart if the quantity is >0,
     * otherwise, remove it if is was in the cart.
     *
     * @param inventoryCode The stock code
     * @param quantity The quantity purchased
     */
    public void addToCart(int inventoryCode, float quantity) {
        if (quantity > 0.0f) {
            cartItems.put(inventoryCode, quantity);
        } else {
            // Since we allow using the back button,
            // get rid of anything we had that was changed to zero
            cartItems.remove(inventoryCode);
        }
    }

    /**
     * Return the shopping cart hashtable so that the
     * purchase can be processed
     *
     * @return The shopping cart hashtable
     */
    public HashMap<Integer,Float> getCartItems() {
        return cartItems;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

}