package tk.monkeycode.blogapi.service.impl;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tk.monkeycode.blogapi.dto.UserLoginDTO;
import tk.monkeycode.blogapi.dto.UserResponseDTO;
import tk.monkeycode.blogapi.exception.ModelNotFoundException;
import tk.monkeycode.blogapi.model.User;
import tk.monkeycode.blogapi.repository.UserRepository;
import tk.monkeycode.blogapi.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final TokenStore tokenStore;
	private final UserRepository userRepository;

	@Override
	public UserResponseDTO login(UserLoginDTO user) {
		User apiUser = userRepository.findByUsername(user.getEmail()).orElseThrow(() -> new ModelNotFoundException("Usuario no existe."));
		Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName("blogapp", apiUser.getUsername());
		String token;
		if (tokens.size() == 0)
			throw new RuntimeException("Usuario no tiene token de acceso.");
		else
			token = List.of(tokens).stream().findFirst().get().toString().replaceAll("\\[|\\]", "");
		UserResponseDTO userResponse = new UserResponseDTO(apiUser);
		userResponse.setToken(token);
		return userResponse;
	}

	@Override
	public UserResponseDTO getCurrentUser() {
		var principal = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new ModelNotFoundException("Usuario no existe."));
		return new UserResponseDTO(currentUser);
	}

}
