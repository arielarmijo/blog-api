package tk.monkeycode.blogapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import tk.monkeycode.blogapi.dto.TagsResponse;
import tk.monkeycode.blogapi.service.TagService;

@RestController
@RequestMapping("tags")
@RequiredArgsConstructor
@Tag(name = "Tags")
public class TagController {
	
	private final TagService tagService;
	
	@Operation(summary = "Obtener lista de tags")
	@ApiResponse(
		responseCode = "200",
		description = "OK",
		content = { @Content(schema = @Schema(implementation = TagsResponse.class)) }
	)
	@GetMapping
	public ResponseEntity<TagsResponse> getTags() {
		TagsResponse tags = tagService.findAll();
		return ResponseEntity.ok(tags);
	}
	
}
