package com.store.groceryhelper.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.store.groceryhelper.exception.GroceryNotFoundException;
import com.store.groceryhelper.model.Category;
import com.store.groceryhelper.model.Grocery;
import com.store.groceryhelper.repository.GroceryRepository;


public class GroceryServiceTest {

    @Mock
    private GroceryRepository groceryRepository;
    @InjectMocks
    private GroceryService testInstance;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateGrocery() {
        var apple = buildGrocery("apple", Category.FRUIT);
        when(groceryRepository.save(apple)).thenReturn(apple);
        var category = testInstance.createGrocery(apple);
        assertThat(category).isEqualTo(apple);
    }

    @Test
    public void shouldDeleteGrocery() {
        testInstance.deleteGrocery(1);
        verify(groceryRepository).deleteById(1);
    }

    @Test
    public void shouldUpdateGrocery() {
        when(groceryRepository.findById(1)).thenReturn(Optional.of(buildGrocery("apple", Category.FRUIT)));
        when(groceryRepository.save(any())).then(i -> i.getArgument(0, Grocery.class));
        var fruit = testInstance.updateGrocery(1, buildGrocery("milk", Category.DAIRY));
        assertThat(fruit).isEqualTo(buildGrocery("milk", Category.DAIRY));
    }

    @Test
    public void shouldGetGrocery() {
        var apple = buildGrocery("apple", Category.FRUIT);
        when(groceryRepository.findById(1)).thenReturn(Optional.of(apple));
        var fruit = testInstance.getGrocery(1);
        assertThat(fruit).isEqualTo(apple);
    }

    @Test
    public void shouldThrowExceptionWhenGroceryNotFound() {
        when(groceryRepository.findById(1)).thenReturn(Optional.empty());
        var exception = assertThrows(GroceryNotFoundException.class, () -> testInstance.getGrocery(1));
        assertThat(exception.getMessage()).isEqualTo("id of 1 is not found");
    }

    private Grocery buildGrocery(String name, Category category) {
        return Grocery.builder()
            .id(1)
            .name(name)
            .category(category).build();
    }
}