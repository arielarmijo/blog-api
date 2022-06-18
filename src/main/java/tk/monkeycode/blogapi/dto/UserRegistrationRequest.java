package tk.monkeycode.blogapi.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request body para registrar usuario")
public class UserRegistrationRequest {

	@Valid
	@NotNull(message = "Atributo user es obligatorio.")
	private UserRegistrationDTO user;
	
}
