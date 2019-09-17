package org.launchcode.models;

import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.validation.Valid;

@Entity
@Controller
@RequestMapping(value = "menu")

public class Menu {
    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private MenuDao menuDao;


    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "My Menus");
        model.addAttribute("menus", menuDao.findAll());

        return "menu/index";
    }
    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Menu menu, Errors errors){
        if (errors.hasErrors()){
            return "menu/add";
        }
        menuDao.save(menu);
        return"redirect:/menu/view/" +menu.getId;
    }

    @RequestMapping(value="view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId) {
        Menu menu = menuDao.findOne(menuId);
        model.addAttribute("menu", menu);
        model.addAttribute("title", menu.getName());
        return "menu/view";
    }

    @RequestMapping(value="add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId) {
        Menu menu = menuDao.findOne(menuId);

        AddMenuItemForm addMenuItemForm = new AddMenuItemForm(menu, cheeseDao.findAll());

        model.addAttribute("title", "Add item to menu: " + menu.getName());
        model.addAttribute("form", addMenuItemForm);

        return "menu/add-item";
    }

    @RequestMapping(value="add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm addMenuItemForm, Errors errors, @RequestParam int menuId, @RequestParam int cheeseId) {
        if (errors.hasErrors()) {
            return "menu/add-item";
        }


        Menu menu = menuDao.findOne(menuId);
        Cheese cheese = cheeseDao.findOne(cheeseId);
        menu.addItem(cheese);
        menuDao.save(menu);

        return "redirect:/menu/view/" + menu.getId();
    }

    public Object getName() {
    }

    public String getId() {
    }

    public void addItem(Cheese cheese) {
    }
}
