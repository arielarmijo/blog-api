package tk.monkeycode.blogapi.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Schema(name = "ArticleRequestData", description = "Datos para crear o actualizar un artículo")
public class ArticleRequestDTO {
	
	@NotNull(message = "title es requerido.")
	@Schema(description = "Título del artículo", example = "How to train your dragon")
	private String title;
	
	@NotNull(message = "description es requerido.")
	@Schema(description = "Breve descripción del artículo", example = "Ever wonder how?")
	private String description;
	
	@NotNull(message = "body es requerido.")
	@Schema(description = "Breve descripción del artículo", example = "Ever wonder how?")
	private String body;
	
	private List<String> tagList;

}
