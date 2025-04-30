package kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Menu {
    private final List<MenuItem> menuItems;
    private String category;

    public Menu(String category) {
        this.menuItems = new ArrayList<>();
        this.category = category;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void addMenuItem(String name, int price, String description) {
        menuItems.add(new MenuItem(name, price, description));
    }

    public void addMenuItems(List<MenuItem> menuItems) {
        this.menuItems.addAll(menuItems);
    }

    public void showMenuItems() {
        System.out.printf("[ %s MENU ]%n", category);
        IntStream.range(0, menuItems.size())
                .forEach(i -> {
                    MenuItem item = menuItems.get(i);
                    System.out.printf("%d. %-16s | W %.1f | %s%n",
                            i + 1,
                            item.getName(),
                            item.getPrice() / 1000.0,
                            item.getDescription());
                });
        System.out.println("0. 뒤로가기");
    }
}
