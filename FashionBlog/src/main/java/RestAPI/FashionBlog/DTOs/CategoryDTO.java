package RestAPI.FashionBlog.DTOs;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryDTO {
    private String name;
    private String description;
}
