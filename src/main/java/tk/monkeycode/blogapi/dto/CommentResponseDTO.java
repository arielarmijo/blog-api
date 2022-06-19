package tk.monkeycode.blogapi.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.monkeycode.blogapi.model.Comment;

@NoArgsConstructor
@Getter @Setter
public class CommentResponseDTO {
	
	private Long id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String body;
	private ProfileDTO author;
	
	public CommentResponseDTO(Comment comment) {
		this.id = comment.getId();
		this.body = comment.getBody();
		this.author = new ProfileDTO(comment.getAuthor());
		this.createdAt = comment.getCreatedAt();
		this.updatedAt = comment.getUpdatedAt();
	}

}
