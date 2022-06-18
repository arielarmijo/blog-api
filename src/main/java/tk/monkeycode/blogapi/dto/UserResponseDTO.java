package tk.monkeycode.blogapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.monkeycode.blogapi.model.Profile;
import tk.monkeycode.blogapi.model.User;

@NoArgsConstructor
@Getter @Setter
@Schema(name = "UserResponseData", description = "Datos de usuario")
public class UserResponseDTO {

	@Schema(description = "Correo electrónico", example = "johnwick@email.com")
	private String email;
	
	@Schema(description = "Nombre de usuario", example = "JohnWick")
	private String username;
	
	@Schema(description = "Token de acceso", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
	private String token;
	
	@Schema(description = "Breve descripción del usuario", example = "Dog lover")
	private String bio;
	
	@Schema(description = "URL de la foto del usuario", example = "https://blog.io/images/smiley-cyrus.jpg")
	private String image;
	
	public UserResponseDTO(User user) {
		this.email = user.getUsername();
		Profile userProfile = user.getProfile();
		if (userProfile != null) {
			this.username = user.getProfile().getUserName();
			this.bio = user.getProfile().getBio();
			this.image = user.getProfile().getImage();
		}
	}
	
}
