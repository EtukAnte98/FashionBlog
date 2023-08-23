package RestAPI.FashionBlog.Controllers;

import RestAPI.FashionBlog.DTOs.CategoryDTO;
import RestAPI.FashionBlog.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/allCategories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(CategoryDTO categoryDTO){
        List<CategoryDTO> categories = categoryService.findAllCategories(categoryDTO);
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/{categoryId}/getCategory")
    public ResponseEntity<CategoryDTO> getCategoryId (@PathVariable Long categoryId){
        CategoryDTO categoryDTO = categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(categoryDTO);
    }
    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory (@RequestBody CategoryDTO categoryDTO){
        CategoryDTO createdCategory = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }
    @PutMapping("/{categoryId}/update")
    public ResponseEntity<CategoryDTO> updateCategory (@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDTO){
        CategoryDTO updatedCategory = categoryService.updateCategory(categoryId,categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }
    @DeleteMapping("/{categoryId}/delete")
    public ResponseEntity<Void> deleteCategory (@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
