package com.store.groceryhelper.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.groceryhelper.exception.GroceryNotFoundException;
import com.store.groceryhelper.model.Category;
import com.store.groceryhelper.model.Grocery;
import com.store.groceryhelper.repository.GroceryRepository;


@Service
public class GroceryService {

    @Autowired
    private GroceryRepository groceryRepository;

    public Grocery createGrocery(Grocery grocery) {
        return groceryRepository.save(grocery);
    }

    public void deleteGrocery(Integer id) {
        groceryRepository.deleteById(id);
    }

    public Grocery getGrocery(Integer id) {
        return groceryRepository.findById(id)
            .orElseThrow(() -> new GroceryNotFoundException(id.toString()));
    }

    public Grocery updateGrocery(Integer id, Grocery grocery) {
        var groceryToUpdate = groceryRepository.findById(id)
            .orElseThrow(() -> new GroceryNotFoundException(id.toString()));

        groceryToUpdate.setCategory(grocery.getCategory());
        groceryToUpdate.setName(grocery.getName());
        return groceryRepository.save(groceryToUpdate);
    }

    public List<Grocery> getGroceryByCategory(Category category) {
        if(Objects.nonNull(category)) {
            return groceryRepository.findByCategory(category);
        } else {
            return groceryRepository.findAll();
        }

    }

}
