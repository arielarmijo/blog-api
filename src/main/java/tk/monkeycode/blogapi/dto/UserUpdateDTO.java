package tk.monkeycode.blogapi.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UserUpdateData", description = "Datos de usuario para actualizar (excepto email)")
public class UserUpdateDTO {

	@Schema(description = "Nombre de usuario", example = "JohnWick")
	@NotNull(message = "Atributo username es requerido.")
	@Size(min = 3, max = 45, message = "Nombre de usuario debe tener entre 3 y 45 caracteres.")
	private String username;
	
	@Schema(description = "Correo electrónico (este campo no se puede actualizar)", example = "johnwick@email.com")
	@NotNull(message = "Atributo email es requerido.")
	private String email;
	
	@Schema(description = "Contraseña debe tener al menos 8 caracteres, una letra y un número.", example = "Passw0rd!")
	@NotNull(message = "Atributo password es requerido.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password debe tener mínimo 8 caracteres, al menos una letra y un número.")
	private String password;
	
	@Schema(description = "URL de la foto del usuario", example = "https://blog.io/images/smiley-cyrus.jpg")
	@NotNull(message = "Atributo image es requerido.")
	private String image;
	
	@Schema(description = "Breve descripción del usuario", example = "Dog lover")
	@NotNull(message = "Atributo bio es requerido.")
	private String bio;
	
}
