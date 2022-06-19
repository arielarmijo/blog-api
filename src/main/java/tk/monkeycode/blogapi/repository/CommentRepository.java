package tk.monkeycode.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.monkeycode.blogapi.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
