package tk.monkeycode.blogapi.service;

import tk.monkeycode.blogapi.dto.ProfileDTO;
import tk.monkeycode.blogapi.dto.UserRegistrationDTO;
import tk.monkeycode.blogapi.dto.UserResponseDTO;
import tk.monkeycode.blogapi.dto.UserUpdateDTO;

public interface UserService {
	
	UserResponseDTO createUser(UserRegistrationDTO user);
	UserResponseDTO updateUser(UserUpdateDTO user);
	ProfileDTO getUserProfile(String userName);
	ProfileDTO followUser(String userName);
	ProfileDTO unfollowUser(String userName);

}
