package tk.monkeycode.blogapi.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CommentsResponse {

	private List<CommentResponseDTO> comments;

	public CommentsResponse(List<CommentResponseDTO> comments) {
		this.comments = comments;
	}
}