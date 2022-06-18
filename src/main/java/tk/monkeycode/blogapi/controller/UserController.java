package tk.monkeycode.blogapi.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import tk.monkeycode.blogapi.dto.UserLoginDTO;
import tk.monkeycode.blogapi.dto.UserLoginRequest;
import tk.monkeycode.blogapi.dto.UserRegistrationDTO;
import tk.monkeycode.blogapi.dto.UserRegistrationRequest;
import tk.monkeycode.blogapi.dto.UserResponse;
import tk.monkeycode.blogapi.dto.UserUpdateDTO;
import tk.monkeycode.blogapi.dto.UserUpdateRequest;
import tk.monkeycode.blogapi.service.AuthService;
import tk.monkeycode.blogapi.service.UserService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {
	

	private final UserService userService;
	private final AuthService authService;
	
	@Operation(summary = "Obtener usuario actual", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(responseCode = "200",
				 description = "OK",
				 content = { @Content(schema = @Schema(implementation = UserResponse.class)) })
	@GetMapping
	public ResponseEntity<Object> getCurrentUser(Principal user) {
		UserResponse userResponse = new UserResponse(authService.getCurrentUser());
		return ResponseEntity.ok(userResponse);
	}
	
	@Operation(summary = "Iniciar sesión de usuario")
	@ApiResponse(
		responseCode = "200",
		description = "OK",
		content = { @Content(schema = @Schema(implementation = UserResponse.class)) }
	)
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@Valid @RequestBody UserLoginRequest user) {
		UserLoginDTO userDto = user.getUser();
		UserResponse userResponse = new UserResponse(authService.login(userDto));
		return ResponseEntity.ok(userResponse);
	}
	
	
	@Operation(summary = "Registrar nuevo usuario")
	@ApiResponse(
		responseCode = "200",
		description = "OK",
		content = { @Content(schema = @Schema(implementation = UserResponse.class)) }
	)
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRegistrationRequest user) {
		UserRegistrationDTO userDto = user.getUser();
		UserResponse userResponse = new UserResponse(userService.createUser(userDto));
		return ResponseEntity.ok(userResponse);
	}
	
	
	@Operation(summary = "Actualizar información del usuario", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(
		responseCode = "200",
		description = "OK",
		content = { @Content(schema = @Schema(implementation = UserResponse.class)) }
	)
	@PutMapping
	public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserUpdateRequest user) {
		UserUpdateDTO userDTO = user.getUser();
		UserResponse userResponse = new UserResponse(userService.updateUser(userDTO));
		return ResponseEntity.ok(userResponse);
	}
	
	
}
