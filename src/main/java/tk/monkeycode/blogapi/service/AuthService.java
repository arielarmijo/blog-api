package tk.monkeycode.blogapi.service;

import tk.monkeycode.blogapi.dto.UserLoginDTO;
import tk.monkeycode.blogapi.dto.UserResponseDTO;

public interface AuthService {

	UserResponseDTO login(UserLoginDTO user);
	UserResponseDTO getCurrentUser();
	
}
