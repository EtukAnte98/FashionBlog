package RestAPI.FashionBlog.Controllers;

import RestAPI.FashionBlog.DTOs.DesignDTO;
import RestAPI.FashionBlog.Services.DesignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designs")
public class DesignController {
    private final DesignService designService;
    public DesignController(DesignService designService){
        this.designService = designService;
    }
    @GetMapping("/allDesigns")
    public ResponseEntity<List<DesignDTO>> getAllDesigns() {
        List<DesignDTO> designs = designService.findAllDesigns();
        return ResponseEntity.ok(designs);
    }
    @GetMapping("/{designId}")
    public ResponseEntity<DesignDTO> getDesignById (@PathVariable Long designId){
        DesignDTO designDTO = designService.findDesignById(designId);
        return ResponseEntity.ok(designDTO);
    }
    @GetMapping("/search")
    public ResponseEntity<List<DesignDTO>> searchDesignByTitle (@RequestParam String title){
        List<DesignDTO> designs = designService.findDesignByTitleContaining(title);
        return ResponseEntity.ok(designs);
    }
    @GetMapping("/{categoryId}/getDesigns")
    public ResponseEntity<List<DesignDTO>> getDesignsByCategoryId (@PathVariable Long categoryId){
        List<DesignDTO> designs = designService.findDesignsByCategoryId(categoryId);
        return ResponseEntity.ok(designs);
    }
    @PostMapping("/create")
    public ResponseEntity<DesignDTO> createDesign (@RequestBody DesignDTO designDTO){
        DesignDTO createdDesign = designService.saveDesign(designDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDesign);
    }
    @PutMapping("/{designId}/update")
    public ResponseEntity<DesignDTO> updateDesign(@PathVariable Long designId, @RequestBody DesignDTO designDTO){
        DesignDTO updatedDesign = designService.updateDesign(designId, designDTO);
        return ResponseEntity.ok(updatedDesign);
    }

    @DeleteMapping("/{designId}/delete")
    public ResponseEntity<Void> deleteDesign(@PathVariable Long designId){
        designService.deleteDesign(designId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{designId}/like")
    public ResponseEntity<Void> likeDesign (@PathVariable Long designId){
        designService.likeDesign(designId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{designId}/unlike")
    public ResponseEntity<Void> unlikeDesign (@PathVariable Long designId){
        designService.unlikeDesign(designId);
        return ResponseEntity.ok().build();
    }
}
