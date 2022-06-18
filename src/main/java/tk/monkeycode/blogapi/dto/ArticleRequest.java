package tk.monkeycode.blogapi.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Schema(name = "ArticleRequest", description = "Request body para crear o actualizar un artículo")
public class ArticleRequest {
	
	@Valid
	@NotNull(message = "Atributo article es obligatorio.")
	private ArticleRequestDTO article;

}
