package com.azharstudios.e_commerce_learn_backend.services;

import com.azharstudios.e_commerce_learn_backend.exception.NotFoundException;
import com.azharstudios.e_commerce_learn_backend.models.Category;
import com.azharstudios.e_commerce_learn_backend.models.Product;
import com.azharstudios.e_commerce_learn_backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAllCategory(){

        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long category_id) {
        return categoryRepository.findById(category_id).orElseThrow(
                () -> new NotFoundException("Category Not Found, with Id " + category_id));
    }


    public List<Category> findCategoryByProduct(Product product){
        return categoryRepository.findCategoryByProduct(product);
    }

    public Category createCategory(Category category){

        return categoryRepository.save(category);
    }

    public Category updateCategory(Long categoryId, Category categoryUpdated){
        Category categoryExist = categoryRepository.findById(categoryId).orElseThrow(
                () -> new NotFoundException("Category Not Found, with Id " + categoryId));

        if (categoryUpdated.getCategoryName() == null || categoryUpdated.getCategoryName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        categoryExist.setCategoryName(categoryUpdated.getCategoryName());

        return categoryRepository.save(categoryExist);
    }

    public void deleteCategory(Long category_id) {
        Category category = categoryRepository.findById(category_id).orElseThrow(
                () -> new NotFoundException("Category Not Found, with Id " + category_id));

        if (!category.getProducts().isEmpty()) {
            throw new IllegalStateException("Cannot delete category with existing products.");
        }

        categoryRepository.deleteById(category_id);
    }

}
