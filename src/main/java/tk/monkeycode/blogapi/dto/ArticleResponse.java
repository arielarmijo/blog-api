package tk.monkeycode.blogapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Single Article Response", description = "Respuesta del servidor al solicitar un artículo")
public class ArticleResponse {

	private final ArticleResponseDTO article;

}
