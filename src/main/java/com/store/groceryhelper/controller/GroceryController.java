package com.store.groceryhelper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.groceryhelper.model.Category;
import com.store.groceryhelper.model.Grocery;
import com.store.groceryhelper.service.GroceryService;


@RestController
@CrossOrigin(origins="http://localhost:3000")
public class GroceryController {

    @Autowired
    private GroceryService groceryService;


    @PostMapping(value = "/grocery")
    public Grocery createGrocery(@RequestBody Grocery grocery) {
        return groceryService.createGrocery(grocery);
    }

    @PutMapping("/grocery/{id}")
    public Grocery updateGrocery(@RequestBody Grocery grocery, @PathVariable Integer id) {
        return groceryService.updateGrocery(id, grocery);
    }

    @GetMapping("/grocery")
    public List<Grocery> getGroceries(@RequestParam(required = false) Category category) {
        return groceryService.getGroceryByCategory(category);
    }

    @GetMapping("/grocery/{id}")
    public Grocery getGrocery(@PathVariable Integer id) {
        return groceryService.getGrocery(id);
    }

    @DeleteMapping("/grocery/{id}")
    public void deleteGrocery(@PathVariable Integer id) {
        groceryService.deleteGrocery(id);
    }

}
