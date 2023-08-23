package RestAPI.FashionBlog.Services;

import RestAPI.FashionBlog.Converter.CategoryConverter;
import RestAPI.FashionBlog.DTOs.CategoryDTO;
import RestAPI.FashionBlog.Entities.Category;
import RestAPI.FashionBlog.Exception.NotFoundException;
import RestAPI.FashionBlog.Repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
private final CategoryRepo categoryRepo;
private final CategoryConverter categoryConverter;
@Autowired
public CategoryService(CategoryRepo categoryRepo, CategoryConverter categoryConverter) {
    this.categoryRepo = categoryRepo;
    this.categoryConverter = categoryConverter;
}
    public CategoryDTO saveCategory (CategoryDTO categoryDTO){
        System.out.println(categoryDTO);
        Category category = categoryConverter.dtoToEntity(categoryDTO);
        System.out.println(category + "======");
        Category savedCategory = categoryRepo.save(category);
        return categoryConverter.entityToDTO(savedCategory);
    }
    public List<CategoryDTO> findAllCategories(CategoryDTO categoryDTO) {
       List<Category> categories = categoryRepo.findAll();
       return categories.stream()
               .map(categoryConverter::entityToDTO)
               .collect(Collectors.toList());
    }
    public CategoryDTO findCategoryById(Long categoryId) {
        Category category = categoryRepo.getCategoryById(categoryId);
        return categoryConverter.entityToDTO(category);
    }
    public CategoryDTO updateCategory (Long categoryId, CategoryDTO categoryDTO){
    Category existingCategory = categoryRepo.findById(categoryId)
            .orElseThrow(() -> new NotFoundException("Category not found with id " + categoryId));
    Category updatedCategory = categoryConverter.dtoToEntity(categoryDTO);
    updatedCategory.setId(existingCategory.getId());
    updatedCategory = categoryRepo.save(updatedCategory);
    return categoryConverter.entityToDTO(updatedCategory);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepo.deleteById(categoryId);
    }
}
