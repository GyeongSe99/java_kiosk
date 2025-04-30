package kiosk;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String, CartItem> cartItems;

    public Cart() {
        this.cartItems = new HashMap<>();
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void resetCartItems() {
        this.cartItems = new HashMap<>();
    }

    public void addToCart(MenuItem menuItem) {
        // 1. 이미 들어가있는 메뉴인지 확인
        // 2. 이미 들어가있다면 수량 1 늘려주기
        // 3. 들어가있지 않았다면 Map에 새로추가
        CartItem existingItem = this.cartItems.get(menuItem.getName());
        if (existingItem == null) {
            cartItems.put(menuItem.getName(), new CartItem(menuItem, 1));
        } else {
            existingItem.increaseQuantity();
        }
    }

    public void showCartItems() {
        System.out.println("[ Orders ]");
        this.cartItems.forEach((key, cartItem) -> System.out.printf(
                "%-14s | W %.1f | %d 개 %n",
                key,
                cartItem.getItem().getPrice() / 1000.0,
                cartItem.getQuantity()
        ));
        System.out.println();
    }

    public int showTotalPrice() {
        System.out.println("[ Total Price ]");
        int totalPrice = getTotalPrice();
        System.out.printf("W %.1f%n", (double) totalPrice / 1000);
        return totalPrice;
    }

    private int getTotalPrice() {
        return this.cartItems.values().stream()
                .mapToInt(cartItem -> cartItem.getItem().getPrice() * cartItem.getQuantity())
                .sum();
    }
}

