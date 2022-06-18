package tk.monkeycode.blogapi.exception;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Schema(name = "Exception", description = "Mensaje de error producido en el servidor")
public class ExceptionResponse {
	
	@Schema(description = "Fecha y hora en la que se produjo el error")
	private LocalDateTime timestamp;
	@Schema(description = "Descripción del error")
    private String mensaje;

}
