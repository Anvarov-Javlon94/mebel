package uz.sqb.ecommerce.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.sqb.ecommerce.entity.Category;
import uz.sqb.ecommerce.repository.CategoryRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class CategoryService {

    CategoryRepository categoryRepository;

    public List<Category> getCategoryList(){
        List<Category> categories = categoryRepository.findAll();
        return getSortedCategoryList(categories);
    }

    public void save(Category category) {
        Category category1 = new Category();
        String category_name = category.getType_of_product();
        category1.setType_of_product(category_name.toUpperCase(Locale.ROOT));
        categoryRepository.save(category1);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteCategoryByID(id);
    }

    public Category getById(Long id) {
        return categoryRepository.getById(id);
    }

    public void putCategory(String type_of_product, Long id) {
        Category category = categoryRepository.getById(id);
        String category_name = type_of_product;
        category.setType_of_product(category_name.toUpperCase(Locale.ROOT));
        categoryRepository.save(category);
    }

    public List<Category> getSortedCategoryList(List<Category> categories){
        return categories.stream()
                .sorted(Comparator.comparing(Category::getId))
                .collect(Collectors.toList());
    }
}
