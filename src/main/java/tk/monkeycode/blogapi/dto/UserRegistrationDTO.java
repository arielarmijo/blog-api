package tk.monkeycode.blogapi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UserRegistrationData", description = "Datos para registrar usuario")
public class UserRegistrationDTO {

	@Schema(description = "Nombre de usuario", example = "JohnWick")
	@NotNull(message = "username es requerido.")
	@Size(min = 3, max = 45, message = "Nombre de usuario debe tener entre 3 y 45 caracteres.")
	private String username;
	
	@Schema(description = "Contraseña debe tener al menos 8 caracteres, una letra y un número.", example = "Passw0rd!")
	@NotNull(message = "password es requerido.")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Password debe tener mínimo 8 caracteres, al menos una letra y un número.")
	private String password;
	
	@Schema(description = "Correo electrónico", example = "johnwick@email.com")
	@NotNull(message = "email es requerido.")
	@Email(message = "Email inválido.")
	private String email;
	
}
