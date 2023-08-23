package RestAPI.FashionBlog.Repositories;

import RestAPI.FashionBlog.Entities.Design;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesignRepo extends JpaRepository<Design, Long> {
    Design getDesignById (Long id);
    List<Design> findByTitleContaining(String title);
    List<Design> findByCategoryId(Long categoriesId);
}
