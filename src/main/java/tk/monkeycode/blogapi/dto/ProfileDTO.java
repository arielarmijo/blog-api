package tk.monkeycode.blogapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.monkeycode.blogapi.model.Profile;

@NoArgsConstructor
@Getter @Setter
@Schema(name = "ProfileData", description = "Datos del perfil de usuario")
public class ProfileDTO {
	
	@Schema(description = "Nombre de usuario", example = "JohnWick")
	private String username;
	
	@Schema(description = "Breve descripción del usuario", example = "Dog lover")
	private String bio;
	
	@Schema(description = "URL de la foto del usuario", example = "https://blog.io/images/smiley-cyrus.jpg")
	private String image;

	@Schema(description = "Indica si el usuario que inició sesión sigue al usuario del perfil", example = "false")
	private boolean following;
	
	public ProfileDTO(Profile profile) {
		this.username = profile.getUserName();
		this.bio = profile.getBio();
		this.image = profile.getImage();
		this.following = false;
	}
}
