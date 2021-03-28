package com.store.groceryhelper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.groceryhelper.model.Category;
import com.store.groceryhelper.model.Grocery;

@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Integer> {

     List<Grocery> findByCategory(Category category);

}
