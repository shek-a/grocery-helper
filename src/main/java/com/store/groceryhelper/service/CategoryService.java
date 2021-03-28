package com.store.groceryhelper.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.store.groceryhelper.model.Category;


@Service
public class CategoryService {

    public List<Category> getAllCategories() {
        return Arrays.asList(Category.values());
    }
}
