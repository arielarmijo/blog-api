package tk.monkeycode.blogapi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import tk.monkeycode.blogapi.dto.ArticleRequest;
import tk.monkeycode.blogapi.dto.ArticleResponse;
import tk.monkeycode.blogapi.dto.ArticlesResponse;
import tk.monkeycode.blogapi.dto.CommentRequest;
import tk.monkeycode.blogapi.dto.CommentRequestDTO;
import tk.monkeycode.blogapi.dto.CommentResponse;
import tk.monkeycode.blogapi.dto.CommentsResponse;
import tk.monkeycode.blogapi.service.ArticleService;

@RestController
@RequiredArgsConstructor
@RequestMapping("articles")
@Tag(name = "Articles")
public class ArticleController {
	
	private final ArticleService articleService;

	@Operation(summary = "Obtener listado de artículos")
	@ApiResponse(responseCode = "200", description = "OK",
				 content = { @Content(schema = @Schema(implementation = ArticlesResponse.class)) })
	@GetMapping
	public ResponseEntity<ArticlesResponse> getArticles(
				@RequestParam(required = false) String tag,
				@RequestParam(required = false) String author,
				@RequestParam(required = false) String favorited,
				@RequestParam(defaultValue = "20") String limit,
				@RequestParam(defaultValue = "0") String offset) {
		ArticlesResponse body = new ArticlesResponse(articleService.findArticles(tag, author, favorited, limit, offset));
		return ResponseEntity.ok(body);
	}
	
	
	@Operation(summary = "Obtener listado de artículos creados por los autores que se están siguiendo",
			   security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(responseCode = "200", description = "OK",
				 content = { @Content(schema = @Schema(implementation = ArticlesResponse.class)) })
	@GetMapping("feed")
	public ResponseEntity<ArticlesResponse> getUserFeed(
				@RequestParam(defaultValue = "20") String limit,
				@RequestParam(defaultValue = "0") String offset) {
		ArticlesResponse body = new ArticlesResponse(articleService.findArticlesFeed(limit, offset));
		return ResponseEntity.ok(body);
	}
	
	
	@Operation(summary = "Obtener artículo")
	@ApiResponse(responseCode = "200", description = "OK",
				 content = { @Content(schema = @Schema(implementation = ArticleResponse.class)) })
	@GetMapping("{slug}")
	public ResponseEntity<ArticleResponse> getArticles(@PathVariable String slug) {
		ArticleResponse body = new ArticleResponse(articleService.findArticleBySlug(slug));
		return ResponseEntity.ok(body);
	}
	
	
	@Operation(summary = "Crear artículo", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(responseCode = "204", description = "CREATED",
				 content = { @Content(schema = @Schema(implementation = ArticleResponse.class)) })
	@PostMapping
	public ResponseEntity<ArticleResponse> createArticle(@RequestBody ArticleRequest article) {
		ArticleResponse body = new ArticleResponse(articleService.createArticle(article));
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}
	
	
	@Operation(summary = "Actualizar artículo", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(responseCode = "200", description = "OK",
				 content = { @Content(schema = @Schema(implementation = ArticleResponse.class)) })
	@PutMapping("{slug}")
	public ResponseEntity<ArticleResponse> updateArticle(@PathVariable String slug, @Valid @RequestBody ArticleRequest article) {
		ArticleResponse body = new ArticleResponse(articleService.updateArticle(slug, article));
		return ResponseEntity.ok(body);
	}
	
	
	@Operation(summary = "Borrar artículo", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(responseCode = "200", description = "OK")
	@DeleteMapping("{slug}")
	public ResponseEntity<String> deleteArticle(@PathVariable String slug) {
		articleService.deleteArticle(slug);
		return ResponseEntity.ok("Artículo borrado con éxito.");
	}
	
	
	@Operation(summary = "Agregar artículo a favoritos", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(responseCode = "200", description = "OK",
				 content = { @Content(schema = @Schema(implementation = ArticleResponse.class)) })
	@PostMapping("{slug}/favorite")
	public ResponseEntity<ArticleResponse> favoriteArticle(@PathVariable String slug) {
		ArticleResponse body = new ArticleResponse(articleService.favoriteArticle(slug));
		return ResponseEntity.ok(body);
	}
	
	
	@Operation(summary = "Quitar artículo de favoritos", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(responseCode = "200", description = "OK",
				 content = { @Content(schema = @Schema(implementation = ArticleResponse.class)) })
	@DeleteMapping("{slug}/favorite")
	public ResponseEntity<ArticleResponse> unfavoriteArticle(@PathVariable String slug) {
		ArticleResponse body = new ArticleResponse(articleService.unfavoriteArticle(slug));
		return ResponseEntity.ok(body);
	}
	
	
	@Operation(summary = "Obtener comentarios de un artículo", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(responseCode = "200", description = "OK",
				 content = { @Content(schema = @Schema(implementation = CommentsResponse.class)) })
	@GetMapping("{slug}/comments")
	public ResponseEntity<CommentsResponse> getArticleComments(@PathVariable String slug) {
		CommentsResponse body = new CommentsResponse(articleService.getArticleComments(slug));
		return ResponseEntity.ok(body);
	}
	
	
	@Operation(summary = "Agregar comentario a un artículo", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(responseCode = "204", description = "CREATED",
				 content = { @Content(schema = @Schema(implementation = CommentResponse.class)) })
	@PostMapping("{slug}/comments")
	public ResponseEntity<CommentResponse> createArticle(@PathVariable String slug, @RequestBody CommentRequest comment) {
		CommentRequestDTO commentDto = comment.getComment();
		CommentResponse body = new CommentResponse(articleService.addComment(slug, commentDto.getBody()));
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}
	
	
	@Operation(summary = "Borrar comentario de un artículo", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponse(responseCode = "200", description = "OK")
	@DeleteMapping("{slug}/comments/{id}")
	public ResponseEntity<String> deleteArticleComments(@PathVariable String slug, @PathVariable(required = true) Long id) {
		articleService.deleteComments(slug, id);
		return ResponseEntity.ok("Comentario borrado con éxito.");
	}
	
	
}
