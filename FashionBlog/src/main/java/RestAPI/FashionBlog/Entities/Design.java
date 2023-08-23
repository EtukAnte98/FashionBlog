package RestAPI.FashionBlog.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Design {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private int likesId;

}
