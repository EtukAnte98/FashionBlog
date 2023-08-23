package RestAPI.FashionBlog.Repositories;

import RestAPI.FashionBlog.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
 Category getCategoryById (Long id);
 Category findByName (String name);
}
