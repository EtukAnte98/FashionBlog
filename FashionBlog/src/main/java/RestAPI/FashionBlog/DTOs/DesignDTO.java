package RestAPI.FashionBlog.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DesignDTO {
    private String title;
    private String description;
    private Long categoryId;
    private int likesId;
}
