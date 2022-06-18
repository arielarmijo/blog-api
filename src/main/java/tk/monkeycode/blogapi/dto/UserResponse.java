package tk.monkeycode.blogapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Respuesta del servidor al solicitar recursos relacionados con el usuario")
public class UserResponse {

	private final UserResponseDTO user;
	
}
