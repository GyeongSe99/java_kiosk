package kiosk_lv4;

import kiosk_lv3.Kiosk;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu burgers = new Menu("Burgers");
        Menu Drinks = new Menu("Drinks");
        Menu Desserts = new Menu("Desserts");

        List<Menu> menuList = new ArrayList<>();
        menuList.add(burgers);
        menuList.add(Drinks);
        menuList.add(Desserts);

        kiosk_lv3.Kiosk kiosk = new Kiosk();
        kiosk.start();
    }
}
