package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.Category;

public interface CategoryDao {
	void saveCategory(Category Category);

	void deleteCategoryById(Long idCategory);

	void updateCategory(Category Category);

	List<Category> findAllCategorys();

	Category findById(Long idCategory);

	Category findByName(String name);
}
