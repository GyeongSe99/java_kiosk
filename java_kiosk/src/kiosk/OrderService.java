package kiosk;

public class OrderService {
    private final Cart cart;

    public OrderService(Cart cart) {
        this.cart = cart;
    }

    public double applyDiscount(int selectedNum, int totalPrice) {
        UserType userType = UserType.values()[selectedNum - 1];
        return totalPrice * (1 - userType.getRate() / 100.0);
    }


    public double order(int selectedNumForUserType) {
        double discounted = applyDiscount(selectedNumForUserType, cart.showTotalPrice());
        System.out.printf("주문이 완료되었습니다. 금액은 W %.1f 입니다.%n", discounted / 1000);
        cart.resetCartItems();
        return discounted;
    }
}
