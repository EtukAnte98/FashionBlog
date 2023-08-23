package RestAPI.FashionBlog.Services;

import RestAPI.FashionBlog.Converter.CommentConverter;
import RestAPI.FashionBlog.DTOs.CommentDTO;
import RestAPI.FashionBlog.Entities.Comment;
import RestAPI.FashionBlog.Entities.Design;
import RestAPI.FashionBlog.Repositories.CommentRepo;
import RestAPI.FashionBlog.Repositories.DesignRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final DesignRepo designRepo;
    private final CommentConverter commentConverter;
    @Autowired
    public CommentService(CommentRepo commentRepo, DesignRepo designRepo, CommentConverter commentConverter) {
        this.commentRepo = commentRepo;
        this.designRepo = designRepo;
        this.commentConverter = commentConverter;
    }
    public List<CommentDTO> findAllComments() {
        List<Comment> comments = commentRepo.findAll();
        return comments.stream()
                .map(commentConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO findCommentById(Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not Found"));
        return commentConverter.entityToDTO(comment);

    }

    public List<CommentDTO> findCommentsByDesignId(Long designId) {
        List<Comment> comments = commentRepo.findByDesign_Id(designId);
        return comments.stream()
                .map(commentConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO saveComment(CommentDTO commentDTO) {
        Design design = designRepo.findById(commentDTO.getDesignId())
                .orElseThrow(() -> new EntityNotFoundException("Design Not found"));
        Comment comment = commentConverter.dtoToEntity(commentDTO, design);
        Comment savedComment = commentRepo.save(comment);
        CommentDTO savedCommentDTO = commentConverter.entityToDTO(savedComment);
        return savedCommentDTO;
    }
    public CommentDTO updateComment(Long commentId, CommentDTO commentDTO){
        Comment existingComment = commentRepo.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        Design design = existingComment.getDesign();
        Comment updatedComment = commentConverter.dtoToEntity(commentDTO, design);
        Comment savedComment = commentRepo.save(updatedComment);
        return commentConverter.entityToDTO(savedComment);
    }
    public void deleteComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment Not found"));
        commentRepo.delete(comment);
    }
}
