package tk.monkeycode.blogapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class CommentsResponse {

	private final List<CommentResponseDTO> comments;

}
