package kiosk_lv5;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String, CartItem> cartItems;

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public Cart() {
        this.cartItems = new HashMap<>();
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
        for (Map.Entry<String, CartItem> entry : this.cartItems.entrySet()) {
            CartItem cartItem = entry.getValue();
            System.out.printf("%-14s | W %.1f | %d 개 %n", entry.getKey(), (double) cartItem.getItem().getPrice() / 1000, cartItem.getQuantity());
        }
        System.out.println();
    }

    public int showTotalPrice() {
        System.out.println("[ Total Price ]");
        int totalPrice = getTotalPrice();
        System.out.printf("W %.1f%n", (double) totalPrice / 1000);
        return totalPrice;
    }

    private int getTotalPrice() {
        int totalPrice = 0;
        for (Map.Entry<String, CartItem> entry : this.cartItems.entrySet()) {
            CartItem cartItem = entry.getValue();
            totalPrice += cartItem.getItem().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }
}

