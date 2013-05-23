package store;

import java.text.DecimalFormat;
import java.util.HashMap;
import store.ShopItem.Unit;

/**
 * This bean handles the items that are sold in the Store.
 * It also builds the rows of items and prices for Shopping.jsp
 * 
 * @author Dale Shpak
 */
public class ShopInventory {
    public static final DecimalFormat decimalFormat = new DecimalFormat("###0.00");
    /** The names and prices of the groceries.
     * This would normally be in a database */
    private static ShopItem[] groceries = new ShopItem[] {
        new ShopItem(127, "Cabbage",  1.25f, Unit.POUND),
        new ShopItem(855, "Potato",   0.20f, Unit.POUND),
        new ShopItem(287, "Lettuce",  2.99f, Unit.EACH),
        new ShopItem(117, "Broccoli", 1.59f, Unit.POUND),
        new ShopItem(435, "Garlic",   4.40f, Unit.POUND),
        new ShopItem(666, "Tomacco",  8.25f, Unit.EACH),
        new ShopItem(101, "Eggs",     3.75f, Unit.DOZEN)
    };
    /** Put the GroceryItems into a HashMap so that we can get them
     *  quickly by inventory number */
    private static HashMap<Integer,ShopItem> inventoryTable;

    /**
     * This static constructor creates and fills the inventoryTable.
     * The table's unique key is the inventory number
     */
    static {
        inventoryTable = new HashMap<Integer,ShopItem>();
        for (ShopItem item : groceries) {
            inventoryTable.put(item.getInventoryNumber(), item);
        }
    }
    
    /**
     * Gets the number of items in the list.
     * This is used when do determine when we have finished the list of items
     * @returns The number of items
     */
    public static int getNumberOfItems () {
        System.out.println ("NumberOfItems=" + groceries.length);
        return groceries.length;
    }    
	
    /**
     * Generates the HTML for a page of items
     * @returns A String containing all of the necessary HTML
     */
    public static String getNextItems (int currentItemNumber, int itemsPerPage) {
        StringBuffer sb = new StringBuffer(1024);
        sb.append("<table>");
        sb.append("<tr><th>Quantity</th><th>Item</th><th>Price</th><th>Unit</th></tr>\r\n");
        for (int items = 0; items < itemsPerPage && currentItemNumber < groceries.length; items++,currentItemNumber++) {
            ShopItem item = groceries[currentItemNumber];
            sb.append("<tr><td><INPUT TYPE = \"text\" NAME=\"" + item.getInventoryNumber() +"\" VALUE=\"0\" SIZE=3></td> ");
            sb.append("<td>" + item.getName() + "</td><td>" +
                    decimalFormat.format(item.getPrice()) + "</td><td>" + item.getUnit() + "</td></tr>\r\n");
        }
        sb.append("</table>");
        sb.append("<input type=\"hidden\" name=\"itemNumber\" value=\"" + currentItemNumber + "\" >\r\n");

        return sb.toString();
    }

    public static ShopItem getShopItem(int inventoryNumber) {
        return inventoryTable.get(inventoryNumber);
    }
}