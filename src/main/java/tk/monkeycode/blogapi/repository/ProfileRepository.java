package tk.monkeycode.blogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.monkeycode.blogapi.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Optional<Profile> findByUserName(String username);
	
}
