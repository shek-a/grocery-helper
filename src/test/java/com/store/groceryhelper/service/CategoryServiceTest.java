package com.store.groceryhelper.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.store.groceryhelper.model.Category;

public class CategoryServiceTest {

    @Test
    public void shouldGetCategories() {
        var categories = new CategoryService().getAllCategories();
        assertThat(categories.get(0)).isEqualTo(Category.DAIRY);
        assertThat(categories.get(1)).isEqualTo(Category.FRUIT);
        assertThat(categories.get(2)).isEqualTo(Category.VEGETABLE);
        assertThat(categories.get(3)).isEqualTo(Category.OTHER);
    }
}