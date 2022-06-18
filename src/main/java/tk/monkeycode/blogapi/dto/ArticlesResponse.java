package tk.monkeycode.blogapi.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Multiple Articles Response", description = "Listado de artículos devuelto por el servidor")
public class ArticlesResponse {
	
	private final List<ArticleResponseDTO> articles;

}
