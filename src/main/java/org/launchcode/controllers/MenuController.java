package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Cheese Menus");
        model.addAttribute("menus", menuDao.findAll());

        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMenuForm(Model model) {
        model.addAttribute(new Menu());
        model.addAttribute("title", "Add Menu");

        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMenuFrom(@ModelAttribute @Valid Menu newMenu,
                                     Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        menuDao.save(newMenu);
        return "redirect:view/" + newMenu.getId();
    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId) {
        Menu theMenu = menuDao.findOne(menuId);
        model.addAttribute("title", theMenu.getName());
        model.addAttribute("menu", theMenu);

        return "menu/view";

    }

    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId) {
        Menu theMenu = menuDao.findOne(menuId);
        Iterable<Cheese> cheeses = cheeseDao.findAll();
        AddMenuItemForm form = new AddMenuItemForm(cheeses, theMenu);
        model.addAttribute("form", form);
        model.addAttribute("title", "'Add item to menu: " + theMenu.getName());

        return "menu/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(@ModelAttribute @Valid AddMenuItemForm form,
                          Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("form", form);
            return "menu/add-item";
        }

        Cheese cheese = cheeseDao.findOne(form.getCheeseId());
        Menu menu = menuDao.findOne(form.getMenuId());
        menu.addItem(cheese);
        menuDao.save(menu);

        return "redirect:/menu/view/" + menu.getId();
    }
}
