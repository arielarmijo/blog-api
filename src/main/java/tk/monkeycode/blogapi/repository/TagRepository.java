package tk.monkeycode.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.monkeycode.blogapi.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
