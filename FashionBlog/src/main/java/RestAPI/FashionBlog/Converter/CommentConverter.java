package RestAPI.FashionBlog.Converter;

import RestAPI.FashionBlog.DTOs.CommentDTO;
import RestAPI.FashionBlog.Entities.Comment;
import RestAPI.FashionBlog.Entities.Design;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    public CommentDTO entityToDTO (Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setText(comment.getText());
        commentDTO.setDesignId(comment.getDesign().getId());
        return commentDTO;
    }

    public Comment dtoToEntity (CommentDTO commentDTO, Design design){
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setDesign(design);
        return comment;
    }
}
