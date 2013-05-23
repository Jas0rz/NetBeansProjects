package store;

/**
 * Particulars for an item in the Store
 *
 * @author dale
 */
public class ShopItem {
    public enum Unit {
        EACH, POUND, DOZEN
    };
    private int inventoryNumber;
    private String name;
    private float price;
    private Unit unit;

    public ShopItem(int inventoryNumber, String name, float price, Unit unit) {
        this.inventoryNumber = inventoryNumber;
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    /**
     * @return the inventoryNumber
     */
    public int getInventoryNumber() {
        return inventoryNumber;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return the unit
     */
    public Unit getUnit() {
        return unit;
    }
}