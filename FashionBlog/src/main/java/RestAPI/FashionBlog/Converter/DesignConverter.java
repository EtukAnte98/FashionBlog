package RestAPI.FashionBlog.Converter;

import RestAPI.FashionBlog.DTOs.DesignDTO;
import RestAPI.FashionBlog.Entities.Category;
import RestAPI.FashionBlog.Entities.Design;
import org.springframework.stereotype.Component;

@Component
public class DesignConverter {
    private final CategoryConverter categoryConverter;

    public DesignConverter(CategoryConverter categoryConverter){
        this.categoryConverter = categoryConverter;
    }

    public DesignDTO  entityToDTO (Design design){
        DesignDTO designDTO = new DesignDTO();
        designDTO.setTitle(design.getTitle());
        designDTO.setDescription(design.getDescription());
        designDTO.setCategoryId(design.getCategory().getId());
        designDTO.setLikesId(design.getLikesId());
        return designDTO;
    }

    public Design dtoToEntity (DesignDTO designDTO,Category category){
        Design design = new Design();
        design.setTitle(designDTO.getTitle());
        design.setDescription(designDTO.getDescription());
        design.setCategory(category);
        design.setLikesId(designDTO.getLikesId());
        return design;
    }
}
