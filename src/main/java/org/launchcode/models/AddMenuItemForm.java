package org.launchcode.models;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {
    private Menu menu;

    private Iterable<Cheese> cheeses;

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @NotNull
    private int menuId;

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    @NotNull
    private int cheeseId;

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses) {
        this.menu = menu;
        this.cheeses = cheeses;
    }

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public AddMenuItemForm() {
    }

    public Menu getMenu() {
        return menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public int getMenuId() {
        return menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

}
