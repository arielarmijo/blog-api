package tk.monkeycode.blogapi.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CommentResponseDTO {
	
	private String id;
	private LocalDate createdAt;
	private String body;
	private ProfileDTO author;

}
