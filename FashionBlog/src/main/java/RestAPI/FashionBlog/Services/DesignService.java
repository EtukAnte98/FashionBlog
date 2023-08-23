package RestAPI.FashionBlog.Services;

import RestAPI.FashionBlog.Converter.DesignConverter;
import RestAPI.FashionBlog.DTOs.DesignDTO;
import RestAPI.FashionBlog.Entities.Category;
import RestAPI.FashionBlog.Entities.Design;
import RestAPI.FashionBlog.Exception.NotFoundException;
import RestAPI.FashionBlog.Repositories.CategoryRepo;
import RestAPI.FashionBlog.Repositories.DesignRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesignService {
    private DesignRepo designRepo;
    private final DesignConverter designConverter;
    private final CategoryRepo categoryRepo;

    @Autowired
    public DesignService(DesignRepo designRepo, CategoryRepo categoryRepo, DesignConverter designConverter) {
        this.designRepo = designRepo;
        this.categoryRepo = categoryRepo;
        this.designConverter = designConverter;
    }

    public List<DesignDTO> findAllDesigns() {
        List<Design> designs = designRepo.findAll();
        return designs.stream()
                .map(designConverter::entityToDTO)
                .collect(Collectors.toList());
    }
    public DesignDTO findDesignById(Long designId) {
        Design design = designRepo.getDesignById(designId);
        return designConverter.entityToDTO(design);
    }

    public List<DesignDTO> findDesignByTitleContaining(String title) {
        List<Design> designs = designRepo.findByTitleContaining(title);
        return designs.stream()
                .map(designConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<DesignDTO> findDesignsByCategoryId(Long categoryId) {
        List<Design> designs = designRepo.findByCategoryId(categoryId);
        return designs.stream()
                .map(designConverter::entityToDTO)
                .collect(Collectors.toList());
    }
    public DesignDTO saveDesign(DesignDTO designDTO) {
        Category category = categoryRepo.findById(designDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id" + designDTO.getCategoryId()));
        Design design = designConverter.dtoToEntity(designDTO, category);
        design = designRepo.save(design);
        return designConverter.entityToDTO(design);
    }
    public DesignDTO updateDesign (Long designId, DesignDTO designDTO) {
        Design existingDesign = designRepo.findById(designId)
                .orElseThrow(()-> new NotFoundException("Design Not found with Id: " + designId));
        Category category = categoryRepo.findById(designDTO.getCategoryId())
                .orElseThrow(()-> new NotFoundException("Category not found with id " + designDTO.getCategoryId()));
        Design updatedDesign = designConverter.dtoToEntity(designDTO,category);
        updatedDesign.setId(existingDesign.getId());
        updatedDesign = designRepo.save(updatedDesign);
        return designConverter.entityToDTO(updatedDesign);
    }

    public void deleteDesign(Long designId) {
        designRepo.deleteById(designId);
    }

    public void likeDesign(Long designId) {
        Design design = designRepo.getDesignById(designId);
        design.setLikesId(design.getLikesId() + 1);
        designRepo.save(design);
    }
    public void unlikeDesign(Long designId) {
        Design design = designRepo.getDesignById(designId);
        design.setLikesId(design.getLikesId() - 1);
        designRepo.save(design);
    }
}
