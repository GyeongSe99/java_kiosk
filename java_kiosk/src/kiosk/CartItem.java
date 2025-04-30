package kiosk;

/**
 * Cart에 담길 물건의 형태
 */
public class CartItem {
    private final MenuItem item;
    private int quantity;

    public CartItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return item.getPrice() * quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }
}
