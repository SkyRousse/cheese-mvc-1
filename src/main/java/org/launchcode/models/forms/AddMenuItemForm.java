package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {

    // needed to render the form -- these only need getters
    private Menu menu;
    private Iterable<Cheese> cheeses;

    // needed to process the form -- these need getters and setters
    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    // constructors

    public AddMenuItemForm() {}

    public AddMenuItemForm(Iterable<Cheese> cheeses, Menu menu) {
        this.cheeses = cheeses;
        this.menu = menu;
    }

    // getters
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

    // setters
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }
}
