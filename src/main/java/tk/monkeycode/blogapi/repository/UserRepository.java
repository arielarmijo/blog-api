package tk.monkeycode.blogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.monkeycode.blogapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}
