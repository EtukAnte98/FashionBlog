package RestAPI.FashionBlog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Jeremiah's Week 8 Project",
				version = "1.0.0",
				description = "Fashion Blog project",
				termsOfService = "Jerry_Irving",
				contact = @Contact(
						name = "Mr Jeremiah",
						email = "Offongjeremiah@gmail.com"
				),
				license = @License(
						name = "License",
						url = "Jeremiah"
				)
		)
)
public class FashionBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashionBlogApplication.class, args);
	}

}
