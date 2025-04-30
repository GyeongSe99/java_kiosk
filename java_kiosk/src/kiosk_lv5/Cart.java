package kiosk_lv5;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String, CartItem> cartItems;

    public Cart() {
        this.cartItems = new HashMap<>();
    }

    public void addToCart(MenuItem menuItem) {
        // 1. 이미 들어가있는 메뉴인지 확인
        // 2. 이미 들어가있다면 수량 1 늘려주기
        // 3. 들어가있지 않았다면 추가
        CartItem existingItem = this.cartItems.get(menuItem.getName());
        if (existingItem == null) {
            cartItems.put(menuItem.getName(), new CartItem(menuItem, 1));
        } else {
            existingItem.increaseQuantity();
        }
    }
}
