package tk.monkeycode.blogapi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tk.monkeycode.blogapi.dto.ProfileDTO;
import tk.monkeycode.blogapi.dto.UserRegistrationDTO;
import tk.monkeycode.blogapi.dto.UserResponseDTO;
import tk.monkeycode.blogapi.dto.UserUpdateDTO;
import tk.monkeycode.blogapi.exception.ModelNotFoundException;
import tk.monkeycode.blogapi.model.Profile;
import tk.monkeycode.blogapi.model.Role;
import tk.monkeycode.blogapi.model.User;
import tk.monkeycode.blogapi.repository.ProfileRepository;
import tk.monkeycode.blogapi.repository.RoleRepository;
import tk.monkeycode.blogapi.repository.UserRepository;
import tk.monkeycode.blogapi.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final ProfileRepository profileRepository;

	@Override
	@Transactional
	public UserResponseDTO createUser(UserRegistrationDTO user) {
		Profile newProfile = new Profile();
		newProfile.setUserName(user.getUsername());
		newProfile = profileRepository.save(newProfile);
		Role defaultRole = new Role("user");
		Role role = roleRepository.findByName("user").orElseGet(() -> roleRepository.save(defaultRole));
		User newUser = new User();
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setUsername(user.getEmail());
		newUser.setProfile(newProfile);
		newUser.setRoles(List.of(role));
		newUser = userRepository.save(newUser);
		return new UserResponseDTO(newUser);
	}

	@Override
	@Transactional
	public UserResponseDTO updateUser(UserUpdateDTO user) {
		User oldUser = userRepository.findByUsername(user.getEmail())
									 .orElseThrow(() -> new ModelNotFoundException("Usuario no existe."));
		Profile profile = oldUser.getProfile();
		profile.setBio(user.getBio());
		profile.setImage(user.getImage());
		profile.setUserName(user.getUsername());
		oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
		oldUser.setProfile(profile);
		User updatedUser = userRepository.save(oldUser);
		return new UserResponseDTO(updatedUser);
	}

	@Override
	public ProfileDTO getUserProfile(String userName) {
		Profile profile = profileRepository.findByUserName(userName).orElseThrow(() -> new ModelNotFoundException("Profile no existe."));
		return new ProfileDTO(profile);
	}

	@Override
	public ProfileDTO followUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfileDTO unfollowUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username).orElseThrow(() -> new ModelNotFoundException("Usuario no existe."));
	}

}
