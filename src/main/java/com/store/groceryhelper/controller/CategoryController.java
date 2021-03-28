package com.store.groceryhelper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.groceryhelper.model.Category;
import com.store.groceryhelper.service.CategoryService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/category")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
