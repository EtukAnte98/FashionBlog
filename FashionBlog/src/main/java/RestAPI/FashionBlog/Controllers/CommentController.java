package RestAPI.FashionBlog.Controllers;

import RestAPI.FashionBlog.DTOs.CommentDTO;
import RestAPI.FashionBlog.Services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }
    @GetMapping("/allComments")
    public ResponseEntity<List<CommentDTO>> getAllComments(){
        List<CommentDTO> comments =commentService.findAllComments();
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/{commentId}/getComments")
    public ResponseEntity<CommentDTO> getCommentById (@PathVariable Long commentId){
        CommentDTO commentDTO = commentService.findCommentById(commentId);
        return ResponseEntity.ok(commentDTO);
    }
    @GetMapping("/{designId}/getCommentsByDesign")
    public ResponseEntity<List<CommentDTO>> getCommentByDesignId (@PathVariable Long designId){
        List<CommentDTO> comments = commentService.findCommentsByDesignId(designId);
        return ResponseEntity.ok(comments);
    }
    @PostMapping("/create")
    public ResponseEntity<CommentDTO> createComment (@RequestBody CommentDTO commentDTO){
        CommentDTO createdComment = commentService.saveComment(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }
    @PutMapping("/{commentId}/update")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long commentId, @RequestBody CommentDTO commentDTO){
        CommentDTO updatedComment = commentService.updateComment(commentId, commentDTO);
        return ResponseEntity.ok(updatedComment);
    }
    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<Void> deleteComment (@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
