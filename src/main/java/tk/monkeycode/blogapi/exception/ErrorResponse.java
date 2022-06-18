package tk.monkeycode.blogapi.exception;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Error", description = "Lista de errores detectados en el request body")
public class ErrorResponse {

	private final List<String> errors;
	
}
