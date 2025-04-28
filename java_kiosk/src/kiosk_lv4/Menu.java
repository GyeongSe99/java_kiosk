package kiosk_lv4;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuItem> menuItems;
    private String category;

    public Menu(String category) {
        this.menuItems = new ArrayList<MenuItem>();
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

    public void addMenuItems(List<MenuItem> menuItems) {
        this.menuItems.addAll(menuItems);
    }

    public void printMenuItems() {
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            System.out.printf("%d. %-13s | W %.1f | %s\n", i + 1, item.getName(), (double) item.getPrice() / 1000, item.getDescription());
        }
    }
}
