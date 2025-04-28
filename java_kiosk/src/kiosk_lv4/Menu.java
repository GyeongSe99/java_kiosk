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
}
