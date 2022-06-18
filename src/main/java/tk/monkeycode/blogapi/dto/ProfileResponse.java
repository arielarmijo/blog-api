package tk.monkeycode.blogapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Respuesta del servidor al solicitar perfil se usuario")
public class ProfileResponse {

	private final ProfileDTO profile;
	
}
