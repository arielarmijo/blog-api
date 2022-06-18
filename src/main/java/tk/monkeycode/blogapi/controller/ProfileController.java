package tk.monkeycode.blogapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import tk.monkeycode.blogapi.dto.ProfileResponse;
import tk.monkeycode.blogapi.service.UserService;

@RestController
@RequestMapping("profiles")
@RequiredArgsConstructor
@Tag(name = "Profiles")
public class ProfileController {
	
	private final UserService userService;
	
	@Operation(summary = "Obtener perfil de usuario")
	@ApiResponse(
		responseCode = "200",
		description = "OK",
		content = { @Content(schema = @Schema(implementation = ProfileResponse.class)) }
	)
	@GetMapping("{username}")
	public ResponseEntity<ProfileResponse> getUserProfile(@Parameter(description = "Nombre de usuario") @PathVariable(name = "username") String userName) {
		ProfileResponse profileResponse = new ProfileResponse(userService.getUserProfile(userName));
		return ResponseEntity.ok(profileResponse);
	}
	
	
	@Operation(summary = "Seguir a usuario", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(
		responseCode = "200",
		description = "OK",
		content = { @Content(schema = @Schema(implementation = ProfileResponse.class)) }
	)
	@PostMapping("{username}/follow")
	public ResponseEntity<ProfileResponse> followUser(@Parameter(description = "Nombre de usuario") @PathVariable(name = "username") String userName) {
		ProfileResponse profileResponse = new ProfileResponse(userService.followUser(userName));
		return ResponseEntity.ok(profileResponse);
	}

	
	@Operation(summary = "Dejar de seguir a usuario", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(
		responseCode = "200",
		description = "OK",
		content = { @Content(schema = @Schema(implementation = ProfileResponse.class)) }
	)
	@DeleteMapping("{username}/follow")
	public ResponseEntity<ProfileResponse> unfollowUser(@Parameter(description = "Nombre de usuario") @PathVariable(name = "username") String userName) {
		ProfileResponse profileResponse = new ProfileResponse(userService.unfollowUser(userName));
		return ResponseEntity.ok(profileResponse);
	}
	
	

}
