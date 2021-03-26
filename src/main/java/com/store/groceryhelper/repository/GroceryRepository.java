package com.store.groceryhelper.repository;

import com.store.groceryhelper.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.groceryhelper.model.Grocery;

import java.util.List;

@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Integer> {

     List<Grocery> findByCategory(Category category);

}
