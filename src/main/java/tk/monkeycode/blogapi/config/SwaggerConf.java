package tk.monkeycode.blogapi.config;


import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Blog API", version = "v1", description = "API para una página de blogs."))
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class SwaggerConf {
	
//	@Bean
//	public OpenAPI blogOpenAPI() {
//		return new OpenAPI()
//	        .info(new Info().title("Blog API")
//	  		.description("API para página de blogs")
//			.version("v0.0.1"));
//	}

}
