package tk.monkeycode.blogapi.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Tags", description = "Respuest del servidor al solicitar listado de etiquetas")
public class TagsResponse {
	
	private final List<String> tags;
	
}
