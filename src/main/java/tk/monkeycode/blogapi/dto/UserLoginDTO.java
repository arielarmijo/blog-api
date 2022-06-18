package tk.monkeycode.blogapi.dto;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UserLoginData", description = "Datos para iniciar sesión")
public class UserLoginDTO {
	
	@Schema(description = "Correo electrónico", example = "johnwick@email.com")
	@NotNull(message = "email es requerido.")
	private String email;

	@Schema(description = "Contraseña debe tener al menos 8 caracteres, una letra y un número.", example = "Passw0rd!")
	@NotNull(message = "password es requerido.")
	private String password;
	
}
