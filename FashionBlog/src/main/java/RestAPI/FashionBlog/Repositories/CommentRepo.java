package RestAPI.FashionBlog.Repositories;

import RestAPI.FashionBlog.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByDesign_Id(Long designId);
    Optional<Comment> findById (Long id);
}
